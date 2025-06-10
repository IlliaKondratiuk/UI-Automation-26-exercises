package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.ProductDetailsPage;

import java.util.ResourceBundle;

public class ProductDetailsPageSteps {
    
    private final TestContext context;
    
    ProductDetailsPage productDetailsPage;
    ResourceBundle review;

    public ProductDetailsPageSteps(TestContext context) {
        this.context = context;
        productDetailsPage = new ProductDetailsPage();
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
        Assertions.assertTrue(productDetailsPage.isReviewLabelVisible(),
                "The review writing label is not visible");
        Assertions.assertTrue(productDetailsPage.isReviewTextLabelCorrect(reviewLabelText),
                "The review label text is incorrect");
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
        //Perfectly speaking, such steps should confirm things via backend, but as we don't have access to it, we just check the success message appearance
        Assertions.assertTrue(productDetailsPage.isReviewSubmittedSuccessVisible(),
                "Successful review submission message is not visible");
        Assertions.assertTrue(productDetailsPage.isReviewSubmittedTextCorrect("Thank you for your review."),
                "The text of the successful review submission message is incorrect");

    }
}
