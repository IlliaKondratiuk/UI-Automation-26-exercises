package Cucumber.features.stepDefinitions;

import config.PageNames;
import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.time.Duration;

public class CommonSteps {

    private final TestContext context;

    MainPage mainPage;

    public CommonSteps(TestContext context) {
        this.context = context;
        mainPage = new MainPage();
    }

    @Given("the browser is launched")
    public void the_browser_is_launched() {
        Assertions.assertNotNull(context.getDriver(), "Driver should be initialized in Hooks");
    }

    @When("the {string} page is opened")
    public void the_page_is_opened(String pageName) {
        PageNames page;
        try {
            page = PageNames.valueOf(pageName.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown page name: " + pageName);
        }
        context.getDriver().get(page.getUrl());
    }

    @Then("the logo is visible")
    public void the_logo_is_visible() {
        Assertions.assertTrue(mainPage.logoIsVisible(), "Step 1: The logo is not visible");
    }

    @And("cookies window is handled")
    public void cookies_are_handled() {
        mainPage.handleCookies();
    }

    @Then("the cart page is opened successfully")
    public void the_cart_page_is_opened_successfully() {
        Assertions.assertEquals("https://automationexercise.com/view_cart",
                context.getDriver().getCurrentUrl(),
                "The cart page is not opened, " +  context.getDriver().getCurrentUrl() + "is opened instead.");
    }

    @And("the user is redirected to 'payment done'")
    public void the_user_is_redirected_to_payment_done() {
        Assertions.assertTrue(context.getDriver().getCurrentUrl().toUpperCase().contains("payment_done".toUpperCase()),
                "The user wasn't redirected to the 'Payment Done' page");
    }

    @When("the user scrolls to the {string} of the page")
    public void the_user_scrolls_to_the_direction_of_the_page(String direction) {
        mainPage.handleAds();

        switch (direction) {
            case "top":
                mainPage.scrollToTheTop();
                break;
            case "bottom":
                mainPage.scrollToTheBottom();
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    @When("the user clicks the 'Scroll Up' button")
    public void the_user_clicks_the_scroll_up_button() {
        mainPage.clickScrollUpButton();
    }

    @Then("the page is scrolled to the top border")
    public void the_page_is_scrolled_to_the_top_border() {
        try {
            WebDriverWait wait = new WebDriverWait(context.getDriver(), Duration.ofSeconds(2));
            wait.until(driver -> mainPage.isAtTopOfPage());
        } catch (TimeoutException e) {
            Assertions.fail("The page couldn't be scrolled to the top border");
        }
    }

    @When("the user scrolls to the top of the page")
    public void the_user_scrolls_to_the_top_of_the_page() {
        mainPage.scrollToTheTop();
    }

    @Then("the 'Scroll Up' button is not visible")
    public void the_scroll_up_button_disappears() {
        try {
            WebDriverWait wait = new WebDriverWait(context.getDriver(), Duration.ofSeconds(2));
            wait.until(driver -> !mainPage.isScrollUpVisible());
        } catch (TimeoutException e) {
            Assertions.fail("The 'Scroll Up' button is still visible after scrolling to the top of the page");
        }
    }
}
