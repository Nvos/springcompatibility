package czort.contract;

import czort.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface UserContract {

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> findAll();
}
