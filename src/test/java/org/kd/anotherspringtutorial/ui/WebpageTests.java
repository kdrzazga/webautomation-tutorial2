package org.kd.anotherspringtutorial.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kd.anotherspringtutorial.common.TestWatcherStats;
import org.kd.anotherspringtutorial.pages.CheckboxesPage;
import org.kd.anotherspringtutorial.pages.TheInternetPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestWatcherStats.class)
public class WebpageTests extends BaseUiTest {

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

        this.takeScreenshot("checkboxes");
    }

}
