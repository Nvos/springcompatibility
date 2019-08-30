package czort;

import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.UI;

public interface DynamicViewLoader<UI extends com.vaadin.ui.UI> {
    public void addExportedViews(SpringNavigator navigator);
}
