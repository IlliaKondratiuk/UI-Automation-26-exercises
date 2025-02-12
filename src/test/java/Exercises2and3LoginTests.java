// These are "Test Case 2: Login User with correct email and password" and
// "Test Case 3: Login User with incorrect email and password"
// from the Automation Exercise page at automationexercise.com.
// Two exercises have been merged into one test class as they test the same login functionality with valid/invalid data.
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense.
// Steps 9(delete user) and 10(confirm delete) from Test Case 2 have been skipped to keep the account for future runs.
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report.
// The credentials are located in the Parameters class.

import config.Base;
import config.CriticalTests;
import io.qameta.allure.*;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@Category(CriticalTests.class)
@RunWith(Parameterized.class)
public class Exercises2and3LoginTests extends Base {

    private static MainPage mainPage;
    private static LoginPage loginPage;

    private final String email;
    private final String password;
    private final String name;

    private final boolean isValid;

    public Exercises2and3LoginTests(String email, String password, boolean isValid) {
        this.email = email;
        this.password = password;
        this.isValid = isValid;
        this.name = "test1";
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                { "anything@qa.test", "q123456_", true }, //both valid credentials to login
                { "anything@qa.test", "wrongPass", false }, //valid email but wrong password
                { "notregistered@qa.test", "q123456_", false } //invalid email
        });
    }

    @Before
    public void begin() {
        driver.get("https://automationexercise.com/");
        mainPage = new MainPage(driver);
        mainPage.handleCookies();
    }

    @After
    public void tearDown() { //restoring the driver state every time the test is run with new parameters
        driver.manage().deleteAllCookies();
    }

    @Test
    @Epic("User management")
    @Feature("Login functionality")
    @Story("User successfully logs in using valid login credentials and can't log in with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void Exercise2and3LoginTest() {

        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click on 'Signup / Login' button
        mainPage.clickSignupLogin();

        //Step 3. Verify 'Login to your account' is visible
        loginPage = new LoginPage(driver);
        Assert.assertTrue("Step 3: The 'Login to your account' label is not visible",
                loginPage.isLoginTextVisible());

        //Step 4. Enter valid/invalid email address and password
        loginPage.enterEmailPassLogin(this.email, this.password);

        //Step 5. Click 'login' button
        loginPage.clickLoginButton();

        if(isValid) { //splitting the test depending on if the credentials valid or not
            //Step 6(Valid data). Verify that 'Logged in as username' is visible
            mainPage.initUsername(this.name);
            Assert.assertTrue("Step 6: 'Logged in as username' is not visible or the username is incorrect",
                    mainPage.checkLoggedInAsLabelNav());

        } else {
            //Step 6(Invalid data). Verify error 'Your email or password is incorrect!' is visible
            Assert.assertTrue("Step 6: 'Your email or password is incorrect!' is  not visible",
                    loginPage.isIncorrectCredentialsVisible());
        }
    }
}
