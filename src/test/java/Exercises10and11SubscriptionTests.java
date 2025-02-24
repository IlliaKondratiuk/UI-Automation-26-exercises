// These are "Test Case 10: Verify Subscription in home page" and "Test Case 11: Verify Subscription in Cart page"
// from the Automation Exercise page at automationexercise.com.
// Two exercises have been merged into one test class as they test the same footer functionality on different pages.
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense.
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report.
// The parameterized class and the PageNames.enum were used to increase the flexibility of the test, making it
// possible to run it for each page of the website. Even though technically the footer is the same on all pages,
// the test was made pretending it's not, as it can theoretically be different on a commercial project.

import config.Base;
import config.CriticalTests;
import config.PageNames;
import io.qameta.allure.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BasePage;
import pages.CartPage;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@Category(CriticalTests.class)
public class Exercises10and11SubscriptionTests extends Base {

    BasePage testedPage;
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
    @Epic("Subscription")
    @Feature("Subscription via footer")
    @Story("As a user, I can subscribe to the website's newsletter so that I can be notified of all important updates.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex10and11SubscriptionTest() {
        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 1.1. Switch to the tested page
        driver.get(page.getUrl());
        switch (page) {
            case MAIN: testedPage = new MainPage(driver);
            case CART: testedPage = new CartPage(driver);
        }

        //Step 2. Scroll down to the footer.
        testedPage.scrollToTheBottom();

        //Step 3. Verify that text 'SUBSCRIPTION' is visible
        Assert.assertTrue("Step 3: 'SUBSCRIPTION' label in the footer is not visible",
                testedPage.isFooterSubscriptionLabelVisible());

        //Step 4. Enter email address in the subscription input and click arrow button
        testedPage.enterSubscriptionEmail("test@qa.test");
        testedPage.clickSubscriptionArrow();

        //Step 5. Verify that success message 'You have been successfully subscribed!' is visible
        Assert.assertTrue("Step 5: 'You have been successfully subscribed!' is not visible",
                testedPage.isSubscriptionSuccessLabelVisible());
    }
}
