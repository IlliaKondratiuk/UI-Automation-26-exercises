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
        this.driver = driver;
    }

    public boolean getInTouchIsVisible() {
        return isElementVisible(getInTouchLabel);
    }

    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterSubject(String subject) {
        driver.findElement(subjectInput).sendKeys(subject);
    }

    public void enterMessage(String message) {
        driver.findElement(messageInput).sendKeys(message);
    }

    public void uploadFile(String filePath) {
        driver.findElement(fileUpload).sendKeys(filePath);
    }

    public void clickSubmitButton() {
        clickElement(submitButton);
    }

    public void clickHomeButton() {
        clickElement(homeButton);
    }

    public boolean isSuccessTextVisible() {
        return isElementVisible(successLabel);
    }
}
