package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.SearchPageObject;
import pageObjects.ShippingAndReturnPageObject;
import pageObjects.SiteMapPageObject;
import pageObjects.WishlistPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_01_Register_Login extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, password;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		emailAddress = generateEmail();
		password = "123456";
	}

	@Test
	public void User_01_Register_To_System() {
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
		
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToMaleGenderCheckbox();
		registerPage.inputToFirstNameTextbox("John");
		registerPage.inputToLastNameTextbox("Terry");
		registerPage.selectDayOfBirthDropdown("25");
		registerPage.selectMonthOfBirthDropdown("November");
		registerPage.selectYearOfBirthDropdown("2000");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToCompanyNameTextbox("Automation Group");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
		
		homePage = registerPage.clickToLogoutLink();
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	} 
	
	@Test
	public void User_02_Login_To_System() {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}
	
	@Test
	public void User_03_Switch_Page() {
		searchPage = homePage.clickToSearchLinkFooter(driver);
		shippingAndReturnPage = searchPage.clickToShippingAndReturnLinkFooter(driver);
		siteMapPage = shippingAndReturnPage.clickToSiteMapLinkFooter(driver);
		myAccountPage = siteMapPage.clickToMyAccountLinkFooter(driver);
		homePage = myAccountPage.clickToHomePageLogo(driver);
		wishlistPage = homePage.clickToWishListLinkFooter(driver);
	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
	
	HomePageObject homePage;
	LoginPageObject loginPage;
	SearchPageObject searchPage;
	SiteMapPageObject siteMapPage;
	WishlistPageObject wishlistPage;
	RegisterPageObject registerPage;
	MyAccountPageObject myAccountPage;
	ShippingAndReturnPageObject shippingAndReturnPage;
}
