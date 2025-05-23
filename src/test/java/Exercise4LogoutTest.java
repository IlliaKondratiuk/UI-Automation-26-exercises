// This is "Test Case 4: Logout User" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 5.10.0, designing with Page Object Model and generating an Allure(2.32.2) report
// The user data is parsed from src\resources\validlogin.properties

import config.PageNames;
import config.WebDriverConfig;
import config.testConfigs.BaseTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;

import java.util.ResourceBundle;

@Tag("Critical")
public class Exercise4LogoutTest extends BaseTest {

    private static MainPage mainPage;
    private static LoginPage loginPage;

    private static ResourceBundle values;

    @BeforeEach
    public void begin() {
        WebDriverConfig.openURL(PageNames.MAIN.getUrl());

        mainPage = new MainPage();
        mainPage.handleCookies();

        values = ResourceBundle.getBundle("validlogin");
    }

    @Test
    @Epic("User management")
    @Feature("Login functionality")
    @Story("As a user, I can log out of my account to end my session securely.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex4LogoutTest() {
        //Step 1. Check if the logo is visible.
        Assertions.assertTrue(mainPage.logoIsVisible(),
                "Step 1: The logo is not visible");

        //Step 2. Click on 'Signup / Login' button
        mainPage.clickSignupLogin();

        //Step 3. Verify 'Login to your account' is visible
        loginPage = new LoginPage();
        Assertions.assertTrue(loginPage.isLoginTextVisible(),
                "Step 3: The 'Login to your account' label is not visible");

        //Step 4. Enter valid/invalid email address and password
        loginPage.enterEmailPassLogin(values.getString("email"), values.getString("pass"));

        //Step 5. Click 'login' button
        loginPage.clickLoginButton();

        //Step 6. Verify that 'Logged in as username' is visible
        mainPage.initUsername(values.getString("username"));
        Assertions.assertTrue(mainPage.checkLoggedInAsLabelNav(),
                "Step 6: 'Logged in as username' is not visible or the username is incorrect");

        //Step 7. Click 'Logout' button
        mainPage.clickLogOut();

        //Step 8. Verify that user is navigated to login page
        Assertions.assertEquals("https://automationexercise.com/login", WebDriverConfig.getCurrentURL(),
                "Step 8: The user is not navigated to the login page after logging out.");
    }
}
