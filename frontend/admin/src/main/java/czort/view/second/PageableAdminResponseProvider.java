package czort.view.second;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.client.AdminCrudClient;
import czort.response.AdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScope
@SpringComponent
public class PageableAdminResponseProvider extends PageableDataProvider<AdminResponse, Map<String, String>> {

    private final AdminCrudClient client;

    public PageableAdminResponseProvider(AdminCrudClient userClient) {
        this.client = userClient;
    }

    @Override
    protected Page<AdminResponse> fetchFromBackEnd(Query<AdminResponse, Map<String, String>> query, Pageable pageable) {
        return client.findAll(pageable).getBody();
    }

    @Override
    protected List<QuerySortOrder> getDefaultSortOrders() {
        List<QuerySortOrder> sortOrders = new ArrayList<>();
        sortOrders.add(new QuerySortOrder("id", SortDirection.ASCENDING));
        return sortOrders;
    }

    @Override
    protected int sizeInBackEnd(Query<AdminResponse, Map<String, String>> query) {
        return client.count().getBody().getCount();
    }
}
