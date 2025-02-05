package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeletedPage {

    WebDriver driver;

    By deletedLabel = new By.ByXPath("//b[text()='Account Deleted!']");
    By continueButton = new By.ByXPath("//a[@data-qa='continue-button']");

    public DeletedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean deletedLabelIsVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement deletedLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(this.deletedLabel));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

}
