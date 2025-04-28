# This feature file includes the steps from:
# "Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality"
# "Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality"
# from the Automation Exercise page at automationexercise.com
# Steps 1(Launch browser) and 2(Navigate to url) have been added as the background
# Each scenario has a comment explaining the source of the steps, as several tests might interact with the same feature
# Using JUnit 4.13.2 and Cucumber 7.14.0

Feature: UI interactions

  Background:
    Given the browser is launched
    And the 'main' page is opened
    When cookies window is handled
    Then the logo is visible

    #Exercise 25 steps 4-7
  Scenario: Using the "Scroll Up" button
    Given the user scrolls to the 'bottom' of the page
    When the user clicks the 'Scroll Up' button
    Then the page is scrolled to the top border

    #Exercise 26 steps 4-7
  Scenario: Arrow up button disappearing after scrolling the page back up
    Given the user scrolls to the 'bottom' of the page
    When the user scrolls to the 'top' of the page
    Then the 'Scroll Up' button is not visible