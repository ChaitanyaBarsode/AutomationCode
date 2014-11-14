/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.synerzip.rezoomex.base.WaitBase;

/**
 * The Class LoginPage.
 * 
 * @author Chaitanya Barsode <chaitanya.barsode@synerzip.com>
 * @version 1.0
 * @since 10-09-2014
 */
public class SearchPage extends WaitBase{
	/**
	 * The Web Driver.
	 */
	private WebDriver driver = null;
	/**
	 * The Search Result Web Element
	 */
	@FindAll({ @FindBy(xpath = "//div[@class='result_1_box']") })
	private List<WebElement> searchResultWebElements;
	/**
	 * The Search Result Web Elements
	 */
	@FindAll({ @FindBy(xpath = ".//h3[@class='name']") })
	private List<WebElement> candidateNamesList;
	/**
	 * The Candidate Names List Web Elements
	 */
	@FindAll({ @FindBy(xpath = ".//div[@class='date']") })
	private List<WebElement> dateList;
	/**
	 * The Date List Web Elements
	 */
	@FindAll({ @FindBy(xpath = ".//div[@class='summary']") })
	private List<WebElement> summaryList;
	/**
	 * The Highlighted List Web Elements
	 */
	@FindAll({ @FindBy(xpath = ".//span[@class='highlight']") })
	private List<WebElement> highlightList;

	/**
	 * Instantiates a new login page.
	 * 
	 * @param driver
	 *            the driver
	 */
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Get the searchResultWebElements
	 */
	public List<WebElement> getSearchResultWebElements() {
		
		
		return searchResultWebElements;
	}
	
	/**
	 * Get the Candidate Names List
	 */
	public List<WebElement> getCandidateNamesList() {
		return candidateNamesList;
	}

	/**
	 * Get the Date List
	 */
	public List<WebElement> getDateList() {
		return dateList;
	}

	/**
	 * Get the Summary List
	 */
	public List<WebElement> getSummaryList() {
		return summaryList;
	}

	/**
	 * Get the List of Highlighted Elements
	 */
	public List<WebElement> getHighlightList() {
		return highlightList;
	}


	/**
	 * Get the Hash Map searchResultWebElements
	 * @throws InterruptedException 
	 */
	public HashMap getSearchWebElementsMap(String[][] arr) throws InterruptedException {
		LinkedHashMap hm = new LinkedHashMap();
		
		LandingPage lp = new LandingPage(driver);
		SearchPage sp = new SearchPage(driver);
		
		for (int i = 0; i < arr.length; i++) {

			//lp.searchText(ReadKeyword.cellvalue.get(i));
			lp.searchText(arr[i][0]);
			wait(5);
			//Thread.sleep(5000);
			System.out.println(" UI Count of " + arr[i][0]
					+ ": " + sp.getCandidateNamesList().size());
			//Thread.sleep(5000);
			
			lp.clearSearchText();
			wait(1);
			//Thread.sleep(1000);
			hm.put(arr[i][0], sp.getCandidateNamesList().size());
		}
		
		System.out.println(hm);
		
		return hm;
	}
	
	/**
	 * Get the Hash Map searchResultWebElements
	 * @throws InterruptedException 
	 */
	public HashMap getSearchWebElementsMap1(String[][] arr,String [][]arr1) throws InterruptedException {
		
		int actualCount=0;
		int expectedCount=0;
		
		LinkedHashMap hm = new LinkedHashMap();
		
		LandingPage lp = new LandingPage(driver);
		SearchPage sp = new SearchPage(driver);
		
		for (int i = 0; i < arr1.length; i++) {

			//lp.searchText(ReadKeyword.cellvalue.get(i));
			lp.searchText(arr1[i][0]);
			wait(5);
			//Thread.sleep(5000);
			System.out.println(" Highligheted Count of " + arr1[i][0]
					+ ": " + sp.highlightList.size());
			//Thread.sleep(5000);
			String[] arr2 = arr1[i][1].split(",");
			
			int count=highlightList.size();
			
			expectedCount=count;
			String highlightText;
			
			for(int j=0;j<count;j++){
			
				for(int z=0;z<arr2.length;z++)
				{
					highlightText=highlightList.get(j).getText();
					//if(highlightText.equalsIgnoreCase(arr2[z]))
					if(highlightText.equals(arr2[z]))
					//if(highlightText.contains(arr2[z]))
					{
						/*System.out.println(arr2[z]+": Keyword Present in the highlighted List!!!");
						System.out.println(highlightText);*/
						actualCount++;
						//break;
						
					}
				}
				
			}
			lp.clearSearchText();
			wait(1);
			//Thread.sleep(1000);
			hm.put(arr[i][0], sp.highlightList.size());
			
			System.out.println(actualCount);
			System.out.println(expectedCount);
			try {
				Assert.assertEquals(actualCount, expectedCount);
			} catch (Throwable e) {
				System.out.println(e);
			}
			actualCount=0;
			expectedCount=0;
			
		}
		
		
		return hm;
	}

	
}
