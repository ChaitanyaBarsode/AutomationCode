package com.synerzip.rezoomex.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitBase {

	/**
	 * Wrapper method for Thread.sleep()
	 * 
	 * @param timeInSeconds
	 */
	public static void wait(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wrapper method for WebDriverWait (ExpectedCondition =
	 * elementToBeClickable)
	 * 
	 * @param element
	 * @param timeInSeconds
	 */
	public static void waitForElementClickable(WebDriver driver,WebElement element,
			int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wrapper method for WebDriverWait (ExpectedCondition = visibilityOf
	 * element)
	 * 
	 * @param element
	 * @param timeInSeconds
	 */
	public static void waitForElementVisible(WebDriver driver,WebElement element,
			int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
