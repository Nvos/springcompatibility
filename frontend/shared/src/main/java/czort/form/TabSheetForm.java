package czort.form;

import com.vaadin.server.UserError;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TabSheetForm<MODEL> extends TabSheet {
    private FormBinder<MODEL> binder;
    private Map<String, Tab> tabFormMap = new HashMap<>();

    private Component currentTab;
    private Component previousTab;

    public TabSheetForm(MODEL model, Class modelClass) {
        binder = new FormBinder<>(modelClass);
        binder.setBean(model);
        setSizeFull();

        this.addSelectedTabChangeListener(it -> {
            if(currentTab == null) currentTab = this.getSelectedTab();
            else {
                previousTab = currentTab;
                currentTab = this.getSelectedTab();

                Form<MODEL> form = (Form<MODEL>) this.previousTab;
                Tab tab = tabFormMap.get(form.getId());

                if(!form.collectErrors().isEmpty()) {
                    tab.setComponentError(new UserError("Has validation errors"));
                } else {
                    tab.setComponentError(null);
                }
            }
        });
    }

    public TabSheetForm<MODEL> withTab(String caption, Consumer<Form<MODEL>> onTabCreate) {
        Form<MODEL> form = new Form<>(this.binder);
        form.setId(caption);
        Tab tab = addTab(form, caption);
        tabFormMap.put(caption, tab);
        onTabCreate.accept(form);

        return this;
    }

    public FormBinder<MODEL> getBinder() {
        return binder;
    }
}
