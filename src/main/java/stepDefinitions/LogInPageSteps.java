package stepDefinitions;

import config.TestContext;
import io.cucumber.java.en.And;
import pages.LoginPage;

import java.util.ResourceBundle;

public class LogInPageSteps {

    private final TestContext context;

    LoginPage loginPage;

    private static ResourceBundle valuesReg;
    private static ResourceBundle valuesLog;

    public LogInPageSteps(TestContext context) {
        this.context = context;
        loginPage = new LoginPage(context.getDriver());
        valuesReg = ResourceBundle.getBundle("validregister");
        valuesLog = ResourceBundle.getBundle("validlogin");
    }

    @And("registration is initiated")
    public void registration_is_initiated() {
       loginPage.enterNameEmailSignup(valuesReg.getString("name"), valuesReg.getString("email"));
       loginPage.clickSignUpButton();
    }

    @And("the user logs in")
    public void theUserLogsIn() {
        loginPage.enterEmailPassLogin(valuesLog.getString("email"), valuesLog.getString("pass"));
        loginPage.clickLoginButton();
    }
}
