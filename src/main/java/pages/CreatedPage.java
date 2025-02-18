package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatedPage extends BasePage {

    By createdLabel = new By.ByXPath("//b[text()='Account Created!']");
    By continueButton = new By.ByXPath("//a[@data-qa='continue-button']");

    public CreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean createdIsVisible() {
        return isElementVisible(createdLabel);
    }

    public void clickContinue() {
        clickElement(continueButton);
    }
}
