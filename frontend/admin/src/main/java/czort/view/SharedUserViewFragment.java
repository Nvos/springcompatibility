package czort.view;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.GridSelectionModel;
import czort.crud.CrudPresenter;
import czort.crud.CrudViewFragment;
import czort.dialog.FormDialog;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.UserResponse;

import javax.annotation.PostConstruct;

@UIScope
@SpringComponent
public class SharedUserViewFragment extends CrudViewFragment<UserResponse, UserCreateRequest, UserUpdateRequest> {
    private final CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> presenter;
    private final FormDialog<UserCreateRequest> createDialog;
    private final FormDialog<UserUpdateRequest> updateDialog;

    public SharedUserViewFragment(CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> presenter, CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> presenter1, FormDialog<UserCreateRequest> createDialog, FormDialog<UserUpdateRequest> updateDialog) {
        super(presenter);
        this.presenter = presenter1;
        this.createDialog = createDialog;
        this.updateDialog = updateDialog;
    }

    @PostConstruct
    public void postConstruct() {
         withUpdateDialog(updateDialog)
                .withCreateDialog(createDialog)
                .withSection(section -> {
                    section.withButtonCreate(presenter::handleCreate);
                })
                .withGrid(grid -> {
                    grid.withDefaultDataProvider();
                    grid.withGridRef(ref -> {
                        ref.addColumn(UserResponse::getName).setCaption("Name");
                        ref.addColumn(UserResponse::getEmail).setCaption("Email");
                        GridSelectionModel<UserResponse> selectionModel = ref.setSelectionMode(Grid.SelectionMode.MULTI);
                        selectionModel.addSelectionListener(System.out::println);
                    });
                });
    }
}
