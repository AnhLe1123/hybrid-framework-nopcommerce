package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.SearchPageObject;
import utilities.DataUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_04_Search extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		fakeData = DataUtil.getData();
		gender = "Male";
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		dateDOB = "20";
		monthDOB = "March";
		yearDOB = "1990";
		companyName = fakeData.getCompanyName();
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();

		searchKeywordNotExist = "Macbook Pro 2050";
		searchKeywordRelative = "Lenovo";
		searchKeywordAbsolute = "Thinkpad X1 Carbon";
		advancedSearchKeyword = "Apple Macbook Pro";

		exptectedRelativeSearchResults = Arrays.asList("Lenovo IdeaCentre 600 All-in-One PC", "Lenovo Thinkpad X1 Carbon Laptop");
		exptectedAbsoluteSearchResults = Arrays.asList("Lenovo Thinkpad X1 Carbon Laptop");
		exptectedAdvancedSearchResults = Arrays.asList("Apple MacBook Pro 13-inch");

		category = "Computers";
		incorrectManufacturer = "HP";
		correctManufacturer = "Apple";

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		log.info("Pre-condition - Step 02: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 03: Click to Register link");
		homePage.openHeaderPageByName(driver, "register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("Pre-condition - Step 04: Click to gender ratio button with value: " + gender);
		registerPage.clickToRadioButtonByLabel(driver, gender);

		log.info("Pre-condition - Step 05: Enter to Firstname textbox with value: " + firstName);
		registerPage.inputToUserTextboxByID(driver, "FirstName", firstName);

		log.info("Pre-condition - Step 06: Enter to Lastname textbox with value: " + lastName);
		registerPage.inputToUserTextboxByID(driver, "LastName", lastName);

		log.info("Pre-condition - Step 07: Select option in 'DateOfBirthDay' dropdown: " + dateDOB);
		registerPage.selectUserDropdownByName(driver, "DateOfBirthDay", dateDOB);

		log.info("Pre-condition - Step 08: Select option in 'DateOfBirthMonth' dropdown: " + monthDOB);
		registerPage.selectUserDropdownByName(driver, "DateOfBirthMonth", monthDOB);

		log.info("Pre-condition - Step 09: Select option in 'DateOfBirthYear' dropdown: " + yearDOB);
		registerPage.selectUserDropdownByName(driver, "DateOfBirthYear", yearDOB);

		log.info("Pre-condition - Step 10: Enter to Email textbox with value: " + emailAddress);
		registerPage.inputToUserTextboxByID(driver, "Email", emailAddress);

		log.info("Pre-condition - Step 10: Enter to Company Name textbox with value: " + companyName);
		registerPage.inputToUserTextboxByID(driver, "Company", companyName);

		log.info("Pre-condition - Step 11: Enter to Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 12: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", password);

		log.info("Pre-condition - Step 13: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Pre-condition - Step 14: Verify success message displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Pre-condition - Step 15: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Pre-condition - Step 16: Verify HomePage displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 17: Click to Login link");
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 18: Enter to Email textbox with value: " + emailAddress);
		loginPage.inputToUserTextboxByID(driver, "Email", emailAddress);

		log.info("Pre-condition - Step 18: Enter to Password textbox with value: " + password);
		loginPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 19: Click to Login link");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Pre-condition - Step 20: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 21: Click to Search footer link");
		homePage.openFooterPageByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);

		log.info("Pre-condition - Step 22: Verify Search page displayed");
		verifyTrue(searchPage.isPageTitleByTextDisplayed(driver, "Search"));
	}

	@Test
	public void Search_01_Search_With_Empty_Data() {
		log.info("Search_02 - Step 02: Enter to Search Keyword textbox with empty value");
		searchPage.inputToUserTextboxByID(driver, "q", "");

		log.info("Search_01 - Step 01: Click to Search button");
		searchPage.clickToFormSearchButton();

		log.info("Search_01 - Step 01: Verify error message 'Search term minimum length is 3 characters' displayed");
		verifyTrue(searchPage.isErrorMessageDisplayed("Search term minimum length is 3 characters"));
	}

	@Test
	public void Search_02_Search_With_Data_Not_Exist() {
		log.info("Search_02 - Step 01: Click to Search footer link");
		searchPage.openFooterPageByName(driver, "Search");

		log.info("Search_02 - Step 02: Enter to Search Keyword textbox with value: " + searchKeywordNotExist);
		searchPage.inputToUserTextboxByID(driver, "q", searchKeywordNotExist);

		log.info("Search_02 - Step 03: Click to Search button");
		searchPage.clickToFormSearchButton();

		log.info("Search_02 - Step 04: Verify error message 'No products were found that matched your criteria.' displayed");
		searchPage.isErrorMessageDisplayed("No products were found that matched your criteria.");
	}

	@Test
	public void Search_03_Search_With_Relative_Product_Name() {
		log.info("Search_03 - Step 01: Click to Search footer link");
		searchPage.openFooterPageByName(driver, "Search");

		log.info("Search_03 - Step 02: Enter to Search Keyword textbox with value: " + searchKeywordRelative);
		searchPage.inputToUserTextboxByID(driver, "q", searchKeywordRelative);

		log.info("Search_03 - Step 03: Click to Search button");
		searchPage.clickToFormSearchButton();

		log.info("Search_03 - Step 04: Verify 2 products displayed");
		verifyEquals(searchPage.getDisplayedProductsNumber(driver), 2);

		log.info("Search_03 - Step 05: Verify search results contain " + searchKeywordRelative);
		verifyTrue(searchPage.isProductTitlesContainKeyword(driver, searchKeywordRelative));

		log.info("Search_03 - Step 06: Verify name of products displayed");
		verifyEquals(searchPage.getAllProductTitles(driver), exptectedRelativeSearchResults);
	}

	@Test
	public void Search_04_Search_With_Absolute_Product_Name() {
		log.info("Search_04 - Step 01: Click to Search footer link");
		searchPage.openFooterPageByName(driver, "Search");

		log.info("Search_04 - Step 02: Enter to Search Keyword textbox with value: " + searchKeywordAbsolute);
		searchPage.inputToUserTextboxByID(driver, "q", searchKeywordAbsolute);

		log.info("Search_04 - Step 03: Click to Search button");
		searchPage.clickToFormSearchButton();

		log.info("Search_04 - Step 04: Verify 1 product displayed");
		verifyEquals(searchPage.getDisplayedProductsNumber(driver), 1);

		log.info("Search_04 - Step 05: Verify search results contain " + searchKeywordAbsolute);
		verifyTrue(searchPage.isProductTitlesContainKeyword(driver, searchKeywordAbsolute));

		log.info("Search_04 - Step 06: Verify name of product displayed");
		verifyEquals(searchPage.getAllProductTitles(driver), exptectedAbsoluteSearchResults);
	}

	@Test
	public void Search_05_Advanced_Search_With_Parent_Categories() {
		log.info("Search_05 - Step 01: Click to Search footer link");
		searchPage.openFooterPageByName(driver, "Search");

		log.info("Search_05 - Step 02: Enter to Search Keyword textbox with value: " + advancedSearchKeyword);
		searchPage.inputToUserTextboxByID(driver, "q", advancedSearchKeyword);

		log.info("Search_05 - Step 03: Click to 'Advanced search' checkbox");
		searchPage.checkToUserCheckboxByLabel(driver, "Advanced search");

		log.info("Search_05 - Step 04: Select item in dropdown 'Category' with value: " + category);
		searchPage.selectUserDropdownByName(driver, "cid", category);

		log.info("Search_05 - Step 05: Uncheck to checkbox sub category");
		searchPage.uncheckToUserCheckboxByLabel(driver, "Automatically search sub categories");

		log.info("Search_05 - Step 06: Click to Search button");
		searchPage.clickToFormSearchButton();

		log.info("Search_05 - Step 07: Verify error message 'No products were found that matched your criteria.' displayed");
		searchPage.isErrorMessageDisplayed("No products were found that matched your criteria.");
	}

	@Test
	public void Search_06_Advanced_Search_With_Sub_Categories() {
		log.info("Search_06 - Step 01: Click to Search footer link");
		searchPage.openFooterPageByName(driver, "Search");

		log.info("Search_06 - Step 02: Enter to Search Keyword textbox with value: " + advancedSearchKeyword);
		searchPage.inputToUserTextboxByID(driver, "q", advancedSearchKeyword);

		log.info("Search_06 - Step 03: Click to 'Advanced search' checkbox");
		searchPage.checkToUserCheckboxByLabel(driver, "Advanced search");

		log.info("Search_06 - Step 04: Select item in dropdown 'Category' with value: " + category);
		searchPage.selectUserDropdownByName(driver, "cid", category);

		log.info("Search_06 - Step 05: Check to checkbox sub category");
		searchPage.checkToUserCheckboxByLabel(driver, "Automatically search sub categories");

		log.info("Search_06 - Step 06: Click to Search button");
		searchPage.clickToFormSearchButton();

		log.info("Search_06 - Step 07: Verify 1 product displayed");
		verifyEquals(searchPage.getDisplayedProductsNumber(driver), 1);

		log.info("Search_06 - Step 08: Verify search results contain " + advancedSearchKeyword);
		verifyTrue(searchPage.isProductTitlesContainKeyword(driver, advancedSearchKeyword));

		log.info("Search_06 - Step 09: Verify name of product displayed");
		verifyEquals(searchPage.getAllProductTitles(driver), exptectedAdvancedSearchResults);
	}

	@Test
	public void Search_07_Advanced_Search_With_Incorrect_Manufacturer() {
		log.info("Search_07 - Step 01: Click to Search footer link");
		searchPage.openFooterPageByName(driver, "Search");

		log.info("Search_07 - Step 02: Enter to Search Keyword textbox with value: " + advancedSearchKeyword);
		searchPage.inputToUserTextboxByID(driver, "q", advancedSearchKeyword);

		log.info("Search_07 - Step 03: Click to 'Advanced search' checkbox");
		searchPage.checkToUserCheckboxByLabel(driver, "Advanced search");

		log.info("Search_07 - Step 04: Select item in dropdown 'Category' with value: " + category);
		searchPage.selectUserDropdownByName(driver, "cid", category);

		log.info("Search_07 - Step 05: Check to checkbox sub category");
		searchPage.checkToUserCheckboxByLabel(driver, "Automatically search sub categories");

		log.info("Search_07 - Step 06: Select item in dropdown 'Manufacturer' with value: " + incorrectManufacturer);
		searchPage.selectUserDropdownByName(driver, "mid", incorrectManufacturer);

		log.info("Search_07 - Step 07: Click to Search button");
		searchPage.clickToFormSearchButton();

		log.info("Search_07 - Step 08: Verify error message 'No products were found that matched your criteria.' displayed");
		searchPage.isErrorMessageDisplayed("No products were found that matched your criteria.");
	}

	@Test
	public void Search_08_Advanced_Search_With_Correct_Manufacturer() {
		log.info("Search_08 - Step 01: Click to Search footer link");
		searchPage.openFooterPageByName(driver, "Search");

		log.info("Search_08 - Step 02: Enter to Search Keyword textbox with value: " + advancedSearchKeyword);
		searchPage.inputToUserTextboxByID(driver, "q", advancedSearchKeyword);

		log.info("Search_08 - Step 03: Click to 'Advanced search' checkbox");
		searchPage.checkToUserCheckboxByLabel(driver, "Advanced search");

		log.info("Search_08 - Step 04: Select item in dropdown 'Category' with value: " + category);
		searchPage.selectUserDropdownByName(driver, "cid", category);

		log.info("Search_08 - Step 05: Check to checkbox sub category");
		searchPage.checkToUserCheckboxByLabel(driver, "Automatically search sub categories");

		log.info("Search_08 - Step 06: Select item in dropdown 'Manufacturer' with value: " + correctManufacturer);
		searchPage.selectUserDropdownByName(driver, "mid", correctManufacturer);

		log.info("Search_08 - Step 07: Click to Search button");
		searchPage.clickToFormSearchButton();

		log.info("Search_08 - Step 08: Verify 1 product displayed");
		verifyEquals(searchPage.getDisplayedProductsNumber(driver), 1);

		log.info("Search_08 - Step 09: Verify search results contain " + advancedSearchKeyword);
		verifyTrue(searchPage.isProductTitlesContainKeyword(driver, advancedSearchKeyword));

		log.info("Search_08 - Step 10: Verify name of product displayed");
		verifyEquals(searchPage.getAllProductTitles(driver), exptectedAdvancedSearchResults);
	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver();
	}
	
	WebDriver driver;
	DataUtil fakeData;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	SearchPageObject searchPage;
	String gender, firstName, lastName, dateDOB, monthDOB, yearDOB, emailAddress, companyName, password, searchKeywordNotExist, searchKeywordRelative, searchKeywordAbsolute, advancedSearchKeyword, category, incorrectManufacturer,
			correctManufacturer;
	List<String> exptectedRelativeSearchResults, exptectedAbsoluteSearchResults, exptectedAdvancedSearchResults;
}
