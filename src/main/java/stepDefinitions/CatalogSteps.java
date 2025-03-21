package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.ProductDetailsPage;

import java.time.Duration;
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
}
