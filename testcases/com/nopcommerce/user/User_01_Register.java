package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;
import reportConfig.ExtentTestManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
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
	public void User_01_Register_With_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register_With_Empty_Data");

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register_With_Empty_Data - Step 01: Click to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register_With_Empty_Data - Step 02: Verify error message displayed at all required fields");
		verifyEquals(registerPage.getErrorMessageByFieldName("FirstName"), "First name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("LastName"), "Last name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Email is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "Password is required.");

		ExtentTestManager.endTest();
	}

	@Test
	public void User_02_Register_With_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_02_Register_With_Invalid_Email");
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Register_With_Invalid_Email - Step 01: Refresh page");
		registerPage.refreshCurrentPage(driver);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Register_With_Invalid_Email - Step 02: Enter to Email textbox with value: 1234@asdf#!");
		registerPage.inputToEmailTextbox("1234@asdf#!");

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Register_With_Invalid_Email - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Register_With_Invalid_Email - Step 04: Verify error message 'Wrong email' displayed at field Email");
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Wrong email");

		ExtentTestManager.endTest();
	}

	@Test
	public void User_03_Register_With_Valid_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_03_Register_With_Valid_Data");
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 01: Refresh page");
		registerPage.refreshCurrentPage(driver);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 02: Enter to Firstname textbox with value: " + firstName);
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 03: Enter to Lastname textbox with value: " + lastName);
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 04: Enter to Email textbox with value: " + emailAddress);
		registerPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 05: Enter to Password textbox with value: " + password);
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 06: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToConfirmPasswordTextbox(password);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 07: Enter to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 08: Verify success message");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 10: Verify HomePage slider displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_03_Register_With_Valid_Data - Step 11: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.endTest();
	}

	@Test
	public void User_04_Register_With_Existing_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_04_Register_With_Existing_Email");
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_04_Register_With_Existing_Email - Step 01: Enter to Firstname textbox with value: " + firstName);
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_04_Register_With_Existing_Email - Step 02: Enter to Lastname textbox with value: " + lastName);
		registerPage.inputToLastNameTextbox(lastName);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_04_Register_With_Existing_Email - Step 03: Enter to Email textbox with value: " + emailAddress);
		registerPage.inputToEmailTextbox(emailAddress);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_04_Register_With_Existing_Email - Step 04: Enter to Password textbox with value: " + password);
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_04_Register_With_Existing_Email - Step 05: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToConfirmPasswordTextbox(password);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_04_Register_With_Existing_Email - Step 06: Click to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_04_Register_With_Existing_Email - Step 07: Verify error message for existing email displayed");
		verifyTrue(registerPage.isErrorMessageForExistingEmailDisplayed());

		ExtentTestManager.endTest();
	}

	@Test
	public void User_05_Register_With_Password_Less_Than_6_Chars(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_05_Register_With_Password_Less_Than_6_Chars");
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_05_Register_With_Password_Less_Than_6_Chars - Step 01: Enter to Password textbox with value: 123");
		registerPage.inputToPasswordTextbox("123");

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_05_Register_With_Password_Less_Than_6_Chars - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_05_Register_With_Password_Less_Than_6_Chars - Step 03: Verify displayed error message: 'Password must meet the following rules: must have at least 6 characters'");
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password must meet the following rules:\nmust have at least 6 characters");

		ExtentTestManager.endTest();
	}

	@Test
	public void User_06_Register_With_Confirm_Password_Not_Match_With_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_06_Register_With_Confirm_Password_Not_Match_With_Password");
		ExtentTestManager.getTest().log(LogStatus.INFO, "User_06_Register_With_Confirm_Password_Not_Match_With_Password - Step 01: Enter to Password textbox with value: " + password);
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_06_Register_With_Confirm_Password_Not_Match_With_Password - Step 02: Enter to Confirm Password textbox with value: 654321");
		registerPage.inputToConfirmPasswordTextbox("654321");

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_06_Register_With_Confirm_Password_Not_Match_With_Password - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(LogStatus.INFO, "User_06_Register_With_Confirm_Password_Not_Match_With_Password - Step 04: Verify displayed error message: 'The password and confirmation password do not match.'");
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "The password and confirmation password do not match.");

		ExtentTestManager.endTest();
	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
}
