package czort.view;

import com.vaadin.data.ValidationResult;
import com.vaadin.spring.annotation.SpringComponent;
import czort.client.UserCrudClient;
import czort.dialog.FormDialog;
import czort.form.StandardForm;
import org.vaadin.spring.annotation.PrototypeScope;

@SpringComponent
@PrototypeScope
public class TestFormDialog extends FormDialog<Model> {

    private final UserCrudClient userClient;

    public TestFormDialog(UserCrudClient userClient) {
        this.userClient = userClient;
    }

    @Override
    protected StandardForm<Model> bodyComponent(Model model) {
        return new StandardForm<>(model, Model.class)
            .withColumn(column -> {
                column.withTextField("name")
                    .useBinding(it ->  it.withValidator((value, context) ->
                            (value == null || value.length() < 10)
                                    ? ValidationResult.error("Value less than 10")
                                    : ValidationResult.ok()
                    ));
                column.withLabel("", "name section");
                column.withIntegerField("value1");
                column.withLabel("Label", "value2 section");
                column.withLongField("value2")
                    .useBinding(binding -> {
                        binding.withValidator((value, context) ->
                            (value == null || value < 10)
                                    ? ValidationResult.error("Value less than 10")
                                    : ValidationResult.ok()
                        );
                    });
            })
            .withColumn(column -> {
                column.withLabel("Label", "");
                column.withLocalDateField("date");
            });
    }

    @Override
    protected FormDialogFooter footerComponent() {
        return new FormDialogFooter()
                .withCancelButton()
                .withAcceptButton();
    }
}
