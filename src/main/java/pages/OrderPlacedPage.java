package pages;

import org.openqa.selenium.By;

public class OrderPlacedPage extends CommonElementsPage {

    By downloadInvoiceButton = new By.ByXPath("//a[contains(@href, 'download_invoice')]");

    public OrderPlacedPage() {
        super();
    }

    public void clickDownloadInvoice() {
        clickElementByLocator(downloadInvoiceButton);
    }
}
