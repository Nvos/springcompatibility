package czort.view;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
public class ModelProvider extends PageableDataProvider<Model, Model> {

    @Override
    protected Page<Model> fetchFromBackEnd(Query<Model, Model> query, Pageable pageable) {
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
    protected int sizeInBackEnd(Query<Model, Model> query) {
        return 1000;
    }
}
