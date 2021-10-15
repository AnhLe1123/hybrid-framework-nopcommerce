package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_01_Register extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	String validEmailAddress, invalidEmailAddress, password, firstName, lastName;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		validEmailAddress = generateEmail();
		invalidEmailAddress = "1234@asdf#!";
		password = "123456";
		firstName = "John";
		lastName = "Terry";

		log.info("Pre-condition - Step 02: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 03: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
	}

	@Test
	public void User_01_Register_With_Empty_Data() {
		log.info("User_01_Register_With_Empty_Data - Step 01: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("User_01_Register_With_Empty_Data - Step 02: Verify error message displayed at all required fields");
		verifyEquals(registerPage.getErrorMessageByFieldName("FirstName"), "First name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("LastName"), "Last name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Email is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "Password is required.");

	}

	@Test
	public void User_02_Register_With_Invalid_Email() {
		log.info("User_02_Register_With_Invalid_Email - Step 01: Refresh page");
		registerPage.refreshCurrentPage(driver);

		log.info("User_02_Register_With_Invalid_Email - Step 02: Enter to Email textbox with value: " + invalidEmailAddress);
		registerPage.inputToEmailTextbox(invalidEmailAddress);

		log.info("User_02_Register_With_Invalid_Email - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("User_02_Register_With_Invalid_Email - Step 04: Verify error message 'Wrong email' displayed at field Email");
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Wrong email");

	}

	@Test
	public void User_03_Register_With_Valid_Data() {
		log.info("User_02_Register_With_Invalid_Email - Step 01: Refresh page");
		registerPage.refreshCurrentPage(driver);

		log.info("User_02_Register_With_Invalid_Email - Step 02: Enter to Firstname textbox with value: " + firstName);
		registerPage.inputToFirstNameTextbox(firstName);

		log.info("User_02_Register_With_Invalid_Email - Step 03: Enter to Lastname textbox with value: " + lastName);
		registerPage.inputToLastNameTextbox(lastName);

		log.info("User_02_Register_With_Invalid_Email - Step 04: Enter to Email textbox with value: " + validEmailAddress);
		registerPage.inputToEmailTextbox(validEmailAddress);

		log.info("User_02_Register_With_Invalid_Email - Step 05: Enter to Password textbox with value: " + password);
		registerPage.inputToPasswordTextbox(password);

		log.info("User_02_Register_With_Invalid_Email - Step 06: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("User_02_Register_With_Invalid_Email - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("User_02_Register_With_Invalid_Email - Step 08: Verify success message");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("User_02_Register_With_Invalid_Email - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

		log.info("User_02_Register_With_Invalid_Email - Step 10: Verify HomePage slider displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("User_02_Register_With_Invalid_Email - Step 11: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

	}

	@Test
	public void User_04_Register_With_Existing_Email() {
		log.info("User_04_Register_With_Existing_Email - Step 01: Enter to Firstname textbox with value: " + firstName);
		registerPage.inputToFirstNameTextbox(firstName);

		log.info("User_04_Register_With_Existing_Email - Step 02: Enter to Lastname textbox with value: " + firstName);
		registerPage.inputToLastNameTextbox(lastName);

		log.info("User_04_Register_With_Existing_Email - Step 03: Enter to Email textbox with value: " + firstName);
		registerPage.inputToEmailTextbox(validEmailAddress);

		log.info("User_04_Register_With_Existing_Email - Step 04: Enter to Password textbox with value: " + password);
		registerPage.inputToPasswordTextbox(password);

		log.info("User_04_Register_With_Existing_Email - Step 05: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("User_04_Register_With_Existing_Email - Step 06: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("User_04_Register_With_Existing_Email - Step 07: Verify error message for existing email displayed");
		verifyTrue(registerPage.isErrorMessageForExistingEmailDisplayed());

	}

	@Test
	public void User_05_Register_With_Password_Less_Than_6_Chars() {
		log.info("User_05_Register_With_Password_Less_Than_6_Chars - Step 01: Enter to Password textbox with value '123'");
		registerPage.inputToPasswordTextbox("123");

		log.info("User_05_Register_With_Password_Less_Than_6_Chars - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("User_05_Register_With_Password_Less_Than_6_Chars - Step 03: Verify displayed error message: 'Password must meet the following rules: must have at least 6 characters'");
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void User_06_Register_With_Confirm_Password_Not_Match_With_Password() {
		log.info("User_06_Register_With_Confirm_Password_Not_Match_With_Password - Step 01: Enter to Password textbox with value: " + password);
		registerPage.inputToPasswordTextbox(password);

		log.info("User_06_Register_With_Confirm_Password_Not_Match_With_Password - Step 02: Enter to Confirm Password textbox with value: 654321");
		registerPage.inputToConfirmPasswordTextbox("654321");

		log.info("User_06_Register_With_Confirm_Password_Not_Match_With_Password - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("User_06_Register_With_Confirm_Password_Not_Match_With_Password - Step 04: Verify displayed error message: 'The password and confirmation password do not match.'");
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "The password and confirmation password do not match.");

	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver();
	}
}
