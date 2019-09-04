package czort.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HasComponents;
import czort.form.Form;
import czort.form.StandardForm;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.Objects;
import java.util.function.Consumer;

@SpringComponent
@PrototypeScope
public abstract class FormDialog<VALUE> extends BaseDialog<VALUE, FormDialog.FormDialogFooter> {

    private VALUE value;

    @Override
    public FormDialog<VALUE> open() {
        Objects.requireNonNull(value, "Setting initial FormDialog value is required!");

        super.open();

        return this;
    }

    public FormDialog<VALUE> with(Consumer<FormDialog<VALUE>> withSelfProvided) {
        withSelfProvided.accept(this);

        return this;
    }

    @Override
    public FormDialog<VALUE> withSize(Size size) {
        super.withSize(size);

        return this;
    }

    public FormDialog<VALUE> withInitialValue(VALUE initialValue) {
        this.value = initialValue;

        return this;
    }

    @Override
    public FormDialog<VALUE> useFooterComponent(Consumer<FormDialog.FormDialogFooter> withProvidedFooterComponent) {
        super.useFooterComponent(withProvidedFooterComponent);

        return this;
    }

    @Override
    public FormDialog<VALUE> useBodyComponent(Consumer<StandardForm<VALUE>> withProvidedBodyComponent) {
        super.useBodyComponent(withProvidedBodyComponent);

        return this;
    }

    @Override
    protected FormDialogFooter footerComponent() {
        return new FormDialogFooter();
    }

    @Override
    protected HasComponents bodyComponent() {
        return bodyComponent(value);
    }

    public void closeWithoutPrompt() {
        super.close();
    }

    protected abstract Form<VALUE> bodyComponent(VALUE value);


    public class FormDialogFooter extends BaseDialogFooter<VALUE> {
        private Button acceptButton;
        private Button cancelButton;
        protected FormDialog<VALUE> dialog;

        public FormDialogFooter(FormDialog<VALUE> dialog) {
            super(dialog);
        }

        public FormDialogFooter withAcceptButton() {
            if (acceptButton != null) this.removeComponent(acceptButton);

            acceptButton = new Button("Accept", event -> {
                withResult(DialogResult.accept(getForm().getBinder().getBean()));
            });

            withButtonRight(acceptButton);

            return this;
        }

        public FormDialogFooter withCancelButton() {
            if (cancelButton != null) this.removeComponent(cancelButton);

            cancelButton = new Button("Cancel", event -> {
                withResult(DialogResult.cancel());
            });

            withButtonRight(cancelButton);

            return this;
        }

        public FormDialogFooter closeWithoutPrompt() {
            FormDialog.this.closeWithoutPrompt();

            return this;
        }
    }

    protected Form<VALUE> getForm() {
        return (Form<VALUE>)bodyComponent;
    }
}
