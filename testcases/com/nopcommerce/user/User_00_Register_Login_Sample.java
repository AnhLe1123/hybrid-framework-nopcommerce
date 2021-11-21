package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.SearchPageObject;
import pageObjects.user.ShippingAndReturnPageObject;
import pageObjects.user.SiteMapPageObject;
import pageObjects.user.WishlistPageObject;
import utilities.DataUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_00_Register_Login_Sample extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		fakeData = DataUtil.getData();
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		companyName = fakeData.getCompanyName();
		dateDOB = "20";
		monthDOB = "March";
		yearDOB = "1990";
	}

	@Test
	public void User_01_Register_To_System() {
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		homePage.openHeaderPageByName(driver, "register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		showBrowserConsoleLogs(driver);

		registerPage.clickToRadioButtonByLabel(driver, "Male");
		registerPage.inputToUserTextboxByID(driver, "FirstName", firstName);
		registerPage.inputToUserTextboxByID(driver, "LastName", lastName);
		registerPage.selectUserDropdownByName(driver, "DateOfBirthDay", dateDOB);
		registerPage.selectUserDropdownByName(driver, "DateOfBirthMonth", monthDOB);
		registerPage.selectUserDropdownByName(driver, "DateOfBirthYear", yearDOB);
		registerPage.inputToUserTextboxByID(driver, "Email", emailAddress);
		registerPage.inputToUserTextboxByID(driver, "Company", companyName);
		registerPage.inputToUserTextboxByID(driver, "Password", password);
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", password);
		registerPage.clickToButtonByText(driver, "Register");

		verifyTrue(registerPage.isSuccessMessageDisplayed());

		registerPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void User_02_Login_To_System() {
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		showBrowserConsoleLogs(driver);

		loginPage.inputToUserTextboxByID(driver, "Email", emailAddress);
		loginPage.inputToUserTextboxByID(driver, "Password", password);
		loginPage.clickToButtonByText(driver, "Log in");

		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void User_03_Switch_Page() {
		homePage.openFooterPageByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		showBrowserConsoleLogs(driver);

		searchPage.openFooterPageByName(driver, "Shipping & returns");
		shippingAndReturnPage = PageGeneratorManager.getShippingAndReturnPage(driver);
		showBrowserConsoleLogs(driver);

		shippingAndReturnPage.openFooterPageByName(driver, "Sitemap");
		siteMapPage = PageGeneratorManager.getSiteMapPage(driver);
		showBrowserConsoleLogs(driver);

		siteMapPage.openFooterPageByName(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		showBrowserConsoleLogs(driver);

		myAccountPage.clickToHomePageLogo(driver);
		homePage = PageGeneratorManager.getHomePage(driver);
		showBrowserConsoleLogs(driver);

		homePage.openHeaderPageByName(driver, "wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		showBrowserConsoleLogs(driver);
	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		closeBrowserAndDriver();
	}
	
	WebDriver driver;
	DataUtil fakeData;
	HomePageObject homePage;
	LoginPageObject loginPage;
	SearchPageObject searchPage;
	SiteMapPageObject siteMapPage;
	WishlistPageObject wishlistPage;
	RegisterPageObject registerPage;
	MyAccountPageObject myAccountPage;
	ShippingAndReturnPageObject shippingAndReturnPage;
	String emailAddress, password, firstName, lastName, companyName, dateDOB, monthDOB, yearDOB;
}
