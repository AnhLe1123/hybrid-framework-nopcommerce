package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.admin.CreateCustomerPageObject;
import pageObjects.admin.CustomerAddressPageObject;
import pageObjects.admin.CustomerDetailPageObject;
import pageObjects.admin.CustomerSearchPageObject;
import pageObjects.admin.DashboardPageObject;
import pageObjects.admin.LoginPageObject;
import pageObjects.admin.PageGeneratorManager;
import utilities.DataUtil;

public class Admin_02_Create_And_Edit_Customer extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
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

		editCusEmail = "edit_" + cusEmail;
		editCusFirstname = "Edit_" + cusFirstname;
		editCusLastname = "Edit_" + cusLastname;
		editCusFullname = editCusFirstname + " " + editCusLastname;
		editCusDob = "6/24/1970";
		editCusBirthDate = "24";
		editCusBirthMonth = "6";
		editCusCompany = "Edit " + cusCompany;
		editCusComment = "Edit " + cusComment;

		cusCountry = "United States";
		cusState = "California";
		cusCity = fakeData.getCityName();
		cusFirstAddress = fakeData.getFirstAddress();
		cusZipcode = fakeData.getZipCode();
		cusPhone = fakeData.getPhoneNumber();
		cusFax = fakeData.getFaxNumber();
		cusCityStateZip = cusCity + "," + cusState + "," + cusZipcode;

		editCusCountry = "Canada";
		editCusState = "Quebec";
		editCusCity = fakeData.getCityName();
		editCusFirstAddress = fakeData.getFirstAddress();
		editCusZipcode = fakeData.getZipCode();
		editCusPhone = fakeData.getPhoneNumber();
		editCusFax = fakeData.getFaxNumber();
		editCusCityStateZip = editCusCity + "," + editCusState + "," + editCusZipcode;

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 02: Login to Admin account with email: " + adminEmail + " and password: " + adminPassword);
		loginPage.enterToEmailTextbox(adminEmail);
		loginPage.enterToPasswordTextbox(adminPassword);
		dashboardPage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 03: Open menu 'Customers' and submenu 'Customers'");
		dashboardPage.openAdminSubmenuPageByName(driver, "Customers", "Customers");
		customerSearchPage = PageGeneratorManager.getCustomerSearchPage(driver);
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void Customer_01_Create_New_Customer() {
		log.info("Customer_01 - Step 01: Click to Add new button");
		customerSearchPage.clickToAddNewButton(driver);
		createCustomerPage = PageGeneratorManager.getCreateCustomerPage(driver);
		showBrowserConsoleLogs(driver);
		createCustomerPage.sleepInSecond(1);

		log.info("Customer_01 - Step 02: Input to Email textbox with value: " + cusEmail);
		createCustomerPage.inputToAdminTextboxByID(driver, "Email", cusEmail);

		log.info("Customer_01 - Step 03: Input to Password textbox with value: " + cusPassword);
		createCustomerPage.inputToAdminTextboxByID(driver, "Password", cusPassword);

		log.info("Customer_01 - Step 04: Input to First Name textbox with value: " + cusFirstname);
		createCustomerPage.inputToAdminTextboxByID(driver, "FirstName", cusFirstname);

		log.info("Customer_01 - Step 05: Input to Last Name textbox with value: " + cusLastname);
		createCustomerPage.inputToAdminTextboxByID(driver, "LastName", cusLastname);

		log.info("Customer_01 - Step 06: Click to Gender radio button with value: " + cusGender);
		createCustomerPage.clickToAdminRadioButtonByLabel(driver, cusGender);

		log.info("Customer_01 - Step 07: Input to Date of Birth with value: " + cusDob);
		createCustomerPage.inputToAdminTextboxByID(driver, "DateOfBirth", cusDob);

		log.info("Customer_01 - Step 08: Input to Company textbox with value: " + cusCompany);
		createCustomerPage.inputToAdminTextboxByID(driver, "Company", cusCompany);

		log.info("Customer_01 - Step 09: Select option in Customer role dropdown with value: " + cusRole);
		createCustomerPage.selectOptionAtCustomerRolesField(driver, cusRole);

		log.info("Customer_01 - Step 10: Check to Active checkbox");
		createCustomerPage.checkToAdminCheckboxByID(driver, "Active");

		log.info("Customer_01 - Step 11: Input to Admin comment text area with value: " + cusComment);
		createCustomerPage.inputToAdminTextAreaByID(driver, "AdminComment", cusComment);

		log.info("Customer_01 - Step 12: Click to Save and Continue edit button");
		customerDetailPage = createCustomerPage.clickToSaveAndContinueEditButton();

		log.info("Customer_01 - Step 13: Verify success message displayed");
		verifyTrue(customerDetailPage.getDisplayedAminSuccessMessage(driver).contains("The new customer has been added successfully."));

		log.info("Customer_01 - Step 14: Verify created customer info displayed");
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "Email"), cusEmail);
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "FirstName"), cusFirstname);
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "LastName"), cusLastname);
		verifyTrue(customerDetailPage.isAdminRadioButtonSelectedByLabel(driver, cusGender));
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "DateOfBirth"), cusDob);
		verifyEquals(customerDetailPage.getValueInAdminTextboxByID(driver, "Company"), cusCompany);
		verifyTrue(customerDetailPage.isItemSelectedAtCustomerRolesDropdownByLabel("Guests"));
		verifyEquals(customerDetailPage.getValueInAdminTextAreaByID(driver, "AdminComment"), cusComment);

		log.info("Customer_01 - Step 15: Click back to Customer list");
		customerSearchPage = customerDetailPage.clickToBackToCustomerListLink();

		log.info("Customer_01 - Step 16: Select option in Customer role dropdown with value: " + cusRole);
		createCustomerPage.selectOptionAtCustomerRolesField(driver, cusRole);

		log.info("Customer_01 - Step 16: Click to Search button");
		customerSearchPage.clickToSearchButton();

		log.info("Customer_01 - Step 16: Verify Customer info at table");
		verifyTrue(customerSearchPage.isCustomerInfoDisplayedAtTable(cusFullname, cusRole, cusCompany, cusIsActive));
	}

	@Test
	public void Customer_02_Edit_Customer() {
		log.info("Customer_02 - Step 01: Input to Email textbox at Customer search page with value: " + cusEmail);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchEmail", cusEmail);

		log.info("Customer_02 - Step 02: Input to Firstname textbox at Customer search page with value: " + cusFirstname);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchFirstName", cusFirstname);

		log.info("Customer_02 - Step 03: Input to Lastname textbox at Customer search page with value: " + cusLastname);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchLastName", cusLastname);

		log.info("Customer_02 - Step 04: Select option in birth month dropdown at Customer search page with value: " + cusBirthMonth);
		customerSearchPage.selectAdminDropdownByName(driver, "SearchMonthOfBirth", cusBirthMonth);

		log.info("Customer_02 - Step 05: Select option in day month dropdown at Customer search page with value: " + cusBirthDate);
		customerSearchPage.selectAdminDropdownByName(driver, "SearchDayOfBirth", cusBirthDate);

		log.info("Customer_02 - Step 06: Input to Company textbox at Customer search page with value: " + cusCompany);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchCompany", cusCompany);

		log.info("Customer_02 - Step 07: Select option in Customer role dropdown at Customer search page with value: " + cusRole);
		customerSearchPage.selectOptionAtCustomerRolesField(driver, cusRole);

		log.info("Customer_02 - Step 08: Click to Search button");
		customerSearchPage.clickToSearchButton();

		log.info("Customer_02 - Step 09: Click to Edit button by Name at table");
		customerDetailPage = customerSearchPage.clickToEditButtonByCustomerName(cusFullname);

		log.info("Customer_02 - Step 10: Expand Customer info field");
		customerDetailPage.clickToExpandPanelByName(driver, "Customer info");
		customerDetailPage.sleepInSecond(1);

		log.info("Customer_02 - Step 11: Input to Email textbox with value: " + editCusEmail);
		customerDetailPage.inputToAdminTextboxByID(driver, "Email", editCusEmail);

		log.info("Customer_02 - Step 12: Input to Firstname textbox with value: " + editCusFirstname);
		customerDetailPage.inputToAdminTextboxByID(driver, "FirstName", editCusFirstname);

		log.info("Customer_02 - Step 13: Input to Lastname textbox with value: " + editCusLastname);
		customerDetailPage.inputToAdminTextboxByID(driver, "LastName", editCusLastname);

		log.info("Customer_02 - Step 14: Input to Date of Birth with value: " + editCusDob);
		customerDetailPage.inputToAdminTextboxByID(driver, "DateOfBirth", editCusDob);

		log.info("Customer_02 - Step 15: Input to Company textbox with value: " + editCusCompany);
		customerDetailPage.inputToAdminTextboxByID(driver, "Company", editCusCompany);

		log.info("Customer_02 - Step 16: Input to Admin comment text area with value: " + editCusComment);
		customerDetailPage.inputToAdminTextAreaByID(driver, "AdminComment", editCusComment);

		log.info("Customer_02 - Step 17: Click to Save button");
		customerSearchPage = customerDetailPage.clickToSaveButton();

		log.info("Customer_02 - Step 18: Verify success message displayed");
		verifyTrue(customerSearchPage.getDisplayedAminSuccessMessage(driver).contains("The customer has been updated successfully."));
		customerSearchPage.sleepInSecond(1);

		log.info("Customer_02 - Step 19: Input edit customer info in Search field");
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchEmail", editCusEmail);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchFirstName", editCusFirstname);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchLastName", editCusLastname);
		customerSearchPage.selectAdminDropdownByName(driver, "SearchMonthOfBirth", editCusBirthMonth);
		customerSearchPage.selectAdminDropdownByName(driver, "SearchDayOfBirth", editCusBirthDate);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchCompany", editCusCompany);
		customerSearchPage.selectOptionAtCustomerRolesField(driver, cusRole);

		log.info("Customer_02 - Step 20: Click to Search button");
		customerSearchPage.clickToSearchButton();

		log.info("Customer_02 - Step 21: Verify edit customer info at table");
		verifyTrue(customerSearchPage.isCustomerInfoDisplayedAtTable(editCusFullname, cusRole, editCusCompany, cusIsActive));
	}

	@Test
	public void Customer_03_Add_New_Address_In_Customer_Detail() {
		log.info("Customer_03 - Step 01: Click to Edit button by Name at table");
		customerDetailPage = customerSearchPage.clickToEditButtonByCustomerName(editCusFullname);

		log.info("Customer_03 - Step 02: Expand Addresses field");
		customerDetailPage.clickToExpandPanelByName(driver, "Addresses");

		log.info("Customer_03 - Step 03: Click Add new address button");
		customerAddressPage = customerDetailPage.clickToAddNewAddressButton();
		customerAddressPage.sleepInSecond(2);

		log.info("Customer_03 - Step 04: Input to Firstname textbox with value: " + editCusFirstname);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_FirstName", editCusFirstname);

		log.info("Customer_03 - Step 05: Input to Lastname textbox with value: " + editCusLastname);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_LastName", editCusLastname);

		log.info("Customer_03 - Step 06: Input to Email textbox with value: " + editCusEmail);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_Email", editCusEmail);

		log.info("Customer_03 - Step 07: Input to Company textbox with value: " + editCusCompany);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_Company", editCusCompany);

		log.info("Customer_03 - Step 08: Select option in Country dropdown with value: " + cusCountry);
		customerAddressPage.selectAdminDropdownByName(driver, "Address.CountryId", cusCountry);

		log.info("Customer_03 - Step 09: Select option in State dropdown with value: " + cusState);
		customerAddressPage.selectAdminDropdownByName(driver, "Address.StateProvinceId", cusState);

		log.info("Customer_03 - Step 10: Input to City textbox with value: " + cusCity);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_City", cusCity);

		log.info("Customer_03 - Step 11: Input to Address 1 textbox with value: " + cusFirstAddress);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_Address1", cusFirstAddress);

		log.info("Customer_03 - Step 12: Input to Zipcode textbox with value: " + cusZipcode);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_ZipPostalCode", cusZipcode);

		log.info("Customer_03 - Step 13: Input to Phone number textbox with value: " + cusPhone);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_PhoneNumber", cusPhone);

		log.info("Customer_03 - Step 14: Input to Fax number textbox with value: " + cusFax);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_FaxNumber", cusFax);

		log.info("Customer_03 - Step 15: Click to Save button");
		customerAddressPage.clickToSaveButton();

		log.info("Customer_03 - Step 16: Verify success message displayed");
		verifyTrue(customerAddressPage.getDisplayedAminSuccessMessage(driver).contains("The new address has been added successfully."));
		customerAddressPage.sleepInSecond(1);

		log.info("Customer_03 - Step 17: Click to Back to customer details link");
		customerDetailPage = customerAddressPage.clickToBackToCustomerDetailsLink();

		log.info("Customer_03 - Step 18: Expand Addresses field");
		customerDetailPage.clickToExpandPanelByName(driver, "Addresses");

		log.info("Customer_03 - Step 19: Verify address info displayed");
		verifyTrue(customerDetailPage.isAddressInfoDisplayed(editCusFirstname, editCusLastname, editCusEmail, cusPhone, cusFax, editCusCompany, cusFirstAddress, cusCityStateZip, cusCountry));
	}

	@Test
	public void Customer_04_Edit_Address_In_Customer_Detail() {
		log.info("Customer_04 - Step 01: Click to Edit button at Address info");
		customerAddressPage = customerDetailPage.clickToEditAddressButtonByCustomerEmail(editCusEmail);
		customerAddressPage.sleepInSecond(2);

		log.info("Customer_04 - Step 02: Input to Firstname textbox with value: " + cusFirstname);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_FirstName", cusFirstname);
		
		log.info("Customer_04 - Step 03: Input to Lastname textbox with value: " + cusLastname);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_LastName", cusLastname);
		
		log.info("Customer_04 - Step 04: Input to Email textbox with value: " + cusEmail);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_Email", cusEmail);
		
		log.info("Customer_04 - Step 05: Input to Company textbox with value: " + cusCompany);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_Company", cusCompany);
		
		log.info("Customer_04 - Step 06: Select option in Country dropdown with value: " + editCusCountry);
		customerAddressPage.selectAdminDropdownByName(driver, "Address.CountryId", editCusCountry);
		
		log.info("Customer_04 - Step 07: Select option in State dropdown with value: " + editCusState);
		customerAddressPage.selectAdminDropdownByName(driver, "Address.StateProvinceId", editCusState);
		
		log.info("Customer_04 - Step 08: Input to City textbox with value: " + editCusCity);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_City", editCusCity);
		
		log.info("Customer_04 - Step 09: Input to Address 1 textbox with value: " + editCusFirstAddress);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_Address1", editCusFirstAddress);
		
		log.info("Customer_04 - Step 10: Input to Zipcode textbox with value: " + editCusZipcode);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_ZipPostalCode", editCusZipcode);
		
		log.info("Customer_04 - Step 11: Input to Phone number textbox with value: " + editCusPhone);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_PhoneNumber", editCusPhone);
		
		log.info("Customer_04 - Step 12: Input to Fax number textbox with value: " + editCusFax);
		customerAddressPage.inputToAdminTextboxByID(driver, "Address_FaxNumber", editCusFax);
		
		log.info("Customer_04 - Step 13: Click to Save button");
		customerAddressPage.clickToSaveButton();

		log.info("Customer_04 - Step 14: Verify success message displayed");
		verifyTrue(customerAddressPage.getDisplayedAminSuccessMessage(driver).contains("The address has been updated successfully."));
		customerAddressPage.sleepInSecond(1);

		log.info("Customer_04 - Step 15: Click to Back to customer details link");
		customerDetailPage = customerAddressPage.clickToBackToCustomerDetailsLink();

		log.info("Customer_04 - Step 16: Expand Addresses field");
		customerDetailPage.clickToExpandPanelByName(driver, "Addresses");

		log.info("Customer_04 - Step 17: Verify address info displayed");
		verifyTrue(customerDetailPage.isAddressInfoDisplayed(cusFirstname, cusLastname, cusEmail, editCusPhone, editCusFax, cusCompany, editCusFirstAddress, editCusCityStateZip, editCusCountry));
	}

	@Test
	public void Customer_05_Delete_Address_In_Customer_Detail() {
		log.info("Customer_05 - Step 01: Open menu 'Customers' and submenu 'Customers'");
		customerDetailPage.openAdminSubmenuPageByName(driver, "Customers", "Customers");
		customerSearchPage = PageGeneratorManager.getCustomerSearchPage(driver);
		showBrowserConsoleLogs(driver);
		
		log.info("Customer_05 - Step 02: Input to Search fields");
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchEmail", editCusEmail);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchFirstName", editCusFirstname);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchLastName", editCusLastname);
		customerSearchPage.selectAdminDropdownByName(driver, "SearchMonthOfBirth", editCusBirthMonth);
		customerSearchPage.selectAdminDropdownByName(driver, "SearchDayOfBirth", editCusBirthDate);
		customerSearchPage.inputToAdminTextboxByID(driver, "SearchCompany", editCusCompany);
		customerSearchPage.selectOptionAtCustomerRolesField(driver, cusRole);
		
		log.info("Customer_05 - Step 03: Click to Search button");
		customerSearchPage.clickToSearchButton();
		
		log.info("Customer_05 - Step 04: Click to Edit button by Name at table");
		customerDetailPage = customerSearchPage.clickToEditButtonByCustomerName(editCusFullname);
		
		log.info("Customer_05 - Step 05: Expand Addresses field");
		customerDetailPage.clickToExpandPanelByName(driver, "Addresses");
		
		log.info("Customer_05 - Step 06: Click to Delete button at Address info");
		customerDetailPage.clickToDeleteAddressButtonByCustomerEmail(cusEmail);
		customerDetailPage.sleepInSecond(1);
		
		log.info("Customer_05 - Step 07: Verify No data message displayed at table");
		verifyTrue(customerDetailPage.isMessageDisplayedInEmptyTable(driver, "customer-addresses"));
	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	DataUtil fakeData;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	CustomerSearchPageObject customerSearchPage;
	CreateCustomerPageObject createCustomerPage;
	CustomerDetailPageObject customerDetailPage;
	CustomerAddressPageObject customerAddressPage;
	String adminEmail, adminPassword, cusEmail, cusPassword, cusFirstname, cusLastname, cusFullname, cusGender, cusDob, cusBirthDate, cusBirthMonth, cusCompany, cusRole, cusIsActive, cusComment, editCusEmail, editCusFirstname,
			editCusLastname, editCusFullname, editCusDob, editCusBirthDate, editCusBirthMonth, editCusCompany, editCusComment, cusCountry, cusState, cusCity, cusFirstAddress, cusZipcode, cusPhone, cusFax, cusCityStateZip, editCusCountry,
			editCusState, editCusCity, editCusFirstAddress, editCusZipcode, editCusPhone, editCusFax, editCusCityStateZip;
}
