package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.And;
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
}
