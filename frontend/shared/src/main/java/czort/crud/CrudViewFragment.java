package czort.crud;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import czort.dialog.ActionDialog;
import czort.dialog.FormDialog;
import czort.grid.BaseGrid;
import czort.view.GridComposer;

import java.util.Map;
import java.util.function.Consumer;

public class CrudViewFragment<MODEL, CREATE, UPDATE> extends VerticalLayout {
    private Grid<MODEL> grid;
    private CrudContract.Presenter<MODEL, CREATE, UPDATE> presenter;
    private FormDialog<CREATE> createDialog;
    private FormDialog<UPDATE> updateDialog;

    public CrudViewFragment(
            CrudPresenter<MODEL, CREATE, UPDATE> presenter
    ) {
        this.presenter = presenter;

        presenter.bootstrap(this);
        setSizeFull();
    }

    public FormDialog<UPDATE> openUpdateDialog(Long id, UPDATE params) {
        return updateDialog
                .withInitialValue(params)
                .useFooterComponent(ref -> {
                    ref.withAcceptButton(event -> {
                        presenter.update(id, (UPDATE) event);
                        getDataProvider().refreshAll();
                    });
                    ref.withCancelButton();
                })
                .open();
    }

    public FormDialog<CREATE> openCreateDialog(CREATE params) {
        return createDialog
                .withInitialValue(params)
                .useFooterComponent(ref -> {
                    ref.withAcceptButton(event -> {
                        presenter.create((CREATE) event);
                        getDataProvider().refreshAll();
                    });
                    ref.withCancelButton();
                })
                .open();
    }

    // TODO: Add section composer
    public CrudViewFragment<MODEL, CREATE, UPDATE> withSection(Consumer<HorizontalLayout> withLayout) {
        HorizontalLayout layout = new HorizontalLayout();
        Button createButton = new Button("Create", event -> presenter.handleCreate());

        layout.addComponents(createButton);

        withLayout.accept(layout);
        addComponent(layout);

        return this;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withGrid(
            Consumer<GridComposer<MODEL>> withProvidedGridComposer
    ) {
        if (this.grid != null) this.removeComponent(this.grid);

        this.grid = new BaseGrid<>();
        GridComposer<MODEL> composer = new GridComposer<>(this.grid);

        withProvidedGridComposer.accept(composer);
        addComponentsAndExpand(grid);

        return this;
    }

    // TODO: Research tree grid provider and backend requirements
    public CrudViewFragment<MODEL, CREATE, UPDATE> withTreeGrid(
            Consumer<GridComposer<MODEL>> withProvidedTreeGridComposer
    ) {
        if (this.grid != null) this.removeComponent(this.grid);

        this.grid = new TreeGrid<>();
        GridComposer<MODEL> composer = new GridComposer<>(this.grid);

        withProvidedTreeGridComposer.accept(composer);
        addComponent(this.grid);

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

    private DataProvider getDataProvider() {
        if (this.grid != null) return this.grid.getDataProvider();

        throw new UnsupportedOperationException("Before calling 'refreshGrid' create Gridc");
    }
}
