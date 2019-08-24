package czort.crud;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import czort.dialog.ActionDialog;
import czort.dialog.FormDialog;
import czort.grid.BaseGrid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.PropertyAccessor;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.function.Consumer;

@ViewScope
@SpringComponent
public class CrudViewFragment<MODEL, CREATE, UPDATE> extends VerticalLayout implements CrudContract.View<MODEL, CREATE, UPDATE> {

    private final BaseGrid<MODEL> grid;
    private final CrudPresenter<MODEL, CREATE, UPDATE> presenter;
    private final ActionDialog actionDialog;
    private FormDialog<CREATE> createDialog;
    private FormDialog<UPDATE> updateDialog;

    public CrudViewFragment(
            BaseGrid<MODEL> grid,
            CrudPresenter<MODEL, CREATE, UPDATE> presenter,
            ActionDialog actionDialog
    ) {
        this.grid = grid;
        this.presenter = presenter;
        this.actionDialog = actionDialog;

        presenter.bootstrap(this);
    }

    @Override
    public FormDialog<UPDATE> openUpdateDialog(Long id, UPDATE params) {
        return updateDialog
                .withInitialValue(params)
                .useFooterComponent(ref -> {
                    ref.withAcceptButton(event -> {
                        presenter.update(id, (UPDATE) event);
                        getGrid().getDataProvider().refreshAll();
                    });
                    ref.withCancelButton();
                })
                .open();
    }

    @Override
    public FormDialog<CREATE> openCreateDialog(CREATE params) {
        return createDialog
                .withInitialValue(params)
                .useFooterComponent(ref -> {
                    ref.withAcceptButton(event -> {
                        presenter.create((CREATE) event);
                        getGrid().getDataProvider().refreshAll();
                    });
                    ref.withCancelButton();
                })
                .open();
    }

    @Override
    public BaseGrid<MODEL> getGrid() {
        return grid;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withSection() {
        HorizontalLayout layout = new HorizontalLayout();
        Button createButton = new Button("Create", event -> presenter.handleCreate());

        layout.addComponents(createButton);

        addComponent(layout);

        return this;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withGrid(
            Consumer<BaseGrid<MODEL>> withProvidedGrid
    ) {
        withProvidedGrid.accept(this.grid);
        addComponent(grid);

        return this;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withGridDataProvider(
            AbstractDataProvider<MODEL, Map<String, String>> dataProvider
    ) {
        grid.setDataProvider(dataProvider);

        return this;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withGridEdit(ValueProvider<MODEL, Long> idProvider) {
        grid.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                MODEL item = event.getItem();
                presenter.handleEdit(idProvider.apply(item));
            }
        });

        return this;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withTypes(
            Class<MODEL> modelType,
            Class<CREATE> createType,
            Class<UPDATE> updateType
    ) {
        presenter.setTypes(modelType, createType, updateType);

        return this;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withCreateDialog(FormDialog<CREATE> createDialog) {
        this.createDialog = createDialog;

        return this;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withUpdateDialog(FormDialog<UPDATE> updateDialog) {
        this.updateDialog = updateDialog;

        return this;
    }
}
