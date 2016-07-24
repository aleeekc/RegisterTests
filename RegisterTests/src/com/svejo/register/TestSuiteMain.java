package com.svejo.register;

import org.testng.TestNG;

public class TestSuiteMain {

	public static void main(String[] args) {
		
		/*
		if (args[0].toString() == "-h"){
			System.out.println("For Firefox run ....jar 1");
			System.out.println("For Chrome run ....jar 2");
			System.out.println("For Firefox & Chrome run ....jar 3");
			System.exit(0);
		}
		
		if (args[0] == "1"){
			
		}
		*/
		
		TestNG testing = new TestNG();
		Class[] classes = new Class[] { RegisterTest.class, UsernameTests.class, PasswordTests.class, ConfirmPasswordTests.class, EmailFailCase.class };
		testing.setTestClasses(classes);
		testing.run();
	}
}