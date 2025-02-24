// This is "Test Case 8: Verify All Products and product detail page" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report

import config.Base;
import config.CriticalTests;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.MainPage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

@Category(CriticalTests.class)
public class Exercise8VerifyProductsTest extends Base {

    private static MainPage mainPage;
    private static ProductsPage productsPage;
    private static ProductDetailsPage productDetailsPage;

    @Before
    public void begin() {
        driver.get("https://automationexercise.com/");

        mainPage = new MainPage(driver);
        mainPage.handleCookies();
    }

    @Test
    @Epic("Products")
    @Feature("Products page")
    @Story("As a user, I can browse the available products and check the details of a selected product " +
            "so that I can make an informed purchase decision")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex8VerifyProductsTest() {
        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click on 'Products' button
        mainPage.clickProductsButton();

        //Step 3. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertEquals("Step 3: The user is not navigated to the 'All Products' page.",
                "https://automationexercise.com/products", driver.getCurrentUrl());

        //Step 4. The products list is visible
        productsPage = new ProductsPage(driver);
        Assert.assertTrue("Step 4: The product list is not visible or has less than 2 elements",
                productsPage.isProductListVisible());

        //Step 5. Click on 'View Product' of first product
        productsPage.handleAds();
        productsPage.clickViewProduct(1);

        //Step 6. User is landed to product detail page
        Assert.assertEquals("Step 6: The user is not navigated to the product details page.",
                "https://automationexercise.com/product_details/1", driver.getCurrentUrl());

        //Step 7. Verify that every detail is visible: product name, category, price, availability, condition, brand
        productDetailsPage =  new ProductDetailsPage(driver);
        Assert.assertTrue("Step 7: Not all product details are visible",
                productDetailsPage.areAllDetailsVisible());
    }
}
