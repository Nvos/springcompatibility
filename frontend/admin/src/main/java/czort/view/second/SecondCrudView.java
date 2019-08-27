package czort.view.second;

import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import czort.crud.CrudPresenter;
import czort.crud.CrudViewFragment;
import czort.crud.ReMapper;
import czort.dialog.FormDialog;
import czort.mvp.BaseView;
import czort.request.AdminCreateRequest;
import czort.request.AdminUpdateRequest;
import czort.response.AdminResponse;
import czort.response.UserResponse;
import czort.view.GridFilterComposer;
import org.springframework.beans.BeanUtils;
import org.vaadin.spring.events.EventBus;

import java.util.Map;

// TODO: Simplify configuration
// TODO: Abstract configuration which is required by both view and presenter
// TODO: Verify client injection in crud presenter
// TODO: Remove ueless abstractions

@ViewScope
@SpringView(name = SecondCrudView.VIEW_NAME)
public class SecondCrudView extends BaseView<CrudViewFragment<AdminResponse, AdminCreateRequest, AdminUpdateRequest>> {
    public static final String VIEW_NAME = "SecondCrudView";

    private final CrudPresenter<AdminResponse, AdminCreateRequest, AdminUpdateRequest> crudPresenter;
    private final FormDialog<AdminCreateRequest> userCreateDialogFormDialog;
    private final FormDialog<AdminUpdateRequest> userUpdateRequestFormDialog;
    private final AbstractDataProvider<AdminResponse, Map<String, String>> dataProvider;

    protected SecondCrudView(
            EventBus.ViewEventBus viewEventBus,
            CrudPresenter<AdminResponse, AdminCreateRequest, AdminUpdateRequest> crudPresenter,
            FormDialog<AdminCreateRequest> userCreateDialogFormDialog,
            FormDialog<AdminUpdateRequest> userUpdateRequestFormDialog,
            AbstractDataProvider<AdminResponse, Map<String, String>> dataProvider
    ) {
        super(viewEventBus);
        this.crudPresenter = crudPresenter;
        this.userCreateDialogFormDialog = userCreateDialogFormDialog;
        this.userUpdateRequestFormDialog = userUpdateRequestFormDialog;
        this.dataProvider = dataProvider;
    }

    @Override
    public CrudViewFragment<AdminResponse, AdminCreateRequest, AdminUpdateRequest> rootComponent() {
        return new CrudViewFragment<>(crudPresenter)
                .withCreateDialog(userCreateDialogFormDialog)
                .withUpdateDialog(userUpdateRequestFormDialog)
                .withSection(ref -> {
                    // TODO: Common elements such ass show/hide filter
                    // Though... how to pass grid ref to it?
                })
                .withGrid(composer -> composer
                        .withDataProvider(dataProvider)
                        .withGridRef(grid -> {
                            grid.addColumn(AdminResponse::getName).setCaption("Name");
                            grid.addColumn(AdminResponse::getEmail).setCaption("Email");
                        })
                        .withFilters(GridFilterComposer::withDefaultFilters)
                        .withRowDoubleClickHandler(crudPresenter::handleEdit)
                );
    }
}
