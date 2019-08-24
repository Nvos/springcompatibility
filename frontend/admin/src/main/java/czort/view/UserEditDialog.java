package czort.view;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HasComponents;
import czort.dialog.FormDialog;
import czort.form.StandardForm;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import org.vaadin.spring.annotation.PrototypeScope;

@PrototypeScope
@SpringComponent
public class UserEditDialog extends FormDialog<UserUpdateRequest> {

    @Override
    protected HasComponents bodyComponent(UserUpdateRequest userUpdateRequest) {
        return new StandardForm<>(userUpdateRequest, UserUpdateRequest.class)
                .withColumn(column -> {
                    column.withTextField("name");
                    column.withTextField("email");
                });
    }
}
