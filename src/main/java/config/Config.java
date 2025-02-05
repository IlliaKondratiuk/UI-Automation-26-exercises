package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Config {
    static WebDriver driv;
    static public WebDriver create(Drivers driver) {

        switch (driver) {
            case CHROME -> createchrome();
            case CHROME_INC -> createchromeinc();
        }

        return driv;
    }

    // Basic config for running tests in Chrome
    private static void createchrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        driv = new ChromeDriver(options);
    }

    //Basic config for running tests in Chrome using incognito mode
    private static void createchromeinc() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        driv = new ChromeDriver(options);
    }

}
