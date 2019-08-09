package czort.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import czort.dialog.FormDialog;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@SpringView(name = MainView.VIEW_NAME)
public class MainView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "MainView";

    @Autowired
    private transient ObjectProvider<MainDialog> mainDialogObjectProvider;

    @Autowired
    MainFormDialog mainFormDialog;

    @Autowired
    ObjectProvider<FormDialog<Model>> objectProvider;

    @PostConstruct
    public void bootstrap() {
        Button button = new Button("t", (event) -> {
//            getUI().addWindow(mainDialogObjectProvider.getIfUnique());
            objectProvider.getIfUnique()
                .withInitialValue(new Model())
                .withOnAcceptHandler(System.out::println)
                .open();
        });

        addComponent(button);
    }
}