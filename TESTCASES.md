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

While the original scenario only requires to check that the user is navigated to the TC page in the last step, I've also added the check that compares all TC names on the page to the ones in src/main/resources/TestCases.txt 

# Test Case 8: Verify All Products and product detail page

This one has a vague step "6. The products list is visible", so my implementation was just parsing the list of all element that have a product-unique attribute and checking that the list has more than 1 element. 

# Test Case 9: Search Product

In this TC, the expected search results are stored in src/main/resources/SearchResults.txt. 

# Test Case 10: Verify Subscription in home page and Test Case 11: Verify Subscription in Cart page

These two have been merged into one parametric test class that allows to check the subscription feature from all pages, using PageNames as EnumSource

# Test Case 12: Add Products in Cart

This test case wasn't drastically modified, we just parse the product names when we add them, and then check the quantity of products in the cart and their details.

_________________________________________

Test cases from 13 to 26 have been written with Cucumber in order to expand and display my knowledge, although Cucumber is not the best fit for E2E tests. For this reason, some tests have been divided into smaller given-when-then scenarios.

# Test Case 13: Verify Product quantity in Cart

Steps 4-5 are located in Product-catalog.feature, while steps 6-9 are located in Cart.feature.

# Test Case 14: Place Order: Register while Checkout, Test Case 15: Place Order: Register before Checkout, and Test Case 16: Place Order: Login before Checkout
 
These three scenarios are located in Placing-order.feature, utilizing (almost)the same steps in different order. These test cases have A LOT of "and" annotation used, and even though this is not very BDD-friendly, this was done to separate the step definitions by tested pages, instead of creating a single step definition class with a ton of pages used.

# Test Case 17: Remove Products From Cart

Located in Cart.feature. Not too different from the scenario to specifically clarify anything. 

# Test Case 18: View Category Products

Located in Product-catalog.feature. This TC was intentionally made more difficult, and the steps include parsing all the categories and subcategories from the sidebar, and then checking that each subcategory page contains a correct title, with no values hardcoded. The categories are stored via test context.

# Test Case 19: View & Cart Brand Products

Located in Product-catalog.feature. This TC was intentionally made more difficult, and the steps include parsing all the brands from the sidebar and then checking that each opened brand page corresponds to the opened brand by comparing the URL.

# Test Case 20: Search Products and Verify Cart After Login

Located in Product-catalog.feature. Steps 3-7 were intentionally implemented as a parameterized test, while steps 8-12 just check that adding the product and logging in doesn't remove the product from the cart.

# Test Case 21: Add review on product

Located in Review.feature. Apart from dividing it into two smaller scenarios, nothing was changed.

# Test Case 22: Add to cart from Recommended items


# Test Case 23: Verify address details in checkout page


# Test Case 24: Download Invoice after purchase order


# Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality


# Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality

