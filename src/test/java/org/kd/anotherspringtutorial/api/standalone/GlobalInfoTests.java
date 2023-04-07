package org.kd.anotherspringtutorial.api.standalone;

import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kd.anotherspringtutorial.test.BaseApiTest;
import org.kd.anotherspringtutorial.webapp.controller.AuthorsController;
import org.kd.anotherspringtutorial.webapp.controller.clients.GlobalInfoClient;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
public class GlobalInfoTests extends BaseApiTest {

    @Test
    public void testHello() {
        try {
            when().get("/")
                    .then().statusCode(HttpStatus.SC_OK)
                    .and()
                    .body(containsString("HELLO"));
        } catch (Exception cex) {
            Assertions.fail(cex.getMessage());
        }
    }

    private GlobalInfoClient feignClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new Encoder.Default())
            .decoder(new Decoder.Default())
            .logger(new Slf4jLogger(AuthorsController.class))
            .logLevel(Logger.Level.FULL)
            .target(GlobalInfoClient.class, "http://localhost:8080/");

    @Test
    public void testGIwithFeign() {
        assertThat(feignClient.getInfo(), Matchers.containsStringIgnoringCase("hello"));
    }

}
