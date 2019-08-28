package czort.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import czort.crud.CrudViewFragment;
import czort.util.Action;

import java.util.function.Consumer;

public class SectionComposer<MODEL> extends HorizontalLayout {
    private final CrudViewFragment crudViewFragment;
    private final HorizontalLayout leftWrapper;
    private final HorizontalLayout rightWrapper;

    public SectionComposer(CrudViewFragment crudViewFragment) {
        this.crudViewFragment = crudViewFragment;

        // TODO: Style with flex + space-between
        setStyleName("flex");

        rightWrapper = new HorizontalLayout();
        leftWrapper = new HorizontalLayout();

        addComponents(leftWrapper, rightWrapper);
    }

    public SectionComposer<MODEL> addLeft(Component component) {
        this.leftWrapper.addComponent(component);

        return this;
    }

    public SectionComposer<MODEL> addRight(Component component) {
        this.rightWrapper.addComponent(component);

        return this;
    }

    public SectionComposer<MODEL> withButtonFilterToggle() {
        Button button = new Button(VaadinIcons.FILTER , event -> {
            // TODO: Should select second row (index = 1)
            boolean isHeaderVisible = this.getGrid().isHeaderVisible();
            this.getGrid().setHeaderVisible(!isHeaderVisible);
        });

        return addLeft(button);
    }

    public SectionComposer<MODEL> withButtonCreate(Action action) {
        Button button = new Button("Create", event -> action.execute());

        return addLeft(button);
    }

    private Grid getGrid() {
        return this.crudViewFragment.getGrid();
    }
}
