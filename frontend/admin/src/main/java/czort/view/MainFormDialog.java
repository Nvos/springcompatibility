package czort.view;

import com.vaadin.spring.annotation.SpringComponent;
import czort.client.UserClient;
import czort.dialog.FormDialog;
import czort.form.StandardForm;
import org.vaadin.spring.annotation.PrototypeScope;

@SpringComponent
@PrototypeScope
public class MainFormDialog extends FormDialog<Model> {

    private final UserClient userClient;

    public MainFormDialog(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    protected StandardForm<Model> bodyComponent(Model model) {
        return null;
    }
}
