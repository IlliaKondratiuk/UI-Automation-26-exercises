package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.ProductDetailsPage;

public class ProductDetailsPageSteps {
    
    private final TestContext context;
    
    ProductDetailsPage productDetailsPage;

    public ProductDetailsPageSteps(TestContext context) {
        this.context = context;
        productDetailsPage = new ProductDetailsPage(context.getDriver());
    }
    
    @And("the quantity is increased to {int}")
    public void the_quantity_is_increased_to(int quantity) {
        productDetailsPage.enterQuantity(quantity);
    }

    @And("the product is added to the cart")
    public void the_product_is_added_to_the_cart() {
        productDetailsPage.clickAddToCart();
    }

    @When("'View Cart' is clicked")
    public void view_cart_is_clicked() {
        productDetailsPage.clickViewCart();
    }
}
