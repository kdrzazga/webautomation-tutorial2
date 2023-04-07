package org.kd.anotherspringtutorial.api;

import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kd.anotherspringtutorial.common.TestWatcherStats;
import org.kd.anotherspringtutorial.test.BaseApiTest;
import org.kd.anotherspringtutorial.test.utils.data.UsersData;
import org.kd.anotherspringtutorial.webapp.controller.AuthorsController;
import org.kd.anotherspringtutorial.webapp.controller.clients.GlobalInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ExtendWith(TestWatcherStats.class)
public class AuthorsTests extends BaseApiTest {

    @Autowired
    private UsersData usersData;

    private GlobalInfoClient feignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new Encoder.Default())
                .decoder(new Decoder.Default())
                .logger(new Slf4jLogger(AuthorsController.class))
                .logLevel(Logger.Level.FULL)
                .target(GlobalInfoClient.class, "http://localhost:8080/");

    @Test
    public void testFindAuthor() {
        var userName = "admin";

        var response = given().auth()
                .basic(userName, usersData.getPassword(userName))
                .when()
                .get("/author/Odyssey")
                .then()
                .extract().response();

        Assertions.assertEquals(response.body().prettyPrint(), "Homer");
    }

    @Test
    public void testFindAuthorWithFeign() {
        assertThat(feignClient.getInfo(), Matchers.containsStringIgnoringCase("hello"));
    }

}
