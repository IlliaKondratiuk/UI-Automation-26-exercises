# This feature file includes the steps from:
# 1) "Test Case 14: Place Order: Register while Checkout"
# 2) "Test Case 15: Place Order: Register before Checkout"
# 3) "Test Case 16: Place Order: Login before Checkout"
# 4) "Test Case 23: Verify address details in checkout page"
# from the Test Cases page at automationexercise.com
# Steps 1(Launch browser) and 2(Navigate to url) have been added as the background.
# Each scenario has a comment explaining the source of the steps, as several tests might interact with the same feature.
# These test cases have A LOT of "and" annotation used, and even though this is not very BDD-friendly, this was done to
# separate the step definitions by tested pages, instead of creating one step definition class with a ton of pages used.
# Using JUnit 4.13.2 and Cucumber 7.14.0

@AccountCleanup
Feature: Place Order with Different Authentication Flows

  Background:
    Given the browser is launched
    And the 'main' page is opened
    When cookies window is handled
    Then the logo is visible

    # Exercise 14
  Scenario: Register during checkout
    Given 2 products are added to the cart
    And the 'cart' page is opened
    And checkout is initiated
    And login page is opened via the modal window
    And registration is initiated
    And registration is completed
    And the 'cart' page is opened
    And checkout is initiated
    And the order is placed
    When payment is completed
    Then the user is redirected to 'payment done'

    # Exercise 15
  Scenario: Register before checkout
    Given the 'login' page is opened
    And registration is initiated
    And registration is completed
    And the 'main' page is opened
    And 2 products are added to the cart
    And the 'cart' page is opened
    And checkout is initiated
    And the order is placed
    When payment is completed
    Then the user is redirected to 'payment done'

    # Exercise 16
  Scenario: Login before checkout
    Given the 'login' page is opened
    And the user logs in
    And the 'main' page is opened
    And 2 products are added to the cart
    And the 'cart' page is opened
    And checkout is initiated
    And the order is placed
    When payment is completed
    Then the user is redirected to 'payment done'

    # Exercise 23
  Scenario: Entering delivery and billing addresses during registration before checkout
    Given the 'login' page is opened
    And registration is initiated
    And registration is completed
    And the 'main' page is opened
    And 2 products are added to the cart
    And the 'cart' page is opened
    When checkout is initiated
    Then displayed addresses correspond to the address entered during registration