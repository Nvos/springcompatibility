package czort.form;

import com.vaadin.data.Binder;

public class FormBinder<MODEL> extends Binder<MODEL> {
    public FormBinder(Class<MODEL> beanType) {
        super(beanType);
    }
}
