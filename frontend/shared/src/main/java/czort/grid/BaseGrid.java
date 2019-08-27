package czort.grid;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderRow;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BaseGrid<T> extends Grid<T> {
    private final Map<String, String> filter;

    public BaseGrid() {
        this.filter = new HashMap<>();
        this.setHeightMode(HeightMode.CSS);
        this.setWidth(100, Unit.PERCENTAGE);
        this.setHeightUndefined();
    }

    public BaseGrid<T> withFilter() {
        ConfigurableFilterDataProvider<T, Void, Map<String, String>> dataProvider =
                (ConfigurableFilterDataProvider<T, Void, Map<String, String>>)
                        this.getDataProvider().withConfigurableFilter();
        setDataProvider(dataProvider);

        HeaderRow filterRow = appendHeaderRow();

        for (Column<T, ?> column : this.getColumns()) {
            TextField field = createDefaultFilter();
            filterRow.getCell(column).setComponent(field);
            field.addValueChangeListener(it -> {
                filter.put(column.getId(), it.getValue());
                dataProvider.setFilter(filter);
            });
        }

        return this;
    }

    private TextField createDefaultFilter() {
        TextField field = new TextField();

        return field;
    }
}
