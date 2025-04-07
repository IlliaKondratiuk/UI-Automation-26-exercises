package Cucumber.features.stepDefinitions;

import config.PageNames;
import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.MainPage;

public class CommonSteps {

    private final TestContext context;

    MainPage mainPage;

    public CommonSteps(TestContext context) {
        this.context = context;
        mainPage = new MainPage(context.getDriver());
    }

    @Given("the browser is launched")
    public void the_browser_is_launched() {
        Assert.assertNotNull("Driver should be initialized in Hooks", context.getDriver());
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
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());
    }

    @And("cookies window is handled")
    public void cookies_are_handled() {
        mainPage.handleCookies();
    }

    @Then("the cart page is opened successfully")
    public void the_cart_page_is_opened_successfully() {
        Assert.assertEquals("The cart page is not opened",
                "https://automationexercise.com/view_cart",
                context.getDriver().getCurrentUrl());
    }

    @And("the user is redirected to 'payment done'")
    public void the_user_is_redirected_to_payment_done() {
        Assert.assertTrue(context.getDriver().getCurrentUrl().toUpperCase().contains("payment_done".toUpperCase()));
    }

    @When("the user scrolls to the bottom of the page")
    public void the_user_scrolls_to_the_bottom_of_the_page() {
        mainPage.scrollToTheBottom();
    }
}
