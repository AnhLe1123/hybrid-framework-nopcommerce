package com.nopcommerce.admin;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import envConfig.Environment;
import pageObjects.admin.CreateCustomerPageObject;
import pageObjects.admin.CustomerDetailPageObject;
import pageObjects.admin.CustomerSearchPageObject;
import pageObjects.admin.DashboardPageObject;
import pageObjects.admin.LoginPageObject;
import pageObjects.admin.PageGeneratorManager;
import utilities.DataUtil;

public class Admin_03_Search_Customer extends BaseTest {
	@Parameters({ "browser", "env" })
	@BeforeClass
	public void initBrowser(String browserName, String envName) {
		ConfigFactory.setProperty("env", envName);
		environment = ConfigFactory.create(Environment.class);
		fakeData = DataUtil.getData();

		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		cusEmail = fakeData.getEmailAddress();
		cusPassword = fakeData.getPassword();
		cusFirstname = fakeData.getFirstName();
		cusLastname = fakeData.getLastName();
		cusFullname = cusFirstname + " " + cusLastname;
		cusGender = "Male";
		cusDob = "8/20/1975";
		cusBirthDate = "20";
		cusBirthMonth = "8";
		cusCompany = fakeData.getCompanyName();
		cusRole = "Guests";
		cusIsActive = "true";
		cusComment = "Add new Customer (Guest)";

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + environment.adminUrl() + "'");
		driver = getBrowserDriver(browserName, environment.adminUrl());
		loginPage = PageGeneratorManager.getLoginPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 02: Login to Admin account with email: " + adminEmail + " and password: " + adminPassword);
		loginPage.enterToEmailTextbox(adminEmail);
		loginPage.enterToPasswordTextbox(adminPassword);
		dashboardPage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 03: Open menu 'Customers' and submenu 'Customers'");
		dashboardPage.openAdminSubmenuPageByName(driver, "Customers", "Customers");
		customerSearchPage = PageGeneratorManager.getCustomerSearchPage(driver);
		showBrowserConsoleLogs(driver);
		
		log.info("Pre-condition - Step 04: Click to Add new button");
		customerSearchPage.clickToAddNewButton(driver);
		createCustomerPage = PageGeneratorManager.getCreateCustomerPage(driver);
		showBrowserConsoleLogs(driver);
		createCustomerPage.sleepInSecond(1);
		
		log.info("Pre-condition - Step 05: Input to Email textbox with value: " + cusEmail);
		createCustomerPage.inputToAdminTextboxByID(driver, "Email", cusEmail);
		
		log.info("Pre-condition - Step 06: Input to Password textbox with value: " + cusPassword);
		createCustomerPage.inputToAdminTextboxByID(driver, "Password", cusPassword);
		
		log.info("Pre-condition - Step 07: Input to First Name textbox with value: " + cusFirstname);
		createCustomerPage.inputToAdminTextboxByID(driver, "FirstName", cusFirstname);
		
		log.info("Pre-condition - Step 08: Input to Last Name textbox with value: " + cusLastname);
		createCustomerPage.inputToAdminTextboxByID(driver, "LastName", cusLastname);
		
		log.info("Pre-condition - Step 09: Click to Gender radio button with value: " + cusGender);
		createCustomerPage.clickToAdminRadioButtonByLabel(driver, cusGender);
		
		log.info("Pre-condition - Step 10: Input to Date of Birth with value: " + cusDob);
		createCustomerPage.inputToAdminTextboxByID(driver, "DateOfBirth", cusDob);
		
		log.info("Pre-condition - Step 11: Input to Company textbox with value: " + cusCompany);
		createCustomerPage.inputToAdminTextboxByID(driver, "Company", cusCompany);
		
		log.info("Pre-condition - Step 12: Select option in Customer role dropdown with value: " + cusRole);
		createCustomerPage.selectOptionAtCustomerRolesField(driver, cusRole);
		
		log.info("Pre-condition - Step 13: Check to Active checkbox");
		createCustomerPage.checkToAdminCheckboxByID(driver, "Active");
		
		log.info("Pre-condition - Step 14: Input to Admin comment text area with value: " + cusComment);
		createCustomerPage.inputToAdminTextAreaByID(driver, "AdminComment", cusComment);
		
		log.info("Pre-condition - Step 15: Click to Save and Continue edit button");
		customerDetailPage = createCustomerPage.clickToSaveAndContinueEditButton();
		
		log.info("Pre-condition - Step 16: Verify success message displayed");
		verifyTrue(customerDetailPage.getDisplayedAminSuccessMessage(driver).contains("The new customer has been added successfully."));
		
		log.info("Pre-condition - Step 17: Verify created customer info displayed");
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "Email"), cusEmail);
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "FirstName"), cusFirstname);
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "LastName"), cusLastname);
		verifyTrue(customerDetailPage.isAdminRadioButtonSelectedByLabel(driver, cusGender));
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "DateOfBirth"), cusDob);
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "Company"), cusCompany);
		verifyTrue(customerDetailPage.isItemSelectedAtCustomerRolesDropdownByLabel("Guests"));
		verifyEquals(customerDetailPage.getValueInAdminTextAreaByID(driver, "AdminComment"), cusComment);
		
		log.info("Pre-condition - Step 18: Click back to Customer list");
		customerSearchPage = customerDetailPage.clickToBackToCustomerListLink();
		customerSearchPage.sleepInSecond(1);
	}

	@Test
	public void Search_01_Search_Customer_With_Email() {
		log.info("Search_01 - Step 01: Input to Email textbox with value: " + cusEmail);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchEmail", cusEmail);
		
		log.info("Search_01 - Step 02: Select option in Customer role dropdown with value: " + cusRole);
		customerSearchPage.selectOptionAtCustomerRolesField(driver, cusRole);
		
		log.info("Search_01 - Step 03: Click to Search button");
		customerSearchPage.clickToSearchButton();
		
		log.info("Search_01 - Step 04: Verify 1 item displayed at table");
		log.info("Search_01 - Step 05: Verify customer info displayed at table");
		verifyTrue(customerSearchPage.isCustomerInfoDisplayedAtTable(cusFullname, cusRole, cusCompany, cusIsActive));
	}

	@Test
	public void Search_02_Search_Customer_With_FirstName_And_LastName() {
		log.info("Search_02 - Step 01: Open Customer Search page");
		dashboardPage.openAdminSubmenuPageByName(driver, "Customers", "Customers");
		customerSearchPage = PageGeneratorManager.getCustomerSearchPage(driver);
		showBrowserConsoleLogs(driver);
		customerSearchPage.sleepInSecond(1);
		
		log.info("Search_02 - Step 02: Input to First Name textbox with value: " + cusFirstname);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchFirstName", cusFirstname);
		
		log.info("Search_02 - Step 03: Input to Last Name textbox with value: " + cusLastname);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchLastName", cusLastname);
		
		log.info("Search_02 - Step 04: Select option in Customer role dropdown with value: " + cusRole);
		customerSearchPage.selectOptionAtCustomerRolesField(driver, cusRole);
		
		log.info("Search_02 - Step 05: Click to Search button");
		customerSearchPage.clickToSearchButton();
		
		log.info("Search_02 - Step 06: Verify 1 item displayed at table");
		verifyTrue(customerSearchPage.isItemsNumberAtTableDisplayed(driver, "customers", "1-1 of 1 items"));
		
		log.info("Search_02 - Step 07: Verify customer info displayed at table");
		verifyTrue(customerSearchPage.isCustomerInfoDisplayedAtTable(cusFullname, cusRole, cusCompany, cusIsActive));
	}

	@Test
	public void Search_03_Search_Customer_With_Company() {
		log.info("Search_03 - Step 01: Open Customer Search page");
		dashboardPage.openAdminSubmenuPageByName(driver, "Customers", "Customers");
		customerSearchPage = PageGeneratorManager.getCustomerSearchPage(driver);
		showBrowserConsoleLogs(driver);
		customerSearchPage.sleepInSecond(1);
		
		log.info("Search_03 - Step 02: Input to Company textbox with value: " + cusCompany);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchCompany", cusCompany);
		
		log.info("Search_03 - Step 03: Select option in Customer role dropdown with value: " + cusRole);
		customerSearchPage.selectOptionAtCustomerRolesField(driver, cusRole);
		
		log.info("Search_03 - Step 04: Click to Search button");
		customerSearchPage.clickToSearchButton();
		
		log.info("Search_03 - Step 05: Verify 1 item displayed at table");
		verifyTrue(customerSearchPage.isItemsNumberAtTableDisplayed(driver, "customers", "1-1 of 1 items"));
		
		log.info("Search_03 - Step 06: Verify customer info displayed at table");
		verifyTrue(customerSearchPage.isCustomerInfoDisplayedAtTable(cusFullname, cusRole, cusCompany, cusIsActive));
	}

	@Test
	public void Search_04_Search_Customer_With_Full_Data() {
		log.info("Search_04 - Step 01: Open Customer Search page");
		dashboardPage.openAdminSubmenuPageByName(driver, "Customers", "Customers");
		customerSearchPage = PageGeneratorManager.getCustomerSearchPage(driver);
		showBrowserConsoleLogs(driver);
		customerSearchPage.sleepInSecond(1);
		
		log.info("Search_04 - Step 02: Input to Email textbox with value: " + cusEmail);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchEmail", cusEmail);
		
		log.info("Search_04 - Step 03: Input to First Name textbox with value: " + cusFirstname);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchFirstName", cusFirstname);
		
		log.info("Search_04 - Step 04: Input to Last Name textbox with value: " + cusLastname);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchLastName", cusLastname);
		
		log.info("Search_04 - Step 05: Select option in Birth month dropdown with value: " + cusBirthMonth);
		customerSearchPage.selectAdminDropdownByName(driver, "SearchMonthOfBirth", cusBirthMonth);
		
		log.info("Search_04 - Step 06: Select option in Birth date dropdown with value: " + cusBirthDate);
		customerSearchPage.selectAdminDropdownByName(driver, "SearchDayOfBirth", cusBirthDate);
		
		log.info("Search_04 - Step 07: Input to Company textbox with value: " + cusCompany);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchCompany", cusCompany);
		
		log.info("Search_04 - Step 08: Select option in Customer role dropdown with value: " + cusRole);
		customerSearchPage.selectOptionAtCustomerRolesField(driver, cusRole);
		
		log.info("Search_04 - Step 09: Click to Search button");
		customerSearchPage.clickToSearchButton();
		
		log.info("Search_04 - Step 10: Verify 1 item displayed at table");
		verifyTrue(customerSearchPage.isItemsNumberAtTableDisplayed(driver, "customers", "1-1 of 1 items"));
		
		log.info("Search_04 - Step 11: Verify customer info displayed at table");
		verifyTrue(customerSearchPage.isCustomerInfoDisplayedAtTable(cusFullname, cusRole, cusCompany, cusIsActive));
	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	DataUtil fakeData;
	Environment environment;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	CustomerSearchPageObject customerSearchPage;
	CreateCustomerPageObject createCustomerPage;
	CustomerDetailPageObject customerDetailPage;
	String adminEmail, adminPassword, cusEmail, cusPassword, cusFirstname, cusLastname, cusFullname, cusGender, cusDob, cusBirthDate, cusBirthMonth, cusCompany, cusRole, cusIsActive, cusComment;
}
