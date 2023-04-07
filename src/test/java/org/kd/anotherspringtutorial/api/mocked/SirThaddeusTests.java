package org.kd.anotherspringtutorial.api.mocked;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kd.anotherspringtutorial.api.integration.SirThaddeusTextTests;
import org.kd.anotherspringtutorial.test.BaseApiTest;
import org.kd.anotherspringtutorial.test.utils.data.UsersData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class SirThaddeusTests extends BaseApiTest {
    @Autowired
    private UsersData usersData;

    private WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().port(8080));


    private final Logger logger = Logger.getLogger(SirThaddeusTextTests.class.getSimpleName());

    @BeforeEach
    public void setup() {
        wireMockServer.start();

        setupStub();

    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    public void setupStub() {
        configureFor("127.0.0.1", 8089);
        stubFor(get(urlEqualTo("/some/thing"))
                .withHeader("Accept", matching("text/plain"))
                .willReturn(aResponse().
                        withStatus(503).
                        withHeader("Content-Type", "text/html").
                        withBody("Service Not Available"))
        );

        stubFor(get(urlEqualTo("/some/thing"))
                .withHeader("Accept", matching("application/json"))
                .willReturn(aResponse().
                        withStatus(200).
                        withHeader("Content-Type", "application/json")
                        .withBody("{\"serviceStatus\": \"running\"}")
                        .withFixedDelay(2500))
        );
    }

    @Test
    public void testFirstLine() {
        var userName = "admin";

        var response = given().auth()
                .basic(userName, usersData.getPassword(userName))
                .when()
                .get("/read/0")
                .then()
                .extract().response();

        logger.log(Level.INFO, "Response time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + " ms");

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        assertThat(response.getHeader("Location"), Matchers.containsString("8080"));

        assertThat(response.cookies().entrySet(), Matchers.hasSize(1));
        assertThat(response.cookie("author"), Matchers.containsString("Mocked Adam Mickiewicz"));
    }

}
