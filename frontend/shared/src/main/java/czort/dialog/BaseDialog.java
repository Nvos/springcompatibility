package czort.dialog;

import com.vaadin.ui.*;
import org.vaadin.spring.events.EventBus;

public abstract class BaseDialog extends Window {

    protected abstract HasComponents body();

    protected String title() {
        return getClass().getName() + "." + "title";
    }

    protected  HasComponents footer() {
        return new DefaultFooter()
                .withButtonRight("Accept", (event -> close()))
                .withButtonRight("Cancel", (event ->  close()))
                .withButtonLeft("Next", (event ->  close()))
                .withButtonLeft("Prev", (event ->  close()));
    }

    private BaseDialog buildFromRoot() {
        setWidth(600, Unit.PIXELS);
        CssLayout wrapper = new CssLayout();
        wrapper.setWidth(600, Unit.PIXELS);

        setCaption(title());
        HasComponents body = body();
        body.setSizeFull();
        wrapper.addComponents(
            body,
            footer()
        );

        setContent(wrapper);
        center();

        return this;
    }


    public BaseDialog open() {
        buildFromRoot();
        UI.getCurrent().addWindow(this);
        return this;
    }

    public static class DefaultFooter extends HorizontalLayout {
        public DefaultFooter() {
            setSizeFull();
            setMargin(true);

            Label separator = new Label();
            addComponent(separator);
            setExpandRatio(separator, 1f);
        }

        public DefaultFooter withButtonLeft(String caption, Button.ClickListener clickListener) {
            Button button = new Button(caption, clickListener);
            button.setSizeUndefined();

            addComponentAsFirst(button);

            return this;
        }

        public DefaultFooter withButtonLeft(Button button) {
            button.setSizeUndefined();

            addComponentAsFirst(button);

            return this;
        }

        public DefaultFooter withButtonRight(Button button) {
            button.setSizeUndefined();

            addComponent(button, getComponentCount());

            return this;
        }

        public DefaultFooter withButtonRight(String caption, Button.ClickListener clickListener) {
            Button button = new Button(caption, clickListener);
            button.setSizeUndefined();

            addComponent(button, getComponentCount());

            return this;
        }
    }
}
