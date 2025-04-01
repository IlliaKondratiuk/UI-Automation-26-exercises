# This feature file includes the steps from: "Test Case 21: Add review on product",
# from the Automation Exercise page at automationexercise.com
# Steps 1(Launch browser) and 2(Navigate to url) have been added as the background
# Each scenario has a comment explaining the source of the steps, as several tests might interact with the same feature
# Using JUnit 4.13.2 and Cucumber 7.14.0

  Feature: Product reviews

    Background:
      Given the browser is launched
      And the 'main' page is opened
      When cookies window is handled
      Then the logo is visible

    #Exercise 21 steps 3-6
    Scenario: Opening a product details page
      Given the 'products' page is opened
      When any product's details page is opened
      Then the "Write Your Review" label is vibile


    #Exercise 21 steps 7-9
    Scenario: Leaving a review for a product
      Given the 'products' page is opened
      And any product's details page is opened
      And all review fields are filled
      And the review is submitted
      Then the review is posted successfully