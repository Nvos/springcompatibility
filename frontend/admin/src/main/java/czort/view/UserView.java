package czort.view;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.GridSelectionModel;
import czort.crud.CrudPresenter;
import czort.crud.CrudSplitViewFragment;
import czort.crud.CrudViewFragment;
import czort.dialog.FormDialog;
import czort.mvp.BaseView;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.UserResponse;
import org.vaadin.spring.events.EventBus;

@SpringView(name = UserView.VIEW_NAME)
public class UserView extends BaseView<CrudSplitViewFragment> {
    public static final String VIEW_NAME = "UserView";

    private final SharedUserViewFragment sharedUserViewFragment;

    protected UserView(
            EventBus.ViewEventBus viewEventBus,
            SharedUserViewFragment sharedUserViewFragment
) {
        super(viewEventBus);
        this.sharedUserViewFragment = sharedUserViewFragment;
    }

    @Override
    public CrudSplitViewFragment rootComponent() {
        return new CrudSplitViewFragment()
                .withCrudFragmentTop(sharedUserViewFragment);
    }
}
