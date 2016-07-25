package com.svejo.register;

import org.testng.TestNG;

public class TestSuiteMain {

	public static void main(String[] args) {
		
		TestNG testing = new TestNG();
		Class[] classes = new Class[] { RegisterTest.class, UsernameTests.class, PasswordTests.class, ConfirmPasswordTests.class, EmailFailCase.class };
		testing.setTestClasses(classes);
		testing.run();
	}
}