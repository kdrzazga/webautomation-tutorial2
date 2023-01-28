package org.kd.anotherspringtutorial.common;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.kd.anotherspringtutorial.common.utils.Utils;
import org.kd.anotherspringtutorial.test.utils.report.Stats;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestWatcherStats implements TestWatcher, BeforeAllCallback, AfterAllCallback {

    private final Logger logger = Logger.getLogger(TestWatcherStats.class.getSimpleName());

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Utils.clearScreenshots();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.log(Level.INFO, context.getDisplayName() + " passed.");
        Stats.addPassedTC();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.severe( context.getDisplayName() + " FAILED !");
        Stats.addFailedTC();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        logger.log(Level.INFO, "Test cases passed: " + Stats.getPassedTcCount() + ", failed: "
                + Stats.getFailedTcCount());
    }

}
