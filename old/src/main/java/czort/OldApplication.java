package czort;

import czort.entity.UserEntity;
import czort.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class OldApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(OldApplication.class, args);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onInit(ContextRefreshedEvent event) {
        userRepository.save(new UserEntity("oldbob", "oldbob@wp.pl"));
    }
}
