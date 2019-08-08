package czort;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import czort.menu.MainMenu;

@UIScope
public class RootView extends VerticalLayout implements ViewDisplay  {

    private final MainMenu mainMenu;

    public RootView(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void showView(View view) {

    }
}
