package czort.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.ObjectProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SpringComponent
@PrototypeScope
final public class ActionDialog extends BaseDialog<ActionDialog.ActionDialogFooter> {

    private final ObjectProvider<ActionDialog> provider;
    private final List<Component> bodyComponents;

    public ActionDialog(ObjectProvider<ActionDialog> provider) {
        this.provider = provider;
        this.bodyComponents = new ArrayList<>();
    }

    @Override
    public ActionDialog useFooterComponent(Consumer<ActionDialogFooter> withProvidedFooterComponent) {
        super.useFooterComponent(withProvidedFooterComponent);

        return this;
    }

    public ActionDialog withComponent(Component component) {
        this.bodyComponents.add(component);

        return this;
    }

    public ActionDialog withCaption(String caption) {
        setCaption(caption);

        return this;
    }

    @Override
    protected HasComponents bodyComponent() {
        VerticalLayout body = new VerticalLayout();
        bodyComponents.forEach(body::addComponent);

        return body;
    }

    @Override
    protected ActionDialogFooter footerComponent() {
        return new ActionDialogFooter();
    }

    public ActionDialog openAlert(String caption, String description, Consumer<Result> onAcceptClick) {
        provider.getIfUnique()
                .withComponent(new Label(description))
                .withCaption(caption)
                .useFooterComponent(footerComponent -> footerComponent
                        .withAcceptButton(onAcceptClick)
                        .withCancelButton()
                )
                .open();

        return this;
    }

    public class ActionDialogFooter extends BaseDialogFooter {
        private Button acceptButton;
        private Button cancelButton;

        public ActionDialogFooter withAcceptButton(Consumer<Result> onClick) {
            if (acceptButton != null) this.removeComponent(acceptButton);

            acceptButton = new Button("Accept");
            acceptButton.addClickListener(event -> {
                onClick.accept(Result.ACCEPT);
                close();
            });
            withButtonRight(acceptButton);

            return this;
        }

        public ActionDialogFooter withCancelButton(Consumer<Result> onClick) {
            if (cancelButton != null) this.removeComponent(cancelButton);

            cancelButton = new Button("Cancel");
            cancelButton.addClickListener(event -> {
                close();
                onClick.accept(Result.CANCEL);
            });

            withButtonRight(cancelButton);

            return this;
        }

        public ActionDialogFooter withCancelButton() {
            if (cancelButton != null) this.removeComponent(cancelButton);

            cancelButton = new Button("Cancel");
            cancelButton.addClickListener(event -> {
                close();
            });

            withButtonRight(cancelButton);

            return this;
        }
    }

    public enum Result {
        ACCEPT,
        CANCEL
    }
}
