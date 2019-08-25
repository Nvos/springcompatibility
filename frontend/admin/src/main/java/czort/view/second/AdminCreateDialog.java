package czort.view.second;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HasComponents;
import czort.dialog.FormDialog;
import czort.request.AdminCreateRequest;
import czort.request.UserCreateRequest;
import czort.view.SharedForm;
import org.vaadin.spring.annotation.PrototypeScope;

@PrototypeScope
@SpringComponent
public class AdminCreateDialog extends FormDialog<AdminCreateRequest> {

    @Override
    protected HasComponents bodyComponent(AdminCreateRequest model) {
        return new SharedForm(model, AdminCreateRequest.class);
    }
}
