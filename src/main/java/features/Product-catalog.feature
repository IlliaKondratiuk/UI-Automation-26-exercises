# This feature file includes the steps from: "Test Case 13: Add Products in Cart"
# and "Test Case 18: View Category Products" with extended steps
# from the Automation Exercise page at automationexercise.com
# Steps 1(Launch browser) and 2(Navigate to url) have been added as the background
# Test Case 18 was intentionally made more difficult. It parses all the categories and subcategories from the sidebar
# and then checks that each subcategory page contains a correct title, with no values hardcoded.
# Each scenario has a comment explaining the source of the steps, as several tests might interact with the same feature
# Using JUnit 4.13.2 and Cucumber 7.14.0

Feature: Catalog

  Background:
    Given the browser is launched
    And the 'main' page is opened
    When cookies window is handled
    Then the logo is visible

    # Exercise 13 steps 4-5
  Scenario: View product details
    When 'View Product' is clicked for product 1
    Then the product 1 detail page is opened

    # Exercise 18 steps 4-8 (expanded)
  Scenario: View product categories
    Given all categories and subcategories are available
    When the user visits each subcategory page
    Then the headers include both the category and subcategory of the product