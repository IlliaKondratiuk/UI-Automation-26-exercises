//This class is used for sharing the driver state between Cucumber scenarios

package config;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class TestContext {
    private WebDriver driver;
    private ArrayList<String> categoryList;
    private ArrayList<String> categoryFromDetailsList;

    public WebDriver getDriver() {
        return driver;
    }

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public ArrayList<String> getCategoryFromDetailsList() {
        return categoryFromDetailsList;
    }

    public void setCategoryFromDetailsList(ArrayList<String> categoryFromDetailsList) {
        this.categoryFromDetailsList = categoryFromDetailsList;
    }

    public void setCategoryList(ArrayList<String> categoryList) {
        this.categoryList = categoryList;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}