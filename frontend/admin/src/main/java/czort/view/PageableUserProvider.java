package czort.view;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import czort.client.UserClient;
import czort.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
public class PageableUserProvider extends PageableDataProvider<UserResponse, String> {

    private final UserClient userClient;

    public PageableUserProvider(UserClient userClient) {
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
        return userClient.count().getBody().intValue();
    }
}
