package czort;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.UI;

@Title("User")
@SpringUI(path = "user")
public class UserUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        System.out.println("init?");
    }
}
