package czort.dialog;

import com.vaadin.server.UserError;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Window;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.Objects;
import java.util.function.Consumer;

@SpringComponent
@PrototypeScope
public abstract class FormDialog<MODEL> extends BaseDialog<FormDialog.FormDialogFooter> {

    private MODEL model;

    @Override
    public Window open() {
        Objects.requireNonNull(model);
        super.open();

        return this;
    }

    @Override
    public FormDialog<MODEL> withSize(Size size) {
        super.withSize(size);

        return this;
    }

    public FormDialog<MODEL> withInitialValue(MODEL initialValue) {
        this.model = initialValue;

        return this;
    }

    @Override
    public FormDialog<MODEL> useFooterComponent(Consumer<FormDialog.FormDialogFooter> withProvidedFooterComponent) {
        super.useFooterComponent(withProvidedFooterComponent);

        return this;
    }

    @Override
    public FormDialog<MODEL> useBodyComponent(Consumer<HasComponents> withProvidedBodyComponent) {
        super.useBodyComponent(withProvidedBodyComponent);

        return this;
    }

    @Override
    protected FormDialogFooter footerComponent() {
        return new FormDialogFooter();
    }

    @Override
    protected HasComponents bodyComponent() {
        return bodyComponent(model);
    }

    protected abstract HasComponents bodyComponent(MODEL model);

    public class FormDialogFooter extends BaseDialogFooter {
        private Button acceptButton;
        private Button cancelButton;

        public FormDialogFooter() { }

        public FormDialogFooter withAcceptButton(Consumer<MODEL> onClick) {
            if (acceptButton != null) this.removeComponent(acceptButton);

            acceptButton = new Button("Accept");
            acceptButton.addClickListener(event -> onClick.accept(model));
            acceptButton.setComponentError(new UserError("WTF"));
            withButtonRight(acceptButton);

            return this;
        }

        public FormDialogFooter withCancelButton(Consumer<MODEL> onClick) {
            if (cancelButton != null) this.removeComponent(cancelButton);

            cancelButton = new Button("Cancel");
            cancelButton.addClickListener(event -> onClick.accept(model));
            withButtonRight(cancelButton);

            return this;
        }

        public FormDialogFooter withAcceptClickListener(Consumer<MODEL> onClick) {
            Objects.requireNonNull(acceptButton);
            acceptButton.addClickListener(it -> onClick.accept(model));

            return this;
        }

        public FormDialogFooter withCancelClickListener(Consumer<MODEL> onClick) {
            Objects.requireNonNull(cancelButton);
            cancelButton.addClickListener(it -> onClick.accept(model));

            return this;
        }
    }
}
