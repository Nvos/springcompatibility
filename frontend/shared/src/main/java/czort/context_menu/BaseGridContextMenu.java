package czort.context_menu;

import com.vaadin.contextmenu.GridContextMenu;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.ButtonRenderer;
import czort.util.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class BaseGridContextMenu<MODEL> extends GridContextMenu<MODEL> {
    private MODEL item;
    private Grid<MODEL> grid;

    public BaseGridContextMenu(Grid<MODEL> grid) {
        super(grid);
        this.grid = grid;

        addGridBodyContextMenuListener(event -> {
            item = event.getItem();
        });
    }

    public BaseGridContextMenu<MODEL> withAction(String caption, Consumer<MODEL> action) {
        addItem(caption, event -> action.accept(item));
        return this;
    }

    public BaseGridContextMenu<MODEL> withAction(String caption, Resource icon, Consumer<MODEL> action) {
        addItem(caption, icon, event -> action.accept(item));
        return this;
    }

    public BaseGridContextMenu<MODEL> withSeparator() {
        addSeparator();

        return this;
    }

    public BaseGridContextMenu<MODEL> withGridMenuButtonColumn() {
        Grid.Column<MODEL, Button> column = this.grid.addComponentColumn(this::createMenuButton);
        column.setWidth(64);
        column.setExpandRatio(0);
        column.setResizable(false);

        this.grid.setColumnOrder(column);

        return this;
    }

    private Button createMenuButton(MODEL model) {
        Button button = new Button(VaadinIcons.MENU, event -> {
            this.item = model;
            open(event.getClientX(), event.getClientY());
        });
        button.setWidth(32, Sizeable.Unit.PIXELS);

        return button;
    }
}
