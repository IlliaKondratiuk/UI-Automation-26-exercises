package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;

import java.time.Duration;

public class CartPageSteps {

    private final TestContext context;

    CartPage cartPage;

    public CartPageSteps(TestContext context) {
        this.context = context;
        cartPage = new CartPage();
    }

    @And("the product {int} in the cart has quantity {int}")
    public void theProductInTheCartHasQuantity(int productIndex, int quantity) {
        Assertions.assertTrue(cartPage.isProductQuantityCorrect(productIndex, quantity)),
                "The product " + productIndex + " quantity is incorrect");
    }

    @And("checkout is initiated")
    public void checkout_is_initiated() {
        cartPage.clickCheckoutButton();
    }

    @And("login page is opened via the modal window")
    public void login_page_is_opened_via_the_modal_window() {
        cartPage.clickRegisterLoginModalButton();
    }

    @When("user removes the product from the cart")
    public void user_removes_the_product_from_the_cart() {
        cartPage.clickRemoveFromCartByIndex(1); //1 by default as "the product" assumes it's just one product
    }

    @Then("the cart is empty")
    public void the_cart_is_empty() {
        try {
            WebDriverWait wait = new WebDriverWait(context.getDriver(), Duration.ofSeconds(2));
            wait.until(driver -> cartPage.isCartEmpty());
        } catch (TimeoutException e) {
            Assertions.fail("The cart did not become empty in 2 seconds");
        }

    }

    @Then("the added product is still in the cart")
    public void theAddedProductIsStillInTheCart() {
        Assertions.assertEquals(context.getAddedProductName(), cartPage.getProductNameByIndex(1),
                "The product in the cart is not the same as the one that was added previously");
    }
}
