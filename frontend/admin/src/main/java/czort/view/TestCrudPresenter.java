package czort.view;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.mvp.BasePresenter;

@ViewScope
@SpringComponent
public class TestCrudPresenter extends BasePresenter<TestCrudView, Model> {

    @Override
    protected Model initialModel() {
        return new Model();
    }
}
