package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    static public WebDriver create(Drivers driver) {
        switch (driver) {
            case CHROME -> createChrome();
            case CHROME_INC -> createChromeInc();
        }

        return driverThread.get();
    }

    // Basic config for running tests in Chrome
    private static void createChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        driverThread.set(new ChromeDriver(options));
    }

    //Basic config for running tests in Chrome using incognito mode
    private static void createChromeInc() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        driverThread.set(new ChromeDriver(options));
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}
