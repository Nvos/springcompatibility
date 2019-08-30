package czort;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.VerticalLayout;
import czort.view.MainView;
import czort.view.TestCrudView;
import czort.view.third.SplitAdminUserCrudView;
import org.springframework.beans.factory.ObjectProvider;

@Title("Admin")
@Theme("standard")
@SpringUI(path = "admin")
@SpringViewDisplay
public class AdminUI extends RootUI {

    protected final SpringNavigator springNavigator;
    protected final ObjectProvider<DynamicViewLoader<AdminUI>> dynamicViewLoaderObjectProvider;

    public AdminUI(
            SpringNavigator springNavigator,
            ObjectProvider<DynamicViewLoader<AdminUI>> dynamicViewLoaderObjectProvider
    ) {
        this.springNavigator = springNavigator;
        this.dynamicViewLoaderObjectProvider = dynamicViewLoaderObjectProvider;
    }

    @Override
    protected void init(VaadinRequest request) {
        springNavigator.addView(MainView.VIEW_NAME, MainView.class);
        springNavigator.addView(TestCrudView.VIEW_NAME, TestCrudView.class);
        springNavigator.addView(SplitAdminUserCrudView.VIEW_NAME, SplitAdminUserCrudView.class);

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);
        springNavigator.navigateTo(TestCrudView.VIEW_NAME);

        DynamicViewLoader<AdminUI> instance = dynamicViewLoaderObjectProvider.getIfAvailable();
        if(instance != null) instance.addExportedViews(springNavigator);
    }
}
