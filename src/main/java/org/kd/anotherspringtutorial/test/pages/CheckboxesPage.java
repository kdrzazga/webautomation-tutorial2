package org.kd.anotherspringtutorial.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

@Component
public class CheckboxesPage implements Page{

    @Value("${checkboxes.url}")
    private String url;
    private final SelenideElement header = $(".example > h3");

    @Autowired
    private TheInternetPage theInternetPage;

    private final Logger logger = Logger.getLogger(CheckboxesPage.class.getSimpleName());

    public void navigateIndirect() {
        theInternetPage.navigate();
        logger.log(Level.INFO, "Loaded page: " + CheckboxesPage.class.getSimpleName() + "|" + title());
        theInternetPage.clickLinkCheckboxes();
    }

    public void navigate() {
        Selenide.open(url);
    }

    public void checkPageLoaded() {
        header.should(Condition.visible);
        header.shouldHave(Condition.exactText("Checkboxes"));
    }
}
