package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    By logo = new By.ByXPath("//div[contains(@class, 'logo')]//a//img");

    By productsButton = new By.ByXPath("//a[@href='/products']");
    By signupLoginButton = new By.ByXPath("//a[@href='/login']");
    By testCasesButton = new By.ByXPath("//a[@href='/test_cases']");
    By logOutButton = new By.ByXPath("//a[@href='/logout']");
    By contactUsButton = new By.ByXPath("//a[@href='/contact_us']");

    By loggedInAsLabelNav = new By.ByXPath("//i[contains(@class, 'fa-user')]");
    By usernameNav;

    String viewProductXPathBegin = "(//a[contains(@href, 'product_details')])[";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void initUsername(String username) {
        usernameNav = new By.ByXPath("//i[contains(@class, 'fa-user')]/parent::a/b[text()=\"" + username + "\"]");
    }

    public boolean logoIsVisible() {
        return isElementVisible(logo);
    }

    public void clickSignupLogin() {
        clickElement(signupLoginButton);
    }
    public void clickLogOut() {
        clickElement(logOutButton);
    }

    public boolean checkLoggedInAsLabelNav() {
        return isElementVisible(loggedInAsLabelNav) && isElementVisible(usernameNav);
    }

    public void clickContactUsButton() {
        clickElement(contactUsButton);
    }

    public void clickTestCasesButton() {
        clickElement(testCasesButton);
    }

    public void clickProductsButton() {
        clickElement(productsButton);
    }

    public void clickViewProduct(int index) {
        driver.findElement(new By.ByXPath(viewProductXPathBegin + index + "]")).click();
    }
}
