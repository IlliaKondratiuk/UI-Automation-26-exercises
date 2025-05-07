// This is "Test Case 6: Contact Us Form" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report
// The user data is parsed from src\resources\contactvalues.properties
// The dummy file for step 5 is stored in src\resources\contactfile.pdf

import config.PageNames;
import config.testConfigs.BaseTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ContactPage;
import pages.MainPage;

import java.util.ResourceBundle;

@Tag("Critical")
class Exercise6ContactUsTest extends BaseTest {

    private static MainPage mainPage;
    private static ContactPage contactPage;

    private ResourceBundle values;

    private final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\contactfile.pdf";

    @BeforeEach
    public void begin() {
        driver.get(PageNames.MAIN.getUrl());

        mainPage = new MainPage(driver);
        mainPage.handleCookies();

        values = ResourceBundle.getBundle("contactvalues");
    }

    @Test
    @Epic("User management")
    @Feature("Contact form")
    @Story("As a user, I can fill out and submit a contact form so that I can communicate with the support team.")
    @Severity(SeverityLevel.CRITICAL)
    void Ex6ContactUsTest() {
        //Step 1. Check if the logo is visible.
        Assertions.assertTrue(mainPage.logoIsVisible(), "Step 1: The logo is not visible");

        //Step 2. Click on 'Contact Us' button
        mainPage.clickContactUsButton();

        //Step 3. Verify 'GET IN TOUCH' is visible
        contactPage = new ContactPage(driver);
        Assertions.assertTrue(contactPage.getInTouchIsVisible(), "Step 3: 'GET IN TOUCH' is not visible");

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
        Assertions.assertTrue(contactPage.isSuccessTextVisible(),
                "Step 8: success message 'Success! Your details have been submitted successfully.' is not visible");

        //Step 9. Click 'Home' button and verify that landed to home page successfully
        contactPage.clickHomeButton();
        Assertions.assertEquals("https://automationexercise.com/", driver.getCurrentUrl(),
                "Step 9: The user is not navigated to the login page after logging out.");
    }
}
