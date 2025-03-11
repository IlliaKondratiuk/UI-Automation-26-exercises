package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.When;
import pages.PaymentPage;

import java.util.ResourceBundle;

public class PaymentPageSteps {


    private final TestContext context;

    PaymentPage paymentPage;

    private static ResourceBundle values;

    public PaymentPageSteps(TestContext context) {
        this.context = context;
        paymentPage = new PaymentPage(context.getDriver());
        values = ResourceBundle.getBundle("validpayment");
    }

    @When("payment is completed")
    public void payment_is_completed() {
        paymentPage.enterNameOnCard(values.getString("name"));
        paymentPage.enterCardNumber(values.getString("number"));
        paymentPage.enterCvc(values.getString("cvc"));
        paymentPage.enterExpMonth(values.getString("expirationM"));
        paymentPage.enterExpYear(values.getString("expirationY"));

        paymentPage.clickSubmitButton();
    }
}
