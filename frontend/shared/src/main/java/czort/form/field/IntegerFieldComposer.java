package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class IntegerFieldComposer<MODEL> extends BaseFieldComposer<TextField, MODEL, Integer, IntegerFieldComposer<MODEL>> {
    public IntegerFieldComposer(TextField textField, Binder.BindingBuilder<MODEL, Integer> binding) {
        super(textField, binding);
    }
}
