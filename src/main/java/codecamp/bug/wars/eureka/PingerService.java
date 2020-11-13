package codecamp.bug.wars.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "bugwars")
public class PingerService implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(PingerService.class);
    private final HttpClient httpClient;

    // Interesting note...
    //
    // To load a list of items from application.yml, you need 3 things:
    // 1. @ConfigurationProperties utilizing a prefix.  The prefix for 'bugwars.endpoints' is 'bugwars'
    // 2. Declare a variable that matches the name of yaml key, in this case 'endpoints'
    // 3. Provide a getter that provides reference to field.
    private List<String> endpoints;

    public PingerService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<String> urls) {
        endpoints = urls;
    }

    public void callEndpoints() throws Exception {
        logger.info("*** Starting Pinger Service for urls: " + endpoints);
        for (String url : endpoints) {
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create(url))
                    .header("accept", "application/json")
                    .GET()
                    .build();
            logger.info("Calling: " + url);
            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        callEndpoints();
    }
}
