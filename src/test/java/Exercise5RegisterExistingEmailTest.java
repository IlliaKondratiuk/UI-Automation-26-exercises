// This is "Test Case 5: Register User with existing email" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 5.10.0, designing with Page Object Model and generating an Allure(2.32.2) report
// The user data is parsed from src\resources\invalidregister.properties

import config.PageNames;
import config.testConfigs.BaseTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import pages.*;

import java.util.ResourceBundle;

@Tag("Critical")
class Exercise5RegisterExistingEmailTest extends BaseTest {

    private static MainPage mainPage;
    private static LoginPage loginPage;

    private static ResourceBundle values;

    @BeforeEach
    public void begin() {
        driver.get(PageNames.MAIN.getUrl());

        mainPage = new MainPage();
        mainPage.handleCookies();

        values = ResourceBundle.getBundle("invalidregister");
    }

    @Test
    @Epic("User management")
    @Feature("Login functionality")
    @Story("As a user, I cannot register with an email that is already in use so that my account remains unique and secure.")
    @Severity(SeverityLevel.CRITICAL)
    void Ex5RegisterExistingEmailTest() {
        //Step 1. Check if the logo is visible.
        Assertions.assertTrue(mainPage.logoIsVisible(), "Step 1: The logo is not visible");

        //Step 2. Click on 'Signup / Login' button
        mainPage.clickSignupLogin();

        //Step 3. Verify 'New User Signup!' is visible
        loginPage = new LoginPage();
        Assertions.assertTrue(loginPage.isSignUpTextVisible(),
                "Step 3: The sign up text is not visible");

        //Step 4. Enter name and email address
        loginPage.enterNameEmailSignup(values.getString("username"), values.getString("email"));

        //Step 5. Click 'Signup' button
        loginPage.clickSignUpButton();

        //Step 6. Verify error 'Email Address already exist!' is visible
        Assertions.assertTrue(loginPage.isAlreadyExistsVisible(),
                "Step 6: 'Email Address already exist!' is not visible");
    }

}
