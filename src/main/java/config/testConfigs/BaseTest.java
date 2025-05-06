package config.testConfigs;

import config.Config;
import config.Drivers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    public static WebDriver driver;

    //Initializing the driver before executing each test case
    @BeforeAll
    public static void base_before(){
        driver = Config.create(Drivers.CHROME);
    }

    //Closing the driver after executing each test case
    @AfterAll
    public static void base_after(){
        driver.quit();
    }
}