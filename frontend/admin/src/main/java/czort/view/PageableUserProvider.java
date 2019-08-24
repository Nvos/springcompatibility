package czort.view;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.client.UserCrudClient;
import czort.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;

@ViewScope
@SpringComponent
public class PageableUserProvider extends PageableDataProvider<UserResponse, String> {

    private final UserCrudClient userClient;

    public PageableUserProvider(UserCrudClient userClient) {
        this.userClient = userClient;
    }

    @Override
    protected Page<UserResponse> fetchFromBackEnd(Query<UserResponse, String> query, Pageable pageable) {
        return userClient.findAll(pageable).getBody();
    }

    @Override
    protected List<QuerySortOrder> getDefaultSortOrders() {
        List<QuerySortOrder> sortOrders = new ArrayList<>();
        sortOrders.add(new QuerySortOrder("id", SortDirection.ASCENDING));
        return sortOrders;
    }

    @Override
    protected int sizeInBackEnd(Query<UserResponse, String> query) {
        return userClient.count().getBody().getCount();
    }
}
