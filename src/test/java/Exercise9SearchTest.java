// This is "Test Case 9: Search Product" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report
// The expected search results are parsed from src\resources\SearchResults.txt

import config.BaseTest;
import config.CriticalTests;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.MainPage;
import pages.ProductsPage;

import java.io.IOException;

@Category(CriticalTests.class)
public class Exercise9SearchTest extends BaseTest {

    private static MainPage mainPage;
    private static ProductsPage productsPage;

    //Path to the file with the expected product search results
    private final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\SearchResults.txt";

    @Before
    public void begin() {
        driver.get("https://automationexercise.com/");

        mainPage = new MainPage(driver);
        mainPage.handleCookies();
    }

    @Test
    @Epic("Products")
    @Feature("Products page")
    @Story("As a user, I can search for a product so that I can see relevant results and choose from available options.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex9SearchProduct() {
        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click on 'Products' button
        mainPage.clickProductsButton();

        //Step 3. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertEquals("Step 3: The user is not navigated to the 'All Products' page.",
                "https://automationexercise.com/products", driver.getCurrentUrl());

        //Step 4. Enter product name in search input and click search button
        productsPage = new ProductsPage(driver);
        productsPage.enterProduct("Dress");
        productsPage.clickSearchButton();

        //Step 5. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue("Step 5: 'SEARCHED PRODUCTS' is not visible",
                productsPage.areSearchedProductsVisible());

        //Step 6. Verify all the products related to search are visible
        try {
            Assert.assertTrue("Step 6: The search results are incorrect or not visible.",
                    productsPage.areSearchResultsCorrect(filePath));
        } catch (IOException e) {
            Assert.fail("Step 6: An error occurred trying to read the file with expected expected search results");
        }
    }
}
