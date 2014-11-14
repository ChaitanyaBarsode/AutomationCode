/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.synerzip.rezoomex.util.ErrorUtil;
import com.synerzip.rezoomex.util.TestUtil;
import com.synerzip.rezoomex.util.Xls_Reader;

/**
 * Test Base class implements a singleton pattern of driver instance This should
 * be extended by all test case classes
 * 
 * There are other utility methods and instance variables which can be used by
 * all extending classes.
 * 
 * @author Chaitanya Barsode <chaitanya.barsode@synerzip.com>
 * @version 1.0
 * @since 16-09-2014
 */

public abstract class TestBase {

	/** The driver. */
	protected static WebDriver driver = null;

	/** The app_log. */
	protected static org.apache.log4j.Logger app_log = null;

	/** The config. */
	protected static Properties config = null;

	/** The five second. */
	protected int FIVE_SECOND = 5000;

	/**
	 * Instantiates a new test base.
	 */
	/**
	 * The logger for Class.
	 */
	public static Logger APP_LOGS = null;
	/**
	 * The CONFIG Property File.
	 */
	public static Properties CONFIG = null;
	/**
	 * The OR Property File.
	 */
	public static Properties OR = null;
	/**
	 * The Data Property File.
	 */
	public static Properties Data = null;
	/**
	 * The Expected_string property file.
	 */
	public static Properties Expected_string = null;
	/**
	 * Browser Status.
	 */
	public static boolean isBrowserOpened = false;
	/**
	 * isLoggedIn Status.
	 */
	public static boolean isLoggedIn = false;
	/**
	 * isInitalized Status
	 */
	public static boolean isInitalized = false;
	/**
	 * SuiteXls Status
	 */
	public static Xls_Reader suiteXls = null;

	public static String filePath;
	/**
	 * This method will initialize the Property files defined.
	 * 
	 */
	public static String test;
	/**
	 * Instantiates a new test base.
	 */
	protected TestBase() throws Exception {
		initLog();
		initConfig();
		initDriver();
		initialize();
		// openBrowser();
	}

	/**
	 * Inits the log.
	 */
	private void initLog() {

		PropertyConfigurator
				.configure(System.getProperty("user.dir")
						+ "//src//test//java//com//synerzip//rezoomex//util//log4j.properties");
		// PropertyConfigurator
		// .configure("C:\\Users\\chaitanyab\\Desktop\\RezoomexTest\\src\\test\\java\\com\\synerzip\\rezoomex\\util\\log4j.properties");
		app_log = org.apache.log4j.Logger.getLogger("RezoomexLogger");
		// app_log = org.apache.log4j.Logger.getLogger("HRMSLogger");
		PropertyConfigurator
				.configure(System.getProperty("user.dir")
						+ "//src//test//java//com//synerzip//rezoomex//util//log4j.properties");
		// PropertyConfigurator.configure("C:\\Users\\chaitanyab\\Desktop\\RezoomexTest\\src\\test\\java\\com\\synerzip\\rezoomex\\util\\log4j.properties");
		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.Jdk14Logger");
		app_log.debug("YesDebug is initialized");
	}

	/**
	 * Inits the config.
	 */
	private static void initConfig() {
		if (config == null) {
			Properties config_env = new Properties();
			FileInputStream ip = null;

			try {
				ip = new FileInputStream(System.getProperty("user.dir")
						+ "//src//test//java//config//config_env.properties");
				// ip = new
				// FileInputStream("C:\\Users\\chaitanyab\\Desktop\\RezoomexTest\\src\\test\\java\\config\\config_env.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config_env.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}

			config = new Properties();

			// String fileName =
			// "C:\\Users\\chaitanyab\\Desktop\\RezoomexTest\\src\\test\\java\\config\\config_qa.properties";
			String fileName = System.getProperty("user.dir")
					+ "\\src\\test\\java\\config\\config_"
					+ config_env.getProperty("Environment") + ".properties";
			// String fileName =
			// System.getProperty("user.dir")+"//src//test//java//config//config_qa.properties";
			try {
				ip = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialize driver as per the configuration/properties file's browser type
	 */
	private static void initDriver() {
		if (driver == null) {
			if (config.getProperty("browser").equals("Mozilla")) {
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("IE")) {
				driver = new InternetExplorerDriver();
			} else if (config.getProperty("browser").equals("Chrome")) {
				driver = new ChromeDriver();
			}
			String waitTime = config.getProperty("defaultImplicitWait");
			driver.manage().timeouts()
					.implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
			driver.manage().window().maximize();
			//driver.get(config.getProperty("testsiteBaseURl"));
		}
	}

	/**
	 * Quit driver.
	 */
	public static void quitDriver() {

		driver.quit();
		driver = null;
		System.out.println("Closing the Browser");
	}

	/**
	 * Gets the driver. Used to re-initialize driver in case required (example,
	 * after browser quit/crash)
	 * 
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
			initDriver();
		}
		return driver;
	}

	/**
	 * Gets the config.
	 * 
	 * @return the config
	 */
	public static Properties getConfig() {
		if (config == null) {
			initConfig();
		}
		return config;
	}

	/**
	 * Login.
	 */
	public void login() {
		// doLogin();
	}

	public static void initialize() throws Exception {

		System.out.println("is ");
		if (!isInitalized) {
			APP_LOGS = Logger.getLogger("devpinoyLogger");
			APP_LOGS.debug("Initialize log file successfully");

			try {

				test = TestBase.class.getClassLoader().getResource("")
						.getPath();
				test = TestBase.class.getClassLoader().getResource("")
						.getPath();
				InputStream inputStream = TestBase.class.getClassLoader()
						.getResourceAsStream("config.properties");
				CONFIG = new Properties();
				OR = new Properties();
				Data = new Properties();
				Expected_string = new Properties();
				// suiteXls = new
				// Xls_Reader(System.getProperty("user.dir")+"//test//java//com//rezoomex//xls//Suite.xlsx");
				CONFIG.load(inputStream);
				inputStream = TestBase.class.getClassLoader()
						.getResourceAsStream("OR.properties");
				OR.load(inputStream);
				inputStream = TestBase.class.getClassLoader()
						.getResourceAsStream("DataFile.properties");
				Data.load(inputStream);
				inputStream = TestBase.class.getClassLoader()
						.getResourceAsStream("Assert_String.properties");

				Expected_string.load(inputStream);
				System.out.println(System.getProperty("user.dir"));
				System.out.println(System.getProperty("user.dir")
						+ "//test//java//com//rezoomex//xls//Suite.xlsx");
				suiteXls = new Xls_Reader(System.getProperty("user.dir")
						+ "//src//test//java//com//rezoomex//xls//Suite.xlsx");
				// suiteXls = new Xls_Reader("Suite.xlsx");
				filePath = CONFIG.getProperty("baseUrl");
				System.out.println(filePath);

			} catch (IOException e) {
				e.printStackTrace();
			}

			// Reading the Excel and property file
			APP_LOGS.debug("Loading Property files");
			// ip =new
			// FileInputStream(System.getProperty("user.dir")+"//test//resources//DataFile.properties");
			// suiteXls = new
			// Xls_Reader(System.getProperty("user.dir")+"//test//java//com//rezoomex//xls//Suite.xlsx");
			// config property file

			// CONFIG= new Properties();
			// FileInputStream ip = new
			// FileInputStream(System.getProperty("user.dir")
			// +"//test//resources//config.properties");
			// CONFIG.load(ip);

			// OR property file
			// OR= new Properties();
			/*
			 * ip =new FileInputStream(System.getProperty("user.dir")+
			 * "//test//resources//OR.properties"); OR.load(ip);
			 * 
			 * // Data Properties file Data= new Properties(); ip =new
			 * FileInputStream(System.getProperty("user.dir")+
			 * "//test//resources//DataFile.properties"); Data.load(ip);
			 * 
			 * // Assert_String Properties file Expected_string= new
			 * Properties(); ip =new
			 * FileInputStream(System.getProperty("user.dir"
			 * )+"//test//resources//Assert_String.properties");
			 * Expected_string.load(ip);
			 * 
			 * APP_LOGS.debug("Loaded Property Files successfully");
			 * 
			 * // LOad the Excel file APP_LOGS.debug("Loading Excel files");
			 * suiteXls = new Xls_Reader(System.getProperty("user.dir")+
			 * "//test//java//com//rezoomex//xls//Suite.xlsx");
			 * APP_LOGS.debug("Loaded Excel Files successfully");
			 */
			isInitalized = true;
		}
	}

	// selenium Webdriver open a browser if its not opened
	public static void openBrowser() {

		if (!isBrowserOpened) {
			if (CONFIG.getProperty("browserType").equals("Firefox")) {
				System.out.println(CONFIG.getProperty("browserType"));
				driver = new FirefoxDriver();
			} else if (CONFIG.getProperty("browserType").equals("IE"))
				driver = new InternetExplorerDriver();
			else if (CONFIG.getProperty("browserType").equals("CHROME"))
				driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			isBrowserOpened = true;
			driver.navigate().to(CONFIG.getProperty("baseUrl"));
			APP_LOGS.debug("Selecting the Browser");

		}
	}

	// close browser
	public void closeBrowser() {
		driver.quit();
		isBrowserOpened = false;
		APP_LOGS.debug("driver quit");
	}

	// getObjectid find element by Xpath
	public static WebElement getObjectxpath(String xpathKey) {
		try {
			return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		} catch (Throwable t) {
			// report error
			ErrorUtil.addVerificationFailure(t);
			Assert.assertTrue(false, t.getMessage());
			APP_LOGS.debug("No element present" + xpathKey);
			return null;
		}

	}

	// find the test suite is runnable
	public static boolean isSuiteRunnable(Xls_Reader xls, String suiteName) {
		boolean isexecutable = false;
		for (int i = 2; i <= xls.getRowCount("Test Suite"); i++) {

			if ((xls.getCellData("Test Suite", "TSID", i)).equals(suiteName)) {
				if ((xls.getCellData("Test Suite", "Runmode", i))
						.equalsIgnoreCase("Y")) {
					isexecutable = true;
				} else {
					isexecutable = false;
				}
			}
		}
		xls = null;
		return isexecutable;

	}

	// / returns true if runmode of the test is equal to Y
	public static boolean isTestCaseRunnable(Xls_Reader xls, String SheetName,
			String testCaseName) {
		boolean isExecutable = false;
		for (int i = 2; i <= xls.getRowCount(SheetName); i++) {

			if (xls.getCellData(SheetName, "testCaseName", i).equalsIgnoreCase(
					testCaseName)) {

				if (xls.getCellData(SheetName, "Runmode", i).equalsIgnoreCase(
						"Y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}
		}

		return isExecutable;

	}

	/**
	 * Wrapper method for Thread.sleep()
	 * 
	 * @param timeInSeconds
	 */
	public static void wait(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wrapper method for WebDriverWait (ExpectedCondition =
	 * elementToBeClickable)
	 * 
	 * @param element
	 * @param timeInSeconds
	 */
	public static void waitForElementClickable(WebElement element,
			int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wrapper method for WebDriverWait (ExpectedCondition = visibilityOf
	 * element)
	 * 
	 * @param element
	 * @param timeInSeconds
	 */
	public static void waitForElementVisible(WebElement element,
			int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Gets the result.
	 * 
	 * @param result
	 *            the result
	 * @return the result
	 */
	@AfterMethod
	public void getResult(ITestResult result) {
		System.out
				.println("Method Name: " + result.getMethod().getMethodName());
		System.out.println("Sucess Status: " + result.isSuccess());

		if (!result.isSuccess()) {
			TestUtil.takeScreenShot(driver, result.getMethod().getMethodName());
			quitDriver();
			// getDriver();
			// login();
		}
	}
}
