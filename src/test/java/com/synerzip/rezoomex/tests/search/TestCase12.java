/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.tests.search;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.synerzip.rezoomex.pages.LandingPage;
import com.synerzip.rezoomex.util.SearchUtil;

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
public class TestCase12 extends HomePageBase {
	protected TestCase12() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void checkcreatedAssessment() throws Exception {
		System.out.println("In Test Method ::");
		SearchUtil.invalidSeachText(config.getProperty("testcase12"));
	}

	/**
	 * After the execution of the test case.
	 */
	@AfterSuite
	public void afterTest() {
		quitDriver();
	}

}
