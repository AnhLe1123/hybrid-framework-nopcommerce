package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.MyAddressPageObject;
import pageObjects.user.MyPasswordPageObject;
import pageObjects.user.MyProductReviewPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.ProductDetailPageObject;
import pageObjects.user.ProductReviewPageObject;
import pageObjects.user.RegisterPageObject;
import utilities.DataUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_03_My_Account extends BaseTest {
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

		editGender = "Female";
		editFirstName = fakeData.getEditFirstName();
		editLastName = fakeData.getEditLastName();
		editFullName = editFirstName + " " + editLastName;
		editDateDOB = "1";
		editMonthDOB = "January";
		editYearDOB = "1999";
		editCompanyName = fakeData.getEditCompanyName();
		editEmailAddress = fakeData.getEditEmailAddress();
		editPassword = fakeData.getEditPassword();

		country = "United States";
		state = "Connecticut";
		city = fakeData.getCityName();
		firstAddress = fakeData.getFirstAddress();
		secondAddress = fakeData.getSecondAddress();
		zipCode = fakeData.getZipCode();
		phoneNumber = fakeData.getPhoneNumber();
		faxNumber = fakeData.getFaxNumber();

		productTitle = "Build your own computer";
		productReviewTitle = "Computer review";
		productReviewContent = "Good quality and customer service.";

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		log.info("Pre-condition - Step 02: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 03: Click to Register link");
		homePage.openHeaderPageByName(driver, "register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		showBrowserConsoleLogs(driver);

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
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 16: Verify HomePage displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 17: Click to Login link");
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 18: Enter to Email textbox with value: " + emailAddress);
		loginPage.inputToUserTextboxByID(driver, "Email", emailAddress);

		log.info("Pre-condition - Step 18: Enter to Password textbox with value: " + password);
		loginPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 19: Click to Login link");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Pre-condition - Step 20: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 21: Click to My account link");
		homePage.openHeaderPageByName(driver, "account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 22: Verify My Account page displayed");
		verifyTrue(myAccountPage.isPageTitleByTextDisplayed(driver, "My account - Customer info"));
	}

	@Test
	public void My_Account_01_Change_Customer_Info() {
		log.info("My_Account_01 - Step 01: Click to gender ratio button with value: " + editGender);
		myAccountPage.clickToRadioButtonByLabel(driver, editGender);

		log.info("My_Account_01 - Step 02: Enter to Firstname textbox in My account page with value: " + editFirstName);
		myAccountPage.inputToUserTextboxByID(driver, "FirstName", editFirstName);

		log.info("My_Account_01 - Step 03: Enter to Lastname textbox in My account page with value: " + editLastName);
		myAccountPage.inputToUserTextboxByID(driver, "LastName", editLastName);

		log.info("My_Account_01 - Step 04: Select option in 'DateOfBirthDay' dropdown in My account page with value: " + editDateDOB);
		myAccountPage.selectUserDropdownByName(driver, "DateOfBirthDay", editDateDOB);

		log.info("My_Account_01 - Step 05: Select option in 'DateOfBirthMonth' dropdown in My account page with value: " + editMonthDOB);
		myAccountPage.selectUserDropdownByName(driver, "DateOfBirthMonth", editMonthDOB);

		log.info("My_Account_01 - Step 06: Select option in 'DateOfBirthYear' dropdown in My account page with value: " + editYearDOB);
		myAccountPage.selectUserDropdownByName(driver, "DateOfBirthYear", editYearDOB);

		log.info("My_Account_01 - Step 07: Enter to Email textbox in My account page with value : " + editEmailAddress);
		myAccountPage.inputToUserTextboxByID(driver, "Email", editEmailAddress);

		log.info("My_Account_01 - Step 08: Enter to Company Name textbox with value in My account page with value : " + editCompanyName);
		myAccountPage.inputToUserTextboxByID(driver, "Company", editCompanyName);

		log.info("My_Account_01 - Step 09: Click to Save button");
		myAccountPage.clickToButtonByText(driver, "Save");

		log.info("My_Account_01 - Step 10: Verify new First Name with value: " + editFirstName);
		verifyEquals(myAccountPage.getValueInUserTextboxByID(driver, "FirstName"), editFirstName);

		log.info("My_Account_01 - Step 11: Verify new Last Name with value: " + editLastName);
		verifyEquals(myAccountPage.getValueInUserTextboxByID(driver, "LastName"), editLastName);

		log.info("My_Account_01 - Step 12: Verify new selected option in 'DateOfBirthDay' dropdown with value : " + editDateDOB);
		verifyEquals(myAccountPage.getSelectedItemInDropdownByName(driver, "DateOfBirthDay"), editDateDOB);

		log.info("My_Account_01 - Step 13: Verify new selected option in 'DateOfBirthMonth' dropdown with value : " + editMonthDOB);
		verifyEquals(myAccountPage.getSelectedItemInDropdownByName(driver, "DateOfBirthMonth"), editMonthDOB);

		log.info("My_Account_01 - Step 14: Verify new selected option in 'DateOfBirthYear' dropdown with value : " + editYearDOB);
		verifyEquals(myAccountPage.getSelectedItemInDropdownByName(driver, "DateOfBirthYear"), editYearDOB);

		log.info("My_Account_01 - Step 15: Verify new Email Address with value: " + editEmailAddress);
		verifyEquals(myAccountPage.getValueInUserTextboxByID(driver, "Email"), editEmailAddress);

		log.info("My_Account_01 - Step 16: Verify new Company Name with value: " + editCompanyName);
		verifyEquals(myAccountPage.getValueInUserTextboxByID(driver, "Company"), editCompanyName);
	}

	@Test
	public void My_Account_02_Change_Customer_Address() {
		log.info("My_Account_02 - Step 01: Click to Addresses link at sidebar");
		myAccountPage.openSidebarPageByName(driver, "Addresses");
		myAddressPage = PageGeneratorManager.getMyAddressPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("My_Account_02 - Step 02: Verify My Address page displayed");
		verifyTrue(myAddressPage.isPageTitleByTextDisplayed(driver, "My account - Addresses"));

		log.info("My_Account_02 - Step 03: Click to Add New button");
		myAddressPage.clickToButtonByText(driver, "Add new");

		log.info("My_Account_02 - Step 04: Enter to First Name textbox in My address page with value: " + editFirstName);
		myAddressPage.inputToUserTextboxByID(driver, "Address_FirstName", editFirstName);

		log.info("My_Account_02 - Step 05: Enter to Last Name textbox in My address page with value: " + editLastName);
		myAddressPage.inputToUserTextboxByID(driver, "Address_LastName", editLastName);

		log.info("My_Account_02 - Step 06: Enter to Email textbox in My address page with value: " + editEmailAddress);
		myAddressPage.inputToUserTextboxByID(driver, "Address_Email", editEmailAddress);

		log.info("My_Account_02 - Step 07: Enter to Company textbox in My address page with value: " + companyName);
		myAddressPage.inputToUserTextboxByID(driver, "Address_Company", companyName);

		log.info("My_Account_02 - Step 08: Select option in Country dropdown in My address page with value: " + country);
		myAddressPage.selectUserDropdownByName(driver, "Address.CountryId", country);

		log.info("My_Account_02 - Step 09: Select option in State dropdown in My address page with value: " + state);
		myAddressPage.selectUserDropdownByName(driver, "Address.StateProvinceId", state);

		log.info("My_Account_02 - Step 10: Enter to City textbox in My address page with value: " + city);
		myAddressPage.inputToUserTextboxByID(driver, "Address_City", city);

		log.info("My_Account_02 - Step 11: Enter to Address 1 textbox in My address page with value: " + firstAddress);
		myAddressPage.inputToUserTextboxByID(driver, "Address_Address1", firstAddress);

		log.info("My_Account_02 - Step 12: Enter to Address 2 textbox in My address page with value: " + secondAddress);
		myAddressPage.inputToUserTextboxByID(driver, "Address_Address2", secondAddress);

		log.info("My_Account_02 - Step 13: Enter to Postal Code textbox in My address page with value: " + zipCode);
		myAddressPage.inputToUserTextboxByID(driver, "Address_ZipPostalCode", zipCode);

		log.info("My_Account_02 - Step 14: Enter to Phone Number textbox in My address page with value: " + phoneNumber);
		myAddressPage.inputToUserTextboxByID(driver, "Address_PhoneNumber", phoneNumber);

		log.info("My_Account_02 - Step 15: Enter to Fax Number textbox in My address page with value: " + faxNumber);
		myAddressPage.inputToUserTextboxByID(driver, "Address_FaxNumber", faxNumber);

		log.info("My_Account_02 - Step 16: Click to 'Save' button");
		myAddressPage.clickToButtonByText(driver, "Save");

		log.info("My_Account_02 - Step 17: Verify info added successfully");
		myAddressPage.isInfoDisplayedByFieldName(driver, "name", editFullName);
		myAddressPage.isInfoDisplayedByFieldName(driver, "email", editEmailAddress);
		myAddressPage.isInfoDisplayedByFieldName(driver, "phone", phoneNumber);
		myAddressPage.isInfoDisplayedByFieldName(driver, "fax", faxNumber);
		myAddressPage.isInfoDisplayedByFieldName(driver, "company", companyName);
		myAddressPage.isInfoDisplayedByFieldName(driver, "address1", firstAddress);
		myAddressPage.isInfoDisplayedByFieldName(driver, "address2", secondAddress);
		myAddressPage.isInfoDisplayedByFieldName(driver, "city-state-zip", city + ", " + state + ", " + zipCode);
		myAddressPage.isInfoDisplayedByFieldName(driver, "country", country);
	}

	@Test
	public void My_Account_03_Change_Password() {
		log.info("My_Account_03 - Step 01: Click to Change Password link at sidebar");
		myAddressPage.openSidebarPageByName(driver, "Change password");
		myPasswordPage = PageGeneratorManager.getMyPasswordPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("My_Account_03 - Step 02: Verify Change Password page displayed");
		verifyTrue(myPasswordPage.isPageTitleByTextDisplayed(driver, "My account - Change password"));

		log.info("My_Account_03 - Step 03: Enter to Old Password textbox with value: " + password);
		myPasswordPage.inputToUserTextboxByID(driver, "OldPassword", password);

		log.info("My_Account_03 - Step 04: Enter to New Password textbox with value: " + editPassword);
		myPasswordPage.inputToUserTextboxByID(driver, "NewPassword", editPassword);

		log.info("My_Account_03 - Step 05: Enter to Confirm Password textbox with value: " + editPassword);
		myPasswordPage.inputToUserTextboxByID(driver, "ConfirmNewPassword", editPassword);

		log.info("My_Account_03 - Step 06: Click to Change Password button");
		myPasswordPage.clickToButtonByText(driver, "Change password");

		log.info("My_Account_03 - Step 07: Verify Success message 'Password was changed' displayed");
		verifyTrue(myPasswordPage.isSuccessMessageDisplayed());

		log.info("My_Account_03 - Step 08: Click to Close button at notification bar");
		myPasswordPage.clickToCloseButtonAtNotificationBar();
		myAccountPage.sleepInSecond(2);

		log.info("My_Account_03 - Step 09: Click to Logout header link");
		myPasswordPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("My_Account_03 - Step 10: Verify HomePage Slider displayed");
		homePage.isHomePageSliderDisplayed();

		log.info("My_Account_03 - Step 11: Click to Login header link");
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("My_Account_03 - Step 12: Enter to Email textbox with value: " + editEmailAddress);
		loginPage.inputToUserTextboxByID(driver, "Email", editEmailAddress);

		log.info("My_Account_03 - Step 13: Enter to Password textbox with old password: " + password);
		loginPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("My_Account_03 - Step 14: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("My_Account_03 - Step 15: Verify error message 'The credentials provided are incorrect' displayed");
		verifyTrue(loginPage.isUnccessfulErrorMessageByTextDisplayed("The credentials provided are incorrect"));

		log.info("My_Account_03 - Step 16: Click to Login header link");
		loginPage.openHeaderPageByName(driver, "login");

		log.info("My_Account_03 - Step 17: Enter to Email textbox with value: " + editEmailAddress);
		loginPage.inputToUserTextboxByID(driver, "Email", editEmailAddress);

		log.info("My_Account_03 - Step 18: Enter to Password textbox with new password: " + editPassword);
		loginPage.inputToUserTextboxByID(driver, "Password", editPassword);

		log.info("My_Account_03 - Step 19: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("My_Account_03 - Step 20: Verify HomePage Slider displayed");
		homePage.isHomePageSliderDisplayed();
	}

	@Test
	public void My_Account_04_My_Product_Reviews() {
		log.info("My_Account_04 - Step 01: Click to Product title " + "'" + productTitle + " '");
		homePage.clickToProductTitleByName(driver, productTitle);
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);

		log.info("My_Account_04 - Step 02: Click to 'Add your review' link");
		productDetailPage.clickToAddReviewLink();
		productReviewPage = PageGeneratorManager.getProductReviewPage(driver);

		log.info("My_Account_04 - Step 03: Enter to Review Title textbox with content: " + productReviewTitle);
		productReviewPage.inputToUserTextboxByID(driver, "AddProductReview_Title", productReviewTitle);

		log.info("My_Account_04 - Step 04: Enter to Review Text textarea with content: " + productReviewContent);
		productReviewPage.inputToReviewTextArea(productReviewContent);
		log.info("My_Account_04 - Step 05: Click to Rate option with value: ");
		productReviewPage.clickToRatingRatioByValue("4");

		log.info("My_Account_04 - Step 06: Click to Submit Review button");
		productReviewPage.clickToButtonByText(driver, "Submit review");

		log.info("My_Account_04 - Step 07: Verify Success message 'Product review is successfully added.' displayed");
		verifyTrue(productReviewPage.isAddReviewSuccessMessageDisplayed());

		log.info("My_Account_04 - Step 08: Verify Product Name, Review title and Review text displayed in Product review page");
		verifyTrue(productReviewPage.isReviewProductNameDisplayed(driver, productTitle));
		verifyTrue(productReviewPage.isReviewByTitleContentDisplayed(driver, "review-title", productReviewTitle));
		verifyTrue(productReviewPage.isReviewByTitleContentDisplayed(driver, "text-body", productReviewContent));

		log.info("My_Account_04 - Step 09: Click to 'My Account' header link");
		productReviewPage.openHeaderPageByName(driver, "account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		log.info("My_Account_04 - Step 10: Click to 'My product reviews' link at sidebar");
		myAccountPage.openSidebarPageByName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);

		log.info("My_Account_04 - Step 11: Verify My Product Review page displayed");
		verifyTrue(myProductReviewPage.isPageTitleByTextDisplayed(driver, "My account - My product reviews"));

		log.info("My_Account_04 - Step 12: Verify Review title and Review text displayed in My Product review page");
		verifyTrue(productReviewPage.isReviewByTitleContentDisplayed(driver, "review-title", productReviewTitle));
		verifyTrue(productReviewPage.isReviewByTitleContentDisplayed(driver, "review-text", productReviewContent));
		verifyTrue(productReviewPage.isReviewProductNameDisplayed(driver, productTitle));
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
	MyAccountPageObject myAccountPage;
	MyAddressPageObject myAddressPage;
	MyPasswordPageObject myPasswordPage;
	ProductDetailPageObject productDetailPage;
	ProductReviewPageObject productReviewPage;
	MyProductReviewPageObject myProductReviewPage;
	String gender, firstName, lastName, dateDOB, monthDOB, yearDOB, emailAddress, companyName, password, editGender, editFirstName, editLastName, editFullName, editDateDOB, editMonthDOB, editYearDOB, editCompanyName, editEmailAddress,
			editPassword, country, state, city, firstAddress, secondAddress, zipCode, phoneNumber, faxNumber, productTitle, productReviewTitle, productReviewContent;

}
