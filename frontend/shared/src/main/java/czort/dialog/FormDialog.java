package czort.dialog;

import com.vaadin.ui.HasComponents;
import org.vaadin.spring.events.EventBus;

import java.util.function.Consumer;

public abstract class FormDialog<MODEL> extends BaseDialog {

    private MODEL model;
    private Consumer<MODEL> onAcceptHandler;

    @Override
    protected  HasComponents footer() {
        return new DefaultFooter()
            .withButtonRight("Accept", (event -> onAcceptHandler.accept(model)))
            .withButtonRight("Cancel", (event ->  close()))
            .withButtonLeft("Next", (event ->  close()))
            .withButtonLeft("Prev", (event ->  close()));
    }

    protected abstract HasComponents body(MODEL model);

    @Override
    protected HasComponents body() {
        return body(model);
    }

    public FormDialog<MODEL> withOnAcceptHandler(Consumer<MODEL> onAcceptHandler) {
        this.onAcceptHandler = onAcceptHandler;
        return this;
    }

    public FormDialog<MODEL> withInitialValue(MODEL initialValue) {
        this.model = initialValue;
        return this;
    }
}
