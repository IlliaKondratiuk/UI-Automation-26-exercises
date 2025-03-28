# This feature file includes the steps from: "Test Case 13: Add Products in Cart",
# "Test Case 18: View Category Products" with extended steps,
# and "Test Case 19: View & Cart Brand Products"
# from the Automation Exercise page at automationexercise.com
# Steps 1(Launch browser) and 2(Navigate to url) have been added as the background
# Test Case 18 was intentionally made more difficult. It parses all the categories and subcategories from the sidebar
# and then checks that each subcategory page contains a correct title, with no values hardcoded.
# Test case 19 was intentionally made more difficult. It parses all the brands from the sidebar
# and then checks that each opened brand page corresponds to the opened brand by checking the URL.
# Test case 20 steps 3-7 were intentionally implemented as a parameterized test.
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

    #Exercise 19 steps 5-8 (expanded)
  Scenario: View product brands
    Given all product brands are available
    When the user visits each brand page through the list
    Then all opened pages correspond to the clicked brand

    #Exercise 20 steps 3-7
  Scenario Outline: Search for a product
    Given the 'products' page is opened
    And the user enters "<color>" in the search bar
    When user presses "search"
    Then the products with "<color>" in the name are displayed
    Examples:
      | color |
      | blue |
      | pink |

    #Exercise 20 steps 8-12
  Scenario: Logging in after adding products to cart
    Given the 'products' page is opened
    And a product is added to the cart
    And the 'login' page is opened
    And the user logs in
    And the 'cart' page is opened
    Then the added product is still in the cart