package org.kd.anotherspringtutorial.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kd.anotherspringtutorial.api.helpers.SirThaddeusTestWatcher;
import org.kd.anotherspringtutorial.utils.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ExtendWith(SirThaddeusTestWatcher.class)
public class SirThaddeusTests {

    @Autowired
    private Report report;

    @Test
    public void testFirstLine() {
        Response response = given().get("/read/0")
                .then()
                .extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        assertThat(response.body().prettyPrint(), Matchers.containsString("country"));
    }

    @Test
    public void testSecondLine() {
        Response response = given().get("/read/1")
                .then()
                .extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assertions.assertEquals(ContentType.TEXT.getContentTypeStrings()[0], response.headers().get("Content-Type").getValue());
    }

    @Test
    public void testNegative() {
        Response response = given().get("/read/B@d_reQU3S7")
                .then()
                .extract().response();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
        Assertions.assertEquals(ContentType.TEXT.getContentTypeStrings()[0], response.headers().get("Content-Type").getValue());
        assertThat(response.body().print(), Matchers.emptyString());
    }

    @Test
    public void testPurposelyFailed(){
        Assertions.fail("Test failed on purpose, for statistics");
    }
}
