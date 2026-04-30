package com.automation.framework.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    public static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static WebDriver driver;

    public static void initializeDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            logger.info("Chrome driver initialized");
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            logger.info("Firefox driver initialized");
        }
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }


    public static void quitDriver() {
        if(driver != null){
            driver.quit();
            logger.info("Driver quit successfully");
        }
    }
}
