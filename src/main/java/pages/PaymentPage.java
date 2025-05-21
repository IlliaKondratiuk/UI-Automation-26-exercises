package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends CommonElementsPage {

    By nameOnCardInput = new By.ByName("name_on_card");
    By cardNumberInput = new By.ByName("card_number");
    By cvcInput = new By.ByName("cvc");
    By expirationMonthInput = new By.ByName("expiry_month");
    By expirationYearInput = new By.ByName("expiry_year");

    By submitButton = new By.ById("submit");

    public PaymentPage() {
        super();
    }

    public void enterNameOnCard(String name) {
        fillInput(nameOnCardInput, name);
    }

    public void enterCardNumber(String number) {
        fillInput(cardNumberInput, number);
    }

    public void enterCvc(String cvc) {
        fillInput(cvcInput, cvc);
    }

    public void enterExpMonth(String month) {
        fillInput(expirationMonthInput, month);
    }

    public void enterExpYear(String year) {
        fillInput(expirationYearInput, year);
    }

    public void clickSubmitButton() {
        clickElementByLocator(submitButton);
    }
}
