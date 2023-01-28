package org.kd.anotherspringtutorial.api;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.kd.anotherspringtutorial.test.BaseApiTest;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
public class GlobalInfoTests extends BaseApiTest {

    @Test
    public void testHello() {
        when().get("/")
                .then().statusCode(HttpStatus.SC_OK)
                .and()
                .body(containsString("HELLO"));
    }

}
