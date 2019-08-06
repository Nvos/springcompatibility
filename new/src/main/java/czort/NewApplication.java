package czort;

import czort.entity.UserEntity;
import czort.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NewApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(NewApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onInit(ApplicationStartedEvent event) {
        userRepository.save(new UserEntity("bob", "bob@wp.pl"));
    }
}
