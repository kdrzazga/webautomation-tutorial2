package org.kd.anotherspringtutorial.api.helpers;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.kd.anotherspringtutorial.utils.report.Stats;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SirThaddeusTestWatcher implements TestWatcher, AfterAllCallback {

    private final Logger logger = Logger.getLogger(SirThaddeusTestWatcher.class.getSimpleName());

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
