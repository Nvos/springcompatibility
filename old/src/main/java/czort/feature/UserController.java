package czort.feature;

import czort.contract.CrudResourceContract;
import czort.entity.UserEntity;
import czort.repository.UserRepository;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.CountResponse;
import czort.response.UserResponse;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@Transactional
@RequestMapping("/user")
@RestController
public class UserController implements CrudResourceContract<UserResponse, UserCreateRequest, UserUpdateRequest> {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreateRequest params) {
        UserEntity entity = new UserEntity(
                params.getName(),
                params.getEmail()
        );

        userRepository.save(entity);

        return ResponseEntity.ok(toResponse(entity));
    }

    @Override
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserUpdateRequest params) {
        UserEntity entity = userRepository.findOne(id);
        entity.setEmail(params.getEmail());
        entity.setName(params.getName());

        return ResponseEntity.ok(toResponse(entity));
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id) {
        userRepository.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable).map(this::toResponse));
    }

    @Override
    public ResponseEntity<CountResponse> count() {
        return ResponseEntity.ok(new CountResponse((int) userRepository.count()));
    }

    @Override
    public ResponseEntity<UserResponse> find(@PathVariable Long id) {

        return ResponseEntity.ok(toResponse(userRepository.findOne(id)));
    }

    private UserResponse toResponse(UserEntity entity) {
        UserResponse response = new UserResponse();
        response.setEmail(entity.getEmail());
        response.setName(entity.getName());
        response.setId(entity.getId());

        return response;
    }
}
