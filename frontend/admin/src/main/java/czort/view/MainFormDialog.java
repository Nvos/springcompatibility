package czort.view;

import com.vaadin.spring.annotation.SpringComponent;
import czort.client.UserCrudClient;
import czort.dialog.FormDialog;
import czort.form.StandardForm;
import org.vaadin.spring.annotation.PrototypeScope;

@SpringComponent
@PrototypeScope
public class MainFormDialog extends FormDialog<Model> {

    private final UserCrudClient userClient;

    public MainFormDialog(UserCrudClient userClient) {
        this.userClient = userClient;
    }

    @Override
    protected StandardForm<Model> bodyComponent(Model model) {
        return null;
    }
}
