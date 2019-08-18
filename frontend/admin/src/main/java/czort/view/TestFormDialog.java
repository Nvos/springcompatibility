package czort.view;

import com.vaadin.data.ValidationResult;
import com.vaadin.server.UserError;
import com.vaadin.spring.annotation.SpringComponent;
import czort.dialog.FormDialog;
import czort.form.Form;
import org.vaadin.spring.annotation.PrototypeScope;

@SpringComponent
@PrototypeScope
public class TestFormDialog extends FormDialog<Model> {

    @Override
    protected Form<Model> bodyComponent(Model model) {
        return new Form<>(model, Model.class)
            .withColumn(column -> {
                column.withTextField("name")
                    .useBinding(it ->  it.withValidator((value, context) ->
                            value.length() > 10
                                    ? ValidationResult.ok()
                                    : ValidationResult.error("Value less than 10")
                    ))
                .useField(it -> it.setComponentError(new UserError("wtf")));
                column.withLabel("", "name section");
                column.withIntegerField("value1");
                column.withLabel("Label", "value2 section");
                column.withLongField("value2")
                    .useBinding(binding -> {
                        binding.withValidator((value, context) ->
                            value > 10
                                    ? ValidationResult.ok()
                                    : ValidationResult.error("Value less than 10")
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
                .withCancelButton(System.out::println)
                .withAcceptButton(System.out::println);
    }
}
