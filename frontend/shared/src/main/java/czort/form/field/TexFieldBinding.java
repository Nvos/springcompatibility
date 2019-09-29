package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.TextField;

public class TexFieldBinding<MODEL> extends FieldBinding<TextField, MODEL, String, TexFieldBinding<MODEL>> {
    public TexFieldBinding(TextField textField, Binder.BindingBuilder<MODEL, String> binding) {
        super(textField, binding);
    }

    public TexFieldBinding<MODEL> withMaxLength(int maxLength) {
        field.setMaxLength(maxLength);
        return this;
    }

    public TexFieldBinding<MODEL> withWidth(int width) {
        field.setWidth(width, Sizeable.Unit.PIXELS);
        return this;
    }

    public TexFieldBinding<MODEL> withReadOnly(boolean isReadOnly) {
        field.setReadOnly(isReadOnly);
        return this;
    }
}
