package czort.form.field;

import com.vaadin.data.Binder;

import java.util.List;

public class GridFieldComposer<MODEL, VALUE>
        extends BaseFieldComposer<GridField<VALUE>, MODEL, List<VALUE>, GridFieldComposer<MODEL, VALUE>> {

    public GridFieldComposer(
            GridField<VALUE> components,
            Binder.BindingBuilder<MODEL, List<VALUE>> bindingCreator
    ) {
        super(components, bindingCreator);
    }
}
