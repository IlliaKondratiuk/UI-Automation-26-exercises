package pages;

import org.openqa.selenium.By;

public class CreatedPage extends CommonElementsPage {

    By createdLabel = new By.ByXPath("//b[text()='Account Created!']");
    By continueButton = new By.ByXPath("//a[@data-qa='continue-button']");

    public CreatedPage() {
        super();
    }

    public boolean createdIsVisible() {
        return isElementVisible(createdLabel);
    }

    public void clickContinue() {
        clickElementByLocator(continueButton);
    }
}
