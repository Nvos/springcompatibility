package czort.feature;

import com.google.common.collect.Lists;
import czort.client.UserClient;
import czort.response.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OldControllerTest {

    @Autowired
    private UserClient userClient;

    @Test
    public void smrth() {
//        userClient.findAll();
        UserResponse response = new UserResponse(null, "oldbob", "oldbob@wp.pl");
        Page<UserResponse> body = userClient.findAll(new PageRequest(0, 20)).getBody();
        assertThat(body.getContent()).hasSizeGreaterThan(0);
        assertThat(body.getContent()).usingRecursiveFieldByFieldElementComparator()
                .usingElementComparatorOnFields("name", "email")
            .contains(response);
    }

    @Test
    public void smrth1() {
        UserResponse response = new UserResponse(null, "oldbob", "oldbob@wp.pl");

        assertThat(response).usingRecursiveComparison()
                .ignoringActualNullFields()
                .isEqualTo(userClient.find(1).getBody());
    }
}
