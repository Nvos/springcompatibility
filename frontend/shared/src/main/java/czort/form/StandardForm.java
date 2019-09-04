package czort.form;

import com.vaadin.ui.*;

import java.util.function.Consumer;

public class StandardForm<MODEL extends Object> extends VerticalLayout implements Form<MODEL>, HasComponents {
    private FormBinder<MODEL> binder;
    private HorizontalLayout formColumns = new HorizontalLayout();

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

    public StandardForm<MODEL> withColumn(Consumer<FormColumn<MODEL>> onColumnCreate) {
        FormColumn<MODEL> column = new FormColumn<>(this);
        formColumns.addComponent(column);

        onColumnCreate.accept(column);

        column.createColumn();

        return this;
    }

    public FormBinder<MODEL> getBinder() {
        return binder;
    }

    public Boolean isModelValid() {
        return getBinder().isValid();
    }
}
