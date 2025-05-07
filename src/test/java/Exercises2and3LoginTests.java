// These are "Test Case 2: Login User with correct email and password" and
// "Test Case 3: Login User with incorrect email and password"
// from the Automation Exercise page at automationexercise.com.
// Two exercises have been merged into one test class as they test the same login functionality with valid/invalid data.
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense.
// Steps 9(delete user) and 10(confirm delete) from Test Case 2 have been skipped to keep the account for future runs.
// Using JUnit 5.10.0, designing with Page Object Model and generating an Allure(2.32.2) report.
// The credentials are located in the Parameters class.

import config.PageNames;
import config.testConfigs.BaseTest;
import io.qameta.allure.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;
import pages.MainPage;

@Tag("Critical")
public class Exercises2and3LoginTests extends BaseTest {

    private static MainPage mainPage;
    private static LoginPage loginPage;

    private final String name = "test1";

    @BeforeEach
    public void begin() {
        driver.get(PageNames.MAIN.getUrl());

        mainPage = new MainPage(driver);
        mainPage.handleCookies();
    }

    @AfterEach
    public void tearDown() { //restoring the driver state every time the test is run with new parameters
        driver.manage().deleteAllCookies();
    }

    @Epic("User management")
    @Feature("Login functionality")
    @Story("As a user, I can log in to my account so that I can access the system securely.")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @CsvSource({"anything@qa.test, q123456_, true",
            "anything@qa.test, wrongPass, false",
            "notregistered@qa.test, q123456_, false"})
    public void Ex2and3LoginTest(String email, String password, boolean isValid) {
        //Step 1. Check if the logo is visible.
        Assertions.assertTrue(mainPage.logoIsVisible(), "Step 1: The logo is not visible");

        //Step 2. Click on 'Signup / Login' button
        mainPage.clickSignupLogin();

        //Step 3. Verify 'Login to your account' is visible
        loginPage = new LoginPage(driver);
        Assertions.assertTrue(loginPage.isLoginTextVisible(),
                "Step 3: The 'Login to your account' label is not visible");

        //Step 4. Enter valid/invalid email address and password
        loginPage.enterEmailPassLogin(email, password);

        //Step 5. Click 'login' button
        loginPage.clickLoginButton();

        if(isValid) { //splitting the test depending on if the credentials valid or not
            //Step 6(Valid data). Verify that 'Logged in as username' is visible
            mainPage.initUsername(name);
            Assertions.assertTrue(mainPage.checkLoggedInAsLabelNav(),
                    "Step 6: 'Logged in as username' is not visible or the username is incorrect");

        } else {
            //Step 6(Invalid data). Verify error 'Your email or password is incorrect!' is visible
            Assertions.assertTrue(loginPage.isIncorrectCredentialsVisible(),
                    "Step 6: 'Your email or password is incorrect!' is  not visible");
        }
    }
}
