package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

import java.util.List;

public class GridFieldBinding<MODEL, VALUE>
        extends FieldBinding<GridField<VALUE>, MODEL, List<VALUE>, GridFieldBinding<MODEL, VALUE>> {

    public GridFieldBinding(
            GridField<VALUE> components,
            Binder.BindingBuilder<MODEL, List<VALUE>> bindingCreator
    ) {
        super(components, bindingCreator);
    }
}
