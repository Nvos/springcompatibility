package czort.form;

import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.data.converter.StringToFloatConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.*;
import czort.form.field.*;

public abstract class FormComposer<MODEL, R extends AbstractLayout> {
    protected final R layout;
    protected final StandardForm<MODEL> form;

    protected FormComposer(R layout, StandardForm<MODEL> form) {
        this.layout = layout;
        this.form = form;
    }

    public <F extends AbstractComponent> ComponentComposer<MODEL> withComponent(String fieldName, F component) {
        ComponentComposer<MODEL> fieldBinding = new ComponentComposer<>(component);
        return withFieldBindingDefaults(fieldName, fieldBinding);
    }

    public TextFieldComposer<MODEL> withTextField(String property) {
        TextField field = defaultTextField(property);
        Binder.BindingBuilder<MODEL, String> builder = defaultTextBinding(field);

        TextFieldComposer<MODEL> fieldBinding = new TextFieldComposer<>(field, builder);

        return withFieldBindingDefaults(property, fieldBinding);
    }

    public IntegerFieldComposer<MODEL> withIntegerField(String property) {
        TextField field = defaultTextField(property);
        Binder.BindingBuilder<MODEL, Integer> builder = defaultTextBinding(field)
                .withConverter(new StringToIntegerConverter("err"));

        IntegerFieldComposer<MODEL> fieldBinding = new IntegerFieldComposer<>(field, builder);

        return withFieldBindingDefaults(property, fieldBinding);
    }

    public FloatFieldComposer<MODEL> withFloatField(String fieldName) {
        TextField field = defaultTextField(fieldName);
        Binder.BindingBuilder<MODEL, Float> builder = defaultTextBinding(field)
                .withConverter(new StringToFloatConverter("err"));

        FloatFieldComposer<MODEL> fieldBinding = new FloatFieldComposer<>(field, builder);

        return withFieldBindingDefaults(fieldName, fieldBinding);
    }

    public LabelComposer<MODEL> withLabel(String fieldName, String value) {
        Label label = new Label(value);
        label.setValue(value);
        label.setCaption(fieldName);

        LabelComposer<MODEL> fieldBinding = new LabelComposer<>(label);
        return withFieldBindingDefaults(fieldName, fieldBinding);
    }

    private TextField defaultTextField(String property) {
        TextField field = new TextField();
        field.setCaption(property);

        return field;
    }

    private Binder.BindingBuilder<MODEL, String> defaultTextBinding(HasValue<String> field) {
        Binder.BindingBuilder<MODEL, String> builder = form.getBinder()
                .forField(field)
                .withNullRepresentation("");

        return builder;
    }

    protected  <FIELD extends AbstractComponent, BINDING, RETURN extends BaseFieldComposer<FIELD, MODEL, BINDING, RETURN>> RETURN withFieldBindingDefaults(
            String fieldName, RETURN fieldBinding
    ) {
        addComponent(fieldBinding.getField());
        fieldBinding.getField().setId(fieldName);
        form.setFieldBinding(fieldBinding.getField().getId(), fieldBinding);

        return fieldBinding;
    }

    public void addComponent(Component component) {
        layout.addComponent(component);
    }

    public R getLayout() {
        return layout;
    }
}
