package czort.view;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import czort.dialog.FormDialog;
import czort.form.Form;
import czort.form.TabSheetForm;
import czort.form.field.ControlledValidator;
import czort.form.field.TexFieldBinding;
import czort.request.UserRequest;
import org.vaadin.spring.annotation.PrototypeScope;

@PrototypeScope
@SpringComponent
public class UserDialog<T extends UserRequest> extends FormDialog<T> {

    private final ControlledValidator<String> lengthValidator = ControlledValidator.decorate((value, context) -> {
        if (value == null) return ValidationResult.error("Err");
        if (value.length() < 5) return ValidationResult.error("Err");
        return ValidationResult.ok();
    });

    @Override
    protected Form<T> bodyComponent(T userRequest) {
        return new TabSheetForm<>(userRequest).with(it -> {
           it.withTab("t1", form -> {
                form.withColumn(column -> {
                    boolean[] isVisible = {true};


                    TexFieldBinding<T> name = column.withTextField("name")
                            .withValidator(lengthValidator);

                    Button hide = new Button("HIDEEEE", event -> {
                        isVisible[0] = !isVisible[0];
                        name.setValidationEnabled(isVisible[0]);
                    });

                    column.withComponent(hide);
                });
            });
            it.withTab("t2", form -> {
                form.withColumn(column -> {
                    column.withTextField("email");
                });
            });
        });
    }

    @Override
    protected FormDialogFooter footerComponent() {
        return new FormDialogFooter()
                .withAcceptButton()
                .withCancelButton();
    }
}
