package czort.client;

import czort.contract.UserContract;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("http://localhost:8080")
public interface UserClient extends UserContract {
}
