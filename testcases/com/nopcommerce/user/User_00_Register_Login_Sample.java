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

		registerPage.clickToRadioButtonByLabel(driver, "Male");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", dateDOB);
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", monthDOB);
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", yearDOB);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		registerPage.inputToTextboxByID(driver, "Company", companyName);
		registerPage.inputToTextboxByID(driver, "Password", password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
		registerPage.clickToButtonByText(driver, "Register");

		verifyTrue(registerPage.isSuccessMessageDisplayed());

		registerPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void User_02_Login_To_System() {
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToTextboxByID(driver, "Email", emailAddress);
		loginPage.inputToTextboxByID(driver, "Password", password);
		loginPage.clickToButtonByText(driver, "Log in");

		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void User_03_Switch_Page() {
		homePage.openFooterPageByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);

		searchPage.openFooterPageByName(driver, "Shipping & returns");
		shippingAndReturnPage = PageGeneratorManager.getShippingAndReturnPage(driver);

		shippingAndReturnPage.openFooterPageByName(driver, "Sitemap");
		siteMapPage = PageGeneratorManager.getSiteMapPage(driver);

		siteMapPage.openFooterPageByName(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		myAccountPage.clickToHomePageLogo(driver);
		homePage = PageGeneratorManager.getHomePage(driver);

		homePage.openHeaderPageByName(driver, "wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
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
