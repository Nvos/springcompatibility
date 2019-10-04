package czort.form.field;

import com.vaadin.ui.AbstractComponent;

public class ComponentComposer<MODEL> extends BaseFieldComposer<AbstractComponent, MODEL, Void, ComponentComposer<MODEL>> {
    public ComponentComposer(AbstractComponent component) {
        super(component, null);
    }
}
