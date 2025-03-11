package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.And;
import org.junit.Assert;
import pages.CartPage;

public class CartPageSteps {

    private final TestContext context;

    CartPage cartPage;

    public CartPageSteps(TestContext context) {
        this.context = context;
        cartPage = new CartPage(context.getDriver());
    }

    @And("the product {int} in the cart has quantity {int}")
    public void theProductInTheCartHasQuantity(int productIndex, int quantity) {
        Assert.assertTrue("The product " + productIndex + " quantity is incorrect",
                cartPage.isProductQuantityCorrect(productIndex, quantity));
    }

    @And("checkout is initiated")
    public void checkout_is_initiated() {
        cartPage.clickCheckoutButton();
    }

    @And("login page is opened via the modal window")
    public void login_page_is_opened_via_the_modal_window() {
        cartPage.clickRegisterLoginModalButton();
    }
}
