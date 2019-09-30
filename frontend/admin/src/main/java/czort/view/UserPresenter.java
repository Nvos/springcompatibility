package czort.view;

import com.google.common.collect.Lists;
import com.googlecode.gentyref.TypeToken;
import com.vaadin.data.ValueProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import czort.contract.CrudResourceContract;
import czort.crud.CrudPresenter;
import czort.crud.ReMapper;
import czort.request.UserCreateRequest;
import czort.request.UserRequest;
import czort.request.UserUpdateRequest;
import czort.response.UserResponse;

@ViewScope
@SpringComponent
public class UserPresenter extends CrudPresenter<UserResponse, UserCreateRequest, UserUpdateRequest> {

    public UserPresenter(CrudResourceContract<UserResponse, UserCreateRequest, UserUpdateRequest> crudClient) {
        super(crudClient);
    }

    @Override
    protected ReMapper<UserResponse, UserCreateRequest, UserUpdateRequest> getReMapper() {
        return new ReMapper<>(
                UserResponse::getId,
                () -> {
                    UserCreateRequest request = new UserCreateRequest();
                    request.setItems(Lists.newArrayList("Value 1", "Value 2" , "Value 3", "Value 4"));

                    return request;
                    },
                it -> new UserUpdateRequest()
        );
    }
}
