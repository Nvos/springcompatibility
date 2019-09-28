package czort.form.field;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

public class ControlledValidator<VALUE> implements Validator<VALUE> {
    private final Validator<VALUE> validator;
    private Boolean isEnabled = true;

    public ControlledValidator(Validator<VALUE> validator) {
        this.validator = validator;
    }

    public static <VALUE> ControlledValidator<VALUE> decorate(Validator<VALUE> validator) {
        return new ControlledValidator<>(validator);
    }

    @Override
    public ValidationResult apply(VALUE value, ValueContext context) {
        if (!isEnabled) return ValidationResult.ok();
        return validator.apply(value, context);
    }

    public ControlledValidator<VALUE> setEnabled(Boolean enabled) {
        isEnabled = enabled;
        return this;
    }
}
