// These are "Test Case 10: Verify Subscription in home page" and "Test Case 11: Verify Subscription in Cart page"
// from the Automation Exercise page at automationexercise.com.
// Two exercises have been merged into one test class as they test the same footer functionality on different pages.
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense.
// Using JUnit 5.10.0, designing with Page Object Model and generating an Allure(2.32.2) report.
// The parameterized class and the PageNames.enum were used to increase the flexibility of the test, making it
// possible to run it for each page of the website. Even though technically the footer is the same on all pages,
// the test was made pretending it's not, as it can theoretically be different on a commercial project.

import config.testConfigs.BaseTest;
import config.PageNames;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.runners.Parameterized;
import pages.CartPage;
import pages.CommonElementsPage;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@Tag("Critical")
public class Exercises10and11SubscriptionTests extends BaseTest {

    CommonElementsPage commonElementsPage;
    MainPage mainPage;

    public PageNames page;

    public Exercises10and11SubscriptionTests(PageNames page) {
        this.page = page;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {PageNames.MAIN},
                {PageNames.CART},
        });
    }

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

    @ParameterizedTest
    @EnumSource(PageNames.class)
    @Epic("Subscription")
    @Feature("Subscription via footer")
    @Story("As a user, I can subscribe to the website's newsletter so that I can be notified of all important updates.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex10and11SubscriptionTest(PageNames page) {
        //Step 1. Check if the logo is visible.
        Assertions.assertTrue(mainPage.logoIsVisible(), "Step 1: The logo is not visible");

        //Step 1.1. Switch to the tested page
        driver.get(page.getUrl());
        switch (page) {
            case MAIN: commonElementsPage = new MainPage(driver);
            case CART: commonElementsPage = new CartPage(driver);
        }

        //Step 2. Scroll down to the footer.
        commonElementsPage.scrollToTheBottom();

        //Step 3. Verify that text 'SUBSCRIPTION' is visible
        Assertions.assertTrue(commonElementsPage.isFooterSubscriptionLabelVisible(),
                "Step 3: 'SUBSCRIPTION' label in the footer is not visible");

        //Step 4. Enter email address in the subscription input and click arrow button
        commonElementsPage.enterSubscriptionEmail("test@qa.test");
        commonElementsPage.clickSubscriptionArrow();

        //Step 5. Verify that success message 'You have been successfully subscribed!' is visible
        Assertions.assertTrue(commonElementsPage.isSubscriptionSuccessLabelVisible(),
                "Step 5: 'You have been successfully subscribed!' is not visible");
    }
}
