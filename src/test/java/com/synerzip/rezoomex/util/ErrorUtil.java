/*
 * Copyright 2014 Synerzip Softech. All Rights Reserved.
 */
package com.synerzip.rezoomex.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * The Class ErrorUtil.
 * 
 * @author	Chaitanya Barsode <chaitanya.barsode@synerzip.com>
 * @version	1.0
 * @since	16-09-2014
 */
public class ErrorUtil {
	
	/** The verification failures map. */
	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();

	/**
	 * Adds the verification failure.
	 * 
	 * @param e
	 *            the e
	 */
	public static void addVerificationFailure(Throwable e) {
		System.out.println("addVerificationFailure: ");
		List<Throwable> verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(),
				verificationFailures);
		verificationFailures.add(e);
	}

	/**
	 * Gets the verification failures.
	 * 
	 * @return the verification failures
	 */
	public static List<Throwable> getVerificationFailures() {
		System.out.println("getVerificationFailures: ");
		List<Throwable> verificationFailures = verificationFailuresMap
				.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList<Throwable>()
				: verificationFailures;
	}

}
