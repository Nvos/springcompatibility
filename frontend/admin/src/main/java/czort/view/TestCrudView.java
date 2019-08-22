package czort.view;

import com.vaadin.data.Binder;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderRow;
import czort.mvp.BaseView;
import czort.mvp.CrudView;
import org.bouncycastle.math.raw.Mod;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;
import org.vaadin.spring.events.EventBus;

@ViewScope
@SpringView(name = TestCrudView.VIEW_NAME)
public class TestCrudView extends BaseView<TestCrudPresenter, CrudView<Model>> {
    public static final String VIEW_NAME = "TestCrudView";

    private final Model filter = new Model();
    private final PageableDataProvider<Model, Model> modelProvider;

    protected TestCrudView(TestCrudPresenter presenter, EventBus.ViewEventBus viewEventBus, ModelProvider modelProvider) {
        super(presenter, viewEventBus);
        this.modelProvider = modelProvider;
    }

    @Override
    public CrudView<Model> rootComponent() {
        return new CrudView<Model>()
                .withGrid(Model.class, modelProvider, grid -> {
                    ConfigurableFilterDataProvider<Model, Void, Model> filterDataProvider = modelProvider
                            .withConfigurableFilter();
                    grid.setDataProvider(filterDataProvider);
                    grid.setWidth("600px");
                    grid.setHeight("600px");

                    filterDataProvider.setFilter(filter);

                    TextField nameFilter = new TextField();
                    nameFilter.setValueChangeMode(ValueChangeMode.TIMEOUT);
                    nameFilter.addValueChangeListener(event -> {
                        filter.setName(event.getValue());
                        filterDataProvider.setFilter(filter);
                        filterDataProvider.refreshAll();
                    });

                    HeaderRow headerRow = grid.appendHeaderRow();

//                    grid.addColumn("").getRenderer();

                    headerRow.getCell("name")

                            .setComponent(nameFilter);
                });
    }
}
