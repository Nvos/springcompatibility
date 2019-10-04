package czort.form;

import com.vaadin.data.*;

import java.util.ArrayList;
import java.util.List;

public class FormBinder<MODEL> extends Binder<MODEL> {
    private boolean isDirty = false;
    private List<BindingValidationStatus<?>> errors = new ArrayList<>();

    public FormBinder(Class<MODEL> beanType) {
        super(beanType);

        this.addValueChangeListener(event -> isDirty = true);
    }

    public boolean isDirty() {
        return isDirty;
    }

    public FormBinder<MODEL> withDefaults() {
        setValidationStatusHandler(status -> {
            errors = status.getFieldValidationErrors();
            getValidationStatusHandler().statusChange(status);
        });

        return this;
    }

    public boolean hasErrors() {
        return errors.isEmpty();
    }

}
