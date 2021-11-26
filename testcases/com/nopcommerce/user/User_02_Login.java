package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import envConfig.Environment;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;
import utilities.DataUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_02_Login extends BaseTest {
	@Parameters({ "browser", "env" })
	@BeforeClass
	public void initBrowser(String browserName, String envName) {
		ConfigFactory.setProperty("env", envName);
		environment = ConfigFactory.create(Environment.class);
		fakeData = DataUtil.getData();
		
		registeredEmail = "abc" + generateFakeNumber() + "@gmail.com";
		notRegisteredEmail = "abc" + generateFakeNumber() + "@gmail.vn";
		invalidEmail = "1234@asdf#!";
		correctPassword = "123456";
		wrongPassword = "654327";
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + environment.userUrl() + "'");
		driver = getBrowserDriver(browserName, environment.userUrl());

		log.info("Pre-condition - Step 02: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 03: Click to Register link");
		homePage.openHeaderPageByName(driver, "register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 04: Click to Male ratio button");
		registerPage.clickToRadioButtonByLabel(driver, "Male");

		log.info("Pre-condition - Step 05: Enter to Firstname textbox");
		registerPage.inputToUserTextboxByID(driver, "FirstName", firstName);

		log.info("Pre-condition - Step 06: Enter to Lastname textbox");
		registerPage.inputToUserTextboxByID(driver, "LastName", lastName);

		log.info("Pre-condition - Step 07: Enter to Email textbox with value: " + registeredEmail);
		registerPage.inputToUserTextboxByID(driver, "Email", registeredEmail);

		log.info("Pre-condition - Step 08: Enter to Password textbox with value: " + correctPassword);
		registerPage.inputToUserTextboxByID(driver, "Password", correctPassword);

		log.info("Pre-condition - Step 09: Enter to Confirm Password textbox with value: " + correctPassword);
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", correctPassword);

		log.info("Pre-condition - Step 10: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Pre-condition - Step 11: Verify success message displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Pre-condition - Step 12: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 13: Verify HomePage displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 14: Click to Login link");
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void Login_01_Login_With_Empty_Data() {
		log.info("Login_01 - Step 01: Input empty Email textbox");
		loginPage.inputToUserTextboxByID(driver, "Email", "");

		log.info("Login_01 - Step 02: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Login_01 - Step 03: Verify displayed error message 'Please enter your email'");
		verifyEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}

	@Test
	public void Login_02_Login_With_Invalid_Email() {
		log.info("Login_02 - Step 01: Input to Email textbox with value: " + invalidEmail);
		loginPage.inputToUserTextboxByID(driver, "Email", invalidEmail);

		log.info("Login_02 - Step 02: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Login_02 - Step 03: Verify displayed error message 'Wrong email'");
		verifyEquals(loginPage.getEmailErrorMessage(), "Wrong email");
	}

	@Test
	public void Login_03_Login_With_Not_Registered_Email() {
		log.info("Login_03 - Step 01: Input to Email textbox with value: " + notRegisteredEmail);
		loginPage.inputToUserTextboxByID(driver, "Email", notRegisteredEmail);

		log.info("Login_03 - Step 02: Input to Password textbox with value: " + invalidEmail);
		loginPage.inputToUserTextboxByID(driver, "Password", correctPassword);

		log.info("Login_03 - Step 03: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Login_03 - Step 04: Verify displayed error message 'Login was unsuccessful. Please correct the errors and try again. No customer account found'");
		verifyTrue(loginPage.isUnccessfulErrorMessageByTextDisplayed("No customer account found"));
	}

	@Test
	public void Login_04_Login_With_Registered_Email_And_Empty_Password() {
		log.info("Login_04 - Step 01: Input to Email textbox with value: " + registeredEmail);
		loginPage.inputToUserTextboxByID(driver, "Email", registeredEmail);

		log.info("Login_04 - Step 02: Input empty Password textbox");
		loginPage.inputToUserTextboxByID(driver, "Password", "");

		log.info("Login_04 - Step 03: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Login_04 - Step 04: Verify displayed error message 'Login was unsuccessful. Please correct the errors and try again. No customer account found'");
		verifyTrue(loginPage.isUnccessfulErrorMessageByTextDisplayed("The credentials provided are incorrect"));
	}

	@Test
	public void Login_05_Login_With_Registered_Email_And_Wrong_Password() {
		log.info("Login_05 - Step 01: Input to Email textbox with value: " + registeredEmail);
		loginPage.inputToUserTextboxByID(driver, "Email", registeredEmail);

		log.info("Login_05 - Step 02: Input to Password textbox with value: " + wrongPassword);
		loginPage.inputToUserTextboxByID(driver, "Password", wrongPassword);

		log.info("Login_05 - Step 03: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Login_05 - Step 04: Verify displayed error message 'Login was unsuccessful. Please correct the errors and try again. No customer account found'");
		verifyTrue(loginPage.isUnccessfulErrorMessageByTextDisplayed("The credentials provided are incorrect"));
	}

	@Test
	public void Login_06_Login_With_Registered_Email_And_Correct_Password() {
		log.info("Login_06 - Step 01: Input to Email textbox with value: " + registeredEmail);
		loginPage.inputToUserTextboxByID(driver, "Email", registeredEmail);

		log.info("Login_06 - Step 02: Input to Password textbox with value: " + correctPassword);
		loginPage.inputToUserTextboxByID(driver, "Password", correctPassword);

		log.info("Login_06 - Step 03: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Login_06 - Step 04: Verify HomePage displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver();
	}
	
	WebDriver driver;
	DataUtil fakeData;
	Environment environment;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String registeredEmail, notRegisteredEmail, invalidEmail, correctPassword, wrongPassword, firstName, lastName;
}
