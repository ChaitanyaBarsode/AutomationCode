package com.synerzip.rezoomex.tests.search;

import org.testng.annotations.Test;

import com.synerzip.rezoomex.pages.LandingPage;
import com.synerzip.rezoomex.pages.SearchPage;
import com.synerzip.rezoomex.util.ReadKeyword;

public class Test_SearchHighlight extends HomePageBase {
	protected Test_SearchHighlight() throws Exception {
		super();
	}

	@Test
	public void searchCount() throws InterruptedException {

		driver.get(config.getProperty("testsiteBaseURl"));
		String solrFilePath=System.getProperty("user.dir")+"//src//test//java//com//rezoomex//xls//TestData_Solr.xlsx";
		String highlightFilePath=System.getProperty("user.dir")+"//src//test//java//com//rezoomex//xls//TestData_HighlightList.xlsx";
		String[][] arr = ReadKeyword.readExcel(solrFilePath);
		String[][] arr1 = ReadKeyword.readExcel(highlightFilePath);
		/*String[][] arr = ReadKeyword.readExcel("E:\\TestData_Solr.xlsx");
		String[][] arr1 = ReadKeyword.readExcel("E:\\TestData_HighlightList.xlsx");*/
		System.out
				.println("..............................UI.........................................");

		LandingPage lp = new LandingPage(driver);
		SearchPage sp = new SearchPage(driver);

		sp.getSearchWebElementsMap1(arr,arr1);

	}

}
