package codecamp.bug.wars.eureka;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.http.HttpClient;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest(properties = {"bugwars.endpoints=http://abc.com, http://def.com, http://ghi.com"})
class BugWarsEurekaApplicationTests {

    @Autowired
    PingerService pingerService;

    @MockBean
    HttpClient httpClient;

    @Test
    void contextLoads() {
    }

    @Test
    void configLoadsUrlsForPingerService() {
        assertEquals(Arrays.asList("http://abc.com", "http://def.com", "http://ghi.com"), pingerService.getEndpoints());
    }

}
