package codecamp.bug.wars.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
@EnableEurekaServer
public class BugWarsEurekaApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var requestCrud = HttpRequest.newBuilder(
				URI.create("http://bug-wars-ai-crud.herokuapp.com/ai"))
				.header("accept", "application/json")
				.GET()
				.build();
		var responseCrud = client.send(requestCrud, HttpResponse.BodyHandlers.ofString());
		System.out.println(responseCrud.body());

		var requestGateway = HttpRequest.newBuilder(
				URI.create("http://bug-wars-api-gateway.herokuapp.com/api/ai"))
				.header("accept", "application/json")
				.GET()
				.build();
		var responseGateway = client.send(requestGateway, HttpResponse.BodyHandlers.ofString());
		System.out.println(responseGateway.body());
		SpringApplication.run(BugWarsEurekaApplication.class, args);
	}

}
