// This is "Test Case 1: Register User" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 5.10.0, designing with Page Object Model and generating an Allure(2.32.2) report
// The user data is parsed from src\resources\validregister.properties

import config.PageNames;
import config.WebDriverConfig;
import config.testConfigs.BaseTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.*;
import java.util.ResourceBundle;

@Tag("Critical")
@Tag("Parallel") //in case you want to check parallel execution
public class Exercise1RegisterUserTest extends BaseTest
{

    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static SignupPage signupPage;
    private static CreatedPage createdPage;
    private static DeletedPage deletedPage;
    private static ResourceBundle values;

    @BeforeEach
    public void begin() {
        WebDriverConfig.openURL(PageNames.MAIN.getUrl());

        mainPage = new MainPage();
        mainPage.handleCookies();

        values = ResourceBundle.getBundle("validregister");
    }

    @Test
    @Epic("User management")
    @Feature("Login functionality")
    @Story("As a user, I can create an account with valid data so that I can access the system and use its features.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex1RegisterDeleteTest() {
        //Step 1. Check if the logo is visible.
        Assertions.assertTrue(mainPage.logoIsVisible(),
                "Step 1: The logo is not visible");

        //Step 2. Click on 'Signup / Login' button
        mainPage.clickSignupLogin();

        //Step 3. Verify 'New User Signup!' is visible
        loginPage = new LoginPage();
        Assertions.assertTrue(loginPage.isSignUpTextVisible(),
                "Step 3: The sign up text is not visible");

        //Step 4. Enter name and email address
        loginPage.enterNameEmailSignup(values.getString("name"), values.getString("email"));

        //Step 5. Click 'Signup' button
        loginPage.clickSignUpButton();

        //Step 6. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        signupPage = new SignupPage();
        Assertions.assertTrue(signupPage.enterAccInfoIsVisible(),
                "Step 6: 'ENTER ACCOUNT INFORMATION' is not visible");

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
        createdPage = new CreatedPage();
        Assertions.assertTrue(createdPage.createdIsVisible(),
                "Step 12: 'ACCOUNT CREATED' is not visible");

        //Step 13. Click 'Continue' button
        createdPage.clickContinue();

        //Step 14. Verify that 'Logged in as username' is visible
        mainPage.initUsername(values.getString("name"));
        Assertions.assertTrue(mainPage.checkLoggedInAsLabelNav(),
                "Step 14: 'Logged in as username' is not visible or the username is incorrect");

        //Step 15. Click 'Delete Account' button
        mainPage.clickDeleteAccButton();

        //Step 16. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        deletedPage = new DeletedPage();
        Assertions.assertTrue(deletedPage.deletedLabelIsVisible(),
                "Step 16: 'ACCOUNT DELETED!' is not visible");
        deletedPage.clickContinue();
    }
}
