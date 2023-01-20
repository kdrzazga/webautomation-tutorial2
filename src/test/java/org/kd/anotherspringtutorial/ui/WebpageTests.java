package org.kd.anotherspringtutorial.ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.TestWatcher;
import org.kd.anotherspringtutorial.pages.CheckboxesPage;
import org.kd.anotherspringtutorial.pages.TheInternetPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebpageTests implements TestWatcher {

    @Autowired
    private TheInternetPage theInternetPage;

    @Autowired
    private CheckboxesPage checkboxesPage;

    @Test
    public void testMainPage() {
        theInternetPage.navigate();
        theInternetPage.checkPageLoaded();
    }

    @Test
    public void testCheckboxesPage() {
        checkboxesPage.navigateIndirect();
        checkboxesPage.checkPageLoaded();
    }

    @AfterAll
    public static void after() {
        Logger logger = Logger.getLogger(WebpageTests.class.getSimpleName());
        logger.log(Level.INFO, "Finished Test suite");
    }

}
