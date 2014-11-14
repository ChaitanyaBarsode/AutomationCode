/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.tests.search;

import org.testng.annotations.Test;

import com.synerzip.rezoomex.pages.AdminPage;

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
public class Test_AdminPage extends HomePageBase {
	protected Test_AdminPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void checkAdminPageFunctionality() throws Exception {
		driver.get("http://search.rezoomex.net/rezoomex-admin/initCreateLink");
		AdminPage ap = new AdminPage(driver);
		ap.selectCompany();
		ap.selectCandidates();
		ap.clickGenerateLinkButton();

	}

}
