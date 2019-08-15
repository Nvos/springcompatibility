package czort.view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import czort.dialog.FormDialog;
import czort.grid.CustomGrid;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Autowired
    private PageableUserProvider pageableUserProvider;

    @PostConstruct
    public void bootstrap() {
        List<Model> items = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Model model = new Model();
            model.setName("name" + i);
            model.setValue1(i);
            model.setValue2((long)i);
            model.setDate(LocalDate.now());

            items.add(model);
        }

        FormLayout layout = new FormLayout();
        FormLayout layout1 = new FormLayout();

        HorizontalLayout wrap = new HorizontalLayout();
        wrap.addComponents(layout, layout1);

        TextField textField1 = new TextField();
        textField1.setHeight("200px");
        textField1.setCaption("Bob");
        TextField textField2 = new TextField();
        textField2.setCaption("Bob 12");
        TextField textField3 = new TextField();
        textField3.setCaption("Bob Jeff");
        TextField textField4 = new TextField();
        textField4.setCaption("Long description");

        layout.addComponents(textField1, textField2);
        layout1.addComponents(textField3, textField4);

        Binder<Model> binder = new Binder<>();

        CustomGrid<Model> grid = new CustomGrid<>();
        grid.all(Model.class)
        .setItems(items);

//        grid.setDataProvider(pageableUserProvider);
        grid.setWidth("600px");
        grid.setHeight("600px");
        addComponent(grid);
        final AtomicInteger atomicInteger = new AtomicInteger();
        Button button = new Button("t", (event) -> {
            objectProvider.getIfUnique()
                .withInitialValue(new Model())
                .withOnAcceptHandler(System.out::println)
                .open();

        });
        addComponent(button);
        addComponent(wrap);
    }
}
