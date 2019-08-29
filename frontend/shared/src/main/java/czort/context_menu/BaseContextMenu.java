package czort.context_menu;

import com.vaadin.contextmenu.ContextMenu;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractComponent;
import czort.util.Action;

public class BaseContextMenu extends ContextMenu {

    public BaseContextMenu(AbstractComponent parentComponent) {
        super(parentComponent, true);
    }

    public BaseContextMenu withAction(String caption, Action action) {
        addItem(caption, event -> action.execute());
        return this;
    }

    public BaseContextMenu withAction(String caption, Resource icon, Action action) {
        addItem(caption, icon, event -> action.execute());
        return this;
    }
}
