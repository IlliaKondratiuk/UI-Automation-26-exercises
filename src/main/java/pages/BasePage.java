//Base page class that contains universal methods for all child page classes

package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;

    By cookieConsentButton = new By.ByXPath("//button[contains(@class, 'fc-cta-consent')]");

    public void clickElement(By el) {
        driver.findElement(el).click();
    }

    public boolean isElementVisible(By el) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(el));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void handleCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement cookieConsentButtonEl = wait.until(ExpectedConditions.visibilityOfElementLocated(cookieConsentButton));
            cookieConsentButtonEl.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie consent popup did not appear, continuing test...");
        }
    }

    public void fillInput(By el, String text) {
        driver.findElement(el).sendKeys(text);
    }

    public void scrollToElement(By locator) {
        WebElement el = driver.findElement(locator);
        new Actions(driver)
                .scrollToElement(el)
                .perform();
    }
}
