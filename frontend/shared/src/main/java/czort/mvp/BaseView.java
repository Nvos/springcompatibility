package czort.mvp;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.springframework.context.ApplicationEventPublisher;
import org.vaadin.spring.events.EventBus;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseView<ROOT extends Component> extends CustomComponent implements View {
    protected transient final EventBus.ViewEventBus viewEventBus;

    protected ROOT rootComponent;

    protected BaseView(EventBus.ViewEventBus viewEventBus) {
        this.viewEventBus = viewEventBus;
    }

    public abstract ROOT rootComponent();

    @PostConstruct
    public void onPostConstruct() {
        viewEventBus.subscribe(this);

        rootComponent = rootComponent();
        setCompositionRoot(rootComponent);

        withDefaults();
    }

    private BaseView<ROOT> withDefaults() {
        setSizeFull();

        return this;
    }

    @PreDestroy
    public void onPreDestroy() {
        viewEventBus.subscribe(this);
    }
}
