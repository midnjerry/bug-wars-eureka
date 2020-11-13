package codecamp.bug.wars.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
@EnableEurekaServer
public class BugWarsEurekaApplication {

    @Autowired
    PingerService pingerService;

    public static void main(String[] args) {
        SpringApplication.run(BugWarsEurekaApplication.class, args);
    }

    @Bean
    public HttpClient HttpClient() {
        return HttpClient.newHttpClient();
    }

}
