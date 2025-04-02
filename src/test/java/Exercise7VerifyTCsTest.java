// This is "Test Case 7: Verify Test Cases Page" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report
// Step 4 has been added by me to increase the complexity of the exercise
// The expected TC names are parsed from src\resources\TestCases.txt

import config.PageNames;
import config.testConfigs.BaseTest;
import config.testConfigs.CriticalTests;

import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.MainPage;
import pages.TestCasesPage;

import java.io.IOException;

@Category(CriticalTests.class)
public class Exercise7VerifyTCsTest extends BaseTest {

    private static MainPage mainPage;
    private static TestCasesPage testCasesPage;

    //Path to the file with the expected names of the test cases
    private final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\TestCases.txt";

    @Before
    public void begin() {
        driver.get(PageNames.MAIN.getUrl());

        mainPage = new MainPage(driver);
        mainPage.handleCookies();
    }

    @Test
    @Epic("Test Cases")
    @Feature("Test cases page")
    @Story("As a user, I can visit the test cases page so that I can view all published exercises for test AQA specialists.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex7VerifyTCsTest() {
        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click on 'Test Cases' button
        mainPage.clickTestCasesButton();

        //Step 3. Verify user is navigated to test cases page successfully
        Assert.assertEquals("Step 3: The user is not navigated to the test cases page.",
                 "https://automationexercise.com/test_cases", driver.getCurrentUrl());

        //Step 4. Verify that all test cases' names are correct
        testCasesPage = new TestCasesPage(driver);
        try {
            Assert.assertTrue("Step 4: The test cases' names are incorrect",
                    testCasesPage.areTestNamesCorrect(filePath));
        } catch (IOException e) {
            Assert.fail("Step 4: An error occurred trying to read the file with expected TC names");
        }
    }
}
