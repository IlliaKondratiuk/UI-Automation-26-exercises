package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    By signUpText = new By.ByXPath("//h2[text()='New User Signup!']");
    By loginText = new By.ByXPath("//h2[text()='Login to your account']");

    By nameSignupInput = new By.ByXPath("//input[@data-qa='signup-name']");
    By emailSignupInput = new By.ByXPath("//input[@data-qa='signup-email']");
    By emailLoginInput = new By.ByXPath("//input[@data-qa='login-email']");
    By passLoginInput = new By.ByXPath("//input[@data-qa='login-password']");

    By signUpButton = new By.ByXPath("//button[@data-qa='signup-button']");
    By loginButton = new By.ByXPath("//button[@data-qa='login-button']");

    By incorrectCredentialsMessage = new By.ByXPath("//p[text()='Your email or password is incorrect!']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSignUpTextVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement signupTextEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.signUpText));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isLoginTextVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginTextEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.loginText));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isIncorrectCredentialsVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement incorrectCredentialsLabel =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(this.incorrectCredentialsMessage));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void enterNameEmailSignup(String name, String email) {
       driver.findElement(nameSignupInput).sendKeys(name);
       driver.findElement(emailSignupInput).sendKeys(email);
    }

    public void enterEmailPassLogin(String email, String pass) {
       driver.findElement(emailLoginInput).sendKeys(email);
       driver.findElement(passLoginInput).sendKeys(pass);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


}
