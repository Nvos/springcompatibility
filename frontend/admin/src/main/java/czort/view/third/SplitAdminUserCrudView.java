package czort.view.third;

import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import czort.crud.CrudPresenter;
import czort.crud.CrudSplitViewFragment;
import czort.crud.CrudViewFragment;
import czort.dialog.FormDialog;
import czort.mvp.BaseView;
import czort.request.AdminCreateRequest;
import czort.request.AdminUpdateRequest;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.AdminResponse;
import czort.response.UserResponse;
import czort.view.GridFilterComposer;
import czort.view.second.SecondCrudView;
import org.vaadin.spring.events.EventBus;

import java.util.Map;

@ViewScope
@SpringView(name = SplitAdminUserCrudView.VIEW_NAME)
public class SplitAdminUserCrudView extends BaseView<CrudSplitViewFragment> {
    public static final String VIEW_NAME = "SplitAdminUserCrudView";

    private final CrudPresenter<AdminResponse, AdminCreateRequest, AdminUpdateRequest> adminCrudPresenter;
    private final FormDialog<AdminCreateRequest> adminCreateRequestFormDialog;
    private final FormDialog<AdminUpdateRequest> adminUpdateRequestFormDialog;
    private final AbstractDataProvider<AdminResponse, Map<String, String>> adminDataProvider;

    private final CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> userCrudPresenter;
    private final FormDialog<UserCreateRequest> userCreateDialogFormDialog;
    private final FormDialog<UserUpdateRequest> userUpdateRequestFormDialog;
    private final AbstractDataProvider<UserResponse, Map<String, String>> userDataProvider;

    protected SplitAdminUserCrudView(
            EventBus.ViewEventBus viewEventBus,
            CrudPresenter<AdminResponse, AdminCreateRequest, AdminUpdateRequest> adminCrudPresenter,
            FormDialog<AdminCreateRequest> adminCreateRequestFormDialog,
            FormDialog<AdminUpdateRequest> adminUpdateRequestFormDialog,
            AbstractDataProvider<AdminResponse, Map<String, String>> adminDataProvider,
            CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> userCrudPresenter,
            FormDialog<UserCreateRequest> userCreateDialogFormDialog,
            FormDialog<UserUpdateRequest> userUpdateRequestFormDialog,
            AbstractDataProvider<UserResponse, Map<String, String>> userDataProvider
    ) {
        super(viewEventBus);
        this.userCrudPresenter = userCrudPresenter;
        this.adminCreateRequestFormDialog = adminCreateRequestFormDialog;
        this.adminUpdateRequestFormDialog = adminUpdateRequestFormDialog;
        this.adminDataProvider = adminDataProvider;
        this.adminCrudPresenter = adminCrudPresenter;
        this.userCreateDialogFormDialog = userCreateDialogFormDialog;
        this.userUpdateRequestFormDialog = userUpdateRequestFormDialog;
        this.userDataProvider = userDataProvider;
    }

    @Override
    public CrudSplitViewFragment rootComponent() {
        return new CrudSplitViewFragment()
                .withCrudFragmentTop(new CrudViewFragment<>(userCrudPresenter)
                        .withCreateDialog(userCreateDialogFormDialog)
                        .withUpdateDialog(userUpdateRequestFormDialog)
                        .withSection(ref -> {
                           ref.withButtonCreate(userCrudPresenter::handleCreate);
                           ref.withButtonFilterToggle();
                        })
                        .withGrid(composer -> composer
                                .withDataProvider(userDataProvider)
                                .withGridRef(grid -> {
                                    grid.addColumn(UserResponse::getName).setCaption("Name");
                                    grid.addColumn(UserResponse::getEmail).setCaption("Email");
                                })
                                .withRowDoubleClickHandler(userCrudPresenter::handleEdit)
                        )
                )
                .withCrudFragmentBottom(new CrudViewFragment<>(adminCrudPresenter)
                .withCreateDialog(adminCreateRequestFormDialog)
                .withUpdateDialog(adminUpdateRequestFormDialog)
                    .withSection(ref -> {
                        ref.withButtonCreate(adminCrudPresenter::handleCreate);
                        ref.withButtonFilterToggle();
                    })
                .withGrid(composer -> composer
                        .withDataProvider(adminDataProvider)
                        .withGridRef(grid -> {
                            grid.addColumn(AdminResponse::getName).setCaption("Name");
                            grid.addColumn(AdminResponse::getEmail).setCaption("Email");
                        })
                        .withFilters(GridFilterComposer::withDefaultFilters)
                        .withRowDoubleClickHandler(adminCrudPresenter::handleEdit)
                ));
    }
}
