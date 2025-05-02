package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeletedPage extends CommonElements {

    By deletedLabel = new By.ByXPath("//b[text()='Account Deleted!']");
    By continueButton = new By.ByXPath("//a[@data-qa='continue-button']");

    public DeletedPage(WebDriver driver) {
        super(driver);
    }

    public boolean deletedLabelIsVisible() {
        return isElementVisible(deletedLabel);
    }

    public void clickContinue() {
        clickElementByLocator(continueButton);
    }
}
