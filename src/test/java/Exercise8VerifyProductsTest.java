// This is "Test Case 8: Verify All Products and product detail page" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 5.10.0, designing with Page Object Model and generating an Allure(2.32.2) report

import config.PageNames;
import config.WebDriverConfig;
import config.testConfigs.BaseTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

@Tag("Critical")
class Exercise8VerifyProductsTest extends BaseTest {

    private static MainPage mainPage;
    private static ProductsPage productsPage;
    private static ProductDetailsPage productDetailsPage;

    @BeforeEach
    public void begin() {
        WebDriverConfig.openURL(PageNames.MAIN.getUrl());

        mainPage = new MainPage();
        mainPage.handleCookies();
    }

    @Test
    @Epic("Products")
    @Feature("Products page")
    @Story("As a user, I can browse the available products and check the details of a selected product " +
            "so that I can make an informed purchase decision")
    @Severity(SeverityLevel.CRITICAL)
    void Ex8VerifyProductsTest() {
        //Step 1. Check if the logo is visible.
        Assertions.assertTrue(mainPage.logoIsVisible(), "Step 1: The logo is not visible");

        //Step 2. Click on 'Products' button
        mainPage.clickProductsButton();

        //Step 3. Verify user is navigated to ALL PRODUCTS page successfully
        Assertions.assertEquals("https://automationexercise.com/products", driver.getCurrentUrl(),
                "Step 3: The user is not navigated to the 'All Products' page.");

        //Step 4. The products list is visible
        productsPage = new ProductsPage(driver);
        Assertions.assertTrue(productsPage.isProductListVisible(),
                "Step 4: The product list is not visible or has less than 2 elements");

        //Step 5. Click on 'View Product' of first product
        productsPage.handleAds();
        productsPage.clickViewProduct(1);

        //Step 6. User is landed to product detail page
        Assertions.assertEquals("https://automationexercise.com/product_details/1", driver.getCurrentUrl(),
                "Step 6: The user is not navigated to the product details page.");

        //Step 7. Verify that every detail is visible: product name, category, price, availability, condition, brand
        productDetailsPage =  new ProductDetailsPage(driver);
        Assertions.assertTrue(productDetailsPage.areAllDetailsVisible(),
                "Step 7: Not all product details are visible");
    }
}
