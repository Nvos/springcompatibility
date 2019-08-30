package czort;

import com.vaadin.spring.navigator.SpringNavigator;
import czort.view.ExportedView;
import org.springframework.stereotype.Component;

@Component
public class DynamicAdminViewLoader implements DynamicViewLoader<AdminUI> {

    @Override
    public void addExportedViews(SpringNavigator navigator) {
        navigator.addView(ExportedView.VIEW_NAME, ExportedView.class);
    }
}
