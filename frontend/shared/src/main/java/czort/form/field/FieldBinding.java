package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FieldBinding<FIELD extends AbstractComponent, MODEL, BINDING> {
    protected final FIELD field;
    protected final Binder.BindingBuilder<MODEL, BINDING> bindingCreator;
    protected Binder.Binding<MODEL, BINDING> binding;
    protected List<ControlledValidator<BINDING>> validators = new ArrayList<>();

    public FieldBinding(FIELD field, Binder.BindingBuilder<MODEL, BINDING> bindingCreator) {
        this.field = field;
        this.bindingCreator = bindingCreator;
    }

    public Binder.BindingBuilder<MODEL, BINDING> getBindingCreator() {
        return bindingCreator;
    }

    public FIELD getField() {
        return field;
    }

    public FieldBinding<FIELD, MODEL, BINDING> useBinding(Consumer<Binder.Binding<MODEL, BINDING>> withProvidedBindingCreator) {
        withProvidedBindingCreator.accept(binding);
        return this;
    }

    public FieldBinding useBindingCreator(Consumer<Binder.BindingBuilder<MODEL, BINDING>> withProvidedBinding) {
        withProvidedBinding.accept(bindingCreator);
        return this;
    }

    public FieldBinding useField(Consumer<FIELD> withProvidedField) {
        withProvidedField.accept(field);
        return this;
    }

    public FieldBinding<FIELD, MODEL, BINDING> asRequired() {
        bindingCreator.asRequired();
        return this;
    }

    public FieldBinding<FIELD, MODEL, BINDING> withValidators(Validator<BINDING>... validators) {
        Arrays.stream(validators).forEach(this::withValidator);
        return this;
    }

    public FieldBinding<FIELD, MODEL, BINDING> withValidator(Validator<BINDING> validator) {
        ControlledValidator<BINDING> result = ControlledValidator.decorate(validator);
        validators.add(result);
        bindingCreator.withValidator(result);

        return this;
    }

    public FieldBinding<FIELD, MODEL, BINDING> withCaption(String caption) {
        field.setCaption(caption);

        return this;
    }

    public Binder.Binding<MODEL, BINDING> getBinding() {
        return binding;
    }

    public FieldBinding<FIELD, MODEL, BINDING> bind() {
        this.binding = bindingCreator.bind(field.getId());
        return this;
    }

    public FieldBinding<FIELD, MODEL, BINDING> setValidationEnabled(boolean isEnabled) {
        this.validators.forEach(it -> {
            it.setEnabled(isEnabled);
        });

        if (!isEnabled) {
            field.setComponentError(null);
        } else {
            binding.validate();
        }

        return this;
    }
}
