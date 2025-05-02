package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestCasesPage extends CommonElementsPage {

    By testCases = new By.ByXPath("//a[@data-toggle='collapse']//u");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public boolean areTestNamesCorrect(String nameFilePath) throws IOException {
        //Extracting the expected names of the test cases from the .txt file
        List<String> expectedNames = Files.readAllLines(Paths.get(nameFilePath));

        //Finding all the collapsed TC names on the page
        List<WebElement> testCasesCollapsed = driver.findElements(testCases);

        //Comparing the name of each element in the list with each expected name
        for (int i = 0; i < testCasesCollapsed.size(); i++) {
            if (!testCasesCollapsed.get(i).getText().equals(expectedNames.get(i))) {
                return false;
            }
        }
        return true;
    }
}
