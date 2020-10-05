package codecamp.bug.wars.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BugWarsEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugWarsEurekaApplication.class, args);
	}

}
