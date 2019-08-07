package czort.feature;

import czort.client.UserClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OldControllerTest {

    @Autowired
    private UserClient userClient;

    @Test
    public void smrth() {
        userClient.findAll();
    }

    @Test
    public void smrth1() {
        userClient.find(1);
    }
}
