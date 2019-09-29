package czort.form;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import czort.form.field.FieldBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class TabSheetForm<MODEL> extends TabSheet implements Form<MODEL> {
    private FormBinder<MODEL> binder;
    private Map<String, Form<MODEL>> tabFormMap = new HashMap<>();

    public TabSheetForm(MODEL model, Class<MODEL> modelClass) {
        binder = new FormBinder<>(modelClass);
        binder.setBean(model);
        setSizeFull();
    }

    public TabSheetForm(MODEL model) {
        binder = new FormBinder<>((Class<MODEL>)model.getClass());
        binder.setBean(model);
        setSizeFull();
    }

    public TabSheetForm<MODEL> with(Consumer<TabSheetForm<MODEL>> withSelfProvided) {
        withSelfProvided.accept(this);
        return this;
    }

    public TabSheetForm<MODEL> setTabVisible(int index, boolean isVisible) {
        Tab tab = getTab(index);
        tab.setVisible(isVisible);
        StandardForm<MODEL> form = (StandardForm<MODEL>)tabFormMap.get(tab.getCaption());
        if (!isVisible) {
            form.getFieldBindings().forEach(it -> it.getBinding().unbind());
        } else {
            form.getFieldBindings().forEach(it -> it.getBindingCreator().bind(it.getField().getId()));
        }

        return this;
    }

    public TabSheetForm<MODEL> withTab(String caption, Consumer<StandardForm<MODEL>> onTabCreate) {
        StandardForm<MODEL> form = new StandardForm<>(this.binder);
        form.setId(caption);
        addTab(form, caption);
        tabFormMap.put(caption, form);
        onTabCreate.accept(form);

        return this;
    }

    public FormBinder<MODEL> getBinder() {
        return binder;
    }

    @Override
    public Form<MODEL> build() {
        tabFormMap.values().forEach(Form::build);
        return this;
    }

    @Override
    public Optional<FieldBinding<? extends AbstractComponent, MODEL, ?, ?>> getFieldBindingById(String id) {
        for (Form<MODEL> it : tabFormMap.values()) {
            Optional<FieldBinding<? extends AbstractComponent, MODEL, ?, ?>> fieldBinding = it.getFieldBindingById(id);
            if (fieldBinding.isPresent()) {
                return fieldBinding;
            }
        }

        return Optional.empty();
    }
}
