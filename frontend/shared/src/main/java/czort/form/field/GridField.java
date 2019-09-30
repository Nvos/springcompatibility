package czort.form.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import czort.grid.BaseGrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GridField<VALUE> extends CustomField<List<VALUE>> {

    private final BaseGrid<VALUE> grid;
    private List<VALUE> items = new ArrayList<>();

    public GridField(BaseGrid<VALUE> grid) {
        this.grid = grid;
    }

    @Override
    protected Component initContent() {
        return grid;
    }

    @Override
    protected void doSetValue(List<VALUE> value) {
        Objects.requireNonNull(value, "Items provided to GridField cannot be null");
        items = value;
        grid.setItems(value);
    }

    @Override
    public List<VALUE> getValue() {
        return items;
    }
}
