package czort.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HasComponents;
import czort.form.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.Objects;
import java.util.function.Consumer;

@SpringComponent
@PrototypeScope
public abstract class FormDialog<MODEL> extends BaseDialog<FormDialog.FormDialogFooter> {

    private MODEL model;
    private FormDialogResult<MODEL> result;
    private Consumer<FormDialogResult<MODEL>> formDialogResultHandler;

    @Autowired
    private ActionDialog actionDialog;

    @Override
    public FormDialog<MODEL> open() {
        Objects.requireNonNull(model, "Setting initial FormDialog value is required!");

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

    public FormDialog<MODEL> onResultChange(Consumer<FormDialogResult<MODEL>> formDialogResultHandler) {
        this.formDialogResultHandler = formDialogResultHandler;

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

    public void closeWithoutPrompt() {
        super.close();
    }

    protected abstract HasComponents bodyComponent(MODEL model);

    public FormDialog<MODEL> setFormResult(FormDialogResult<MODEL> result) {
        this.result = result;
        if (formDialogResultHandler != null) formDialogResultHandler.accept(result);

        return this;
    }

    public class FormDialogFooter extends BaseDialogFooter {
        private Button acceptButton;
        private Button cancelButton;

        public FormDialogFooter() { }

        public FormDialogFooter withAcceptButton() {
            if (acceptButton != null) this.removeComponent(acceptButton);

            acceptButton = new Button("Accept", event -> {
                setFormResult(FormDialogResult.accept(getForm().getBinder().getBean()));
            });

            withButtonRight(acceptButton);

            return this;
        }

        public FormDialogFooter withCancelButton() {
            if (cancelButton != null) this.removeComponent(cancelButton);

            cancelButton = new Button("Cancel", event -> {
                setFormResult(FormDialogResult.cancel());
            });

            withButtonRight(cancelButton);

            return this;
        }

        public FormDialogFooter closeWithoutPrompt() {
            FormDialog.this.closeWithoutPrompt();

            return this;
        }
    }

    protected Form<MODEL> getForm() {
        return (Form<MODEL>)bodyComponent;
    }
}
