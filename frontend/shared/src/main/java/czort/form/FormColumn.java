package czort.form;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FormColumn<MODEL> extends FormLayout {
    private final StandardForm<MODEL> parent;
    private final List<FieldBinding<Component, Object>> bindings = new ArrayList<>();

    public FormColumn(StandardForm<MODEL> parent) {
        this.parent = parent;
    }

    public FieldBinding<TextField, String> withTextField(String property) {
        TextField field = new TextField();
        field.setId(property);
        field.setCaption(property);

        Binder.BindingBuilder<MODEL, String> builder = parent.getBinder()
                .forField(field)
                .withNullRepresentation("");

        FieldBinding fieldBinding = new FieldBinding(builder, field);
        bindings.add(fieldBinding);

        return fieldBinding;
    }

    public FieldBinding<TextField, Integer> withIntegerField(String property) {
        return withTextField(property).asIntegerField();
    }

    public FieldBinding<TextField, Long> withLongField(String property) {
        return withTextField(property).asLongField();
    }

    public FieldBinding<TextField, String> withLocalDateField(String property) {
        DateField field = new DateField();
        field.setId(property);
        field.setCaption(property);

        Binder.BindingBuilder<MODEL, LocalDate> builder = parent.getBinder().forField(field);
        FieldBinding fieldBinding = new FieldBinding(builder, field);
        bindings.add(fieldBinding);

        return fieldBinding;
    }

    public Label withLabel(String caption, String value) {
        Label label = new Label(value);
        label.setCaption(caption);

        FieldBinding fieldBinding = new FieldBinding(null, label);
        bindings.add(fieldBinding);

        return label;
    }

    public StandardForm<MODEL> createColumn() {
        this.bindings.forEach(it -> {
            String property = it.field.getId();
            addComponent(it.field);

            if(it.binding != null)
                it.binding.bind(property);
        });

        return parent;
    }

    public List<FieldBinding<Component, Object>> getBindings() {
        return bindings;
    }

    private UserError toUserError(ValidationResult vr) {
        return new UserError(vr.getErrorMessage());
    }

    public class FieldBinding<FIELD, BINDING> {
        private final Binder.BindingBuilder<MODEL, BINDING> binding;
        private final FIELD field;

        public FieldBinding(Binder.BindingBuilder<MODEL, BINDING> binding, FIELD field) {
            this.binding = binding;
            this.field = field;
        }

        public FieldBinding<FIELD, BINDING> useBinding(Consumer<Binder.BindingBuilder<MODEL, BINDING>> withProvidedBinding) {
            withProvidedBinding.accept(binding);

            return this;
        }

        public FieldBinding<FIELD, BINDING> useField(Consumer<FIELD> withProvidedField) {
            withProvidedField.accept(field);

            return this;
        }

        public FieldBinding<FIELD, Long> asLongField() {
            if (!(field instanceof TextField)) throw new UnsupportedOperationException("Method can be only called on " +
                    "TextField");

            ((Binder.BindingBuilder<MODEL, String>) binding)
                    .withConverter(new StringToLongConverter("Value has to be long"))
                    .withNullRepresentation(0L);

            return (FieldBinding<FIELD, Long>) this;
        }

        public FieldBinding<FIELD, Integer> asIntegerField() {
            if (!(field instanceof TextField)) throw new UnsupportedOperationException("Method can be only called on " +
                    "TextField");

            ((Binder.BindingBuilder<MODEL, String>) binding)
                    .withConverter(new StringToIntegerConverter("Value has to be integer"))
                    .withNullRepresentation(0);

            return (FieldBinding<FIELD, Integer>) this;
        }

        public Binder.BindingBuilder<MODEL, BINDING> getBinding() {
            return binding;
        }

        public FIELD getField() {
            return field;
        }
    }
}
