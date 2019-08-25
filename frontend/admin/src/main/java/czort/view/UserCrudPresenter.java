package czort.view;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.contract.CrudResourceContract;
import czort.crud.CrudPresenter;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.UserResponse;

@ViewScope
@SpringComponent
public class UserCrudPresenter extends CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> {
    public UserCrudPresenter(CrudResourceContract<UserResponse, UserCreateRequest, UserUpdateRequest> crudClient) {
        super(crudClient);
    }
}
