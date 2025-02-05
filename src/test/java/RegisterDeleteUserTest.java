// This is "Test Case 1: Register User" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure() report
// The user data is parsed from src\resources\ex1values

import config.Base;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.*;

import java.util.ResourceBundle;

@Epic("User management")
@Feature("Login functionality")
@Story("User creates and deletes an account with valid data")
@Severity(SeverityLevel.CRITICAL)
public class RegisterDeleteUserTest extends Base
{
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static SignupPage signupPage;
    private static CreatedPage createdPage;
    private static DeletedPage deletedPage;
    private static ResourceBundle values;

    @BeforeClass
    public static void begin() {
    driver.get("https://automationexercise.com/");

    mainPage = new MainPage(driver);
    mainPage.handleCookies();

    values = ResourceBundle.getBundle("ex1values");
    }

    @Test
    public void registerDeleteTest() {

        //Step 1. Checking if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Clicking on Signup/Login
        mainPage.clickSignupLogin();

        //Step 3. Verify 'New User Signup!' is visible
        loginPage = new LoginPage(driver);
        Assert.assertTrue("Step 3: The sign up text is not visible",loginPage.isSignUpTextVisible());

        //Step 4. Enter name and email address
        loginPage.enterNameEmailSignup(values.getString("name"), values.getString("email"));

        //Step 5. Click 'Signup' button
        loginPage.clickSignUpButton();

        //Step 6. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        signupPage = new SignupPage(driver);
        Assert.assertTrue(signupPage.enterAccInfoIsVisible());

        //Step 7. Fill details: Title, Email, Password, Date of birth
        signupPage.selectTitle();
        signupPage.enterPass(values.getString("pass"));
        signupPage.selectDOB(values.getString("dobday"), values.getString("dobmon"), values.getString("dobyear"));

        //Step 8. Select checkbox 'Sign up for our newsletter!'

        signupPage.checkNewsletterBox();

        //Step 9. Select checkbox 'Receive special offers from our partners!'

        signupPage.checkOffersBox();

        //Step 10. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number

        signupPage.enterFirstName(values.getString("firstName"));
        signupPage.enterLastName(values.getString("lastName"));
        signupPage.enterCompany(values.getString("company"));
        signupPage.enterAddress1(values.getString("address1"));
        signupPage.enterAddress2(values.getString("address2"));
        signupPage.selectCountry(values.getString("country"));
        signupPage.enterState(values.getString("state"));
        signupPage.enterCity(values.getString("city"));
        signupPage.enterZipcode(values.getString("zip"));
        signupPage.enterMobileNumber(values.getString("number"));

        //Step 11. Click 'Create Account button'

        signupPage.clickCreate();

        //Step 12. Verify that 'ACCOUNT CREATED!' is visible

        createdPage = new CreatedPage(driver);
        Assert.assertTrue("Step 12: 'ACCOUNT CREATED' is not visible", createdPage.createdIsVisible());


        //Step 13. Click 'Continue' button
        createdPage.clickContinue();

        //Step 14. Verify that 'Logged in as username' is visible
        mainPage.initUsername(values.getString("name"));
        Assert.assertTrue("Step 14: 'Logged in as username' is not visible or the username is incorrect",
                mainPage.checkLoggedInAsLabelNav());

        //Step 15. Click 'Delete Account' button
        mainPage.clickDeleteAccButton();

        deletedPage = new DeletedPage(driver);
        Assert.assertTrue("Step 16: 'ACCOUNT DELETED!' is not visible", deletedPage.deletedLabelIsVisible());
        deletedPage.clickContinue();
    }
}
