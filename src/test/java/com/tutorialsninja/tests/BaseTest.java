package com.tutorialsninja.tests;

import com.tutorialsninja.utils.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    //TODO get from configuration file
    protected static final String BASE_URL = "http://tutorialsninja.com/demo/";

    @BeforeMethod
    public void setUp(@org.testng.annotations.Optional("chrome") String browser) {
        logger.info("Starting setup for testing");
        DriverManager.initializeDriver(browser);
        DriverManager.getDriver().get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Starting tear down for testing");
        DriverManager.quitDriver();
    }
}