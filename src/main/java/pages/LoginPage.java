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
    By nameInput = new By.ByXPath("//input[@data-qa='signup-name']");
    By emailInput = new By.ByXPath("//input[@data-qa='signup-email']");
    By signUpButton = new By.ByXPath("//button[@data-qa='signup-button']");

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

    public void enterNameEmailSignup(String name, String email) {
       driver.findElement(nameInput).sendKeys(name);
       driver.findElement(emailInput).sendKeys(email);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }
}
