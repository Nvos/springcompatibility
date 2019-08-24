package czort.mvp;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import czort.grid.BaseGrid;

import java.util.function.Consumer;

public class CrudView<MODEL> extends VerticalLayout {

    public CrudView<MODEL> withGrid(BaseGrid<MODEL> grid, Consumer<BaseGrid<MODEL>> withProvidedGrid) {
        addComponent(grid);
        withProvidedGrid.accept(grid);

        return this;
    }
}
