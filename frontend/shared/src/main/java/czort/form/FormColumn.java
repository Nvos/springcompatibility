package czort.form;

import com.vaadin.ui.*;
import czort.form.field.*;

import java.util.function.Consumer;

public class FormColumn<MODEL> extends FormComposer<MODEL, FormLayout> {
    public FormColumn(StandardForm<MODEL> form) {
        super(new FormLayout(), form);
    }

    public ComponentComposer<MODEL> withFieldRow(String fieldName, Consumer<FieldRowComposer<MODEL>> withFieldRowProvided) {
        FieldRowComposer<MODEL> fieldRowComposer = new FieldRowComposer<>(form);
        withFieldRowProvided.accept(fieldRowComposer);
        HorizontalLayout layout = fieldRowComposer.getLayout();
        layout.setCaption(fieldName);

        return withComponent(fieldName, layout);
    }
}
