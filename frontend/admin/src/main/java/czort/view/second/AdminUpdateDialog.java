package czort.view.second;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HasComponents;
import czort.dialog.FormDialog;
import czort.request.AdminCreateRequest;
import czort.request.AdminUpdateRequest;
import czort.view.SharedForm;
import org.vaadin.spring.annotation.PrototypeScope;

@PrototypeScope
@SpringComponent
public class AdminUpdateDialog extends FormDialog<AdminUpdateRequest> {

    @Override
    protected HasComponents bodyComponent(AdminUpdateRequest model) {
        return new SharedForm(model, AdminUpdateRequest.class);
    }
}
