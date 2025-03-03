package stepDefinitions;

import config.TestContext;
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
    public void theLogoIsVisible() {
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());
    }
}
