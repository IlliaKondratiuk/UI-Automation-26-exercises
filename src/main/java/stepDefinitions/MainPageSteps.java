package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.MainPage;

public class MainPageSteps {

    private final TestContext context;

    MainPage mainPage;

    public MainPageSteps(TestContext context) {
        this.context = context;
        this.mainPage = new MainPage(context.getDriver());
    }

    @When("'View Product' is clicked for product {int}")
    public void view_product_is_clicked_for_product(int index) {
        mainPage.clickViewProduct(index);
    }

    @Then("the product {int} detail page is opened")
    public void the_product_detail_page_is_opened(int index) {
        Assert.assertEquals("Product details page is wrong",
                "https://automationexercise.com/product_details/" + index,
                context.getDriver().getCurrentUrl());
    }

    @Given("the product {int} detail page was opened")
    public void the_product_detail_page_was_opened(int index) {
        context.getDriver().get("https://automationexercise.com/product_details/" + index);
    }

    @Given("products are added to the cart")
    public void products_are_added_to_the_cart() {
        mainPage.handleAds();
        mainPage.clickAddToCart(1);
        mainPage.clickContinueShopping();
        mainPage.clickAddToCart(2);
        mainPage.clickContinueShopping();
    }
}
