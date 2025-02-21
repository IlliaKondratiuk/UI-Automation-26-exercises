// This is "Test Case 6: Contact Us Form" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report
// The user data is parsed from src\resources\contactvalues.properties
// The dummy file for step 5 is stored in src\resources\contactfile.pdf

import config.Base;
import config.CriticalTests;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.ContactPage;
import pages.MainPage;

import java.util.ResourceBundle;

@Category(CriticalTests.class)
public class Exercise6ContactUsTest extends Base {

    private static MainPage mainPage;
    private static ContactPage contactPage;

    private ResourceBundle values;

    private final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\contactfile.pdf";

    @Before
    public void begin() {
        driver.get("https://automationexercise.com/");

        mainPage = new MainPage(driver);
        mainPage.handleCookies();

        values = ResourceBundle.getBundle("contactvalues");
    }

    @Test
    @Epic("User management")
    @Feature("Contact form")
    @Story("As a user, I can fill out and submit a contact form so that I can communicate with the support team.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex6ContactUsTest() {
        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click on 'Contact Us' button
        mainPage.clickContactUsButton();

        //Step 3. Verify 'GET IN TOUCH' is visible
        contactPage = new ContactPage(driver);
        Assert.assertTrue("Step 3: 'GET IN TOUCH' is not visible", contactPage.getInTouchIsVisible());

        //Step 4. Enter name, email, subject and message
        contactPage.enterName(values.getString("name"));
        contactPage.enterEmail(values.getString("email"));
        contactPage.enterSubject(values.getString("subject"));
        contactPage.enterMessage(values.getString("message"));

        //Step 5. Upload file
        contactPage.uploadFile(filePath);

        //Step 6. Click 'Submit' button
        contactPage.clickSubmitButton();

        //Step 7. Click OK button
        driver.switchTo().alert().accept();

        //Step 8. Verify success message 'Success! Your details have been submitted successfully.' is visible
        Assert.assertTrue("Step 8: success message 'Success! Your details have been submitted successfully.' is not visible",
                contactPage.isSuccessTextVisible());

        //Step 9. Click 'Home' button and verify that landed to home page successfully
        contactPage.clickHomeButton();
        Assert.assertEquals("Step 9: The user is not navigated to the login page after logging out.",
                "https://automationexercise.com/", driver.getCurrentUrl());
    }
}
