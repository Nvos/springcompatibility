package czort.contract;

import czort.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserContract {

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable);

    @GetMapping(value = "/user/count")
    public ResponseEntity<Long> count();

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> find(@PathVariable(value = "id") Integer id);
}
