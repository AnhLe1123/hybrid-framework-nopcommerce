package com.nopcommerce.user;

import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_01_Register_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, password;
	
	@BeforeClass
	public void initBrowser() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		emailAddress = generateEmail();
		password = "123456";
		
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void User_01_Register_To_System() {
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
		
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		
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
		
		registerPage.clickToLogoutLink();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	} 
	
	@Test
	public void User_02_Login_To_System() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isHomePageSliderDisplayed());
	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.com";
	}
	
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
}
