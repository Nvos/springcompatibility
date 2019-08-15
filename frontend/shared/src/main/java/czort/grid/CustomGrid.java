package czort.grid;

import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.AbstractRenderer;

public class CustomGrid<T> extends Grid<T> {
    public CustomGrid<T> all(Class<T> beanType) {
        czort.grid.Column.all(beanType).forEach(it -> addColumn(
                it.getDefinition().getGetter(),
                it.getRenderer()
        ));

        return this;
    }
}
