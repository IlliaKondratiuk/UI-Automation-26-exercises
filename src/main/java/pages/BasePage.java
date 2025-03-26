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

    By logo = new By.ByXPath("//div[contains(@class, 'logo')]//a//img");

    By productsButton = new By.ByXPath("//a[@href='/products']");
    By signupLoginButton = new By.ByXPath("//a[@href='/login']");
    By testCasesButton = new By.ByXPath("//a[@href='/test_cases']");
    By logOutButton = new By.ByXPath("//a[@href='/logout']");
    By contactUsButton = new By.ByXPath("//a[@href='/contact_us']");
    By homeButton = new By.ByXPath("//a[@href='/']");
    By cookieConsentButton = new By.ByXPath("//button[contains(@class, 'fc-cta-consent')]");
    By subscriptionArrowButton = new By.ById("subscribe");
    By deleteAccButton = new By.ByXPath("//a[@href='/delete_account']");

    By loggedInAsLabelNav = new By.ByXPath("//i[contains(@class, 'fa-user')]");
    By usernameNav;

    By adsBottom = new By.ByClassName("grippy-host");
    By subscriptionFooterLabel = new By.ByXPath("//div[@class='single-widget']//h2[text()='Subscription']");
    By subscriptionSuccessLabel = new By.ByXPath("//div[contains(@class, 'alert-success') and text()='You have been successfully subscribed!']");

    By subscribeEmailInput = new By.ById("susbscribe_email");

    protected void clickElement(By el) {
        driver.findElement(el).click();
    }

    protected void clickElement(By el, int secondsToWait) {
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

    public void clickHomeButton() {
        clickElement(homeButton);
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

    public void handleAds() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(adsBottom));

        //Although using Thread.sleep() is a bad practice, this situation is caused by the fact that there are ads
        //on the website during testing, which is unlikely to happen in a proper testing environment. The Google Ads
        //arrow on the bottom ad isn't clickable until the ad is fully displayed on the page, which is not trackable
        //by ExpectedConditions like ElementIsVisible or ElementIsClickable, so the use of Thread.sleep was chosen.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            clickElement(adsBottom);
        } catch (NoSuchElementException e) {
            System.out.println("The ads weren't loaded therefore handling was skipped");
        }
    }

    public void scrollToTheBottom() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickSignupLogin() {
        clickElement(signupLoginButton);
    }

    public void clickLogOut() {
        clickElement(logOutButton);
    }

    public void clickContactUsButton() {
        clickElement(contactUsButton);
    }

    public void clickTestCasesButton() {
        clickElement(testCasesButton);
    }

    public void clickProductsButton() {
        clickElement(productsButton);
    }

    public void enterSubscriptionEmail(String email) {
        fillInput(subscribeEmailInput, email);
    }

    public void clickSubscriptionArrow() {
        clickElement(subscriptionArrowButton);
    }

    public void initUsername(String username) {
        usernameNav = new By.ByXPath("//i[contains(@class, 'fa-user')]/parent::a/b[text()=\"" + username + "\"]");
    }

    public boolean logoIsVisible() {
        return isElementVisible(logo);
    }

    public boolean checkLoggedInAsLabelNav() {
        return isElementVisible(loggedInAsLabelNav) && isElementVisible(usernameNav);
    }

    public boolean isFooterSubscriptionLabelVisible() {
        return isElementVisible(subscriptionFooterLabel);
    }

    public boolean isSubscriptionSuccessLabelVisible() {
        return isElementVisible(subscriptionSuccessLabel);
    }

    public void clickDeleteAccButton() {
        clickElement(deleteAccButton);
    }

    public void scrollToAndClickElement(By locator) {
        scrollToElement(locator);
        clickElement(locator);
    }

}
