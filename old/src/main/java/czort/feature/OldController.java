package czort.feature;

import com.netflix.client.ClientFactory;
import czort.client.UserClient;
import czort.contract.UserContract;
import czort.entity.UserEntity;
import czort.repository.UserRepository;
import czort.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OldController implements UserContract {

    private final UserRepository userRepository;
//    private final UserClient userClient;

    @Autowired
    public OldController(UserRepository userRepository) {
        this.userRepository = userRepository;

//        this.userClient = userClient;
    }

    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        System.out.println(pageable.toString());
        Page<UserResponse> own = userRepository.findAll(pageable)
                .map(it -> new UserResponse(it.getId(), it.getName(), it.getEmail()));

        return ResponseEntity.ok(own);
    }

    @Override
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(userRepository.count());
    }
    public ResponseEntity<UserResponse> find(@PathVariable("id") Integer id) {
        UserEntity entity = new UserEntity();
      return null;




//        return ResponseEntity.ok();
    }
}
