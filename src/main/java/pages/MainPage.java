package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    By logo = new By.ByXPath("//div[contains(@class, 'logo')]//a//img");

    By productsButton = new By.ByXPath("//a[@href='/products']");
    By signupLoginButton = new By.ByXPath("//a[@href='/login']");
    By testCasesButton = new By.ByXPath("//a[@href='/test_cases']");
    By cookieConsentButton = new By.ByXPath("//button[contains(@class, 'fc-cta-consent')]");
    By deleteAccButton = new By.ByXPath("//a[@href='/delete_account']");
    By logOutButton = new By.ByXPath("//a[@href='/logout']");
    By contactUsButton = new By.ByXPath("//a[@href='/contact_us']");

    By loggedInAsLabelNav = new By.ByXPath("//i[contains(@class, 'fa-user')]");
    By usernameNav;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void initUsername(String username) {
        usernameNav = new By.ByXPath("//i[contains(@class, 'fa-user')]/parent::a/b[text()=\"" + username + "\"]");
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

    public boolean logoIsVisible() {
        return isElementVisible(logo);
    }

    public void clickSignupLogin() {
        driver.findElement(signupLoginButton).click();
    }
    public void clickLogOut() {
        driver.findElement(logOutButton).click();
    }

    public boolean checkLoggedInAsLabelNav() {
        return isElementVisible(loggedInAsLabelNav) && isElementVisible(usernameNav);
    }

    public void clickDeleteAccButton() {
        clickElement(deleteAccButton);
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
}
