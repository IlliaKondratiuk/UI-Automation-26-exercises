//Base page class that contains universal methods for all child page classes

package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    protected void clickElementByLocator(By el) {
        driver.findElement(el).click();
    }

    protected void clickElementByLocator(By el, int secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        wait.until(ExpectedConditions.elementToBeClickable(el));
        driver.findElement(el).click();
    }

    protected boolean isElementVisible(By el) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(el));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    protected boolean isElementVisible(By el, int secondsToWait) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(secondsToWait)).until(ExpectedConditions.visibilityOfElementLocated(el));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    protected void fillInput(By el, String text) {
        driver.findElement(el).clear(); //added in case the input has a default value
        driver.findElement(el).sendKeys(text);
    }

    protected void scrollToElement(By locator) {
        WebElement el = driver.findElement(locator);
        new Actions(driver)
                .scrollToElement(el)
                .perform();
    }

    protected void scrollToByIndex(By locator, int index) {
        WebElement el = driver.findElements(locator).get(index);
        new Actions(driver)
                .scrollToElement(el)
                .perform();
    }

    public void scrollToTheTop() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, 0)");
    }

    public void scrollToTheBottom() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToAndClickElement(By locator) {
        scrollToElement(locator);
        clickElementByLocator(locator);
    }

    public boolean isAtTopOfPage() {
        Long scrollPosition = (Long) ((JavascriptExecutor) driver)
                .executeScript("return window.pageYOffset;");
        return scrollPosition == 0;
    }

}
