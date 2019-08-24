package czort.view;

import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import czort.crud.CrudViewFragment;
import czort.dialog.FormDialog;
import czort.mvp.BaseView;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.UserResponse;
import org.vaadin.spring.events.EventBus;

import java.util.Map;

// TODO: Simplify configuration
// TODO: Verify if withTypes is needed (mappings can be provided as lambdas model<=>update/create)
// TODO: Abstract configuration which is required by both view and presenter

@ViewScope
@SpringView(name = TestCrudView.VIEW_NAME)
public class TestCrudView extends BaseView<CrudViewFragment<UserResponse, UserCreateRequest, UserUpdateRequest>> {
    public static final String VIEW_NAME = "TestCrudView";

    private final CrudViewFragment<UserResponse, UserCreateRequest, UserUpdateRequest> crudViewFragment;
    private final FormDialog<UserCreateRequest> userCreateDialogFormDialog;
    private final FormDialog<UserUpdateRequest> userUpdateRequestFormDialog;
    private final AbstractDataProvider<UserResponse, Map<String, String>> dataProvider;

    protected TestCrudView(
            EventBus.ViewEventBus viewEventBus,
            CrudViewFragment<UserResponse, UserCreateRequest, UserUpdateRequest> crudViewFragment,
            FormDialog<UserCreateRequest> userCreateDialogFormDialog, FormDialog<UserUpdateRequest> userUpdateRequestFormDialog, AbstractDataProvider<UserResponse, Map<String, String>> dataProvider) {
        super(viewEventBus);
        this.crudViewFragment = crudViewFragment;
        this.userCreateDialogFormDialog = userCreateDialogFormDialog;
        this.userUpdateRequestFormDialog = userUpdateRequestFormDialog;
        this.dataProvider = dataProvider;
    }

    @Override
    public CrudViewFragment<UserResponse, UserCreateRequest, UserUpdateRequest> rootComponent() {
        return crudViewFragment
                .withTypes(UserResponse.class, UserCreateRequest.class, UserUpdateRequest.class)
                .withCreateDialog(userCreateDialogFormDialog)
                .withUpdateDialog(userUpdateRequestFormDialog)
                .withSection()
                .withGrid(ref -> {
                    ref.addColumn(UserResponse::getName).setCaption("Name");
                    ref.addColumn(UserResponse::getEmail).setCaption("Email");
                })
                .withGridDataProvider(dataProvider)
                .withGridEdit(UserResponse::getId);
    }
}
