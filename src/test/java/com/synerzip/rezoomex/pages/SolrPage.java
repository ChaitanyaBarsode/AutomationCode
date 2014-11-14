/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.synerzip.rezoomex.base.WaitBase;

/**
 * The Class LoginPage.
 * 
 * @author Chaitanya Barsode <chaitanya.barsode@synerzip.com>
 * @version 1.0
 * @since 10-09-2014
 */
public class SolrPage extends WaitBase {
	/**
	 * The Web Driver.
	 */
	private WebDriver driver = null;

	/**
	 * Core Selector Web Element
	 */
	@FindBy(xpath = "//a[@class='chzn-single chzn-default']")
	private WebElement coreSelector;
	/**
	 * Query Link
	 */
	@FindBy(xpath = "//a[@href='#/collection1/query']")
	private WebElement queryLink;
	/**
	 * Solr Text Area
	 */
	@FindBy(xpath = "//div[@class = 'fieldset']//textarea")
	private WebElement solrTextArea;
	/**
	 * Submit Button
	 */
	@FindBy(xpath = "//button [@type = 'submit']")
	private WebElement submitButton;
	/**
	 * Solr Count
	 */
	@FindBy(xpath = "//span [text()='numFound']//following-sibling::*")
	private WebElement solrCount;

	/**
	 * Instantiates a new login page.
	 * 
	 * @param driver
	 *            the driver
	 */
	public SolrPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Click Query Link
	 */
	public void clickQueryLink() {
		queryLink.click();
	}

	/**
	 * Get the Core Selector
	 */
	public WebElement getTextBoxWebElement() {
		return coreSelector;
	}

	/**
	 * Click Core Selector
	 * 
	 * @param User
	 *            entered text
	 * @throws InterruptedException
	 */
	public HashMap clickSolrCollector(String[][] arr)
			throws InterruptedException {

		LinkedHashMap hm = new LinkedHashMap();
		waitForElementClickable(driver, coreSelector, 60);
		coreSelector.click();

		WebElement w = driver.findElement(By.xpath("//input[@type='search']"));
		w.sendKeys("collection1");
		w.sendKeys(Keys.RETURN);

		waitForElementClickable(driver, queryLink, 60);
		// Thread.sleep(5000);
		queryLink.click();

		// Thread.sleep(5000);

		String t = "";

		for (int i = 0; i < arr.length; i++) {

			solrTextArea.clear();

			String[] arr1 = arr[i][1].split(",");

			for (int j = 0; j < arr1.length; j++) {

				t = t + " microresume:" + arr1[j];

				if (arr1.length > 1 && j < arr1.length - 1) {
					t = t + " or";
				}
			}
			solrTextArea.sendKeys(t);

			// solrTextArea.sendKeys("microresume:"+ReadKeyword.cellvalue.get(i));
			wait(2);
			// Thread.sleep(2000);
			submitButton.click();
			wait(2);
			// Thread.sleep(2000);
			String SolrCount = solrCount.getText();
			// System.out.print("The Solr count is: " + SolrCount);
			wait(2);
			// Thread.sleep(2000);
			// hm.put("microresume:"+ReadKeyword.cellvalue.get(i),
			// solrCount.getText());
			hm.put(t, solrCount.getText());
			t = "";
		}
		return hm;
	}

}
