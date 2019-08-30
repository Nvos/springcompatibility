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
import czort.mvp.Presenter;
import czort.util.ComponentUtils;
import czort.view.GridComposer;
import czort.view.SectionComposer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@ViewScope
@SpringComponent
public class CrudViewFragment<MODEL, CREATE, UPDATE> extends VerticalLayout {
    private Grid<MODEL> grid;
    private CrudPresenter<MODEL, CREATE, UPDATE> presenter;
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
                    ref.withAcceptButton();
                    ref.withCancelButton();
                })
                .onResultChange(result -> result
                        .onAccept(value -> {
                            presenter.update(id, value);
                            getDataProvider().refreshAll();

                            updateDialog.closeWithoutPrompt();
                        })
                        .onCancel(cancelResult -> {
                            updateDialog.close();
                        })
                )
                .open();
    }

    public FormDialog<CREATE> openCreateDialog(CREATE params) {
        return createDialog
                .withInitialValue(params)
                .useFooterComponent(ref -> {
                    ref.withAcceptButton();
                    ref.withCancelButton();
                })
                .open();
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withSection(Consumer<SectionComposer<MODEL>> withProvidedSectionComposer) {
        SectionComposer<MODEL> composer = new SectionComposer<>(this);

        withProvidedSectionComposer.accept(composer);
        addComponent(composer);

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

    public CrudViewFragment<MODEL, CREATE, UPDATE> withTranslations() {
        ComponentUtils.iterateFromRoot(this,
                component -> System.out.println(component.getId() + ", " + component.getCaption()));

        this.grid.getColumns()
                .forEach(component -> {
                    System.out.println(component.getId() + ", " + component.getCaption());
                });

        return this;
    }

    public DataProvider getDataProvider() {
        if (this.grid != null) return this.grid.getDataProvider();

        throw new UnsupportedOperationException("Before calling 'refreshGrid' create Grid");
    }

    public Grid<MODEL> getGrid() {
        return this.grid;
    }
}
