/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.tests.search;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.synerzip.rezoomex.pages.SearchPage;
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
public class TestCase11 extends HomePageBase {
	protected TestCase11() throws Exception {
		super();
	}

	@Test
	public void checkSearchCount() throws Exception {
		// LandingPage loginPage=new LandingPage(driver);

		System.out.println("In Test Method ::");
		SearchPage sp = new SearchPage(driver);

		SearchUtil.seachText(config.getProperty("testcase11"));
		int expectedSearchedResult = 50;
		int actualSearchedResult = sp.getSearchResultWebElements().size();
		System.out.println(sp.getSearchResultWebElements().size());
		System.out.println(sp.getCandidateNamesList().size());
		System.out.println(sp.getDateList().size());
		System.out.println(sp.getSummaryList().size());
		System.out.println(sp.getHighlightList().size());
		System.out.println(sp.getHighlightList().get(0).getText());
		System.out.println(sp.getHighlightList().get(0).getText()
				.contains("Java"));
		Assert.assertEquals(actualSearchedResult, expectedSearchedResult);

	}

}
