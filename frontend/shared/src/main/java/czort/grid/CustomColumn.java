package czort.grid;

import com.vaadin.data.ValueProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.Renderer;

public class CustomColumn<V, T> extends Grid.Column<V, T> {
    public CustomColumn(ValueProvider<V, T> valueProvider, Renderer<? super T> renderer) {
        super(valueProvider, renderer);
    }

    public <P> CustomColumn(ValueProvider<V, T> valueProvider, ValueProvider<T, P> presentationProvider, Renderer<? super P> renderer) {
        super(valueProvider, presentationProvider, renderer);
    }

    public <P> CustomColumn(ValueProvider<V, T> valueProvider, ValueProvider<T, P> presentationProvider, Renderer<? super P> renderer, NestedNullBehavior nestedNullBehavior) {
        super(valueProvider, presentationProvider, renderer, nestedNullBehavior);
    }
}
