package org.kd.anotherspringtutorial.api.mocked;

import org.hamcrest.Matchers;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SirThaddeusTests extends BaseApiTest {
    @Autowired
    private UsersData usersData;

    private final Logger logger = Logger.getLogger(SirThaddeusTextTests.class.getSimpleName());

    @BeforeEach
    public void setup() {
        setupStub();
    }

    public void setupStub() {
        configureFor("127.0.0.1", 8981);
        stubFor(get(urlEqualTo("/author/Sir Thaddeus"))
                .withHeader("Accept", matching("\\*\\/\\*"))
                .willReturn(aResponse().
                        withStatus(200).
                        withHeader("Content-Type", "text/plain").
                        withBody("Mocked Adam Mickiewicz"))
        );
    }

    @Test
    public void testStub() {
        var userName = "admin";

        var response = given().baseUri("http://localhost:8981")
                .auth().basic(userName, usersData.getPassword(userName))
                .when().get("/author/Sir Thaddeus")
                .then().extract().response();

        logger.log(Level.INFO, "Response time: " + response.getTimeIn(TimeUnit.MILLISECONDS) + " ms");

        assertEquals(HttpStatus.OK.value(), response.statusCode());
        assertEquals(response.body().prettyPrint(),"Mocked Adam Mickiewicz");
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

        assertEquals(HttpStatus.OK.value(), response.statusCode());

        assertThat(response.cookies().entrySet(), Matchers.hasSize(1));
        assertThat(response.cookie("author"), Matchers.containsString("Mocked Adam Mickiewicz"));

    }
}