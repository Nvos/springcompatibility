package czort.view;

import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.client.UserCrudClient;
import czort.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ViewScope
@SpringComponent
public class UserDataProvider extends PageableDataProvider<UserResponse, Object> {

    private final UserCrudClient userCrudClient;

    public UserDataProvider(UserCrudClient userCrudClient) {
        this.userCrudClient = userCrudClient;
    }

    @Override
    public boolean isInMemory() {
        return false;
    }

    @Override
    protected int sizeInBackEnd(Query<UserResponse, Object> query) {
        return userCrudClient.count().getBody().getCount();
    }

    @Override
    protected Page<UserResponse> fetchFromBackEnd(Query<UserResponse, Object> query, Pageable pageable) {
        return userCrudClient.findAll(pageable)
                .getBody();
    }

    @Override
    protected List<QuerySortOrder> getDefaultSortOrders() {
        return new ArrayList<>();
    }
}
