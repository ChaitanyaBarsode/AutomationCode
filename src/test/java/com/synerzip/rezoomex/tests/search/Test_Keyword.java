package com.synerzip.rezoomex.tests.search;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.synerzip.rezoomex.pages.LandingPage;
import com.synerzip.rezoomex.pages.SearchPage;
import com.synerzip.rezoomex.util.ReadKeyword;

public class Test_Keyword extends HomePageBase {
	protected Test_Keyword() throws Exception {
		super();
	}

	@Test
	public void searchCount() throws InterruptedException {

		String[][] arr = ReadKeyword.readExcel("E:\\TestData.xlsx");
		driver.get(config.getProperty("testsiteBaseURl"));
		
		LandingPage lp = new LandingPage(driver);
		SearchPage sp = new SearchPage(driver);

		/*System.out.println(arr.length);
		System.out.println(arr[0].length);*/

		for (int i = 0; i < arr.length; i++) {

			lp.searchText(ReadKeyword.cellvalue.get(i));
			List<WebElement> searchResults = sp.getSearchResultWebElements();
			System.out.println(" UI Count of " + ReadKeyword.cellvalue.get(i)
					+ ": " + searchResults.size());
			//Thread.sleep(5000);
			wait(5);
			lp.clearSearchText();
			wait(1);
		}

	}
}
