package stepDefinitions;

import config.TestContext;
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
    public void the_page_is_opened(String url) {
        context.getDriver().get(url);
    }

    @Then("the logo is visible")
    public void the_logo_is_visible() {
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());
    }

    @And("cookies window is handled")
    public void cookies_are_handled() {
        mainPage.handleCookies();
    }

    @Then("the cart page is opened")
    public void the_cart_page_is_opened() {
        Assert.assertEquals("The cart page is not opened",
                "https://automationexercise.com/view_cart",
                context.getDriver().getCurrentUrl());
    }
}
