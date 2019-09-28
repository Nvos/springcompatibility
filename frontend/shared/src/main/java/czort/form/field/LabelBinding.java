package czort.form.field;

import com.vaadin.ui.Label;

public class LabelBinding<MODEL> extends FieldBinding<Label, MODEL, Void> {
    public LabelBinding(Label label) {
        super(label, null);
    }
}
