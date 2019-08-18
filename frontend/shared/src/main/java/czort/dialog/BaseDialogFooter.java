package czort.dialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class BaseDialogFooter extends HorizontalLayout implements Footer {
    public BaseDialogFooter() {
        setSizeFull();
        setMargin(true);

        Label separator = new Label();
        addComponent(separator);
        setExpandRatio(separator, 1f);
    }

    public BaseDialogFooter withButtonRight(Button button) {
        addComponent(button, getComponentCount());

        return this;
    }

    public BaseDialogFooter withButtonLeft(Button button) {
        addComponentAsFirst(button);

        return this;
    }
}
