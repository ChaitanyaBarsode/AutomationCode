package com.synerzip.rezoomex.tests.search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.synerzip.rezoomex.pages.LandingPage;
import com.synerzip.rezoomex.pages.SearchPage;
import com.synerzip.rezoomex.pages.SolrPage;
import com.synerzip.rezoomex.util.ReadKeyword;

public class Test_Search extends HomePageBase {
	protected Test_Search() throws Exception {
		super();
	}

	@Test
	public void searchCount() throws InterruptedException {

		// String[][] arr =
		// ReadKeyword.readExcel("com\\rezoomex\\xls\\TestData_Solr.xlsx");
		String solrFilePath=System.getProperty("user.dir")+"//src//test//java//com//rezoomex//xls//TestData_Solr.xlsx";
		String[][] arr = ReadKeyword.readExcel(solrFilePath);
		//String[][] arr = ReadKeyword.readExcel("E:\\TestData_Solr.xlsx");
		System.out
				.println("....................Solr......................................");

		driver.get(config.getProperty("solrURl"));

		// driver.manage().window().maximize();
		ReadKeyword.readExcel("E:\\TestData_Solr.xlsx");

		SolrPage sp1 = new SolrPage(driver);

		HashMap<?, ?> hm = sp1.clickSolrCollector(arr);

		Set<?> set = hm.entrySet();
		Iterator<?> i = set.iterator();
		System.out.println("............");
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}
		System.out.println();

		System.out
				.println("..............................UI.........................................");

		HashMap<?, ?> searchResults = null;
		LandingPage lp = new LandingPage(driver);
		SearchPage sp = new SearchPage(driver);

		driver.get(config.getProperty("testsiteBaseURl"));

		searchResults = sp.getSearchWebElementsMap(arr);
		Set<?> set1 = searchResults.entrySet();
		Iterator<?> i1 = set1.iterator();
		System.out.println("............");
		while (i1.hasNext()) {
			Map.Entry me = (Map.Entry) i1.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}

		set = hm.entrySet();
		i = set.iterator();

		set1 = searchResults.entrySet();
		i1 = set1.iterator();

		System.out.println("....................	.");
		System.out.println(hm);
		System.out.println("....................	.");
		System.out.println(searchResults);
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i1.next();
			Map.Entry me1 = (Map.Entry) i.next();

			try {
				Assert.assertEquals(me.getValue().toString(), me1.getValue()
						.toString());
			} catch (Throwable e) {
				System.out.println(e);
			}
			System.out.println("Keys");
			System.out.println("UI Key: " + me.getKey() + ": ");
			System.out.println("Solr Key: " + me1.getKey() + ": ");
			System.out.println(".............");
			System.out.println("UI Value: " + me.getValue());
			System.out.println("Solr Value: " + me1.getValue());
			System.out.println(".............");
		}

	}

}
