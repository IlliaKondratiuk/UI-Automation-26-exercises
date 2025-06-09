package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
