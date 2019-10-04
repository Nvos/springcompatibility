package czort.form;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.HasComponents;
import czort.form.field.BaseFieldComposer;

import java.util.Optional;

public interface Form<MODEL> extends HasComponents {
    FormBinder<MODEL> getBinder();
    Form<MODEL> build();
    Optional<BaseFieldComposer<? extends AbstractComponent, MODEL, ?, ?>> getFieldBindingById(String id);
}
