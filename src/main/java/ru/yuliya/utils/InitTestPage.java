package ru.yuliya.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class InitTestPage {

	protected Logger log;

	protected WebDriver driver;

	@BeforeClass(description = "Configure initial resources before class")
	protected void initial() throws IOException {
		driver = WebDriverUtils.createAndStartWebService();
		log = WebDriverUtils.getLog();
	}

	@AfterClass(description = "Quit driver after class")
	protected static void closeResource() {
		WebDriverUtils.quitDriver();
	}

	protected void pageLogging(String step, String action, String expecetedResult) {
		log.log(Level.INFO, step + " Action: " + action + ", " + step + " Expected: " + expecetedResult);
	}

}
