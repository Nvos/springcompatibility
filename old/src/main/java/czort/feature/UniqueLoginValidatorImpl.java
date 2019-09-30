package czort.feature;

import czort.repository.UserRepository;
import czort.validator.UniqueLogin;
import czort.validator.UniqueLoginValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueLoginValidatorImpl implements UniqueLoginValidator {

    private final UserRepository repository;

    public UniqueLoginValidatorImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueLogin uniqueLogin) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !repository.existsByNameEquals(s);
    }
}
