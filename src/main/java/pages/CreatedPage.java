package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatedPage extends CommonElements {

    By createdLabel = new By.ByXPath("//b[text()='Account Created!']");
    By continueButton = new By.ByXPath("//a[@data-qa='continue-button']");

    public CreatedPage(WebDriver driver) {
        super(driver);
    }

    public boolean createdIsVisible() {
        return isElementVisible(createdLabel);
    }

    public void clickContinue() {
        clickElementByLocator(continueButton);
    }
}
