package czort.form.field;

import com.vaadin.ui.Label;

public class LabelComposer<MODEL> extends BaseFieldComposer<Label, MODEL, Void, LabelComposer<MODEL>> {
    public LabelComposer(Label label) {
        super(label, null);
    }
}
