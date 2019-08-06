package czort.feature;

import czort.contract.UserContract;
import czort.repository.UserRepository;
import czort.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewController implements UserContract {

    private final UserRepository userRepository;

    public NewController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(
                userRepository.findAll()
                        .stream()
                        .map(it -> new UserResponse(it.getId(), it.getName(), it.getEmail()))
                        .collect(Collectors.toList())
        );
    }
}
