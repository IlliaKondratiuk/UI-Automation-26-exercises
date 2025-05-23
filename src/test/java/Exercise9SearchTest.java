// This is "Test Case 9: Search Product" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 5.10.0, designing with Page Object Model and generating an Allure(2.32.2) report
// The expected search results are parsed from src\resources\SearchResults.txt

import config.PageNames;
import config.WebDriverConfig;
import config.testConfigs.BaseTest;

import config.testConfigs.TestLogger;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;
import pages.ProductsPage;

import java.io.IOException;

@Tag("Critical")
@ExtendWith(TestLogger.class)
class Exercise9SearchTest extends BaseTest {

    private static MainPage mainPage;
    private static ProductsPage productsPage;

    //Path to the file with the expected product search results
    private final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\SearchResults.txt";

    @BeforeEach
    public void begin() {
        WebDriverConfig.openURL(PageNames.MAIN.getUrl());

        mainPage = new MainPage();
        mainPage.handleCookies();
    }

    @Test
    @Epic("Products")
    @Feature("Products page")
    @Story("As a user, I can search for a product so that I can see relevant results and choose from available options.")
    @Severity(SeverityLevel.CRITICAL)
    void Ex9SearchProduct() {
        //Step 1. Check if the logo is visible.
        Assertions.assertTrue(mainPage.logoIsVisible(), "Step 1: The logo is not visible");

        //Step 2. Click on 'Products' button
        mainPage.clickProductsButton();

        //Step 3. Verify user is navigated to ALL PRODUCTS page successfully
        Assertions.assertEquals("https://automationexercise.com/products", driver.getCurrentUrl(),
                "Step 3: The user is not navigated to the 'All Products' page.");

        //Step 4. Enter product name in search input and click search button
        productsPage = new ProductsPage();
        productsPage.enterProduct("Dress");
        productsPage.clickSearchButton();
        productsPage.handleAds();

        //Step 5. Verify 'SEARCHED PRODUCTS' is visible
        Assertions.assertTrue(productsPage.areSearchedProductsVisible(),
                "Step 5: 'SEARCHED PRODUCTS' is not visible");

        //Step 6. Verify all the products related to search are visible
        try {
            Assertions.assertTrue(productsPage.areSearchResultsCorrect(filePath),
                    "Step 6: The search results are incorrect or not visible.");
        } catch (IOException e) {
            Assertions.fail("Step 6: An error occurred trying to read the file with expected expected search results");
        }
    }
}
