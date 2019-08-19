package czort.form;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TabSheetForm<MODEL> extends TabSheet implements Form<MODEL> {
    private FormBinder<MODEL> binder;
    private Map<String, Tab> tabFormMap = new HashMap<>();

    private Component currentTab;
    private Component previousTab;

    public TabSheetForm(MODEL model, Class<MODEL> modelClass) {
        binder = new FormBinder<>(modelClass);
        binder.setBean(model);
        setSizeFull();

        this.addSelectedTabChangeListener(it -> {
            if(currentTab == null) currentTab = this.getSelectedTab();
            else {
                previousTab = currentTab;
                currentTab = this.getSelectedTab();
            }
        });
    }

    public TabSheetForm<MODEL> withTab(String caption, Consumer<StandardForm<MODEL>> onTabCreate) {
        StandardForm<MODEL> form = new StandardForm<>(this.binder);
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
