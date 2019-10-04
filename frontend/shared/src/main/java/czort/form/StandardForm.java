package czort.form;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import czort.form.field.BaseFieldComposer;
import czort.form.field.GridField;
import czort.form.field.GridFieldComposer;
import czort.grid.BaseGrid;
import czort.view.GridComposer;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class StandardForm<MODEL> extends VerticalLayout implements Form<MODEL>, HasComponents {
    private FormBinder<MODEL> binder;
    private HorizontalLayout formColumns = new HorizontalLayout();
    private final Map<String, BaseFieldComposer<? extends Component, MODEL, ?, ?>> CACHE = new HashMap<>();


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
        formColumns.addComponent(column.getLayout());

        onColumnCreate.accept(column);

        return this;
    }

    public <VALUE> GridFieldComposer<MODEL, VALUE> withGrid(
            String fieldName,
            BiConsumer<GridComposer<VALUE>, GridField<VALUE>> withProvided
    ) {
        BaseGrid<VALUE> grid = new BaseGrid<>();
        GridField<VALUE> field = new GridField<>(grid);
        field.setId(fieldName);

        GridComposer<VALUE> gridComposer = new GridComposer<>(grid);
        withProvided.accept(gridComposer, field);

        Binder.BindingBuilder<MODEL, List<VALUE>> builder = getBinder()
                .forField(field)
                .withNullRepresentation(new ArrayList<>());

        GridFieldComposer<MODEL, VALUE> fieldBinding = new GridFieldComposer<>(field, builder);
        setFieldBinding(fieldName, fieldBinding);

        addComponentsAndExpand(field);

        return fieldBinding;
    }

    public FormBinder<MODEL> getBinder() {
        return binder;
    }

    @Override
    public Form<MODEL> build() {
        getFieldBindings().forEach(BaseFieldComposer::bind);
        return this;
    }

    @Override
    public Optional<BaseFieldComposer<? extends AbstractComponent, MODEL, ?, ?>> getFieldBindingById(String id) {
        return Optional.ofNullable(CACHE.get(id));
    }

    public Boolean isModelValid() {
        return getBinder().isValid();
    }


    public Form<MODEL> setFieldBinding(
            String property,
            BaseFieldComposer<? extends Component, MODEL, ?, ?> baseFieldComposer
    ) {
        CACHE.put(property, baseFieldComposer);
        return this;
    }

    public List<BaseFieldComposer<? extends Component, MODEL, ?, ?>> getFieldBindings() {
        return new ArrayList<>(CACHE.values());
    }
}
