package czort.view;

import com.google.common.collect.Lists;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import czort.client.UserCrudClient;
import czort.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringComponent
public class UserProvider extends PageableDataProvider<UserResponse, Map<String, String>> {
    private final UserCrudClient userCrudClient;

    public UserProvider(UserCrudClient userCrudClient) {
        this.userCrudClient = userCrudClient;
    }

    @Override
    protected Page<UserResponse> fetchFromBackEnd(Query<UserResponse, Map<String, String>> query, Pageable pageable) {
        ResponseEntity<Page<UserResponse>> result = userCrudClient.findAll(pageable);

        return result.getBody();
    }

    @Override
    protected List<QuerySortOrder> getDefaultSortOrders() {
        return Lists.newArrayList(new QuerySortOrder("id", SortDirection.DESCENDING));
    }

    @Override
    protected int sizeInBackEnd(Query<UserResponse, Map<String, String>> query) {
        return userCrudClient.count().getBody().getCount();
    }
}
