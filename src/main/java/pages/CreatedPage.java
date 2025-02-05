package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreatedPage {

    WebDriver driver;

    By createdLabel = new By.ByXPath("//b[text()='Account Created!']");
    By continueButton = new By.ByXPath("//a[@data-qa='continue-button']");

    public CreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean createdIsVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement createdLabelEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.createdLabel));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

}
