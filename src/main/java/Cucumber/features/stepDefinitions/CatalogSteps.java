package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.MainPage;
import pages.ProductDetailsPage;

import java.time.Duration;
import java.util.ArrayList;

public class CatalogSteps {

    private final TestContext context;

    MainPage mainPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;

    public CatalogSteps(TestContext context) {
        this.context = context;
        mainPage = new MainPage(context.getDriver());
        productDetailsPage = new ProductDetailsPage(context.getDriver());
        cartPage = new CartPage(context.getDriver());
    }

    @Given("all categories and subcategories are available")
    public void all_categories_and_subcategories_are_available() {
        context.setCategoryList(mainPage.getCategoriesSubcategoriesList());
    }

    @When("the user visits each subcategory page")
    public void the_user_visits_each_subcategory_page() { //actually no choice but to close ads that many times
        ArrayList<String> catsAndSubcats = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(context.getDriver(), Duration.ofSeconds(2));

        mainPage.handleAds();

        for (int i = 0; i < mainPage.getCategoryQuantity(); i++) {
            final int index = i;

            mainPage.clickCategory(i);
            for (int j = 0; j < mainPage.getVisibleSubcategoryQuantity(); j++) {
                wait.until(driver -> mainPage.isCategoryExpanded(index));
                mainPage.clickSubcategory(j);
                catsAndSubcats.add(productDetailsPage.getCatAndSubcat());
                productDetailsPage.clickHomeButton();
                mainPage.handleAds();
                mainPage.clickCategory(i);
            }
            wait.until(driver -> mainPage.isCategoryExpanded(index));
            mainPage.clickCategory(i);
        }
        context.setCategoryFromDetailsList(catsAndSubcats);
    }


    @Then("the headers include both the category and subcategory of the product")
    public void the_headers_include_both_the_category_and_subcategory_of_the_product() {
        Assert.assertEquals("The categories and subcategories of the products are not the same as in the list",
                context.getCategoryList(), context.getCategoryFromDetailsList());
    }

    @Given("all product brands are available")
    public void all_product_brands_are_available() {
        context.setBrandNamesList(mainPage.getBrandNamesList());
    }

    @When("the user visits each brand page through the list")
    public void the_user_visits_each_brand_page_through_the_list() {
        ArrayList<String> brandsInLinks = new ArrayList<>();
        mainPage.handleAds();

        for (int i = 0; i < mainPage.getBrandQuantity(); i++) {
            mainPage.clickBrand(i);
            brandsInLinks.add(context.getDriver().getCurrentUrl().substring(46).toUpperCase()
                    .replace("%20", " "));
            context.getDriver().navigate().back();
        }
        context.setBrandNamesFromLinksList(brandsInLinks);
    }

    @Then("all opened pages correspond to the clicked brand")
    public void all_opened_pages_correspond_to_the_clicked_brand() {
        Assert.assertEquals("The brands in the opened pages are not the same as in the brand list in the catalog",
                context.getBrandNamesList(), context.getBrandNamesFromLinksList());
    }

    @Then("the added products are present in the cart")
    public void the_added_products_are_present_in_the_cart() {
        //Just comparing the product names as we don't have access to the DB
        Assert.assertEquals("The products in the cart are different from the recommended products that were added to the cart",
                context.getAddedProductsNames(), cartPage.getProductsInCartNames());
    }
}
