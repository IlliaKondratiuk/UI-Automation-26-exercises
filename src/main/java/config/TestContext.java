//This class is used for sharing the driver state between Cucumber scenarios

package config;

import org.openqa.selenium.WebDriver;

public class TestContext {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}