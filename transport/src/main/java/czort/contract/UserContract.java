package czort.contract;

import czort.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserContract {

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<List<UserResponse>> findAll();

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> find(@PathVariable(value = "id") Integer id);
}
