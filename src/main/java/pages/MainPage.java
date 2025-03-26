package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    By continueShoppingButton = new By.ByXPath("//button[contains(@class, 'close-modal') and text()='Continue Shopping']");

    By categories = new By.ByXPath("//div[contains(@class, 'category-products')]//a[@data-toggle]");
    By subcategories = new By.ByXPath("//div[contains(@class, 'category-products')]//a[contains(@href, 'category')]");
    By visibleSubcategories = new By.ByXPath("//div[contains(@class, 'category-products')]" +
            "//div[contains(@class, 'in')]//a[contains(@href, 'category')]");
    By brands = new By.ByXPath("//div[@class='brands-name']//a");

    String addToCartButtonXPathBegin = "(//a[@data-product-id=";
    String viewProductXPathBegin = "(//a[contains(@href, 'product_details')])[";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickViewProduct(int index) {
        driver.findElement(new By.ByXPath(viewProductXPathBegin + index + "]")).click();
    }

    public void clickAddToCart(int productIndex) {
        scrollToElement(new By.ByXPath(viewProductXPathBegin + productIndex + "]"));
        clickElement(new By.ByXPath(addToCartButtonXPathBegin + productIndex + "])"));
    }

    public void clickContinueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        clickElement(continueShoppingButton);
    }

    public ArrayList<String> getCategoriesSubcategoriesList() {
        ArrayList<String> result = new ArrayList<>();

        List<WebElement> subcategoriesList = driver.findElements(subcategories);

        for(WebElement el : subcategoriesList) {
            result.add((el.findElement(new By.ByXPath("ancestor::div[2]")).getAttribute("id") + " - "
                    + el.getAttribute("textContent")).trim().toUpperCase());
        }

        return result;
    }

    public int getCategoryQuantity() {
        return driver.findElements(categories).size();
    }

    public int getBrandQuantity() {
        return driver.findElements(brands).size();
    }

    public int getVisibleSubcategoryQuantity() {
        return driver.findElements(visibleSubcategories).size();
    }

    public void clickCategory(int index) {
        driver.findElements(categories).get(index).click();
    }

    public void clickBrand(int index) {
        scrollToByIndex(brands, index);
        driver.findElements(brands).get(index).click();
    }

    public boolean isCategoryExpanded(int index) {
        return driver.findElements(categories).get(index).
                findElements(new By.ByXPath("../../../div")).
                get(1).getAttribute("style").equals("height: auto;");
    }

    public void clickSubcategory(int index) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(visibleSubcategories).get(index)));
            driver.findElements(visibleSubcategories).get(index).click();
        } catch (ElementNotInteractableException e) {
            Assert.fail("Subcategory couldn't be clicked as it's not visible");
        }
    }

    public ArrayList<String> getBrandNamesList() {
        ArrayList<String> result = new ArrayList<>();

        List<WebElement> brandList = driver.findElements(brands);

        for(WebElement el : brandList) {
            result.add(el.getText().replaceAll("^\\(\\d+\\)\\s*", ""));
        }

        return result;
    }
}
