package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.And;
import pages.SignupPage;

import java.util.ResourceBundle;

public class SignUpPageSteps {

    private final TestContext context;

    SignupPage signupPage;

    private static ResourceBundle values;

    public SignUpPageSteps(TestContext context) {
        this.context = context;
        signupPage = new SignupPage(context.getDriver());
        values = ResourceBundle.getBundle("validregister");
    }

    @And("registration is completed")
    public void registration_is_completed() {
        signupPage.selectTitle();
        signupPage.enterPass(values.getString("pass"));
        signupPage.selectDOB(values.getString("dobday"),
                values.getString("dobmon"),
                values.getString("dobyear"));

        signupPage.checkNewsletterBox();
        signupPage.checkOffersBox();

        signupPage.enterFirstName(values.getString("firstName"));
        signupPage.enterLastName(values.getString("lastName"));
        signupPage.enterCompany(values.getString("company"));
        signupPage.enterAddress1(values.getString("address1"));
        signupPage.enterAddress2(values.getString("address2"));
        signupPage.selectCountry(values.getString("country"));
        signupPage.enterState(values.getString("state"));
        signupPage.enterCity(values.getString("city"));
        signupPage.enterZipcode(values.getString("zip"));
        signupPage.enterMobileNumber(values.getString("number"));

        signupPage.clickCreate();
    }

}
