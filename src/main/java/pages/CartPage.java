package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CartPage extends CommonElementsPage {

    By allQuantitiesButtons = new By.ByXPath("//td[@class='cart_quantity']//button");
    By checkoutButton = new By.ByXPath("//a[contains(@class, 'check_out')]");
    By registerLoginModalButton = new By.ByXPath("//div[@class='modal-body']//a[@href='/login']");
    By allCartProductNames = new By.ByXPath("//td[@class='cart_description']//a[@href]");

    By productTableRow = new By.ByXPath("//tr[contains(@id, 'product')]");

    String cartNameXPathBegin = "(//td[@class='cart_description']//a)[";
    String cartPriceXPathBegin = "(//td[@class='cart_price']//p)[";
    String cartQuantXPathBegin = "(//td[@class='cart_quantity']//button)[";
    String cartTotalXPathBegin = "(//td[@class='cart_total']//p)[";
    String removeFromCartButtonXPathBegin = "(//a[@class='cart_quantity_delete'])[";

    public CartPage() {
        super();
    }

    public int getAllProductsQuantity() {
        return driver.findElements(allQuantitiesButtons).stream()
                .mapToInt(button -> Integer.parseInt(button.getText()))
                .sum();
    }

    // Retrieving the name, price, quantity and total price of the product by index.
    public Map<String, String> getProductDetails(int i) {
        Map<String, String> details = new HashMap<>();

        By productName = new By.ByXPath(cartNameXPathBegin + i + "]");
        By productPrice = new By.ByXPath(cartPriceXPathBegin + i + "]");
        By productQuantity = new By.ByXPath(cartQuantXPathBegin + i + "]");
        By productTotal = new By.ByXPath(cartTotalXPathBegin + i + "]");

        details.put("name", driver.findElement(productName).getText());
        details.put("price", driver.findElement(productPrice).getText());
        details.put("quantity", driver.findElement(productQuantity).getText());
        details.put("total", driver.findElement(productTotal).getText());

        return details;
    }

    public boolean verifyProductName(int productIndex, String expectedName) {
        return expectedName.equals(getProductDetails(productIndex).get("name"));
    }

    public boolean verifyProductPrice(int productIndex, String expectedPrice) {
        return expectedPrice.equals(getProductDetails(productIndex).get("price"));
    }

    public boolean verifyProductQuantity(int productIndex, Integer expectedQuantity) {
        return expectedQuantity.equals(Integer.parseInt(getProductDetails(productIndex).get("quantity")));
    }

    public boolean verifyDetails(Map<String, String> expectedDetails, int productIndex, int expectedQuantity, int step) {
        boolean isValid = true;

        // Get actual product details from the cart
        Map<String, String> actualDetails = getProductDetails(productIndex);
        int actualQuantity = Integer.parseInt(actualDetails.get("quantity"));

        // Check Name
        if (!expectedDetails.get("name").equals(actualDetails.get("name"))) {
            Assert.fail("Product name mismatch at step " + step +
                    ": expected '" + expectedDetails.get("name") +
                    "' but found '" + actualDetails.get("name") + "'");
            isValid = false;
        }

        // Check Price
        if (!expectedDetails.get("price").equals(actualDetails.get("price"))) {
            Assert.fail("Product price mismatch at step " + step +
                    ": expected '" + expectedDetails.get("price") +
                    "' but found '" + actualDetails.get("price") + "'");
            isValid = false;
        }

        // Check Quantity
        if (expectedQuantity != actualQuantity) {
            Assert.fail("Product quantity mismatch at step " + step +
                    ": expected '" + expectedQuantity +
                    "' but found '" + actualQuantity + "'");
            isValid = false;
        }

        //Check total(perfectly there would be an expected value of quantity*price but the price on the website is just a string
        //so let's at least check that the price and the total price are equal since quantity is 1 in the only test for this)
        if (!actualDetails.get("price").equals(actualDetails.get("total"))) { //
            Assert.fail("Product total price mismatch at step " + step +
                    ": expected '" + actualDetails.get("price") +
                    "' but found '" + actualDetails.get("total") + "'");
            isValid = false;
        }

        return isValid;
    }

    public boolean isProductQuantityCorrect(int productIndex, int quantity) {
        By productQuantity = new By.ByXPath(cartQuantXPathBegin + productIndex + "]");

        return driver.findElement(productQuantity).getText().equals(Integer.toString(quantity));
    }

    public void clickCheckoutButton() {
        clickElementByLocator(checkoutButton);
    }

    public void clickRegisterLoginModalButton() {
        clickElementByLocator(registerLoginModalButton);
    }

    public void clickRemoveFromCartByIndex(int i) {
        clickElementByLocator(new By.ByXPath(removeFromCartButtonXPathBegin + i + "]"));
    }

    public boolean isCartEmpty() {
        try {
            driver.findElement(productTableRow);
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    public String getProductNameByIndex(int index) {
        return driver.findElement(new By.ByXPath(cartNameXPathBegin + index + "]")).getText();
    }

    public ArrayList<String> getProductsInCartNames() {
        return driver.findElements(allCartProductNames).stream().map(WebElement::getText)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
