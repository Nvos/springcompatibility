package czort.form;

import com.vaadin.data.Binder;

public class FormBinder<MODEL> extends Binder<MODEL> {
    private boolean isDirty = false;

    public FormBinder(Class<MODEL> beanType) {
        super(beanType);

        this.addValueChangeListener(event -> isDirty = true);
    }

    public boolean isDirty() {
        return isDirty;
    }
}
