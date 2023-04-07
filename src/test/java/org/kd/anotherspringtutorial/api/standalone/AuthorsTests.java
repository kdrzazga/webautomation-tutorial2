package org.kd.anotherspringtutorial.api.standalone;

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

}
