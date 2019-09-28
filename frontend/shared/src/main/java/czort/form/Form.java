package czort.form;

import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import czort.form.field.FieldBinding;

import java.util.function.Function;

public interface Form<MODEL> extends HasComponents {
    FormBinder<MODEL> getBinder();
    Form<MODEL> build();
//    FieldBinding<? extends Component, MODEL, ?> getFieldBindingById(String id);
}
