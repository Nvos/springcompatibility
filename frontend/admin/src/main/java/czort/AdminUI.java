package czort;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.VerticalLayout;
import czort.view.UserView;

@Title("Admin")
@Theme("standard")
@SpringUI(path = "admin")
@SpringViewDisplay
public class AdminUI extends RootUI {

    protected final SpringNavigator springNavigator;

    public AdminUI(
            SpringNavigator springNavigator
    ) {
        this.springNavigator = springNavigator;
    }

    @Override
    protected void init(VaadinRequest request) {
//        springNavigator.addView(UserView.VIEW_NAME, UserView.class);

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);

        springNavigator.navigateTo(UserView.VIEW_NAME);
    }
}
