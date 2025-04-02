package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.ProductsPage;

public class ProductsPageSteps {

    private final TestContext context;

    ProductsPage productsPage;

    public ProductsPageSteps(TestContext context) {
        this.context = context;
        productsPage = new ProductsPage(context.getDriver());
    }

    @And("the user enters {string} in the search bar")
    public void theUserEntersInTheSearchBar(String searchWord) {
        productsPage.enterProduct(searchWord);
    }

    @When("user presses \"search\"")
    public void user_presses_search() {
        productsPage.clickSearchButton();
    }

    @Then("the products with {string} in the name are displayed")
    public void the_products_with_word_in_the_name_are_displayed(String word) {
        Assert.assertTrue("The results include a product that doesn't match the searched word",
                productsPage.doAllProductsContainTheWord(word));
    }

    @Given("a product is added to the cart")
    public void a_product_is_added_to_the_cart() {
        productsPage.handleAds();
        productsPage.clickAddToCart(1);
        context.setAddedProductName(productsPage.getProductNameByIndex(1)); //saving the added product name
        productsPage.clickContinueShopping();
    }

    @When("any product's details page is opened")
    public void any_products_details_page_is_opened() {
        productsPage.handleAds();
        productsPage.clickViewProduct(1);
    }
}
