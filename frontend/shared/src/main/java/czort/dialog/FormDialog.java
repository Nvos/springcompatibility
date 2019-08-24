package czort.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Window;
import czort.form.Form;
import czort.form.FormBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringComponent
@PrototypeScope
public abstract class FormDialog<MODEL> extends BaseDialog<FormDialog.FormDialogFooter> {

    private MODEL model;

    @Autowired
    private ActionDialog actionDialog;

    @Override
    public FormDialog<MODEL> open() {
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

    public void closeWithoutPrompt() {
        super.close();
    }

    protected abstract HasComponents bodyComponent(MODEL model);

    public class FormDialogFooter extends BaseDialogFooter {
        private Button acceptButton;
        private Button cancelButton;

        public FormDialogFooter() { }

        public FormDialogFooter withAcceptButton(Consumer<MODEL> withProvidedModel) {
            if (acceptButton != null) this.removeComponent(acceptButton);

            acceptButton = new Button("Accept", event -> {
                withProvidedModel.accept(getForm().getBinder().getBean());
                close();
            });

            withButtonRight(acceptButton);

            return this;
        }

        public FormDialogFooter withCancelButton() {
            if (cancelButton != null) this.removeComponent(cancelButton);

            cancelButton = new Button("Cancel");
            cancelButton.addClickListener(event -> close());
            withButtonRight(cancelButton);

            return this;
        }
    }

    protected Form<MODEL> getForm() {
        return (Form<MODEL>)bodyComponent;
    }
}
