package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_01_Register extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	HomePageObject homePage;
	RegisterPageObject registerPage;
	String emailAddress, password, firstName, lastName;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		emailAddress = generateEmail();
		password = "123456";
		firstName = "John";
		lastName = "Terry";

		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		registerPage = homePage.clickToRegisterLink();
	}

	@Test
	public void User_01_Register_With_Empty_Data() {
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getErrorMessageByFieldName("FirstName"), "First name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("LastName"), "Last name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Email is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "Password is required.");
	}

	@Test
	public void User_02_Register_With_Invalid_Email() {
		registerPage.refreshCurrentPage(driver);
		registerPage.inputToEmailTextbox("1234@asdf#!");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Wrong email");
	}

	@Test
	public void User_03_Register_With_Valid_Data() {
		registerPage.refreshCurrentPage(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		homePage = registerPage.clickToLogoutLink();
		verifyTrue(homePage.isHomePageSliderDisplayed());
		registerPage = homePage.clickToRegisterLink();
	}

	@Test
	public void User_04_Register_With_Existing_Email() {
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		verifyTrue(registerPage.isErrorMessageForExistingEmailDisplayed());
	}

	@Test
	public void User_05_Register_With_Password_Less_Than_6_Chars() {
		registerPage.inputToPasswordTextbox("123");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void User_06_Register_With_Confirm_Password_Not_Match_With_Password() {
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("654321");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
}
