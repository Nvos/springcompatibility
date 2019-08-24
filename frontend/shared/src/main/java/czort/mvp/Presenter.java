package czort.mvp;

import com.vaadin.ui.Component;

public interface Presenter<FRAGMENT extends Component> {
    public void bootstrap(FRAGMENT view);
}
