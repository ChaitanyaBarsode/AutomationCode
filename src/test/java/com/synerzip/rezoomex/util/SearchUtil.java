package com.synerzip.rezoomex.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.synerzip.rezoomex.base.TestBase;

public class SearchUtil extends TestBase {

	public SearchUtil(WebDriver driver) throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public static int seachText(String text) throws Exception {
		// initialize();
		// openBrowser();
		getObjectxpath("inputfieldxpath").clear();
		getObjectxpath("inputfieldxpath").sendKeys(text);

		Thread.sleep(2000l);
		getObjectxpath("inputbuttonxpath").click();
		Thread.sleep(5000l);
		List<String> canlist = new ArrayList<String>();

		List<WebElement> ld = driver.findElements(By
				.xpath("//div[@class='result_1_box']"));

		if (ld.size() != 0) {
			int count = 0;
			int resultCount = 0;

			for (WebElement rn : ld) {
				System.out.println("--------------------------" + count++
						+ "-------------------------");
				System.out.println(rn.findElement(
						By.xpath(".//h3[@class='name']")).getText());
				System.out.println(rn.findElement(
						By.xpath(".//div[@class='date']")).getText());
				System.out.println(rn.findElement(
						By.xpath(".//div[@class='summary']")).getText());

				WebElement sum = rn.findElement(By
						.xpath(".//div[@class='summary']"));
				List<WebElement> highlightedList = sum.findElements(By
						.xpath(".//span[@class='highlight']"));
				List<String> storeHighLightedText = new ArrayList<String>();
				for (WebElement highlightedText : highlightedList) {
					System.out.println("%%% ---------------------------------");
					System.out.println(highlightedText.getText());
					storeHighLightedText.add(highlightedText.getText());

					System.out.println("%%% --------------------------------");

				}

				System.out
						.println("---------------------------------------------------");
				System.out.println(toUniqueArray(storeHighLightedText));

				if (toUniqueArray(storeHighLightedText).size() == 0) {

					Assert.fail("Search text is not displayed in result.");

				}
				if (toUniqueArray(storeHighLightedText).size() > resultCount
						&& count != 1) {

				//	Assert.fail("Order of profile is not in format");

				}
				resultCount = toUniqueArray(storeHighLightedText).size();
			}
		} else {

			Assert.fail("Search result is not displayed.");

		}

		return 0;

	}

	public static int invalidSeachText(String searchText) throws Exception {
		// initialize();
		// openBrowser();
		getObjectxpath("inputfieldxpath").clear();
		getObjectxpath("inputfieldxpath").sendKeys(searchText);
		Thread.sleep(5000l);
		getObjectxpath("inputbuttonxpath").click();
		Thread.sleep(5000l);
		List<String> canlist = new ArrayList<String>();
		List<WebElement> ld;
		try
		{
		 ld = driver.findElements(By
				.xpath("//div[@class='result_1_box']"));
		}
		catch(Exception e)
		{
			ld=null;
			System.out.println(e.getMessage());
		}

		if (ld.size() == 0) {
			// code for Message Verification
			System.out.println("Search Page is getting displayed");

		} else {

			Assert.fail("Search result is displayed for invalid Search Text");

		}

		return 0;

	}

	public static List toUniqueArray(List<String> tmpList) {
		List<String> listCustomer = new ArrayList<String>();
		for (String newKeyword : tmpList) {
			if (!listCustomer.contains(newKeyword)) {
				listCustomer.add(newKeyword);
			}
		}
		return listCustomer;
	}

}
