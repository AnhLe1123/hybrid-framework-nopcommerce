package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.admin.DashboardPageObject;
import pageObjects.admin.LoginPageObject;
import pageObjects.admin.PageGeneratorManager;
import pageObjects.admin.ProductDetailPageObject;
import pageObjects.admin.ProductSearchPageObject;

public class Admin_01_Search_Product extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		sku = "LE_IC_600";
		price = "500";
		stockQuantity = "10000";
		isPublished = "true";
		allCategory = "All";
		parentCategory = "Computers";
		childCategory = "Computers >> Desktops";
		manufacturer = "Apple";

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 02: Login to Admin account with email: " + adminEmail + " and password: " + adminPassword);
		loginPage.enterToEmailTextbox(adminEmail);
		loginPage.enterToPasswordTextbox(adminPassword);
		dashboardPage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 03: Open menu 'Catalog' and submenu 'Products'");
		dashboardPage.openAdminSubmenuPageByName(driver, "Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);
		
		log.info("Pre-condition - Step 04: Expand Search field");
		productSearchPage.clickToExpandSearchPanel(driver);
	}

	@Test
	public void Search_01_Search_With_Product_Name() {
		log.info("Search_01 - Step 01: Input to Product name textbox with value: " + productName);
		productSearchPage.inputToProductNameTextbox(productName);

		log.info("Search_01 - Step 02: Click to Search button");
		productSearchPage.clickToSearchButton();

		log.info("Search_01 - Step 03: Verify 1 item displayed in table");
		verifyTrue(productSearchPage.isProductsNumberAtTableDisplayed("1-1 of 1 items"));
		
		log.info("Search_01 - Step 03: Verify product info displayed in table");
		verifyTrue(productSearchPage.isPictureImageUpdated(productName, productName));
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Product name", "1"), productName);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "SKU", "1"), sku);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Price", "1"), price);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Stock quantity", "1"), stockQuantity);
		verifyTrue(productSearchPage.isPublishedIconDisplayedByValue(isPublished));
	}

	@Test
	public void Search_02_Search_With_Product_Name_Parent_Category_Unchecked_Subcategory() {
		log.info("Search_02 - Step 01: Input to Product name textbox with value: " + productName);
		productSearchPage.inputToProductNameTextbox(productName);
		
		log.info("Search_02 - Step 02: Select option in Category dropdown with value: " + parentCategory);
		productSearchPage.selectAdminDropdownByName(driver, "SearchCategoryId", parentCategory);
		
		log.info("Search_02 - Step 03: Uncheck to checkbox Search subcategories");
		productSearchPage.uncheckToAdminCheckboxByID(driver, "SearchIncludeSubCategories");
		
		log.info("Search_02 - Step 04: Click to Search button");
		productSearchPage.clickToSearchButton();
		
		log.info("Search_02 - Step 05: Verify No data message displayed");
		productSearchPage.isMessageDisplayedInEmptyTable(driver, "products");
	}

	@Test
	public void Search_03_Search_With_Product_Name_Parent_Category_Checked_Subcategory() {
		log.info("Search_03 - Step 01: Input to Product name textbox with value: " + productName);
		productSearchPage.inputToProductNameTextbox(productName);
		
		log.info("Search_03 - Step 02: Select option in Category dropdown with value: " + parentCategory);
		productSearchPage.selectAdminDropdownByName(driver, "SearchCategoryId", parentCategory);
		
		log.info("Search_03 - Step 03: Check to checkbox Search subcategories");
		productSearchPage.checkToAdminCheckboxByID(driver, "SearchIncludeSubCategories");
		
		log.info("Search_03 - Step 04: Click to Search button");
		productSearchPage.clickToSearchButton();
		
		log.info("Search_03 - Step 05: Verify 1 item displayed in table");
		verifyTrue(productSearchPage.isProductsNumberAtTableDisplayed("1-1 of 1 items"));
		
		log.info("Search_03 - Step 06: Verify product info displayed in table");
		verifyTrue(productSearchPage.isPictureImageUpdated(productName, productName));
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Product name", "1"), productName);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "SKU", "1"), sku);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Price", "1"), price);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Stock quantity", "1"), stockQuantity);
		verifyTrue(productSearchPage.isPublishedIconDisplayedByValue(isPublished));
	}

	@Test
	public void Search_04_Search_With_Product_Name_Child_Category() {
		log.info("Search_04 - Step 01: Input to Product name textbox with value: " + productName);
		productSearchPage.inputToProductNameTextbox(productName);
		
		log.info("Search_04 - Step 02: Select option in Category dropdown with value: " + childCategory);
		productSearchPage.selectAdminDropdownByName(driver, "SearchCategoryId", childCategory);
		
		log.info("Search_04 - Step 03: Uncheck to checkbox Search subcategories");
		productSearchPage.uncheckToAdminCheckboxByID(driver, "SearchIncludeSubCategories");
		
		log.info("Search_04 - Step 04: Click to Search button");
		productSearchPage.clickToSearchButton();
		
		log.info("Search_04 - Step 05: Verify 1 item displayed in table");
		verifyTrue(productSearchPage.isProductsNumberAtTableDisplayed("1-1 of 1 items"));
		
		log.info("Search_04 - Step 06: Verify product info displayed in table");
		verifyTrue(productSearchPage.isPictureImageUpdated(productName, productName));
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Product name", "1"), productName);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "SKU", "1"), sku);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Price", "1"), price);
		verifyEquals(productSearchPage.getTextValueInAdminTableAtColumnNameAndRowIndex(driver, "Stock quantity", "1"), stockQuantity);
		verifyTrue(productSearchPage.isPublishedIconDisplayedByValue(isPublished));
	}

	@Test
	public void Search_05_Search_With_Product_Name_Manufacturer() {
		log.info("Search_05 - Step 01: Input to Product name textbox with value: " + productName);
		productSearchPage.inputToProductNameTextbox(productName);
		
		log.info("Search_05 - Step 02: Select option in Category dropdown with value: " + allCategory);
		productSearchPage.selectAdminDropdownByName(driver, "SearchCategoryId", allCategory);
		
		log.info("Search_05 - Step 03: Uncheck to checkbox Search subcategories");
		productSearchPage.uncheckToAdminCheckboxByID(driver, "SearchIncludeSubCategories");
		
		log.info("Search_05 - Step 04: Select option in Manufacturer dropdown with value: " + manufacturer);
		productSearchPage.selectAdminDropdownByName(driver, "SearchManufacturerId", manufacturer);
		
		log.info("Search_05 - Step 05: Click to Search button");
		productSearchPage.clickToSearchButton();
		
		log.info("Search_05 - Step 06: Verify No data message displayed");
		productSearchPage.isMessageDisplayedInEmptyTable(driver, "products");
	}

	@Test
	public void Search_06_Go_Directly_To_Product_SKU() {
		log.info("Search_06 - Step 01: Open menu 'Catalog' and submenu 'Products'");
		dashboardPage.openAdminSubmenuPageByName(driver, "Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);
		
		log.info("Pre-condition - Step 02: Expand Search field");
		productSearchPage.clickToExpandSearchPanel(driver);
		
		log.info("Search_06 - Step 02: Input to 'Go directly to product SKU' textbox with value: " + sku);
		productSearchPage.inputToGoSkuTextbox(sku);
		
		log.info("Search_06 - Step 03: Click to Go button");
		productDetailPage = productSearchPage.clickToSkuGoButton();

		log.info("Search_06 - Step 04: Verify Product detail page displayed with title " + productName);
		productDetailPage.isTitleByProductNameDisplayed(productName);
		
		log.info("Search_06 - Step 05: Open Product info tab");
		productDetailPage.clickToExpandPanelByName(driver, "Product info");
		
		log.info("Search_06 - Step 06: Verify Product name textbox has value: " + productName);
		verifyEquals(productDetailPage.getValueInAdminTextboxByID(driver, "Name"), productName);
		
		log.info("Search_06 - Step 07: Verify SKU textbox has value: " + sku);
		verifyEquals(productDetailPage.getValueInAdminTextboxByID(driver, "Sku"), sku);
		
		log.info("Search_06 - Step 08: Verify Categories textbox has value: " + childCategory);
		verifyTrue(productDetailPage.isCategoryTagByLabelDisplayed(childCategory));
		
		log.info("Search_06 - Step 09: Verify Published checkbox is checked");
		verifyTrue(productDetailPage.isAdminCheckboxByIDSelected(driver, "Published"));
		
		log.info("Search_06 - Step 10: Open Price tab");
		productDetailPage.clickToExpandPanelByName(driver, "Prices");
		
		log.info("Search_06 - Step 11: Verify Price textbox has equal value with: " + price);
		verifyEquals(productDetailPage.getValueInDropdownByID("Price"), price);
		
		log.info("Search_06 - Step 12: Open Inventory tab");
		productDetailPage.clickToExpandPanelByName(driver, "Inventory");
		
		log.info("Search_06 - Step 13: Verify Stock quanity textbox displayed with value: " + stockQuantity);
		verifyEquals(productDetailPage.getValueInDropdownByID("StockQuantity"), stockQuantity);
	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailPageObject productDetailPage;
	String adminEmail, adminPassword, productName, sku, price, stockQuantity, isPublished, allCategory, parentCategory, childCategory, manufacturer;
}
