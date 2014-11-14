/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.tests.search;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.synerzip.rezoomex.pages.LandingPage;

/**
 * <h1>Test Cases for 1. Check user name and password. 2. Check Sign-In 3. Check
 * Forgot-Link 4. Check Logo</h1> The Test_LoginPageUI class helps for setting
 * verifying login screen components.
 * <p>
 * 
 * @author Chaitanya Barsode <chaitanya.barsode@synerzip.com>
 * @version 1.0
 * @since 10-09-2014
 */
public class Test_HomePage extends HomePageBase {

	protected Test_HomePage() throws Exception {
		super();
	}

	/**
	 * loginPage Web Element.
	 */

	public static LandingPage loginPage;

	/**
	 * Before Test Method
	 */

	@BeforeTest
	public void beforeTest() {
		loginPage = new LandingPage(driver);
	}

	/**
	 * Check Text Box Test Method.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void checkTextBoxTest() throws InterruptedException {

		// LoginPage lp=new LoginPage(driver);
		// LoginPage loginPage = new LoginPage(driver);
		boolean textboxStatus = loginPage.getTextBoxWebElement().isDisplayed();
		boolean searchButtonStatus = loginPage.getSearchButtonWebElement()
				.isDisplayed();
		Assert.assertEquals(textboxStatus, true);
		Assert.assertEquals(searchButtonStatus, true);
	}

	/**
	 * Check Logo Method.
	 */
	@Test
	public void checkLogoTest() {
		boolean logoStatus = loginPage.getLogoWebElement().isDisplayed();
		Assert.assertEquals(logoStatus, true);
	}

	/**
	 * Check RezoomexText Element.
	 */
	@Test
	public void checkRezoomexTextTest() {

		String actualText = loginPage.getRezoomexTextWebElement().getText();
		String expectedText = "VERY SHORT, VERY SMART RESUME";
		Assert.assertEquals(expectedText, actualText);

	}

	/**
	 * Check Talent Text Web Element.
	 */
	@Test
	public void checkTalentTextTest() {

		String actualText = loginPage.getRezoomexFindTalentText().getText();
		actualText = actualText.replace('"', ' ');
		// actualText=actualText.replace('"',' ');

		String expectedText = " Find best IT talent in Your City ";
		Assert.assertEquals(expectedText, actualText);

	}

	/**
	 * After the execution of the test case.
	 */
	@AfterSuite
	public void afterTest() {
		quitDriver();
	}

}
