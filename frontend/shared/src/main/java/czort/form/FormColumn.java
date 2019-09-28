package czort.form;

import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import czort.form.field.FieldBinding;
import czort.form.field.IntegerFieldBinding;
import czort.form.field.LabelBinding;
import czort.form.field.TexFieldBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FormColumn<MODEL> extends FormLayout {
    private final StandardForm<MODEL> parent;

    public FormColumn(StandardForm<MODEL> parent) {
        this.parent = parent;
    }

    public Component withComponent(AbstractComponent component) {
        FieldBinding fieldBinding = new FieldBinding<>(component, null);
        withFieldBinding(fieldBinding);

        return component;
    }

    public TexFieldBinding<MODEL> withTextField(String property) {
        TextField field = defaultTextField(property);
        Binder.BindingBuilder<MODEL, String> builder = defaultTextBinding(field);

        TexFieldBinding<MODEL> fieldBinding = new TexFieldBinding<>(field, builder);
        withFieldBinding(fieldBinding);

        return fieldBinding;
    }

    public IntegerFieldBinding<MODEL> withIntegerField(String property) {
        TextField field = defaultTextField(property);
        Binder.BindingBuilder<MODEL, Integer> builder = defaultTextBinding(field)
                .withConverter(new StringToIntegerConverter("err"));

        IntegerFieldBinding<MODEL> fieldBinding = new IntegerFieldBinding<>(field, builder);
        withFieldBinding(fieldBinding);

        return fieldBinding;
    }

    public Label withLabel(String token, String value) {
        Label label = new Label(value);
        label.setId(token);
        label.setCaption(token);

        LabelBinding<MODEL> fieldBinding = new LabelBinding<>(label);
        withFieldBinding(fieldBinding);

        return label;
    }

//    public FieldBinding<TextField, Long> withLongField(String property) {
//        return withTextField(property).asLongField();
//    }
//
//    public FieldBinding<TextField, String> withLocalDateField(String property) {
//        DateField field = new DateField();
//        field.setId(property);
//        field.setCaption(property);
//
//        Binder.BindingBuilder<MODEL, LocalDate> builder = parent.getBinder().forField(field);
//        FieldBinding fieldBinding = new FieldBinding(builder, field);
//        bindings.add(fieldBinding);
//
//        return fieldBinding;
//    }

    private TextField defaultTextField(String property) {
        TextField field = new TextField();
        field.setId(property);
        field.setCaption(property);

        return field;
    }

    private Binder.BindingBuilder<MODEL, String> defaultTextBinding(HasValue<String> field) {
        Binder.BindingBuilder<MODEL, String> builder = parent.getBinder()
                .forField(field)
                .withNullRepresentation("");

        return builder;
    }

    private FormColumn<MODEL> withFieldBinding(FieldBinding<? extends Component, MODEL, ?> fieldBinding) {
        parent.setFieldBinding(fieldBinding.getField().getId(), fieldBinding);
        return this;
    }

    private UserError toUserError(ValidationResult vr) {
        return new UserError(vr.getErrorMessage());
    }
}
