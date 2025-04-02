//This class is used for sharing the driver state between Cucumber scenarios

package config.testConfigs;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class TestContext {
    private WebDriver driver;
    private ArrayList<String> categoryList;
    private ArrayList<String> categoryFromDetailsList;
    private ArrayList<String> brandNamesList;
    private ArrayList<String> brandNamesFromLinksList;
    private String addedProductName;

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

    public void setBrandNamesList(ArrayList<String> brandNamesList) {
        this.brandNamesList = brandNamesList;
    }

    public void setBrandNamesFromLinksList(ArrayList<String> brandNamesFromLinksList) {
        this.brandNamesFromLinksList = brandNamesFromLinksList;
    }

    public void setAddedProductName(String addedProductName) {
        this.addedProductName = addedProductName;
    }

    public String getAddedProductName() {
        return addedProductName;
    }

    public ArrayList<String> getBrandNamesList() {
        return brandNamesList;
    }

    public ArrayList<String> getBrandNamesFromLinksList() {
        return brandNamesFromLinksList;
    }
}