package czort;

import com.vaadin.spring.navigator.SpringNavigator;
import org.springframework.stereotype.Component;

@Component
public class DynamicUserViewLoader implements DynamicViewLoader<UserUI> {

    @Override
    public void addExportedViews(SpringNavigator navigator) {

    }
}
