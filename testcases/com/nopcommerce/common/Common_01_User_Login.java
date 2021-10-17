package com.nopcommerce.common;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class Common_01_User_Login extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

	String emailAddress, password, firstName, lastName;
	public static Set<Cookie> loginPageCookie;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void initBrowser(String browserName, String appUrl) {
		log.info("Pre-condition - Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		emailAddress = "abc" + generateFakeNumber() + "@gmail.com";
		password = "123456";
		firstName = "John";
		lastName = "Terry";

		log.info("Common_01 - Step 01: Verify HomePage is displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 02: Click to Register link");
		homePage.openHeaderPageByName(driver, "register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("Common_01 - Step 03: Click to Male ratio button");
		registerPage.clickToRadioButtonByLabel(driver, "Male");

		log.info("Common_01 - Step 04: Enter to Firstname textbox");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("Common_01 - Step 05: Enter to Lastname textbox");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);

		log.info("Common_01 - Step 06: Enter to Email textbox with value: " + emailAddress);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("Common_01 - Step 07: Enter to Password textbox with value: " + password);
		registerPage.inputToTextboxByID(driver, "Password", password);

		log.info("Common_01 - Step 08: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Common_01 - Step 09: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Common_01 - Step 10: Verify success message displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Common_01 - Step 11: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Common_01 - Step 12: Verify HomePage displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 13: Click to Login link");
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Common_01 - Step 14: Enter to Email textbox with value: " + emailAddress);
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("Common_01 - Step 15: Enter to Password textbox with value: " + password);
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Common_01 - Step 16: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Common_01 - Step 17: Verify HomePage is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 18: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		log.info(loginPageCookie);

		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver();
	}

}
