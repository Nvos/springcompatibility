package czort.util;

import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ComponentUtils {

    public static void iterateFromRoot(Component root, Consumer<Component> withProvidedComponent) {
        List<Component> stack = new ArrayList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Component component = stack.remove(0);

            if(component instanceof HasComponents) ((HasComponents) component).iterator().forEachRemaining(stack::add);

            withProvidedComponent.accept(component);
        }
    }
}
