package czort.view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import czort.dialog.BaseDialog;
import czort.dialog.FormDialog;
import czort.grid.BaseGrid;
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
    private transient ObjectProvider<TestFormDialog> dialogProvider;

    @Autowired
    private transient ObjectProvider<TestTabSheetFormDialog> tabSheetFormDialogObjectProvider;

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


//        grid.setDataProvider(pageableUserProvider);

        final AtomicInteger atomicInteger = new AtomicInteger();
        Button button = new Button("t", (event) -> {
            Model model = new Model();
            model.setName("");

            dialogProvider.getIfUnique()
                    .withInitialValue(model)
                    .withSize(BaseDialog.Size.UNDEFINED)
                    .open();
        });

        Button button1 = new Button("t1", (event) -> {
            Model model = new Model();
            model.setName("");
            model.setValue2(11L);

            tabSheetFormDialogObjectProvider.getIfUnique()
                    .withInitialValue(model)
                    .withSize(BaseDialog.Size.LARGE)
                    .open();
        });

        addComponents(button, button1);
        addComponent(wrap);
    }
}
