package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends CommonElementsPage {

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

    public SignupPage() {
        super();
    }

    public boolean enterAccInfoIsVisible() {
        return isElementVisible(enterAccInfoLabel);
    }

    public void selectTitle() {
        clickElementByLocator(titleRadioMr);
    }

    public void enterPass(String pass) {
        fillInput(passInput, pass);
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
        clickElementByLocator(newsletterBox);
    }

    public void checkOffersBox() {
        clickElementByLocator(offersBox);
    }

    public void enterFirstName(String fName) {
        fillInput(firstNameInput, fName);
    }

    public void enterLastName(String lName) {
        fillInput(lastNameInput, lName);
    }

    public void enterCompany(String company) {
        fillInput(companyInput, company);
    }

    public void enterAddress1(String address) {
        fillInput(address1input, address);
    }

    public void enterAddress2(String address) {
        fillInput(address2input, address);
    }

    public void selectCountry(String country) {
        Select countryS = new Select(driver.findElement(countryDD));
        countryS.selectByValue(country);
    }

    public void enterState(String state) {
        fillInput(stateInput, state);
    }

    public void enterCity(String city) {
        fillInput(cityInput, city);
    }

    public void enterZipcode(String zipcode) {
        fillInput(zipInput, zipcode);
    }

    public void enterMobileNumber(String number) {
        fillInput(numberInput, number);
    }

    public void clickCreate() {
        clickElementByLocator(createButton);
    }
}
