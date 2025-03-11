@AccountCleanup
Feature: Place Order with Different Authentication Flows

  Background:
    Given the browser is launched
    And the 'main' page is opened
    When cookies window is handled
    Then the logo is visible

  Scenario: Register during checkout
    Given products are added to the cart
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

  Scenario: Register before checkout
    Given the 'login' page is opened
    And registration is initiated
    And registration is completed
    And the 'main' page is opened
    And products are added to the cart
    And the 'cart' page is opened
    And checkout is initiated
    And the order is placed
    When payment is completed
    Then the user is redirected to 'payment done'

  Scenario: Login before checkout
    Given the 'login' page is opened
    And the user logs in
    And the 'main' page is opened
    And products are added to the cart
    And the 'cart' page is opened
    And checkout is initiated
    And the order is placed
    When payment is completed
    Then the user is redirected to 'payment done'