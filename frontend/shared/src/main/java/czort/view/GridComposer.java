package czort.view;

import com.vaadin.contextmenu.GridContextMenu;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;
import czort.grid.BaseGrid;

import java.util.function.Consumer;

public class GridComposer<MODEL> {
    private Grid<MODEL> grid;

    public GridComposer(Grid<MODEL> grid) {
        this.grid = grid;
    }

    public GridComposer<MODEL> withGridRef(Consumer<BaseGrid<MODEL>> withProvidedGrid) {
        if (!(this.grid instanceof BaseGrid))
            throw new UnsupportedOperationException("'withGridRef' can be called only when provided instance of " +
                    "BaseGrid");

        withProvidedGrid.accept((BaseGrid<MODEL>) this.grid);

        return this;
    }

    public GridComposer<MODEL> withTreeGridRef(Consumer<TreeGrid<MODEL>> withProvidedGrid) {
        if (!(this.grid instanceof TreeGrid))
            throw new UnsupportedOperationException("'withGridRef' can be called only when provided instance of " +
                    "TreeGrid");

        withProvidedGrid.accept((TreeGrid<MODEL>) this.grid);

        return this;
    }

    public GridComposer<MODEL> withActionButton() {
        Button button = new Button(VaadinIcons.MENU);
        this.grid.addComponentColumn(model -> button);

        return this;
    }

    public GridComposer<MODEL> withContextMenu(Consumer<GridContextMenu<MODEL>> withProvidedContextMenu) {
        GridContextMenu<MODEL> contextMenu = new GridContextMenu<>(this.grid);
        withProvidedContextMenu.accept(contextMenu);

        return this;
    }

    public GridComposer<MODEL> withDataProvider(DataProvider dataProvider) {
        this.grid.setDataProvider(dataProvider);

        return this;
    }

    public GridComposer<MODEL> withFilters(Consumer<GridFilterComposer> withGridFilterComposer) {
        GridFilterComposer<MODEL> composer = new GridFilterComposer<>(this.grid);
        withGridFilterComposer.accept(composer);

        return this;
    }

    public GridComposer<MODEL> withRowDoubleClickHandler(Consumer<MODEL> withProvidedSelectedItem) {
        grid.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                MODEL item = event.getItem();
                withProvidedSelectedItem.accept(item);
            }
        });

        return this;
    }
}
