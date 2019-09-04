package czort.view.second;

import com.googlecode.gentyref.TypeToken;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.contract.CrudResourceContract;
import czort.crud.CrudPresenter;
import czort.crud.ReMapper;
import czort.request.AdminCreateRequest;
import czort.request.AdminUpdateRequest;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.AdminResponse;
import czort.response.UserResponse;
import org.springframework.beans.BeanUtils;

// It is necessary to create such instances as fully generic component lacks
// reifed type generics to decide which client to inject
@ViewScope
@SpringComponent
public class AdminCrudPresenter extends CrudPresenter<AdminResponse, AdminCreateRequest, AdminUpdateRequest> {
    public AdminCrudPresenter(CrudResourceContract<AdminResponse, AdminCreateRequest, AdminUpdateRequest> crudClient) {
        super(crudClient,
                new TypeToken<CrudResourceContract<AdminResponse, AdminCreateRequest, AdminUpdateRequest>>() {});
    }

    @Override
    protected ReMapper<AdminResponse, AdminCreateRequest, AdminUpdateRequest> getReMapper() {
        return new ReMapper<AdminResponse, AdminCreateRequest, AdminUpdateRequest>(
                AdminResponse::getId,
                AdminCreateRequest::new,
                model -> {
                    AdminUpdateRequest update = new AdminUpdateRequest();
                    BeanUtils.copyProperties(model, update);

                    return update;
                }
        ) {};
    }
}
