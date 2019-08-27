package czort.view;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;

import java.util.*;

public class GridFilterComposer<MODEL> {
    private final Grid grid;
    private List<HasValue> filterFields;
    private Map<String, Object> filter;
    private ConfigurableFilterDataProvider<MODEL, Void, Map<String, Object>> filterDataProvider;
    private HeaderRow filterRow;

    public GridFilterComposer(Grid grid) {
        this.grid = grid;
        this.filterFields = new ArrayList<>();
        this.filter = new HashMap<>();
        this.filterDataProvider = this.grid.getDataProvider().withConfigurableFilter();
        this.filterRow = this.grid.appendHeaderRow();
    }

    public GridFilterComposer withHeaderTextFilter(String fieldName) {
        TextField field = new TextField();
        filterFields.add(field);

        return withFilter(fieldName, field);
    }

    public GridFilterComposer withDateFilter(String fieldName) {
        TextField field = new TextField();
        filterFields.add(field);

        return withFilter(fieldName, field);
    }

    public <T> GridFilterComposer withComboBoxFilter(String fieldName, ComboBox<T> comboBox) {
        filterFields.add(comboBox);

        return withFilter(fieldName, comboBox);
    }

    public GridFilterComposer withColumnHeaderTextFilter(String fieldName) {
        TextField field = new TextField();
        filterFields.add(field);

        return withFilter(fieldName, field).withColumnFilter(fieldName, field);
    }

    public GridFilterComposer withColumnDateFilter(String fieldName) {
        TextField field = new TextField();
        filterFields.add(field);

        return withFilter(fieldName, field);
    }

    public <T> GridFilterComposer withColumnComboBoxFilter(String fieldName, ComboBox<T> comboBox) {
        filterFields.add(comboBox);

        return withFilter(fieldName, comboBox);
    }

    public GridFilterComposer withDefaultFilters() {

        return this;
    }

    private <T> GridFilterComposer withColumnFilter(String fieldName, HasValue<T> field) {
        HeaderCell cell = filterRow.getCell(fieldName);
        Objects.requireNonNull(cell, "Failed to find column with fieldName: " + fieldName);

        cell.setComponent((Component) field);

        return this;
    }

    private GridFilterComposer withFilter(String fieldName, HasValue<?> field) {
        field.addValueChangeListener(value -> {
            this.filter.put(fieldName, value.getValue());
            this.filterDataProvider.refreshAll();
            this.grid.scrollToStart();
        });

        return this;
    }
}
