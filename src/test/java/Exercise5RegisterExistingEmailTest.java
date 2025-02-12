import config.Base;
import config.CriticalTests;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.*;

import java.util.ResourceBundle;

@Category(CriticalTests.class)
public class Exercise5RegisterExistingEmailTest extends Base {

    private static MainPage mainPage;
    private static LoginPage loginPage;

    private static ResourceBundle values;

    @Before
    public void begin() {

        driver.get("https://automationexercise.com/");

        mainPage = new MainPage(driver);
        mainPage.handleCookies();

        values = ResourceBundle.getBundle("invalidregister");
    }

    @Test
    @Epic("User management")
    @Feature("Login functionality")
    @Story("User can't create a new account using an email corresponding to an existing account")
    @Severity(SeverityLevel.CRITICAL)
    public void registerExistingEmailTest() {

        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click on 'Signup / Login' button
        mainPage.clickSignupLogin();

        //Step 3. Verify 'New User Signup!' is visible
        loginPage = new LoginPage(driver);
        Assert.assertTrue("Step 3: The sign up text is not visible",loginPage.isSignUpTextVisible());

        //Step 4. Enter name and email address
        loginPage.enterNameEmailSignup(values.getString("username"), values.getString("email"));

        //Step 5. Click 'Signup' button
        loginPage.clickSignUpButton();

        //Step 6. Verify error 'Email Address already exist!' is visible
        Assert.assertTrue("Step 6: 'Email Address already exist!' is not visible",
                loginPage.isAlreadyExistsVisible());
    }

}
