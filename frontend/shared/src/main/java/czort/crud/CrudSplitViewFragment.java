package czort.crud;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

import java.util.function.Consumer;

public class CrudSplitViewFragment extends VerticalSplitPanel {

    public <MODEL, CREATE, UPDATE> CrudSplitViewFragment withCrudFragmentTop(
            CrudViewFragment<MODEL, CREATE, UPDATE> fragment
    ) {
        setFirstComponent(fragment);

        return this;
    }

    public <MODEL, CREATE, UPDATE> CrudSplitViewFragment withCrudFragmentBottom(
            CrudViewFragment<MODEL, CREATE, UPDATE> fragment
    ) {
        setSecondComponent(fragment);

        return this;
    }
}
