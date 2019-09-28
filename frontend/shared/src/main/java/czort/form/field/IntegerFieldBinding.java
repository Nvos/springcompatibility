package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class IntegerFieldBinding<MODEL> extends FieldBinding<TextField, MODEL, Integer> {
    public IntegerFieldBinding(TextField textField, Binder.BindingBuilder<MODEL, Integer> binding) {
        super(textField, binding);
    }
}
