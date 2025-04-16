package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.CheckOutPage;

import java.util.ResourceBundle;

public class CheckOutPageSteps {

    private final TestContext context;
    ResourceBundle registerData;

    CheckOutPage checkOutPage;

    public CheckOutPageSteps(TestContext context) {
        this.context = context;
        checkOutPage = new CheckOutPage(context.getDriver());
        registerData = ResourceBundle.getBundle("validregister");
    }

    @And("the order is placed")
    public void the_order_is_placed() {
        checkOutPage.handleAds();
        checkOutPage.clickCheckOutButton();
    }

    @Then("displayed addresses correspond to the address entered during registration")
    public void displayed_addresses_correspond_to_the_address_entered_during_registration() {
        Assert.assertEquals("The registered first name and the delivery first name are different",
                registerData.getString("firstName"), checkOutPage.getDeliveryFirstName());

        Assert.assertEquals("The registered last name and the delivery last name are different",
                registerData.getString("lastName"), checkOutPage.getDeliveryLastName());

        Assert.assertEquals("The registered company and the delivery company are different",
                registerData.getString("company"), checkOutPage.getDeliveryCompany());

        Assert.assertEquals("The registered street and the delivery street are different",
                registerData.getString("address1"), checkOutPage.getDeliveryStreet());

        Assert.assertEquals("The registered house number and the delivery house number are different",
                registerData.getString("address2"), checkOutPage.getDeliveryHouseNumber());

        Assert.assertEquals("The registered city and the delivery city are different",
                registerData.getString("city"), checkOutPage.getDeliveryCity());

        Assert.assertEquals("The registered state and the delivery state are different",
                registerData.getString("state"), checkOutPage.getDeliveryState());

        Assert.assertEquals("The registered ZIP code and the delivery ZIP code are different",
                registerData.getString("zip"), checkOutPage.getDeliveryZip());

        Assert.assertEquals("The registered country and the delivery country are different",
                registerData.getString("country"), checkOutPage.getDeliveryCountry());

        Assert.assertEquals("The registered phone number and the delivery phone number are different",
                registerData.getString("number"), checkOutPage.getDeliveryPhoneNumber());

        Assert.assertEquals("The registered first name and the billing first name are different",
                registerData.getString("firstName"), checkOutPage.getBillingFirstName());

        Assert.assertEquals("The registered last name and the billing last name are different",
                registerData.getString("lastName"), checkOutPage.getBillingLastName());

        Assert.assertEquals("The registered company and the billing company are different",
                registerData.getString("company"), checkOutPage.getBillingCompany());

        Assert.assertEquals("The registered street and the billing street are different",
                registerData.getString("address1"), checkOutPage.getBillingStreet());

        Assert.assertEquals("The registered house number and the billing house number are different",
                registerData.getString("address2"), checkOutPage.getBillingHouseNumber());

        Assert.assertEquals("The registered city and the billing city are different",
                registerData.getString("city"), checkOutPage.getBillingCity());

        Assert.assertEquals("The registered state and the billing state are different",
                registerData.getString("state"), checkOutPage.getBillingState());

        Assert.assertEquals("The registered ZIP code and the billing ZIP code are different",
                registerData.getString("zip"), checkOutPage.getBillingZip());

        Assert.assertEquals("The registered country and the billing country are different",
                registerData.getString("country"), checkOutPage.getBillingCountry());

        Assert.assertEquals("The registered phone number and the billing phone number are different",
                registerData.getString("number"), checkOutPage.getBillingPhoneNumber());
    }
}
