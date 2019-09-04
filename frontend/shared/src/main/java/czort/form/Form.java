package czort.form;

import com.vaadin.data.Binder;
import com.vaadin.ui.HasComponents;

import java.util.function.Function;

public interface Form<MODEL> extends HasComponents {
    public FormBinder<MODEL>  getBinder();
}
