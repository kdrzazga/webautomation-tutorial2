package org.kd.anotherspringtutorial.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.kd.anotherspringtutorial.email.EmailSender;
import org.kd.anotherspringtutorial.test.utils.TestType;
import org.kd.anotherspringtutorial.test.utils.Utils;
import org.kd.anotherspringtutorial.test.utils.report.Stats;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestWatcherStats implements TestWatcher, BeforeAllCallback, AfterAllCallback {

    private final Logger logger = Logger.getLogger(TestWatcherStats.class.getSimpleName());
    private final EmailSender emailSender = new EmailSender();

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        Utils.clearScreenshots();
        driverInit(Utils.getTestType(extensionContext));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.log(Level.INFO, context.getDisplayName() + " passed.");
        Stats.addPassedTC();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.severe(context.getDisplayName() + " FAILED !");
        Stats.addFailedTC();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        logger.log(Level.INFO, "Test cases passed: " + Stats.getPassedTcCount() + ", failed: "
                + Stats.getFailedTcCount());
        tearDown(Utils.getTestType(extensionContext));
    }

    private void driverInit(TestType testType) {
        logger.log(Level.INFO, "Test type: " + testType);
        if (TestType.UI.equals(testType)) {
            setupBrowserParams();
        }
    }

    private void tearDown(TestType testType) {
        if (TestType.UI.equals(testType)) {
            WebDriverRunner.getWebDriver().close();
            WebDriverRunner.getWebDriver().quit();
        }

        emailSender.sendReport(testType);
    }

    private static void setupBrowserParams() {
        String webdriverPath = System.getProperty("user.dir") + "\\src\\test\\" + Utils.readProperty("chromedriver.path");
        System.setProperty("webdriver.chrome.driver", webdriverPath);
        Configuration.screenshots = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.driverManagerEnabled = false;
        Configuration.pollingInterval = 500;
        Configuration.holdBrowserOpen = true;
    }

}
