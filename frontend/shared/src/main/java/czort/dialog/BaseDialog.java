package czort.dialog;

import com.vaadin.ui.*;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class BaseDialog<FOOTER extends Footer> extends Window {

    protected FOOTER footerComponent;
    protected HasComponents bodyComponent;

    private Consumer<FOOTER> withProvidedFooterComponent;
    private Consumer<HasComponents> withProvidedBodyComponent;

    private Size currentSize = Size.MEDIUM;

    public Window open() {
        if (!this.isAttached()) {
            buildFromRoot();

            setModal(true);
            setResizable(false);
            setResponsive(false);
            setClosable(false);

            Objects.requireNonNull(bodyComponent);
            Objects.requireNonNull(footerComponent);

            onRootCreated(this.bodyComponent, this.footerComponent);

            UI.getCurrent().addWindow(this);
        }

        return this;
    }

    public BaseDialog withSize(Size size) {
       this.currentSize = size;

        return this;
    }

    public BaseDialog useFooterComponent(Consumer<FOOTER> withProvidedFooterComponent) {
        this.withProvidedFooterComponent = withProvidedFooterComponent;

        return this;
    }

    public BaseDialog useBodyComponent(Consumer<HasComponents> withProvidedBodyComponent) {
        this.withProvidedBodyComponent = withProvidedBodyComponent;

        return this;
    }

    protected void onRootCreated(HasComponents bodyComponent, FOOTER footerComponent) {
        if(withProvidedBodyComponent != null) withProvidedBodyComponent.accept(bodyComponent);
        if(withProvidedFooterComponent != null) withProvidedFooterComponent.accept(footerComponent);
    }

    protected abstract HasComponents bodyComponent();

    protected String title() {
        return getClass().getName() + "." + "title";
    }

    protected FOOTER footerComponent() {
        return (FOOTER) new BaseDialogFooter();
    }

    private BaseDialog buildFromRoot() {

        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setMargin(false);
        wrapper.setSpacing(false);

        if(currentSize == Size.UNDEFINED) {
            setWidthUndefined();
            wrapper.setWidthUndefined();
        } else {
            wrapper.setWidth(currentSize.value, Unit.PIXELS);
            setWidth(currentSize.value, Unit.PIXELS);
        }
        setCaption(title());
        bodyComponent = bodyComponent();
        bodyComponent.setSizeFull();

        footerComponent = footerComponent();
        footerComponent.setSizeFull();

        wrapper.addComponents(
            bodyComponent,
            footerComponent
        );

        setContent(wrapper);
        center();

        return this;
    }

    public enum Size {
        UNDEFINED(-1),
        SMALL(300),
        MEDIUM(500),
        LARGE(800);
        Integer value;

        Size(int value) {
            this.value = value;
        }
    }
}
