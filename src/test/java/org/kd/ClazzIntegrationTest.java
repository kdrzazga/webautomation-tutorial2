package org.kd;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kd.appclasses.Clazz;
import org.kd.main.MainApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {MainApp.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClazzIntegrationTest {

    @Autowired
    private Clazz clazz;

    @Test
    public void testClazzInject() {
        assertNotNull(clazz);
        assertEquals("info", clazz.info());
        assertEquals("8081", clazz.getPort());
    }

}
