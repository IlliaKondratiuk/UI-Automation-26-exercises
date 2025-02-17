package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {

    WebDriver driver;

    //The "//div[@class='product-details']" is included in all paths to ensure that the found elements are located
    //under the product details div as other names/categories/prices/etc. potentially may be added to the page later
    //e.g. recommended products and their details. "text()" is added to ensure the details are not empty.
    By productName = new By.ByXPath("//div[@class='product-details']//h2[text()]");
    By productCategory = new By.ByXPath("//div[@class='product-details']//p[contains(text(), 'Category')]");
    By productPrice = new By.ByXPath("//div[@class='product-details']//span//span[text()]");
    By productAvailability = new By.ByXPath("//div[@class='product-details']//b[contains(text(), 'Availability')]" +
            "/parent::p[text()]");
    By productCondition = new By.ByXPath("//div[@class='product-details']//b[contains(text(), 'Condition')]" +
            "/parent::p[text()]");
    By productBrand = new By.ByXPath("//div[@class='product-details']//b[contains(text(), 'Brand')]" +
            "/parent::p[text()]");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNameVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.productName));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isCategoryVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productCategoryEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.productCategory));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isPriceVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productPriceEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.productPrice));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isAvailabilityVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productAvailabilityEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.productAvailability));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isConditionVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productConditionEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.productCondition));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isBrandVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productBrandEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.productBrand));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean areAllDetailsVisible() {
        return isNameVisible() && isCategoryVisible() && isPriceVisible() && isAvailabilityVisible() &&
                isConditionVisible() && isBrandVisible();
    }
}
