package czort.view;

import czort.form.Form;
import czort.form.StandardForm;
import czort.request.UserCreateRequest;

public class SharedForm extends StandardForm<Object> {
    public SharedForm(Object o, Class objectClass) {
        super(o, objectClass);

        withColumn(column -> {
            column.withTextField("name");
            column.withTextField("email");
        });
    }
}
