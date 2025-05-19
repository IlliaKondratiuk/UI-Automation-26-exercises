package config.testConfigs;

import config.Config;
import config.Drivers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    //Initializing the driver before executing each test case
    @BeforeEach
    public void setUp() {
        Config.create(Drivers.CHROME);
    }

    //Closing the driver after executing each test case
    @AfterEach
    public void tearDown() {
        Config.quitDriver();
    }

    //Used in each test to get its own driver
    public WebDriver getDriver() {
        return Config.getDriver();
    }
}