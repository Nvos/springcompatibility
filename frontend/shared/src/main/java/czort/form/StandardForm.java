package czort.form;

import com.vaadin.ui.*;
import czort.form.field.FieldBinding;

import java.util.*;
import java.util.function.Consumer;

public class StandardForm<MODEL> extends VerticalLayout implements Form<MODEL>, HasComponents {
    private FormBinder<MODEL> binder;
    private HorizontalLayout formColumns = new HorizontalLayout();
    private final Map<String, FieldBinding<? extends Component, MODEL, ?, ?>> CACHE = new HashMap<>();


    public StandardForm(FormBinder<MODEL> binder) {
        this.binder = binder;
        formColumns.setSizeFull();
        addComponent(formColumns);
    }

    public StandardForm(MODEL model, Class<MODEL> modelClass) {
        binder =  new FormBinder<>(modelClass);
        binder.setBean(model);
        formColumns.setSizeFull();
        addComponent(formColumns);
    }

    @SuppressWarnings("unchecked")
    public StandardForm(MODEL model) {
        binder =  new FormBinder<>((Class<MODEL>)model.getClass());
        binder.setBean(model);
        formColumns.setSizeFull();
        addComponent(formColumns);
    }

    public StandardForm<MODEL> withColumn(Consumer<FormColumn<MODEL>> onColumnCreate) {
        FormColumn<MODEL> column = new FormColumn<>(this);
        formColumns.addComponent(column);

        onColumnCreate.accept(column);

        return this;
    }

    public FormBinder<MODEL> getBinder() {
        return binder;
    }

    @Override
    public Form<MODEL> build() {
        getFieldBindings().forEach(it -> {
            String property = it.getField().getId();
            addComponent(it.getField());

            if(it.getBindingCreator() != null) {
                it.bind();
            }
        });

        return this;
    }

    @Override
    public Optional<FieldBinding<? extends AbstractComponent, MODEL, ?, ?>> getFieldBindingById(String id) {
        return Optional.ofNullable(CACHE.get(id));
    }

    public Boolean isModelValid() {
        return getBinder().isValid();
    }


    public Form<MODEL> setFieldBinding(
            String property,
            FieldBinding<? extends Component, MODEL, ?, ?> fieldBinding
    ) {
        CACHE.put(property, fieldBinding);
        return this;
    }

    public List<FieldBinding<? extends Component, MODEL, ?, ?>> getFieldBindings() {
        return new ArrayList<>(CACHE.values());
    }
}
