package czort.view.second;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.contract.CrudResourceContract;
import czort.crud.CrudPresenter;
import czort.request.AdminCreateRequest;
import czort.request.AdminUpdateRequest;
import czort.response.AdminResponse;

// It is necessary to create such instances as fully generic component lacks
// reifed type generics to decide which client to inject
@ViewScope
@SpringComponent
public class AdminCrudPresenter extends CrudPresenter<AdminResponse, AdminCreateRequest, AdminUpdateRequest> {
    public AdminCrudPresenter(CrudResourceContract<AdminResponse, AdminCreateRequest, AdminUpdateRequest> crudClient) {
        super(crudClient);
    }
}
