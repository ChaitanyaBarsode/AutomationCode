package com.synerzip.rezoomex.tests.search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.synerzip.rezoomex.base.TestBase;
import com.synerzip.rezoomex.pages.SolrPage;
import com.synerzip.rezoomex.util.ReadKeyword;

public class Test_SolrKeyWord extends TestBase {

	protected Test_SolrKeyWord() throws Exception {
		super();
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void test() throws InterruptedException {

		driver.get(config.getProperty("solrURl"));
	//	driver.manage().window().maximize();
		String arr[][]=ReadKeyword.readExcel("E:\\TestData_Solr.xlsx");

		SolrPage sp = new SolrPage(driver);

		HashMap<?, ?> hm = sp.clickSolrCollector(arr);

		Set<?> set = hm.entrySet();
		Iterator<?> i = set.iterator();
		System.out.println("............");
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}
		System.out.println();

	}

}