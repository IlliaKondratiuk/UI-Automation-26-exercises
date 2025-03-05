#1. Launch browser
#2. Navigate to url 'http://automationexercise.com'
#3. Verify that home page is visible successfully
#4. Click 'View Product' for any product on home page
#5. Verify product detail is opened
#6. Increase quantity to 4
#7. Click 'Add to cart' button
#8. Click 'View Cart' button
#9. Verify that product is displayed in cart page with exact quantity

Feature: Add product to cart

  Background:
    Given the browser is launched
    When the 'http://automationexercise.com' page is opened
    And cookies window is handled
    Then the logo is visible

  Scenario: View product details
    When 'View Product' is clicked for product 1
    Then the product 1 detail page is opened

  Scenario: Add product to cart and verify quantity
    Given the product 1 detail page was opened
    And the quantity is increased to 4
    And the product is added to the cart
    When 'View Cart' is clicked
    Then the cart page is opened
    And the product 1 in the cart has quantity 4