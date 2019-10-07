package czort.view;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import czort.dialog.FormDialog;
import czort.form.Form;
import czort.form.TabSheetForm;
import czort.form.field.ControlledValidator;
import czort.form.field.GridFieldComposer;
import czort.request.UserRequest;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.ArrayList;
import java.util.List;

@PrototypeScope
@SpringComponent
public class UserDialog<T extends UserRequest> extends FormDialog<T> {

    private final FormDialogFooter footer = new FormDialogFooter()
                .withAcceptButton()
                .withCancelButton();

    private final Validator<Integer> lengthValidator = (value, context) -> {
        if (value == null) return ValidationResult.error("Err");
        if (value < 5) return ValidationResult.error("Err");
        return ValidationResult.ok();
    };

    @Override
    protected Form<T> bodyComponent(T userRequest) {
        return new TabSheetForm<>(userRequest).with(it -> {
           it.withTab("t1", form -> {
                form.withColumn(column -> {
                    column.withIntegerField("value").withValidator(lengthValidator);
//                    column.withFieldRow("row", row -> {
//                       row.withIntegerField("value").withWidth(32);
//                       row.withIntegerField("value").withWidth(32);
//                       row.withIntegerField("value").withWidth(32);
//                       row.withIntegerField("value").withWidth(32);
//                    });
                });
                form.withOnValidationStatusChange(isValid -> {
                    footer.getAcceptButton().setEnabled(!isValid);
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
                });
            });

            it.withTab("grid", form -> {
                GridFieldComposer<T, String> binding = form.withGrid("items", (grid, field) -> {
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
                   column.withComponent("ok", new Button("New", event -> {
                       T bean = form.getBinder().getBean();
                       System.out.println(bean);
                   }));
                });
            });
        });
    }

    @Override
    protected FormDialogFooter footerComponent() {
        return footer;
    }
}
