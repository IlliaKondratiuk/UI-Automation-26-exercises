package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {

    WebDriver driver;

    By enterAccInfoLabel = new By.ByXPath("//b[text()='Enter Account Information']");
    By titleRadioMr = new By.ById("id_gender1");
    By passInput = new By.ById("password");
    By dobDay = new By.ById("days");
    By dobMonth = new By.ById("months");
    By dobYear = new By.ById("years");
    By newsletterBox = new By.ById("newsletter");
    By offersBox = new By.ById("optin");
    By firstNameInput = new By.ById("first_name");
    By lastNameInput = new By.ById("last_name");
    By companyInput = new By.ById("company");
    By address1input = new By.ById("address1");
    By address2input  = new By.ById("address2");
    By countryDD = new By.ById("country");
    By stateInput = new By.ById("state");
    By cityInput = new By.ById("city");
    By zipInput = new By.ById("zipcode");
    By numberInput = new By.ById("mobile_number");
    By createButton = new By.ByXPath("//button[@data-qa = 'create-account']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean enterAccInfoIsVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement enterAccInfoEl = wait.until(ExpectedConditions.visibilityOfElementLocated(this.enterAccInfoLabel));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void selectTitle() {
        driver.findElement(titleRadioMr).click();
    }

    public void enterPass(String pass) {
        driver.findElement(passInput).sendKeys(pass);
    }

    public void selectDOB(String day, String month, String year) {
        Select dobDayS = new Select(driver.findElement(dobDay));
        Select dobMonthS = new Select(driver.findElement(dobMonth));
        Select dobYearS = new Select(driver.findElement(dobYear));

        dobDayS.selectByValue(day);
        dobMonthS.selectByValue(month);
        dobYearS.selectByValue(year);
    }

    public void checkNewsletterBox() {
        driver.findElement(newsletterBox).click();
    }

    public void checkOffersBox() {
        driver.findElement(offersBox).click();
    }

    public void enterFirstName(String fName) {
        driver.findElement(firstNameInput).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        driver.findElement(lastNameInput).sendKeys(lName);
    }

    public void enterCompany(String company) {
        driver.findElement(companyInput).sendKeys(company);
    }

    public void enterAddress1(String address) {
        driver.findElement(address1input).sendKeys(address);
    }

    public void enterAddress2(String address) {
        driver.findElement(address2input).sendKeys(address);
    }

    public void selectCountry(String country) {
        Select countryS = new Select(driver.findElement(countryDD));
        countryS.selectByValue(country);
    }

    public void enterState(String state) {
        driver.findElement(stateInput).sendKeys(state);
    }

    public void enterCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }

    public void enterZipcode(String zipcode) {
        driver.findElement(zipInput).sendKeys(zipcode);
    }

    public void enterMobileNumber(String number) {
        driver.findElement(numberInput).sendKeys(number);
    }

    public void clickCreate() {
        driver.findElement(createButton).click();
    }
}
