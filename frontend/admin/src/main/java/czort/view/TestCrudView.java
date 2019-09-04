package czort.view;

import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import czort.AdminUI;
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

@ViewScope
@SpringView(name = TestCrudView.VIEW_NAME, ui = AdminUI.class)
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
        return CrudViewFragment.instance(crudPresenter)
                .withCreateDialog(userCreateDialogFormDialog)
                .withUpdateDialog(userUpdateRequestFormDialog)
                .withSection(ref -> {
                    Button navigateButton = new Button("Navigate", event -> {
                        UI.getCurrent().getNavigator().navigateTo(SecondCrudView.VIEW_NAME);
                    });

                    ref.addComponent(navigateButton);
                })
                .withGrid(composer -> composer
                        .withDataProvider(dataProvider)
                        .withGridRef(grid -> {
                            grid.addColumn(UserResponse::getName).setCaption("Name").setExpandRatio(1);
                            grid.addColumn(UserResponse::getEmail).setCaption("Email").setExpandRatio(1);
                        })
                        .withRowDoubleClickHandler(crudPresenter::handleEdit)
                        .withContextMenu(contextMenu -> {
                            contextMenu.withAction("Edit", model -> System.out.println("Edit " + model));
                            contextMenu.withAction("Delete", model -> System.out.println("Delete " + model));
                            contextMenu.withAction("Copy", model -> System.out.println("Copy " + model));
                            contextMenu.withGridMenuButtonColumn();
                        })
                )
                .withTranslations();
    }
}
