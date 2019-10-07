package czort.crud;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.VerticalSplitPanel;

@ViewScope
@SpringComponent
public class CrudSplitViewFragment extends VerticalSplitPanel {

    public <MODEL, CREATE, UPDATE> CrudSplitViewFragment withCrudFragmentTop(
            CrudViewFragment<MODEL, CREATE, UPDATE> fragment
    ) {
        setFirstComponent(fragment);
        fragment.setSizeFull();

        return this;
    }

    public <MODEL, CREATE, UPDATE> CrudSplitViewFragment withCrudFragmentBottom(
            CrudViewFragment<MODEL, CREATE, UPDATE> fragment
    ) {
        setSecondComponent(fragment);
        fragment.setSizeFull();

        return this;
    }
}
