package czort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.vaadin.spring.events.annotation.EnableEventBus;

@EnableEventBus
@EnableFeignClients
@SpringBootApplication
public class RootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
