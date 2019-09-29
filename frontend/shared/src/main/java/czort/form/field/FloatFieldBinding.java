package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class FloatFieldBinding<MODEL> extends FieldBinding<TextField, MODEL, Float, FloatFieldBinding<MODEL>> {
    public FloatFieldBinding(TextField textField, Binder.BindingBuilder<MODEL, Float> binding) {
        super(textField, binding);
    }
}