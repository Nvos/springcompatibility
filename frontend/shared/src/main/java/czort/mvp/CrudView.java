package czort.mvp;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.function.Consumer;

public class CrudView<MODEL> extends VerticalLayout {
    private Grid<MODEL> grid;

    public CrudView<MODEL> withGrid(
            Class<MODEL> modelClass,
            PageableDataProvider<MODEL, MODEL> dataProvider,
            Consumer<Grid<MODEL>> withProvidedGrid
    ) {
        grid = new Grid<>(modelClass);
        grid.setDataProvider(dataProvider);

        withProvidedGrid.accept(grid);
        addComponent(grid);

        return this;
    }
}
