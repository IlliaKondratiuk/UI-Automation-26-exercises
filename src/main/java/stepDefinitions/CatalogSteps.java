package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.MainPage;
import pages.ProductDetailsPage;

import java.util.ArrayList;

public class CatalogSteps {

    private final TestContext context;

    MainPage mainPage;
    ProductDetailsPage productDetailsPage;

    public CatalogSteps(TestContext context) {
        this.context = context;
        mainPage = new MainPage(context.getDriver());
        productDetailsPage = new ProductDetailsPage(context.getDriver());
    }

    @When("the user visits each subcategory page")
    public void the_user_visits_each_subcategory_page() { //actually no choice but to close ads that many times
        ArrayList<String> catsAndSubcats = new ArrayList<>();

        for (int i = 0; i < mainPage.getCategoryQuantity(); i++) {
            if (i == 0) {
                mainPage.handleAds(); //because on i>0 it would reopen the ad banner
            }
            mainPage.clickCategory(i);
            for (int j = 0; j < mainPage.getVisibleSubcategoryQuantity(); j++) {
                mainPage.clickSubcategory(j);
                catsAndSubcats.add(productDetailsPage.getCatAndSubcat());
                productDetailsPage.clickHomeButton();
                mainPage.handleAds();
                mainPage.clickCategory(i);
            }
            mainPage.clickCategory(i);
        }
        context.setCategoryFromDetailsList(catsAndSubcats);
    }


    @Then("the headers include both the category and subcategory of the product")
    public void the_headers_include_both_the_category_and_subcategory_of_the_product() {
        Assert.assertEquals("The categories and subcategories of the products are not the same as in the list",
                context.getCategoryList(), context.getCategoryFromDetailsList());
    }
}
