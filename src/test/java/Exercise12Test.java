// This is "Test Case 12: Add Products in Cart" from the Automation Exercise page at automationexercise.com
// from the Automation Exercise page at automationexercise.com.
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense.
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report.

import config.BaseTest;
import config.CriticalTests;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.CartPage;
import pages.MainPage;
import pages.ProductsPage;

import java.util.Map;

@Category(CriticalTests.class)
public class Exercise12Test extends BaseTest {

    MainPage mainPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @Before
    public void begin() {
        driver.get("https://automationexercise.com/");

        mainPage = new MainPage(driver);
        mainPage.handleCookies();
    }

    @Test
    @Epic("Products")
    @Feature("Add to cart")
    @Story("As a user, I can add products to the cart so that I can review their details at a convenient time before completing a purchase.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex10and11SubscriptionTest() {
        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click 'Products' button
        mainPage.clickProductsButton();

        //Step 3. Hover over first product and click 'Add to cart'
        productsPage = new ProductsPage(driver);

        //Step 4. Click 'Continue Shopping' button
        productsPage.handleAds();
        productsPage.clickAddToCart(1);
        Map<String, String> details1 = productsPage.getProductDetails(1); //extracting the name and the price
        productsPage.clickContinueShopping(); //closing the "Added to Cart" modal window

        //Step 5. Hover over second product and click 'Add to cart'
        productsPage.clickAddToCart(2);
        Map<String, String> details2 = productsPage.getProductDetails(2); //extracting the name and the price
        productsPage.clickContinueShopping(); //closing the "Added to Cart" modal window

        //Step 6. Click 'View Cart' button
        productsPage.clickViewCart();
        cartPage = new CartPage(driver);

        //Step 7. Verify both products are added to Cart
        Assert.assertEquals("Step 7: The quantity of added products is not 2",
                2, cartPage.getAllProductsQuantity());

        //Step 8. Verify their prices, quantity and total price
        Assert.assertTrue(cartPage.verifyDetails(details1, 1,1, 8));
        Assert.assertTrue(cartPage.verifyDetails(details2, 2,1, 8));
    }
}
