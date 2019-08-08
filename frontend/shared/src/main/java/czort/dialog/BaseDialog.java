package czort.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;
import org.vaadin.spring.events.EventBus;

import javax.annotation.PostConstruct;

public abstract class BaseDialog extends Window {

    @Autowired
    protected transient EventBus.ViewEventBus viewEventBus;

    @Autowired
    protected transient BeanFactory beanFactory;

    public abstract HasComponents body();

    protected HasComponents header() {
        return new DefaultHeader(this.getClass().getName());
    }

    protected  HasComponents footer() {
        return new DefaultFooter()
                .withButton("Accept", (event -> fireClose()))
                .withButton("Cancel", Alignment.MIDDLE_LEFT, (event -> fireClose()));
    }

    @PostConstruct
    private void buildRoot() {
        viewEventBus.subscribe(this);
        CssLayout wrapper = new CssLayout();

        wrapper.addComponents(
            header(),
            body(),
            footer()
        );

        setContent(wrapper);
        center();
    }

    public static class DefaultFooter extends CssLayout {
        public DefaultFooter() {
            setSizeFull();
        }

        @Override
        protected String getCss(Component c) {
            if (c instanceof CssLayout) {
                // Color the boxes with random colors

                return "display: flex";
            }
            return null;
        }



        public DefaultFooter withButton(String caption, Button.ClickListener clickListener) {
            Button button = new Button(caption, clickListener);
            button.setSizeUndefined();

            addComponent(button);

            return this;
        }

        public DefaultFooter withButton(String caption, Alignment alignment, Button.ClickListener clickListener) {
            Button button = new Button(caption, clickListener);
            button.setSizeUndefined();

            addComponent(button);

            return this;
        }
    }
    public static class DefaultHeader extends HorizontalLayout {
        public DefaultHeader(String title) {
            Label label = new Label(title);
            addComponent(label);
        }
    }
}
