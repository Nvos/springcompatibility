package czort.view;

import com.vaadin.spring.annotation.SpringView;
import czort.crud.CrudPresenter;
import czort.crud.CrudViewFragment;
import czort.dialog.FormDialog;
import czort.mvp.BaseView;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.UserResponse;
import org.vaadin.spring.events.EventBus;

@SpringView(name = UserView.VIEW_NAME)
public class UserView extends BaseView<CrudViewFragment<UserResponse, UserCreateRequest, UserUpdateRequest>> {
    public static final String VIEW_NAME = "UserView";

    private final CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> presenter;
    private final FormDialog<UserCreateRequest> createDialog;
    private final FormDialog<UserUpdateRequest> updateDialog;

    protected UserView(EventBus.ViewEventBus viewEventBus,
                       CrudPresenter<UserResponse,
                               UserCreateRequest,
                               UserUpdateRequest> presenter,
                       FormDialog<UserCreateRequest> createDialog, FormDialog<UserUpdateRequest> updateDialog) {
        super(viewEventBus);
        this.presenter = presenter;
        this.createDialog = createDialog;
        this.updateDialog = updateDialog;
    }

    @Override
    public CrudViewFragment<UserResponse,  UserCreateRequest, UserUpdateRequest> rootComponent() {
        return new CrudViewFragment<>(presenter)
                .withUpdateDialog(updateDialog)
                .withCreateDialog(createDialog)
                .withSection(section -> {
                    section.withButtonCreate(presenter::handleCreate);
                })
                .withGrid(grid -> {
                    grid.withGridRef(ref -> {
                        ref.addColumn(UserResponse::getName).setCaption("Name");
                        ref.addColumn(UserResponse::getEmail).setCaption("Email");
                    });
                });
    }
}
