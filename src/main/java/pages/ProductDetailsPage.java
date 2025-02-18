package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {
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
        return isElementVisible(productName);
    }

    public boolean isCategoryVisible() {
        return isElementVisible(productCategory);
    }

    public boolean isPriceVisible() {
        return isElementVisible(productPrice);
    }

    public boolean isAvailabilityVisible() {
        return isElementVisible(productAvailability);
    }

    public boolean isConditionVisible() {
        return isElementVisible(productCondition);
    }

    public boolean isBrandVisible() {
        return isElementVisible(productBrand);
    }

    public boolean areAllDetailsVisible() {
        return isNameVisible() && isCategoryVisible() && isPriceVisible() && isAvailabilityVisible() &&
                isConditionVisible() && isBrandVisible();
    }
}
