package org.kd.anotherspringtutorial.test;

import com.codeborne.selenide.WebDriverRunner;
import org.kd.anotherspringtutorial.test.utils.Utils;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseUiTest {

    @Value("${screenshots.dir}")
    private String screenshotsDir;

    @Autowired
    private Utils utils;

    private final Logger logger = Logger.getLogger(BaseUiTest.class.getSimpleName());

    public void takeScreenshot(String filename) {
        File screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotPath = screenshotsDir + "\\" + filename + utils.generateTimestamp() +".png";
            logger.log(Level.INFO, "\nSaving screenshot " + screenshotPath);
            FileUtils.copyFile(screenshot, new File(screenshotPath));

        } catch (IOException e) {
            logger.severe("Error saving screenshot " + e.getMessage());
        }
    }
}
