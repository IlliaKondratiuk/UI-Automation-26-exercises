package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage extends BasePage {

    By checkOutButton = new By.ByXPath("//a[contains(@class, 'check_out')]");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckOutButton() {
        scrollToAndClickElement(checkOutButton);
    }
}
