package czort.mvp;

import com.vaadin.navigator.View;

public abstract class BasePresenter<VIEW extends BaseView, MODEL> {
    protected VIEW view;
    protected MODEL model;

    protected BasePresenter<VIEW, MODEL> bootstrap(VIEW view) {
        this.view = view;

        return this;
    }

    protected abstract MODEL initialModel();
}
