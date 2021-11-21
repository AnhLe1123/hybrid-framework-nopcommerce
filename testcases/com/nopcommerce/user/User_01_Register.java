package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;
import utilities.DataUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_01_Register extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		fakeData = DataUtil.getData();

		validEmailAddress = fakeData.getEmailAddress();
		invalidEmailAddress = "1234@asdf#!";
		password = fakeData.getPassword();
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();

		log.info("Pre-condition - Step 02: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 03: Click to Register link");
		homePage.openHeaderPageByName(driver, "register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void Register_01_Register_With_Empty_Data() {
		log.info("Register_01 - Step 01: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("User_01_Register_With_Empty_Data - Step 02: Verify error message displayed at all required fields");
		verifyEquals(registerPage.getErrorMessageByFieldName("FirstName"), "First name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("LastName"), "Last name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Email is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "Password is required.");

	}

	@Test
	public void Register_02_Register_With_Invalid_Email() {
		log.info("Register_02 - Step 01: Refresh page");
		registerPage.refreshCurrentPage(driver);

		log.info("Register_02 - Step 02: Enter to Email textbox with value: " + invalidEmailAddress);
		registerPage.inputToUserTextboxByID(driver, "Email", invalidEmailAddress);

		log.info("Register_02 - Step 03: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register_02 - Step 04: Verify error message 'Wrong email' displayed at field Email");
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Wrong email");

	}

	@Test
	public void Register_03_Register_With_Valid_Data() {
		log.info("Register_03 - Step 01: Refresh page");
		registerPage.refreshCurrentPage(driver);

		log.info("Register_03 - Step 02: Enter to Firstname textbox with value: " + firstName);
		registerPage.inputToUserTextboxByID(driver, "FirstName", firstName);

		log.info("Register_03 - Step 03: Enter to Lastname textbox with value: " + lastName);
		registerPage.inputToUserTextboxByID(driver, "LastName", lastName);

		log.info("Register_03 - Step 04: Enter to Email textbox with value: " + validEmailAddress);
		registerPage.inputToUserTextboxByID(driver, "Email", validEmailAddress);

		log.info("Register_03 - Step 05: Enter to Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Register_03 - Step 06: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register_03 - Step 07: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register_03 - Step 08: Verify success message");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Register_03 - Step 09: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Register_03 - Step 10: Verify HomePage slider displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Register_03 - Step 11: Click to Register link");
		homePage.openHeaderPageByName(driver, "register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		showBrowserConsoleLogs(driver);

	}

	@Test
	public void Register_04_Register_With_Existing_Email() {
		log.info("Register_04 - Step 01: Enter to Firstname textbox with value: " + firstName);
		registerPage.inputToUserTextboxByID(driver, "FirstName", firstName);

		log.info("Register_04 - Step 02: Enter to Lastname textbox with value: " + lastName);
		registerPage.inputToUserTextboxByID(driver, "LastName", lastName);

		log.info("Register_04 - Step 03: Enter to Email textbox with value: " + validEmailAddress);
		registerPage.inputToUserTextboxByID(driver, "Email", validEmailAddress);

		log.info("Register_04 - Step 04: Enter to Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Register_04 - Step 05: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register_04 - Step 06: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register_04 - Step 07: Verify error message for existing email displayed");
		verifyTrue(registerPage.isErrorMessageForExistingEmailDisplayed());

	}

	@Test
	public void Register_05_Register_With_Password_Less_Than_6_Chars() {
		log.info("Register_05 - Step 01: Click to Register link");
		registerPage.openHeaderPageByName(driver, "register");

		log.info("Register_05 - Step 02: Enter to Password textbox with value '123'");
		registerPage.inputToUserTextboxByID(driver, "Password", "123");

		log.info("Register_05 - Step 03: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register_05 - Step 04: Verify displayed error message: 'Password must meet the following rules: must have at least 6 characters'");
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Register_With_Confirm_Password_Not_Match_With_Password() {
		log.info("Register_06 - Step 01: Enter to Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register_06 - Step 02: Enter to Confirm Password textbox with value: 654321");
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", "654321");

		log.info("Register_06 - Step 03: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register_06 - Step 04: Verify displayed error message: 'The password and confirmation password do not match.'");
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "The password and confirmation password do not match.");

	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver();
	}
	
	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	DataUtil fakeData;
	String validEmailAddress, invalidEmailAddress, password, firstName, lastName;
}
