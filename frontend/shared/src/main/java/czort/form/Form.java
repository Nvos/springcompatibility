package czort.form;

import com.vaadin.data.Binder;

import java.util.function.Function;

public interface Form<MODEL> {
    public FormBinder<MODEL>  getBinder();
}
