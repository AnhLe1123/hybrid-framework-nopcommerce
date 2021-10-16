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

	String emailAddress, password;
	public static Set<Cookie> loginPageCookie;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void initBrowser(String browserName, String appUrl) {
		log.info("Pre-condition - Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		emailAddress = generateEmail();
		password = "123456";

		log.info("Common_01 - Step 01: Verify HomePage is displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 02: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		log.info("Common_01 - Step 03: Click to Male ratio button");
		registerPage.clickToMaleGenderCheckbox();

		log.info("Common_01 - Step 04: Enter to Firstname textbox");
		registerPage.inputToFirstNameTextbox("John");

		log.info("Common_01 - Step 05: Enter to Lastname textbox");
		registerPage.inputToLastNameTextbox("Terry");

		log.info("Common_01 - Step 06: Enter to Email textbox with value: " + emailAddress);
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Common_01 - Step 07: Enter to Password textbox with value: " + password);
		registerPage.inputToPasswordTextbox(password);

		log.info("Common_01 - Step 08: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Common_01 - Step 09: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Common_01 - Step 10: Verify success message displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Common_01 - Step 11: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

		log.info("Common_01 - Step 12: Verify HomePage displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 13: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		log.info("Common_01 - Step 14: Enter to Email textbox with value: " + emailAddress);
		loginPage.inputToEmailTextbox(emailAddress);

		log.info("Common_01 - Step 15: Enter to Password textbox with value: " + password);
		loginPage.inputToPasswordTextbox(password);

		log.info("Common_01 - Step 16: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		log.info("Common_01 - Step 17: Verify HomePage is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 18: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		log.info(loginPageCookie);

		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver();
	}

}
