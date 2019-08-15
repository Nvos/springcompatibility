package czort.grid;

import com.vaadin.data.BeanPropertySet;
import com.vaadin.data.PropertyDefinition;
import com.vaadin.data.PropertySet;
import com.vaadin.data.ValueProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.AbstractRenderer;
import com.vaadin.ui.renderers.LocalDateRenderer;
import com.vaadin.ui.renderers.NumberRenderer;
import com.vaadin.ui.renderers.TextRenderer;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Column {

    private PropertyDefinition definition;
    private AbstractRenderer renderer;

    public Column(PropertyDefinition definition, AbstractRenderer renderer) {
        this.definition = definition;
        this.renderer = renderer;
    }

    public PropertyDefinition getDefinition() {
        return definition;
    }

    public AbstractRenderer getRenderer() {
        return renderer;
    }

    public static List<Column> all(Class beanType) {
        PropertySet<Object> propertySet = BeanPropertySet.get(beanType);

        return propertySet.getProperties().map(it -> {
            if (it.getType().equals(Integer.class) || it.getType().isInstance(Long.class))
                return numberColumn(it);
            if (it.getType().equals(LocalDate.class))
                return localDateColumn(it);
            return textColumn(it);
        }).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public static  Column localDateColumn(PropertyDefinition definition) {
        return new Column(
                definition,
                new LocalDateRenderer()
        );
    }

    @SuppressWarnings("unchecked")
    public static  Column numberColumn(PropertyDefinition definition) {
        return new Column(
                definition,
                new NumberRenderer()
        );
    }

    @SuppressWarnings("unchecked")
    public static  Column textColumn(PropertyDefinition definition) {
        return new Column(
                definition,
                new TextRenderer()
        );
    }
}
