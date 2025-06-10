package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.CheckOutPage;

import java.util.ResourceBundle;

public class CheckOutPageSteps {

    private final TestContext context;
    ResourceBundle registerData;

    CheckOutPage checkOutPage;

    public CheckOutPageSteps(TestContext context) {
        this.context = context;
        checkOutPage = new CheckOutPage();
        registerData = ResourceBundle.getBundle("validregister");
    }

    @And("the order is placed")
    public void the_order_is_placed() {
        checkOutPage.handleAds();
        checkOutPage.clickCheckOutButton();
    }

    @Then("displayed addresses correspond to the address entered during registration")
    public void displayed_addresses_correspond_to_the_address_entered_during_registration() {
        Assertions.assertEquals(registerData.getString("firstName"), checkOutPage.getDeliveryFirstName(),
                "The registered first name and the delivery first name are different");
        Assertions.assertEquals(registerData.getString("lastName"), checkOutPage.getDeliveryLastName(),
                "The registered last name and the delivery last name are different");
        Assertions.assertEquals(registerData.getString("company"), checkOutPage.getDeliveryCompany(),
                "The registered company and the delivery company are different");
        Assertions.assertEquals(registerData.getString("address1"), checkOutPage.getDeliveryStreet(),
                "The registered street and the delivery street are different");
        Assertions.assertEquals(registerData.getString("address2"), checkOutPage.getDeliveryHouseNumber(),
                "The registered house number and the delivery house number are different");
        Assertions.assertEquals(registerData.getString("city"), checkOutPage.getDeliveryCity(),
                "The registered city and the delivery city are different");
        Assertions.assertEquals(registerData.getString("state"), checkOutPage.getDeliveryState(),
                "The registered state and the delivery state are different");
        Assertions.assertEquals(registerData.getString("zip"), checkOutPage.getDeliveryZip(),
                "The registered ZIP code and the delivery ZIP code are different");
        Assertions.assertEquals(registerData.getString("country"), checkOutPage.getDeliveryCountry(),
                "The registered country and the delivery country are different");
        Assertions.assertEquals(registerData.getString("number"), checkOutPage.getDeliveryPhoneNumber(),
                "The registered phone number and the delivery phone number are different");
        Assertions.assertEquals(registerData.getString("firstName"), checkOutPage.getBillingFirstName(),
                "The registered first name and the billing first name are different");
        Assertions.assertEquals(registerData.getString("lastName"), checkOutPage.getBillingLastName(),
                "The registered last name and the billing last name are different");
        Assertions.assertEquals(registerData.getString("company"), checkOutPage.getBillingCompany(),
                "The registered company and the billing company are different");
        Assertions.assertEquals(registerData.getString("address1"), checkOutPage.getBillingStreet(),
                "The registered street and the billing street are different");
        Assertions.assertEquals(registerData.getString("address2"), checkOutPage.getBillingHouseNumber(),
                "The registered house number and the billing house number are different");
        Assertions.assertEquals(registerData.getString("city"), checkOutPage.getBillingCity(),
                "The registered city and the billing city are different");
        Assertions.assertEquals(registerData.getString("state"), checkOutPage.getBillingState(),
                "The registered state and the billing state are different");
        Assertions.assertEquals(registerData.getString("zip"), checkOutPage.getBillingZip(),
                "The registered ZIP code and the billing ZIP code are different");
        Assertions.assertEquals(registerData.getString("country"), checkOutPage.getBillingCountry(),
                "The registered country and the billing country are different");
        Assertions.assertEquals(registerData.getString("number"), checkOutPage.getBillingPhoneNumber(),
                "The registered phone number and the billing phone number are different");
    }
}
