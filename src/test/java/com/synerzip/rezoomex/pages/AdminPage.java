/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * The Class AdminPage.
 * 
 * @author Chaitanya Barsode <chaitanya.barsode@synerzip.com>
 * @version 1.0
 * @since 10-09-2014
 */
public class AdminPage {
	/**
	 * The Web Driver.
	 */
	private WebDriver driver = null;

	/**
	 * Company Selection list
	 */
	@FindBy(xpath = "//select[@name='companies']")
	private WebElement company;
	/**
	 * Candidate Selection list
	 */
	@FindBy(xpath = "//select[@name='candidates']")
	private WebElement candidate;
	/**
	 * Generate Link Button
	 */
	@FindBy(xpath = "//input[@value='Generate Links']")
	private WebElement generateLinkButton;
	/**
	 * Instantiates an Admin Page
	 * 
	 * @param driver
	 *            the driver
	 */
	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Click Generate Link Button
	 */
	public void clickGenerateLinkButton() {
		generateLinkButton.click();
	}

	/**
	 * Get Company Web Element
	 */
	public WebElement getCompanyWebElement() {
		return company;
	}
	
	/**
	 * Get Candidate Web Element
	 */
	public WebElement getCandidateWebElement() {
		return candidate;
	}
	
	/**
	 * Select Company List
	 * @return 
	 */
	public Select selectCompany() {
		Select companies = new Select(company);
		System.out.println(companies.getOptions().size());
		companies.selectByIndex(3);
		return companies;
	}
	/**
	 * Select Company List
	 * @return 
	 */
	public Select selectCandidates() {
		Select candidates = new Select(candidate);
		System.out.println(candidates.getOptions().size());
		candidates.selectByIndex(2);
		candidates.selectByIndex(3);
		
		return candidates;
	}
}
