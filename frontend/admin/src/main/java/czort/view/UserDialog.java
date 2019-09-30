package czort.view;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import czort.dialog.FormDialog;
import czort.form.Form;
import czort.form.StandardForm;
import czort.form.TabSheetForm;
import czort.form.field.ControlledValidator;
import czort.form.field.GridFieldBinding;
import czort.form.field.TexFieldBinding;
import czort.request.UserRequest;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

            it.withTab("search", form -> {
                form.withColumn(column -> {
                    column.withLabel("1", "l");
                    column.withLabel("2", "l");
                    column.withLabel("3", "l");
                    column.withLabel("4", "l");
                    column.withLabel("5", "l");
                    column.withLabel("6", "l");
                    column.withLabel("7", "l");
                    column.withLabel("8", "l");
                    column.withLabel("9", "l");
                    column.withLabel("0", "l");
                    column.withLabel("11", "l");
                    column.withLabel("12", "l");
                    column.withComponent(new ItemSearch());
                });
            });

            it.withTab("grid", form -> {
                GridFieldBinding<T, String> binding = form.withGrid("items", (grid, field) -> {
                    grid.withGridRef(ref -> {
                        ref.addColumn(item -> item).setCaption("Value");
                        ref.addItemClickListener(event -> {
                            if (event.getItem() == null) return;

                            List<String> newValue = new ArrayList<>(field.getValue());
                            if (event.getMouseEventDetails().isAltKey()) {
                                newValue.remove(event.getItem());
                            } else if (event.getMouseEventDetails().isCtrlKey()) {
                                newValue.add(event.getItem());
                            }
                            field.setValue(newValue);
                        });
                    });
                });
                form.withColumn(column -> {
                   column.withComponent(new Button("New", event -> {
                       T bean = form.getBinder().getBean();
                       System.out.println(bean);
                   }));
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
