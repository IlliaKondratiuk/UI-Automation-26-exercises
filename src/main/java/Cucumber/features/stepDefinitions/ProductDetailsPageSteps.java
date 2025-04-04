package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.ProductDetailsPage;

import java.util.ResourceBundle;

public class ProductDetailsPageSteps {
    
    private final TestContext context;
    
    ProductDetailsPage productDetailsPage;
    ResourceBundle review;

    public ProductDetailsPageSteps(TestContext context) {
        this.context = context;
        productDetailsPage = new ProductDetailsPage(context.getDriver());
        review = ResourceBundle.getBundle("validreview");
    }
    
    @And("the quantity is increased to {int}")
    public void the_quantity_is_increased_to(int quantity) {
        productDetailsPage.enterQuantity(quantity);
    }

    @And("the product is added to the cart")
    public void the_product_is_added_to_the_cart() {
        productDetailsPage.clickAddToCart();
    }

    @When("'View Cart' is clicked")
    public void view_cart_is_clicked() {
        productDetailsPage.clickViewCart();
    }


    @Then("the review label {string} is visible")
    public void the_label_is_visible(String reviewLabelText) {
        Assert.assertTrue("The review writing label is not visible", productDetailsPage.isReviewLabelVisible());
        Assert.assertTrue("The review label text is incorrect", productDetailsPage.isReviewTextLabelCorrect(reviewLabelText));
    }

    @And("all review fields are filled")
    public void all_review_fields_are_filled() {
        productDetailsPage.fillAllReviewInputs(review.getString("name"),
                review.getString("email"),
                review.getString("review"));
    }

    @And("the review is submitted")
    public void the_review_is_submitted() {
        productDetailsPage.handleAds();
        productDetailsPage.clickSubmitReview();
    }

    @Then("the review is posted successfully")
    public void the_review_is_posted_successfully() {
        Assert.assertTrue("Successfull review submission message is not visible",
                productDetailsPage.isReviewSubmittedSuccessVisible());
        Assert.assertTrue("The text of the successfull review submission message is correct",
                productDetailsPage.isReviewSubmittedTextCorrect("Thank you for your review."));

    }
}
