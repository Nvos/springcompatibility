package czort.form;

import com.vaadin.data.*;
import com.vaadin.server.UserError;
import com.vaadin.ui.Component;
import czort.form.field.FieldBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
