This file contains the details of what was added by me to make the test cases from automationexercise.com more complex or clear.

# General changes(1-12)

Every test case(TC) has the same first 3 steps: "Launch browser, Navigate to url 'http://automationexercise.com', Verify that home page is visible successfully".
Browser launch and URL navigation have been added as the "@Begin" method for each TC. As for "Verify that home page is visible successfully", it's been replaced with "Verify that logo is visible" as there are no specifications to how exactly we should check that the page is visible. That's why I'm just checking the general element to be visible instead.

# Test Case 1: Register User

This test case hasn't been changed significantly, but all the data for the registration is stored separately in src/main/resources/validregister.properties and then parsed with ResourceBundle. 

# Test Case 2: Login User with correct email and password and Test Case 3: Login User with incorrect email and password

These two tests have been merged together as positive and negative tests of the same functionality. Using @ParameterizedTest, this scenario runs 3 times with: correct login-correct pass, correct login-incorrect pass, and incorrect login-incorrect pass. Because of this, there is a TearDown method that clears cookies and quits the driver after each of these tests. Also, the original TC says to delete the account afterward, but this has been skipped to keep a valid account for login tests.

# Test Case 4: Logout User

This test case hasn't been changed significantly, but all the data for the login is stored separately in src/main/resources/validlogin.properties and then parsed with ResourceBundle.

# Test Case 5: Register User with existing email

This test case hasn't been changed significantly, but all the data for the registration is stored separately in src/main/resources/invalidregister.properties and then parsed with ResourceBundle.

# Test Case 6: Contact Us Form

Apart from the contact values being stored in src/main/resources/contactvalues.properties, I've also added the small step with uploading a contactfile.pdf to the contact form.

# Test Case 7: Verify Test Cases Page


# Test Case 8: Verify All Products and product detail page


# Test Case 9: Search Product


# Test Case 10: Verify Subscription in home page


# Test Case 11: Verify Subscription in Cart page



# Test Case 12: Add Products in Cart


# Test Case 13: Verify Product quantity in Cart


# Test Case 14: Place Order: Register while Checkout


# Test Case 15: Place Order: Register before Checkout


# Test Case 16: Place Order: Login before Checkout


# Test Case 17: Remove Products From Cart


# Test Case 18: View Category Products


# Test Case 19: View & Cart Brand Products


# Test Case 20: Search Products and Verify Cart After Login


# Test Case 21: Add review on product


# Test Case 22: Add to cart from Recommended items


# Test Case 23: Verify address details in checkout page


# Test Case 24: Download Invoice after purchase order


# Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality


# Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality

