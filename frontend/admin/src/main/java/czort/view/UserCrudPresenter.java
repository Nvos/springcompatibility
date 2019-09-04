package czort.view;

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

@ViewScope
@SpringComponent
public class UserCrudPresenter extends CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> {

    @Override
    protected ReMapper<UserResponse, UserCreateRequest, UserUpdateRequest> getReMapper() {
        return new ReMapper<>(
                UserResponse::getId,
                UserCreateRequest::new,
                model -> {
                    UserUpdateRequest update = new UserUpdateRequest();
                    BeanUtils.copyProperties(model, update);

                    return update;
                }
        );
    }

    public UserCrudPresenter(CrudResourceContract<UserResponse, UserCreateRequest, UserUpdateRequest> crudClient) {
        super(crudClient, new TypeToken<CrudResourceContract<UserResponse, UserCreateRequest, UserUpdateRequest>>() {});
    }
}
