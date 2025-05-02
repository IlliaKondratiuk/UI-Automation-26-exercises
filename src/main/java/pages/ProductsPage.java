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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsPage extends CommonElements {

    By productList = new By.ByClassName("single-products");
    By allDisplayedProductNames = new By.ByXPath("//div[contains(@class, 'productinfo')]//p");

    By searchInput = new By.ByName("search");
    By searchButton = new By.ById("submit_search");
    By searchedProducts = new By.ByXPath("//h2[text()=\"Searched Products\"]");
    By searchResults = new By.ByXPath("//div[@class='single-products']//div//p");
    By viewCartButton = new By.ByXPath("//a[@href='/view_cart']");
    By continueShoppingButton = new By.ByXPath("//button[contains(@class, 'btn-success')]");
    By viewProductButton = new By.ByXPath("//a[contains(@href, 'product_details/')]");

    String addToCartButtonXPathBegin = "(//a[@data-product-id=";
    String viewProductXPathBegin = "//a[@href='/product_details/";
    String productXPathBegin = "(//div[contains(@class, 'productinfo')])[";

    public ProductsPage(WebDriver driver) {
        super(driver);
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
        clickElementByLocator(new By.ByXPath(viewProductXPathBegin + productIndex + "']"));
    }

    public void clickAddToCart(int productIndex) {
        scrollToElement(new By.ByXPath(viewProductXPathBegin + productIndex + "']"));
        clickElementByLocator(new By.ByXPath(addToCartButtonXPathBegin + productIndex + "])"));
    }

    public void clickViewCart() {
        clickElementByLocator(viewCartButton);
    }

    public void enterProduct(String product) {
        fillInput(searchInput, product);
    }

    public void clickSearchButton() {
        clickElementByLocator(searchButton);
    }

    public void clickContinueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton));
        clickElementByLocator(continueShoppingButton);
    }

    public boolean areSearchedProductsVisible() {
        return isElementVisible(searchedProducts);
    }

    public boolean areSearchResultsCorrect(String nameFilePath) throws IOException {
        //Extracting the expected names of the results from the .txt file
        List<String> expectedNames = Files.readAllLines(Paths.get(nameFilePath));

        //Finding all the collapsed TC names on the page
        List<WebElement> productResults = driver.findElements(searchResults);

        //Comparing the name of each second element in the list with each expected name
        //This is due to the fact that the <p> elements with names are duplicated since they are also present in the divs
        //that appear on hovering over the found results. Therefore, we compare expected names[i] to results[2*i]
        for (int i = 0; i < expectedNames.size(); i++) {
            if (!productResults.get(2 * i).getText().equals(expectedNames.get(i))) {
                return false;
            }
        }
        return true;
    }

    public Map<String, String> getProductDetails(int productIndex) {
        Map<String, String> details = new HashMap<>();

        By productPrice = new By.ByXPath( productXPathBegin + productIndex + "]//h2");
        By productName = new By.ByXPath(productXPathBegin + productIndex + "]//p");
        details.put("name", driver.findElement(productName).getText());
        details.put("price", driver.findElement(productPrice).getText());

        return details;
    }

    public String getProductNameByIndex(int productIndex) {
        return driver.findElement(new By.ByXPath(productXPathBegin + productIndex + "]//p")).getText();
    }

    public boolean doAllProductsContainTheWord(String searchWord) {
        List<WebElement> results = driver.findElements(allDisplayedProductNames);

        return results.stream()
                .allMatch(e -> e.getText().toUpperCase().contains(searchWord.toUpperCase())); //making it case-insensitive
    }
}
