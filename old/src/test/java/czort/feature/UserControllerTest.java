package czort.feature;

import czort.client.UserCrudClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest {

    @Autowired
    private UserCrudClient userClient;

//    @Test
//    public void smrth() {
////        userClient.findAll();
//        UserResponse response = new UserResponse(null, "oldbob", "oldbob@wp.pl");
//        Page<UserResponse> body = userClient.findAll(new PageRequest(0, 20)).getBody();
//        assertThat(body.getContent()).hasSizeGreaterThan(0);
//        assertThat(body.getContent()).usingRecursiveFieldByFieldElementComparator()
//                .usingElementComparatorOnFields("name", "email")
//            .contains(response);
//    }
//
//    @Test
//    public void smrth1() {
//        UserResponse response = new UserResponse(null, "oldbob", "oldbob@wp.pl");
//
//        assertThat(response).usingRecursiveComparison()
//                .ignoringActualNullFields()
//                .isEqualTo(userClient.find(1).getBody());
//    }
}
