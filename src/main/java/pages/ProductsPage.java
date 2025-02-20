package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage {

    By productList = new By.ByClassName("single-products");

    By searchInput = new By.ByName("search");
    By searchButton = new By.ById("submit_search");
    By searchedProducts = new By.ByXPath("//h2[text()=\"Searched Products\"]");
    By searchResults = new By.ByXPath("//div[@class='single-products']//div//p");

    String viewProductXPathBegin = "//a[@href='/product_details/";

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductListVisible() {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> productListEl = driver.findElements(this.productList);
            wait.until(ExpectedConditions.visibilityOfAllElements(productListEl));

            //Checking that more than one "single-products" class element is found
            return productListEl.size() > 1;
        } catch (TimeoutException e) {
          return false;
        }
    }

    public void clickViewProduct(int productIndex) {
        scrollToElement(new By.ByXPath(viewProductXPathBegin + productIndex + "']"));
        clickElement(new By.ByXPath(viewProductXPathBegin + productIndex + "']"));
    }

    public void enterProduct(String product) {
        fillInput(searchInput, product);
    }

    public void clickSearchButton() {
        clickElement(searchButton);
    }

    public boolean isSearchedProductsVisible() {
        return isElementVisible(searchedProducts);
    }

    public boolean areSearchResultsCorrect(String nameFilePath) throws IOException {
        //Extracting the expected names of the results from the .txt file
        List<String> expectedNames = Files.readAllLines(Paths.get(nameFilePath));

        //Finding all the collapsed TC names on the page
        List<WebElement> productResults = driver.findElements(searchResults);

        //Comparing the name of each element in the list with each expected name
        for (int i = 0; i < expectedNames.size(); i++) {
            if (!productResults.get(2 * i).getText().equals(expectedNames.get(i))) {
                String temp = productResults.get(i).getText();
                return false;
            }
        }
        return true;
    }
}
