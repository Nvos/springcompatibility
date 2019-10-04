package czort.crud;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.crud.CrudPresenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;

public class GenericCrudDataProvider<T> extends PageableDataProvider<T, Object> {
    private final CrudPresenter<T, ?, ?> presenter;

    public GenericCrudDataProvider(CrudPresenter<T, ?, ?> presenter) {
        this.presenter = presenter;
    }

    @Override
    protected Page<T> fetchFromBackEnd(Query<T, Object> query, Pageable pageable) {
        return presenter.findAll(pageable);
    }

    @Override
    protected List<QuerySortOrder> getDefaultSortOrders() {
        return new ArrayList<>();
    }

    @Override
    protected int sizeInBackEnd(Query<T, Object> query) {
        return presenter.count();
    }
}
