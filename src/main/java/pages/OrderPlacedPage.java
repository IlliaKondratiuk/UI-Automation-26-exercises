package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPlacedPage extends CommonElements {

    By downloadInvoiceButton = new By.ByXPath("//a[contains(@href, 'download_invoice')]");

    public OrderPlacedPage(WebDriver driver) {
        super(driver);
    }

    public void clickDownloadInvoice() {
        clickElementByLocator(downloadInvoiceButton);
    }
}
