package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.TextField;

public class TextFieldComposer<MODEL> extends BaseFieldComposer<TextField, MODEL, String, TextFieldComposer<MODEL>> {
    public TextFieldComposer(TextField textField, Binder.BindingBuilder<MODEL, String> binding) {
        super(textField, binding);
    }

    public TextFieldComposer<MODEL> withMaxLength(int maxLength) {
        field.setMaxLength(maxLength);
        return this;
    }

    public TextFieldComposer<MODEL> withWidth(int width) {
        field.setWidth(width, Sizeable.Unit.PIXELS);
        return this;
    }

    public TextFieldComposer<MODEL> withReadOnly(boolean isReadOnly) {
        field.setReadOnly(isReadOnly);
        return this;
    }
}
