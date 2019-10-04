package czort.view;

import com.vaadin.contextmenu.GridContextMenu;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;
import czort.context_menu.BaseGridContextMenu;
import czort.crud.CrudPresenter;
import czort.grid.BaseGrid;

import java.util.Objects;
import java.util.function.Consumer;

public class GridComposer<MODEL> {
    private final Grid<MODEL> grid;
    private CrudPresenter<MODEL, ?, ?> presenter;

    public GridComposer(Grid<MODEL> grid) {
        this.grid = grid;
    }
    public GridComposer(Grid<MODEL> grid, CrudPresenter<MODEL, ?, ?> presenter) {
        this.grid = grid;
        this.presenter = presenter;
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

    public GridComposer<MODEL> withDefaultDataProvider() {
        Objects.requireNonNull(presenter, "Presenter cannot be null");
        this.grid.setDataProvider(presenter.getDataProvider());
        return this;
    }

    public GridComposer<MODEL> withDataProvider(DataProvider dataProvider) {
        this.grid.setDataProvider(dataProvider);

        return this;
    }

    public GridComposer<MODEL> withContextMenu(Consumer<BaseGridContextMenu<MODEL>> withProvidedGridContextMenu) {
        BaseGridContextMenu<MODEL> contextMenu = new BaseGridContextMenu<>(this.grid);
        withProvidedGridContextMenu.accept(contextMenu);

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
