package czort.form.field;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class BaseFieldComposer<
        FIELD extends AbstractComponent,
        MODEL,
        BINDING,
        RETURN extends BaseFieldComposer<FIELD, MODEL, BINDING, RETURN>>
{
    protected final FIELD field;
    protected final Binder.BindingBuilder<MODEL, BINDING> bindingCreator;
    protected Binder.Binding<MODEL, BINDING> binding;
    protected List<ControlledValidator<BINDING>> validators = new ArrayList<>();

    public BaseFieldComposer(FIELD field, Binder.BindingBuilder<MODEL, BINDING> bindingCreator) {
        this.field = field;
        this.bindingCreator = bindingCreator;
    }

    public Binder.BindingBuilder<MODEL, BINDING> getBindingCreator() {
        return bindingCreator;
    }

    public FIELD getField() {
        return field;
    }

    @SuppressWarnings("unchecked")
    public RETURN useBinding(Consumer<Binder.Binding<MODEL, BINDING>> withProvidedBindingCreator) {
        withProvidedBindingCreator.accept(binding);
        return (RETURN) this;
    }

    @SuppressWarnings("unchecked")
    public RETURN useBindingCreator(Consumer<Binder.BindingBuilder<MODEL, BINDING>> withProvidedBinding) {
        withProvidedBinding.accept(bindingCreator);
        return (RETURN) this;
    }

    @SuppressWarnings("unchecked")
    public RETURN useField(Consumer<FIELD> withProvidedField) {
        withProvidedField.accept(field);
        return (RETURN) this;
    }

    @SuppressWarnings("unchecked")
    public RETURN asRequired() {
        bindingCreator.asRequired();
        return (RETURN) this;
    }

    @SuppressWarnings("unchecked")
    public RETURN withValidators(Validator<BINDING>... validators) {
        Arrays.stream(validators).forEach(this::withValidator);
        return (RETURN) this;
    }

    @SuppressWarnings("unchecked")
    public RETURN withValidator(Validator<BINDING> validator) {
        ControlledValidator<BINDING> result = ControlledValidator.decorate(validator);
        validators.add(result);
        bindingCreator.withValidator(result);

        return (RETURN) this;
    }

    @SuppressWarnings("unchecked")
    public RETURN withCaption(String caption) {
        field.setCaption(caption);
        return (RETURN) this;
    }

    @SuppressWarnings("unchecked")
    public RETURN withWidth(int width) {
        field.setWidth(width, Sizeable.Unit.PIXELS);
        return (RETURN) this;
    }


    public Binder.Binding<MODEL, BINDING> getBinding() {
        return binding;
    }

    @SuppressWarnings("unchecked")
    public RETURN bind() {
        if (binding == null && bindingCreator != null) {
            this.binding = bindingCreator.bind(field.getId());
        }

        return (RETURN) this;
    }

    @SuppressWarnings("unchecked")
    public RETURN withValidationEnabled(boolean isEnabled) {
        this.validators.forEach(it -> {
            it.setEnabled(isEnabled);
        });

        if (!isEnabled) {
            field.setComponentError(null);
        } else {
            binding.validate();
        }

        return (RETURN) this;
    }
}
