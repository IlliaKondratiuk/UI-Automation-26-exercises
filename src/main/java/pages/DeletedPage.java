package pages;

import org.openqa.selenium.By;

public class DeletedPage extends CommonElementsPage {

    By deletedLabel = new By.ByXPath("//b[text()='Account Deleted!']");
    By continueButton = new By.ByXPath("//a[@data-qa='continue-button']");

    public DeletedPage() {
        super();
    }

    public boolean deletedLabelIsVisible() {
        return isElementVisible(deletedLabel);
    }

    public void clickContinue() {
        clickElementByLocator(continueButton);
    }
}
