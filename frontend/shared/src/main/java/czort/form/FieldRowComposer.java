package czort.form;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.HorizontalLayout;
import czort.form.field.BaseFieldComposer;

public class FieldRowComposer<MODEL> extends FormComposer<MODEL, HorizontalLayout> {
    protected FieldRowComposer(StandardForm<MODEL> form) {
        super(defaultLayout(), form);
    }

    private static HorizontalLayout defaultLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setMargin(false);

        return layout;
    }

    public FieldRowComposer<MODEL> withCaption(String caption) {
        layout.setCaption(caption);
        return this;
    }

    @Override
    protected <FIELD extends AbstractComponent, BINDING, RETURN extends BaseFieldComposer<FIELD, MODEL, BINDING, RETURN>> RETURN withFieldBindingDefaults(String fieldName, RETURN fieldBinding) {
        RETURN composer = super.withFieldBindingDefaults(fieldName, fieldBinding);
        composer.withCaption(null);
        return composer;
    }
}
