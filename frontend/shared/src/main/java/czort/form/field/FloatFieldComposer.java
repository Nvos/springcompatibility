package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;

public class FloatFieldComposer<MODEL> extends BaseFieldComposer<TextField, MODEL, Float, FloatFieldComposer<MODEL>> {
    public FloatFieldComposer(TextField textField, Binder.BindingBuilder<MODEL, Float> binding) {
        super(textField, binding);
    }
}