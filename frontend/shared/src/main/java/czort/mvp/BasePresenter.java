package czort.mvp;

import com.vaadin.ui.Component;

public abstract class BasePresenter<FRAGMENT extends Component> {
    protected transient FRAGMENT view;

    public BasePresenter<FRAGMENT> bootstrap(FRAGMENT view) {
        this.view = view;

        return this;
    }
}
