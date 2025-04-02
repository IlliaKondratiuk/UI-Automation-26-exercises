package Cucumber.features.stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.And;
import pages.CheckOutPage;

public class CheckOutPageSteps {

    private final TestContext context;

    CheckOutPage checkOutPage;

    public CheckOutPageSteps(TestContext context) {
        this.context = context;
        checkOutPage = new CheckOutPage(context.getDriver());
    }

    @And("the order is placed")
    public void the_order_is_placed() {
        checkOutPage.handleAds();
        checkOutPage.clickCheckOutButton();
    }
}
