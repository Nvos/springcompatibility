package czort.form;

import com.vaadin.data.Binder;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import czort.form.field.FieldBinding;

import java.util.Optional;
import java.util.function.Function;

public interface Form<MODEL> extends HasComponents {
    FormBinder<MODEL> getBinder();
    Form<MODEL> build();
    Optional<FieldBinding<? extends AbstractComponent, MODEL, ?, ?>> getFieldBindingById(String id);
}
