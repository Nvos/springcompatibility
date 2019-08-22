package czort.mvp;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.springframework.context.ApplicationEventPublisher;
import org.vaadin.spring.events.EventBus;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class BaseView<PRESENTER extends BasePresenter, ROOT extends Component> extends CustomComponent implements View {
    protected final PRESENTER presenter;
    protected final EventBus.ViewEventBus viewEventBus;

    protected ROOT rootComponent;

    protected BaseView(PRESENTER presenter, EventBus.ViewEventBus viewEventBus) {
        this.presenter = presenter;
        this.viewEventBus = viewEventBus;
    }

    public abstract ROOT rootComponent();

    @PostConstruct
    public void onPostConstruct() {
        presenter.bootstrap(this);
        viewEventBus.subscribe(this);

        rootComponent = rootComponent();
        setCompositionRoot(rootComponent);

        withRootAndPresenter(presenter, rootComponent);
    }

    @PreDestroy
    public void onPreDestroy() {
        viewEventBus.subscribe(this);
    }

    protected void withRootAndPresenter(PRESENTER presenter, ROOT rootComponent) {
    }
}
