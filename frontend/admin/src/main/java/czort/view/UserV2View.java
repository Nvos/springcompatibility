package czort.view;

import com.vaadin.spring.annotation.SpringView;
import czort.crud.CrudSplitViewFragment;
import czort.mvp.BaseView;
import org.vaadin.spring.events.EventBus;

@SpringView(name = UserV2View.VIEW_NAME)
public class UserV2View extends BaseView<CrudSplitViewFragment> {
    public static final String VIEW_NAME = "UserV2View";

    private final SharedUserViewFragment sharedUserViewFragment;

    protected UserV2View(
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
