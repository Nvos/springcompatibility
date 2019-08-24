package czort.client;

import czort.contract.CrudResourceContract;

import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.UserResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient("http://localhost:8081/user")
public interface UserCrudClient extends CrudResourceContract<UserResponse, UserCreateRequest, UserUpdateRequest> {
}
