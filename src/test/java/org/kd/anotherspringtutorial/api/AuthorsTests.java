package org.kd.anotherspringtutorial.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kd.anotherspringtutorial.common.TestWatcherStats;
import org.kd.anotherspringtutorial.test.BaseApiTest;
import org.kd.anotherspringtutorial.test.utils.data.UsersData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

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
