package czort.crud;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import czort.dialog.FormDialog;
import czort.grid.BaseGrid;
import czort.mvp.Presenter;
import czort.util.ComponentUtils;
import czort.view.GridComposer;
import czort.view.SectionComposer;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
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

    public static  <MODEL, CREATE, UPDATE> CrudViewFragment<MODEL, CREATE, UPDATE> instance(CrudPresenter<MODEL, CREATE,
            UPDATE> presenter) {
        CrudViewFragment<MODEL, CREATE, UPDATE> instance = new CrudViewFragment<MODEL, CREATE, UPDATE>(presenter) {};

        String className =
                ((ParameterizedType) instance.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();

        System.out.println(className);

        return instance;
    }

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
                .withResultHandler(System.out::println)
                .open();
    }

    public FormDialog<CREATE> openCreateDialog(CREATE params) {
        return createDialog
                .withInitialValue(params)
                .withResultHandler(System.out::println)
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
        this.grid.setSizeFull();
        GridComposer<MODEL> composer = new GridComposer<>(this.grid, this.presenter);

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
        this.createDialog = (FormDialog<CREATE>) createDialog;

        return this;
    }

    public CrudViewFragment<MODEL, CREATE, UPDATE> withUpdateDialog(FormDialog<UPDATE> updateDialog) {
        this.updateDialog = (FormDialog<UPDATE>) updateDialog;

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
