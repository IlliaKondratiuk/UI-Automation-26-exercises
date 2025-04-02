package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ContactPage extends BasePage {

    By getInTouchLabel = new By.ByXPath("//h2[text()='Get In Touch']");
    By successLabel = new By.ByXPath("//div[@class='contact-form']" +
            "//div[text() = 'Success! Your details have been submitted successfully.']");

    By nameInput = new By.ByName("name");
    By emailInput = new By.ByName("email");
    By subjectInput = new By.ByName("subject");
    By messageInput = new By.ByName("message");

    By fileUpload = new By.ByName("upload_file");

    By submitButton = new By.ByName("submit");
    By homeButton = new By.ByXPath("//div[@id='form-section']//a[@href='/']");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public boolean getInTouchIsVisible() {
        return isElementVisible(getInTouchLabel);
    }

    public void enterName(String name) {
        fillInput(nameInput, name);
    }

    public void enterEmail(String email) {
        fillInput(emailInput, email);
    }

    public void enterSubject(String subject) {
        fillInput(subjectInput, subject);
    }

    public void enterMessage(String message) {
        fillInput(messageInput, message);
    }

    public void uploadFile(String filePath) {
        fillInput(fileUpload, filePath);
    }

    public void clickSubmitButton() {
        clickElementByLocator(submitButton);
    }

    public void clickHomeButton() {
        clickElementByLocator(homeButton);
    }

    public boolean isSuccessTextVisible() {
        return isElementVisible(successLabel);
    }
}
