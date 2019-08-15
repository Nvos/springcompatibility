package czort.client;

import com.fasterxml.jackson.databind.Module;
import feign.Client;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }

    @Bean
    public Module pageJacksonModule() {
        return new PageJacksonModule();
    }

    @Bean
    public Encoder feignEncoderPageable() {
        PageableSpringEncoder encoder = new PageableSpringEncoder(
                new SpringEncoder(this.messageConverters));
        encoder.setPageParameter("page");
        encoder.setSizeParameter("size");
        encoder.setSortParameter("sort");

        return encoder;
    }
}
