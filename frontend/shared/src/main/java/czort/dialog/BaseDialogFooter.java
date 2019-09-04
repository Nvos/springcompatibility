package czort.dialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class BaseDialogFooter<VALUE> extends HorizontalLayout implements Footer {
    protected BaseDialog<VALUE, FormDialog.FormDialogFooter> dialog;

    public BaseDialogFooter(BaseDialog<VALUE, FormDialog.FormDialogFooter> dialog) {
        this.dialog = dialog;
        setSizeFull();
        setMargin(true);

        Label separator = new Label();
        addComponent(separator);
        setExpandRatio(separator, 1f);
    }

    public BaseDialogFooter withButtonRight(Button button) {
        addComponent(button, getComponentCount());
        this.dialog.open();
        return this;
    }

    public BaseDialogFooter withButtonLeft(Button button) {
        addComponentAsFirst(button);

        return this;
    }
}
