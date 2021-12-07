package com.nopcommerce.common;

import commons.BaseTest;
import factoryEnvironment.EnvConfig;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;
import utilities.DataUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class Common_01_User_Login extends BaseTest {
	@Parameters({ "envName", "serverName", "browser", "ipAddress", "port", "osName", "osVersion" })
	@BeforeClass
	public void initBrowser(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber,
			@Optional("Mac OS X") String osName, @Optional("10.16") String osVersion) {
		
		ConfigFactory.setProperty("env", serverName);
		environment = ConfigFactory.create(EnvConfig.class);
		
		log.info("Pre-condition - Open browser '" + browserName + "' and navigate to '" + environment.userUrl() + "'");
		driver = getBrowserDriver(envName, environment.userUrl(), browserName, ipAddress, portNumber, osName, osVersion);
		fakeData = DataUtil.getData();
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();

		log.info("Common_01 - Step 01: Verify HomePage is displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 02: Click to Register link");
		homePage.openHeaderPageByName(driver, "register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("Common_01 - Step 03: Click to Male ratio button");
		registerPage.clickToRadioButtonByLabel(driver, "Male");

		log.info("Common_01 - Step 04: Enter to Firstname textbox");
		registerPage.inputToUserTextboxByID(driver, "FirstName", firstName);

		log.info("Common_01 - Step 05: Enter to Lastname textbox");
		registerPage.inputToUserTextboxByID(driver, "LastName", lastName);

		log.info("Common_01 - Step 06: Enter to Email textbox with value: " + emailAddress);
		registerPage.inputToUserTextboxByID(driver, "Email", emailAddress);

		log.info("Common_01 - Step 07: Enter to Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Common_01 - Step 08: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", password);

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
		loginPage.inputToUserTextboxByID(driver, "Email", emailAddress);

		log.info("Common_01 - Step 15: Enter to Password textbox with value: " + password);
		loginPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Common_01 - Step 16: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Common_01 - Step 17: Verify HomePage is displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Common_01 - Step 18: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		log.info(loginPageCookie);

		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver(envName);
	}

	WebDriver driver;
	EnvConfig environment;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	DataUtil fakeData;
	String emailAddress, password, firstName, lastName;
	public static Set<Cookie> loginPageCookie;
}
