package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage {

    By checkOutButton = new By.ByXPath("//a[contains(@class, 'check_out')]");

    By deliveryName = new By.ByXPath("//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']");
    By deliveryAddress1 = new By.ByXPath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2']");
    By deliveryAddress2 = new By.ByXPath("//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']");
    By deliveryCountry = new By.ByXPath("//ul[@id='address_delivery']//li[@class='address_country_name']");
    By deliveryPhone = new By.ByXPath("//ul[@id='address_delivery']//li[@class='address_phone']");

    By billingName = new By.ByXPath("//ul[@id='address_invoice']//li[@class='address_firstname address_lastname']");
    By billingAddress1 = new By.ByXPath("//ul[@id='address_invoice']//li[@class='address_address1 address_address2']");
    By billingAddress2 = new By.ByXPath("//ul[@id='address_invoice']//li[@class='address_city address_state_name address_postcode']");
    By billingCountry = new By.ByXPath("//ul[@id='address_invoice']//li[@class='address_country_name']");
    By billingPhone = new By.ByXPath("//ul[@id='address_invoice']//li[@class='address_phone']");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckOutButton() {
        scrollToAndClickElement(checkOutButton);
    }

    String getDeliveryFirstName() {
        return driver.findElement(deliveryName).getText().split(" ")[0];
    }

    String getDeliveryLastName() {
        return driver.findElement(deliveryName).getText().split(" ")[1];
    }

    String getDeliveryCompany() {
        return driver.findElements(deliveryAddress1).get(0).getText();
    }

    String getDeliveryStreet() {
        return driver.findElements(deliveryAddress1).get(1).getText();
    }

    String getDeliveryHouseNumber() {
        return driver.findElements(deliveryAddress1).get(2).getText();
    }

    String getDeliveryCity() {
        return driver.findElement(deliveryAddress2).getText().split(" ")[0];
    }

    String getDeliveryState() {
        return driver.findElement(deliveryAddress2).getText().split(" ")[1];
    }

    String getDeliveryZip() {
        return driver.findElement(deliveryAddress2).getText().split(" ")[2] + " "
                + driver.findElement(deliveryAddress2).getText().split(" ")[3];
    }

    String getDeliveryCountry() {
        return driver.findElement(deliveryCountry).getText();
    }

    String getDeliveryPhoneNumber() {
        return driver.findElement(deliveryPhone).getText();
    }

    String getBillingFirstName() {
        return driver.findElement(billingName).getText().split(" ")[0];
    }

    String getBillingLastName() {
        return driver.findElement(billingName).getText().split(" ")[1];
    }

    String getBillingCompany() {
        return driver.findElements(billingAddress1).get(0).getText();
    }

    String getBillingStreet() {
        return driver.findElements(billingAddress1).get(1).getText();
    }

    String getBillingHouseNumber() {
        return driver.findElements(billingAddress1).get(2).getText();
    }

    String getBillingCity() {
        return driver.findElement(billingAddress2).getText().split(" ")[0];
    }

    String getBillingState() {
        return driver.findElement(billingAddress2).getText().split(" ")[1];
    }

    String getBillingZip() {
        return driver.findElement(billingAddress2).getText().split(" ")[2] + " "
                + driver.findElement(billingAddress2).getText().split(" ")[3];
    }

    String getBillingCountry() {
        return driver.findElement(billingCountry).getText();
    }

    String getBillingPhoneNumber() {
        return driver.findElement(billingPhone).getText();
    }

}
