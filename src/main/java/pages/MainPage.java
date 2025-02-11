package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    WebDriver driver;

    By logo = new By.ByXPath("//div[contains(@class, 'logo')]//a//img");

    By signupLoginButton = new By.ByXPath("//a[@href='/login']");
    By cookieConsentButton = new By.ByXPath("//button[contains(@class, 'fc-cta-consent')]");
    By loggedInAsLabelNav = new By.ByXPath("//i[contains(@class, 'fa-user')]");
    By usernameNav;
    By deleteAccButton = new By.ByXPath("//a[@href='/delete_account']");
    By logOutButton = new By.ByXPath("//a[@href='/logout']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void initUsername(String username) {
        usernameNav = new By.ByXPath("//i[contains(@class, 'fa-user')]/parent::a/b[text()=\"" + username + "\"]");
    }

    public void handleCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement cookieConsentButtonEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.cookieConsentButton));
            cookieConsentButtonEl.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie consent popup did not appear, continuing test...");
        }
    }

    public boolean logoIsVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(this.logo));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void clickSignupLogin() {
        driver.findElement(signupLoginButton).click();
    }
    public void clickLogOut() {
        driver.findElement(logOutButton).click();
    }

    public boolean checkLoggedInAsLabelNav() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loggedInAsLabelEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.loggedInAsLabelNav));
            WebElement usernameNavEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.usernameNav));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void clickDeleteAccButton() {
        driver.findElement(deleteAccButton).click();
    }
}
