import config.Base;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@Epic("User management")
@Feature("Login functionality")
@Story("User successfully logs in using valid login credentials")
@Severity(SeverityLevel.CRITICAL)
@RunWith(Parameterized.class)
public class Exercises2and3LoginTests extends Base {

    private static MainPage mainPage;
    private static LoginPage loginPage;
    private final String email;
    private final String password;
    private final boolean isValid;

    public Exercises2and3LoginTests(String email, String password, boolean isValid) {

        this.email = email;
        this.password = password;
        this.isValid = isValid;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {

        return Arrays.asList(new Object[][] {
                { "anything@qa.test", "q123456_", true }, //both valid credentials to login
                { "anything@qa.test", "wrongPass", false }, //valid email but wrong password
                { "notregistered@qa.test", "q123456_", false } //invalid email
        });
    }

    @BeforeClass
    public static void begin() {

        driver.get("https://automationexercise.com/");
        mainPage = new MainPage(driver);
        mainPage.handleCookies();
    }

    @Test
    public void Exercise2and3LoginTest() {

        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click on 'Signup / Login' button
        mainPage.clickSignupLogin();

        //Step 3. Verify 'Login to your account' is visible
        loginPage = new LoginPage(driver);
        Assert.assertTrue("Step 3: The 'Login to your account' label is not visible",
                loginPage.isLoginTextVisible());

        if(isValid) {

            //Step 4. Enter correct email address and password

            //Step 5. Click 'login' button

            //Step 6. Verify that 'Logged in as username' is visible

            //Step 7. Click 'Delete Account' button

            //Step 8. Verify that 'ACCOUNT DELETED!' is visible

        } else {

            //Step 4. Enter incorrect email address and password

            //Step 5. Click 'login' button

            //Step 6. Verify error 'Your email or password is incorrect!' is visible
        }
    }
}
