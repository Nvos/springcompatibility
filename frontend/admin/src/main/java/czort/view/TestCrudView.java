package czort.view;

import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import czort.crud.CrudPresenter;
import czort.crud.CrudViewFragment;
import czort.crud.ReMapper;
import czort.dialog.FormDialog;
import czort.mvp.BaseView;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.UserResponse;
import czort.view.second.SecondCrudView;
import org.springframework.beans.BeanUtils;
import org.vaadin.spring.events.EventBus;

import java.util.Map;

// TODO: Simplify configuration
// TODO: Abstract configuration which is required by both view and presenter
// TODO: Verify client injection in crud presenter
// TODO: Remove ueless abstractions

@ViewScope
@SpringView(name = TestCrudView.VIEW_NAME)
public class TestCrudView extends BaseView<CrudViewFragment<UserResponse, UserCreateRequest, UserUpdateRequest>> {
    public static final String VIEW_NAME = "TestCrudView";

    private final CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> crudPresenter;
    private final FormDialog<UserCreateRequest> userCreateDialogFormDialog;
    private final FormDialog<UserUpdateRequest> userUpdateRequestFormDialog;
    private final AbstractDataProvider<UserResponse, Map<String, String>> dataProvider;

    protected TestCrudView(
            EventBus.ViewEventBus viewEventBus,
            CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> crudPresenter,
            FormDialog<UserCreateRequest> userCreateDialogFormDialog,
            FormDialog<UserUpdateRequest> userUpdateRequestFormDialog,
            AbstractDataProvider<UserResponse, Map<String, String>> dataProvider
    ) {
        super(viewEventBus);
        this.crudPresenter = crudPresenter;
        this.userCreateDialogFormDialog = userCreateDialogFormDialog;
        this.userUpdateRequestFormDialog = userUpdateRequestFormDialog;
        this.dataProvider = dataProvider;
    }

    @Override
    public CrudViewFragment<UserResponse, UserCreateRequest, UserUpdateRequest> rootComponent() {
        return new CrudViewFragment<>(
            crudPresenter.withReMapper(new ReMapper<>(
                UserResponse::getId,
                UserCreateRequest::new,
                model -> {
                    UserUpdateRequest update = new UserUpdateRequest();
                    BeanUtils.copyProperties(model, update);

                    return update;
                }
            )))
                .withCreateDialog(userCreateDialogFormDialog)
                .withUpdateDialog(userUpdateRequestFormDialog)
                .withSection(ref -> {
                    Button navigateButton = new Button("Navigate", event -> {
                       UI.getCurrent().getNavigator().navigateTo(SecondCrudView.VIEW_NAME);
                    });

                    ref.addComponent(navigateButton);
                })
                .withGrid(ref -> {
                    ref.addColumn(UserResponse::getName).setCaption("Name");
                    ref.addColumn(UserResponse::getEmail).setCaption("Email");
                })
                .withGridDataProvider(dataProvider)
                .withGridEdit();
    }
}
