package czort.view;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HasComponents;
import czort.dialog.FormDialog;
import czort.form.Form;
import czort.form.StandardForm;
import czort.request.UserCreateRequest;
import org.vaadin.spring.annotation.PrototypeScope;

@PrototypeScope
@SpringComponent
public class UserCreateDialog extends FormDialog<UserCreateRequest> {

    @Override
    protected HasComponents bodyComponent(UserCreateRequest model) {
        return new StandardForm<>(model, UserCreateRequest.class)
                .withColumn(column -> {
                    column.withTextField("name");
                    column.withTextField("email");
                });
    }
}
