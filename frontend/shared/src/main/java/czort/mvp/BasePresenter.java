package czort.mvp;

import com.vaadin.ui.Component;

public abstract class BasePresenter<FRAGMENT extends Component> implements Presenter<FRAGMENT> {
    protected transient FRAGMENT view;

    public void bootstrap(FRAGMENT view) {
        this.view = view;
    }
}
