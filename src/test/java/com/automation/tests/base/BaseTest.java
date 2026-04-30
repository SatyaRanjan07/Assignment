package com.automation.tests.base;

import com.automation.framework.core.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        logger.info("Starting test setup...");
        DriverManager.initializeDriver("chrome");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Test tear down...");
        DriverManager.quitDriver();
    }
}
