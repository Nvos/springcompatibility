package czort.client;

import czort.contract.CrudResourceContract;
import czort.request.AdminCreateRequest;
import czort.request.AdminUpdateRequest;
import czort.response.AdminResponse;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("http://localhost:8081/admin")
public interface AdminCrudClient extends CrudResourceContract<AdminResponse, AdminCreateRequest, AdminUpdateRequest> {
}
