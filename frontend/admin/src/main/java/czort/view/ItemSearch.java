package czort.view;

import com.google.common.collect.Lists;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;
import czort.grid.BaseGrid;

public class ItemSearch extends CustomField<String> {

    private TextField searchField;
    private BaseGrid<String> grid;
    private CssLayout content;

    @Override
    protected Component initContent() {
        CssLayout layout = new CssLayout();
        layout.setStyleName("wrapper");
        searchField = new TextField();
        searchField.setStyleName("input");
        content = new CssLayout();
        content.setStyleName("content");
        grid = new BaseGrid<>();
        grid.setHeightByRows(5.0);
        grid.setItems(Lists.newArrayList("Value 1", "Value 2", "Value 3", "value 4", "value 5", "value 6", "value 7"));

        grid.addColumn(item -> item).setCaption("WTF");

        layout.addComponent(searchField);
        layout.addComponent(content);
        content.addComponents(grid);

        return layout;
    }

    @Override
    protected void doSetValue(String value) {

    }

    @Override
    public String getValue() {
        return null;
    }
}
