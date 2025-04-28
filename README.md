# UI Test Automation Exercises

This repository contains a collection of completed 26 UI test automation exercises from https://automationexercise.com/test_cases

## ❓ Why this project? 

It is designed for learning, practicing, and demonstrating UI test automation skills. 

## 🛠 Technologies used
- **Language:** Java
- **Frameworks:** Selenium, Cucumber, JUnit 4  
- **Build Tool:** Maven  
- **Reporting:** Allure  

## 📂 Project structure
* 📂 src/test/java  - Test classes for exercises from 1 to 12.
* 📁 src/main/java/config - WebDriver setup and page enums.
* 📁 src/main/java/config/testConfigs - Base test class, test context class for Cucumber, interface for test grouping.
* 📁 src/main/java/pages - Page classes for tested pages(written using the Page Object model).
* 📁 src/main/java/Cucumber/features - Cucumber scenarios that cover exercises from 13 to 26 **(only 13 to 25 for now.)**
* 📁 src/main/java/Cucumber/stepDefinitions - Step definitions for Cucumber scenarios, organized per tested page.
* 📁 src/main/resources - Resources for the tests, including dummy files and data parsed during the test executions.

## 🔹 Things to note and personal decisions
- The first half of the collection(exercises 1-12) were done using pure JUnit 4. The second half(exercises 13-26) will be done using Cucumber+JUnit.
- Each step in test classes for exercises from 1 to 12 contains a comment, that is copied from https://automationexercise.com/test_cases, to show which step each method call or assertion corresponds to.
- Some steps have been modified to be more clear. For example, the repeating step "Verify that home page is visible successfully" was changed to "Verify that the website logo is visible" as the original step is too vague with no criteria of what exactly to test to ensure that the page is visible.
- Some test cases' steps were intentionally modified or added by me in order to increase the difficulty of the project. Including:
- - Each test case that involved filling inputs used external files as sources.
- - Exercises 2-3 and 10-11 were fused into one **parameterized** test class.
- - Test Case 7 now contains an additional step that verifies all the names of each test case listed on the tested page.
- I'm still working on the project. The exercise 26 is yet to be done, and I might change some parts of the project in future(Like moving to JUnit 5, adding listeners, loggers, etc.)

## 🚀 Running the tests
Ensure you have the following installed:
- Java (JDK 11+)
- Maven
- Allure
- Chrome & ChromeDriver

### The commands:
Execute the following command to run the tests via Maven:
```mvn clean test```

To generate Allure reports:
```allure generate <resultsDir>```

To view the reports in a separate webpage:
```mvn allure:serve```