package org.kd.anotherspringtutorial.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

@Component
public class TheInternetPage implements Page {

    @Value("${the.internet.url}")
    private String url;
    private final SelenideElement header = $(".heading");
    private final SelenideElement checkboxesLink = $(byText("Checkboxes"));

    private final Logger logger = Logger.getLogger(TheInternetPage.class.getSimpleName());

    public void navigate() {
        logger.log(Level.INFO, "Navigating to " + url);
        Selenide.open(url);
    }

    public void checkPageLoaded() {
        header.shouldBe(Condition.visible);
        header.shouldHave(Condition.exactText("Welcome to the-internet"));
        logger.log(Level.INFO, "Loaded page: " + TheInternetPage.class.getSimpleName() + "|" + title());
    }

    public void clickLinkCheckboxes() {
        checkboxesLink.shouldBe(Condition.visible);
        checkboxesLink.click();

        logger.log(Level.INFO, "Clicked link " + checkboxesLink.text());
    }
}
