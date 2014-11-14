/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The Class LoginPage.
 * 
 * @author Chaitanya Barsode <chaitanya.barsode@synerzip.com>
 * @version 1.0
 * @since 10-09-2014
 */
public class LandingPage {
	/**
	 * The Web Driver.
	 */
	private WebDriver driver = null;

	/**
	 * Search Box Web Element with its Xpath.
	 */
	@FindBy(xpath = "//*[@id='searchinput']")
	private WebElement searchtextBox;
	/**
	 * Search Button Web Element with its Xpath.
	 */
	@FindBy(xpath = "//*[@id='searchBtn']")
	private WebElement searchButton;

	/**
	 * Search Box Web Element with its Xpath.
	 */
	@FindBy(xpath = "//*[@class='img-responsive logo']")
	private WebElement logo;

	@FindBy(xpath = "//div[@class='tagline']")
	private WebElement rezoomexText;

	@FindBy(xpath = "//div[@class='description']")
	private WebElement findTalentText;

	/**
	 * Instantiates a new login page.
	 * 
	 * @param driver
	 *            the driver
	 */
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Get the Search Button Web Element
	 */
	public WebElement getSearchButtonWebElement() {
		return searchButton;
	}

	/**
	 * Get the Text Box Web Element
	 */
	public WebElement getTextBoxWebElement() {
		return searchtextBox;
	}

	/**
	 * Get the Logo Web Element
	 */
	public WebElement getLogoWebElement() {
		return logo;
	}

	/**
	 * Get the Rezoomex Text Web Element
	 */
	public WebElement getRezoomexTextWebElement() {
		return rezoomexText;
	}

	/**
	 * Get the Rezoomex Find Talent Text Web Element
	 */
	public WebElement getRezoomexFindTalentText() {
		return findTalentText;
	}

	/**
	 * Search Text 
	 * @param User entered text
	 * @throws InterruptedException 
	 */
	public void searchText(String text) throws InterruptedException {
		searchtextBox.sendKeys(text);
		Thread.sleep(2000);
		searchButton.click();
		Thread.sleep(1000);
	}
	
	/**
	 * Clears Search Text 
	 */
	public void clearSearchText() {
		searchtextBox.clear();
	}
}
