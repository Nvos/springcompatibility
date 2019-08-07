package czort.feature;

import czort.client.UserClient;
import czort.contract.UserContract;
import czort.entity.UserEntity;
import czort.repository.UserRepository;
import czort.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> own = userRepository.findAll()
                .stream()
                .map(it -> new UserResponse(it.getId(), it.getName(), it.getEmail()))
                .collect(Collectors.toList());

//        ResponseEntity<List<UserResponse>> remote = userClient.findAll();

//        own.addAll(remote.getBody());

        return ResponseEntity.ok(own);
    }

    public ResponseEntity<UserResponse> find(@PathVariable("id") Integer id) {
        UserEntity one = userRepository.findOne(id);

        return ResponseEntity.ok(new UserResponse(one.getId(), one.getName(), one.getEmail()));
    }
}
