package com.svejo.register;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;

public class PasswordTests {
	private WebDriver driver;
	@SuppressWarnings("unused")
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	String uuid;

	@BeforeMethod
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver",
		//		"chromedriver.exe");
		//driver = new ChromeDriver();
		baseUrl = "https://svejo.net/register";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		uuid = UUID.randomUUID().toString();
		uuid.replaceAll("-", "");
		uuid = uuid.substring(0, 6);
	}

	@Test
	public void passwordWithLessThan8Chars() throws Exception {
		driver.get("https://svejo.net/register");
		AssertJUnit.assertEquals(driver.getTitle(), "svejo.net | users - new");
		driver.findElement(By.id("user_email")).clear();
		driver.findElement(By.id("user_email")).sendKeys(
				"asdasd@asd" + uuid + ".bg");
		driver.findElement(By.id("user_username")).sendKeys("asdasd123a");
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys("asdasda");
		driver.findElement(By.id("user_password_confirmation")).clear();
		driver.findElement(By.id("user_password_confirmation")).sendKeys(
				"asdasda");
		driver.findElement(By.cssSelector("#new_user > input[name=\"commit\"]"))
				.click();
		AssertJUnit
				.assertTrue(driver
						.findElement(By.cssSelector("BODY"))
						.getText()
						.contains(
								"е прекалено късо (не може да бъде по-малко от 6 символа)"));

		// NOTE: THE ACTUAL PASSWORD LENGTH LIMIT IS 6, NOT 8

	}

	@Test
	public void passwordWithMoreThan72Chars() throws Exception {
		driver.get("https://svejo.net/register");
		AssertJUnit.assertEquals(driver.getTitle(), "svejo.net | users - new");
		driver.findElement(By.id("user_email")).clear();
		driver.findElement(By.id("user_email")).sendKeys(
				"asdasd@asd" + uuid + ".bg");
		driver.findElement(By.id("user_username")).sendKeys("asdasdasd");
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password"))
				.sendKeys(
						"asaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaa123");
		driver.findElement(By.id("user_password_confirmation")).clear();
		driver.findElement(By.id("user_password_confirmation"))
				.sendKeys(
						"asaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaaasaaa123");
		driver.findElement(By.cssSelector("#new_user > input[name=\"commit\"]"))
				.click();
		AssertJUnit.assertTrue(driver.findElement(By.cssSelector("BODY"))
				.getText().contains("съдържа невярна стойност"));

		// NOTE: THIS ASSET/TEST ALWAYS BREAKS, BECAUSE THERE ISN'T A LIMIT FOR
		// PASSWORD LENGTH
		// IT SHOULD BE 72

	}

	@AfterMethod
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
