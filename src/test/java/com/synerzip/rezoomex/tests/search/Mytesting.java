package com.synerzip.rezoomex.tests.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.synerzip.rezoomex.base.TestBase;

public class Mytesting extends TestBase {
	 
	 protected Mytesting() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	 public void test () throws InterruptedException 
	 {  
		driver.get("http://only-testing-blog.blogspot.in/2014/01/textbox.html");
		// Selecting value from drop down using visible text
		Select mydrpdwn = new Select(driver.findElement(By.id("Carlist")));
		List<WebElement> options = mydrpdwn.getOptions();

		// For each option in the list, verify if it's the one you want and then
		// click it
		for (WebElement option : options) {
			System.out.println(option.getText());
			if (option.getText().equals("Audi")) {
				option.click();
				break;
			}

			mydrpdwn.selectByIndex(3);
			// mydrpdwn.selectByValue("Volvo");
			mydrpdwn.selectByValue("BMW Car");
			mydrpdwn.selectByVisibleText("Audi");
			mydrpdwn.deselectByValue("BMW Car");
			List<WebElement> t = mydrpdwn.getAllSelectedOptions();
			System.out.println(t.size());

			for (int i = 0; i < t.size(); i++) {
				System.out.println(t.get(i).getText());
			}
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("text2")));
	 }
	 }
	}