package czort.view;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.HasComponents;
import czort.dialog.FormDialog;
import czort.form.TabSheetForm;
import org.vaadin.spring.annotation.PrototypeScope;

@SpringComponent
@PrototypeScope
public class TestTabSheetFormDialog extends FormDialog<Model> {
    @Override
    protected HasComponents bodyComponent(Model model) {
        return new TabSheetForm<>(model, Model.class)
                .withTab("Tab 1", form -> {
                    form.withColumn(column -> {
                        column.withTextField("name");
                    });
                })
                .withTab("Tab 2", form -> {
                    form.withColumn(column -> {
                        column.withIntegerField("value1");
                    });
                }).withTab("Tab 3", form -> {
                    form.withColumn(column -> {
                        column.withLongField("value2");
                    });
                }).withTab("Tab 4", form -> {
                    form.withColumn(column -> {
                        column.withLocalDateField("date");
                    });
                });
    }

    @Override
    protected FormDialogFooter footerComponent() {
        return new FormDialogFooter()
                .withCancelButton()
                .withAcceptButton(System.out::println);
    }
}
