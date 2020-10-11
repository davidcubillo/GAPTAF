package com.framework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class BaseTest {
	/**
	 * Base methods for the test pages
	 */
	private WebDriver driver;

	/**
	 * Adding the Webdriver manager instance for Chrome
	 */

	@BeforeSuite
	public void beforeSuite() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	/**
	 * Closing the driver
	 */
	@AfterSuite
	public void afterSuite() {
		if (null != driver) {
			driver.close();
			driver.quit();
		}
	}
	/**
	 * Adding the driver for public use instanciation
	 */
	public WebDriver getDriver() {
		return driver;
	}
}
