package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    By signUpText = new By.ByXPath("//h2[text()='New User Signup!']");
    By loginText = new By.ByXPath("//h2[text()='Login to your account']");
    By alreadyExistsText = new By.ByXPath("//p[text()='Email Address already exist!']");

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
        return isElementVisible(signUpText);
    }

    public boolean isLoginTextVisible() {
        return isElementVisible(loginText);
    }

    public boolean isIncorrectCredentialsVisible() {
        return isElementVisible(incorrectCredentialsMessage);
    }

    public boolean isAlreadyExistsVisible() {
        return isElementVisible(alreadyExistsText);
    }

    public void enterNameEmailSignup(String name, String email) {
        fillInput(nameSignupInput, name);
        fillInput(emailSignupInput, email);
    }

    public void enterEmailPassLogin(String email, String pass) {
        fillInput(emailLoginInput, email);
        fillInput(passLoginInput, pass);
    }

    public void clickSignUpButton() {
        clickElement(signUpButton);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }
}
