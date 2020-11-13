package codecamp.bug.wars.eureka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PingerServiceTest {

    PingerService pingerService;
    HttpClient httpClient;
    List<String> urls;

    @BeforeEach
    public void setup() {
        httpClient = mock(HttpClient.class);
        urls = Arrays.asList("http://abc.com", "http://def.com", "http://ghi.com");
        pingerService = new PingerService(httpClient);
        pingerService.setEndpoints(urls);
    }

    @Test
    public void shouldCallAllUrls() throws Exception {
        //arrange
        HttpRequest request0 = getRequest(urls.get(0));
        HttpRequest request1 = getRequest(urls.get(1));
        HttpRequest request2 = getRequest(urls.get(2));
        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);

        //act
        pingerService.callEndpoints();

        //assert
        verify(httpClient, times(3)).sendAsync(requestCaptor.capture(), eq(HttpResponse.BodyHandlers.ofString()));

        List<HttpRequest> requests = requestCaptor.getAllValues();
        assertEquals(request0, requests.get(0));
        assertEquals(request1, requests.get(1));
        assertEquals(request2, requests.get(2));
    }

    private HttpRequest getRequest(String url) {
        return HttpRequest.newBuilder(
                URI.create(url))
                .header("accept", "application/json")
                .GET()
                .build();
    }


}