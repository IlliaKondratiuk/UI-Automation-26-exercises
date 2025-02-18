package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage {

    By productList = new By.ByClassName("single-products");
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
        clickElement(new By.ByXPath(viewProductXPathBegin + productIndex + "']"));
    }


    public void handleAds() {
        //this method will handle the ads that temporarily block the View Product buttons
    }
}
