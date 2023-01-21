package org.kd.anotherspringtutorial.api;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kd.anotherspringtutorial.common.TestWatcherStats;
import org.kd.anotherspringtutorial.utils.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ExtendWith(TestWatcherStats.class)
public class SirThaddeusTextTestsRestTemplate {

    @Value("${port}")
    private String port;

    @Autowired
    private Report report;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testFirstLine() {
        var endpoint = "/read/0";

        ResponseEntity<String> response = restTemplate
                .exchange("http://localhost:" + port + endpoint,
                        HttpMethod.GET, null
                        , String.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertThat(response.getBody(), Matchers.containsString("country"));
    }

    @Test
    public void testSecondLine() {
        var endpoint = "/read/1";

        ResponseEntity<String> response = restTemplate
                .exchange("http://localhost:" + port + endpoint,
                        HttpMethod.GET, null, String.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());

        var actualHeaders = new ArrayList<>(response.getHeaders().get("Content-Type"));
        actualHeaders.removeAll(Arrays.asList(ContentType.TEXT.getContentTypeStrings()));

        assertThat(actualHeaders, Matchers.empty());
    }

    @Test
    public void testNegative() {
        var endpoint = "/read/B@d_reQU3S7";

        try {
            restTemplate.getForEntity("http://localhost:" + port + endpoint,
                    String.class);
        } catch (HttpClientErrorException e) {
            Assertions.fail("Rest Template does not support negative testing for " + HttpStatus.BAD_REQUEST);
        }
    }

}
