package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.OrderPlacedPage;
import utils.FileTestUtil;

public class PlacingOrderSteps

{
    private final TestContext context;

    OrderPlacedPage orderPlacedPage;

    public PlacingOrderSteps(TestContext context) {
        this.context = context;
        orderPlacedPage = new OrderPlacedPage();
    }

    @When("'Download Invoice' is clicked")
    public void download_invoice_is_clicked() {
        orderPlacedPage.clickDownloadInvoice();
    }

    @Then("the invoice is downloaded successfully")
    public void the_invoice_is_downloaded_successfully() {
        Assertions.assertTrue(FileTestUtil.isFileDownloaded(System.getProperty("user.home") + "/Downloads", "invoice.txt"),
                "The invoice was not downloaded");
    }
}
