package czort.dialog;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.ObjectProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@PrototypeScope
final public class ActionDialog extends BaseDialog<BaseDialogFooter> {

    private final ObjectProvider<ActionDialog> provider;
    private final List<Component> bodyComponents;

    public ActionDialog(ObjectProvider<ActionDialog> provider) {
        this.provider = provider;
        this.bodyComponents = new ArrayList<>();
    }

    @Override
    protected HasComponents bodyComponent() {
        VerticalLayout body = new VerticalLayout();
        bodyComponents.forEach(body::addComponent);

        return body;
    }

    public ActionDialog withComponent(Component component) {
        this.bodyComponents.add(component);

        return this;
    }

    public ActionDialog withCaption(String caption) {
        setCaption(caption);

        return this;
    }

    public ActionDialog openAlert(String caption, String description, Button.ClickListener onAcceptClick) {
        Label descriptionLabel = new Label(description);
        Button acceptButton = new Button("Accept", onAcceptClick);
        Button cancelButton = new Button("Cancel");


        Window open = provider.getIfUnique()
                .withComponent(descriptionLabel)
                .withCaption(caption)
                .useFooterComponent(footerComponent -> {
                    footerComponent.withButtonRight(acceptButton);
                    footerComponent.withButtonRight(cancelButton);
                })
                .open();

        acceptButton.addClickListener(event -> open.close());
        cancelButton.addClickListener(event -> open.close());


        return this;
    }
}
