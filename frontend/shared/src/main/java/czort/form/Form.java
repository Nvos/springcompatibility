package czort.form;

import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Form<MODEL> extends VerticalLayout {
    private FormBinder<MODEL> binder;
    private HorizontalLayout formColumns = new HorizontalLayout();

    public Form(FormBinder<MODEL> binder) {
        this.binder = binder;
        formColumns.setSizeFull();
        addComponent(formColumns);
    }

    public Form(MODEL model, Class modelClass) {
        binder =  new FormBinder<>(modelClass);
        binder.setBean(model);
        formColumns.setSizeFull();
        addComponent(formColumns);
    }

    public Form<MODEL> withColumn(Consumer<FormColumn<MODEL>> onColumnCreate) {
        FormColumn<MODEL> column = new FormColumn<>(this);
        formColumns.addComponent(column);
        onColumnCreate.accept(column);
        column.createColumn();

        return this;
    }

    public List collectErrors() {
        List<ErrorMessage> errorMessages = new ArrayList<>();

        formColumns.forEach(it -> {
            List<FormColumn<MODEL>.FieldBinding<Component, Object>> bindings = ((FormColumn<MODEL>) it).getBindings();
            bindings.forEach(binding -> {
                ErrorMessage message = ((AbstractComponent) binding.getField()).getErrorMessage();
                if (message != null) errorMessages.add(message);
            });
        });

        return errorMessages;
    }

    public FormBinder<MODEL> getBinder() {
        return binder;
    }
}
