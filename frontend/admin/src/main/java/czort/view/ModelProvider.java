package czort.view;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScope
@SpringComponent
public class ModelProvider extends PageableDataProvider<Model, Map<String, String>> {

    @Override
    protected Page<Model> fetchFromBackEnd(Query<Model, Map<String, String>> query, Pageable pageable) {
        List<Model> items = new ArrayList<>();

        for(int i = pageable.getPageSize() * pageable.getPageNumber(); i < pageable.getPageSize() * pageable.getPageNumber() + pageable.getPageSize(); i++) {
            Model model = new Model();
            model.setName("name" + i);
            items.add(model);
        }

        return new PageImpl<>(items, pageable, 1000);
    }

    @Override
    protected List<QuerySortOrder> getDefaultSortOrders() {
        return new ArrayList<>();
    }

    @Override
    protected int sizeInBackEnd(Query<Model, Map<String, String>> query) {
        return 1000;
    }
}
