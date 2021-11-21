package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.CompareProductPageObject;
import pageObjects.user.DesktopsPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.NotebooksPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.ProductDetailPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.ShoppingCartPageObject;
import pageObjects.user.WishlistPageObject;
import utilities.DataUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_06_Wishlist_Compare_Recent_View extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		fakeData = DataUtil.getData();
		gender = "Male";
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		fullName = firstName + " " + lastName;
		emailAddress = fakeData.getEmailAddress();
		password = fakeData.getPassword();

		menuName = "Computers";
		submenuName = "Desktops";
		recentViewSubmenu = "Notebooks";
		productTitle = "Build your own computer";
		compareProductTitle = "Digital Storm VANQUISH 3 Custom Performance PC";

		productSKU = "COMP_CUST";
		processor = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ram = "8GB [+$60.00]";
		hdd = "400 GB [+$100.00]";
		os = "Vista Home [+$50.00]";
		software = "Microsoft Office [+$50.00]";
		productPrice = "$1,475.00";
		productQuantity = "2";
		expectedSpecifications = Arrays.asList(productTitle, processor, ram, hdd, os, software);

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

		log.info("Pre-condition - Step 07: Enter to Email textbox with value: " + emailAddress);
		registerPage.inputToUserTextboxByID(driver, "Email", emailAddress);

		log.info("Pre-condition - Step 08: Enter to Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 09: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToUserTextboxByID(driver, "ConfirmPassword", password);

		log.info("Pre-condition - Step 10: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Pre-condition - Step 11: Verify success message displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Pre-condition - Step 12: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 13: Verify HomePage displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 14: Click to Login link");
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 15: Enter to Email textbox with value: " + emailAddress);
		loginPage.inputToUserTextboxByID(driver, "Email", emailAddress);

		log.info("Pre-condition - Step 16: Enter to Password textbox with value: " + password);
		loginPage.inputToUserTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 17: Click to Login link");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Pre-condition - Step 18: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		showBrowserConsoleLogs(driver);

		log.info("Pre-condition - Step 19: Open submenu " + submenuName);
		homePage.openUserSubmenuPageByName(driver, menuName, submenuName);
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("My_Account_04 - Step 01: Click to Product title " + "'" + productTitle + "'");
		desktopsPage.clickToProductTitleByName(driver, productTitle);
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void Wishlist_Compare_Recent_View_01_Add_To_Wishlist() {
		log.info("Wishlist_01 - Step 01: Select product specifications");
		productDetailPage.selectUserDropdownByName(driver, "product_attribute_1", processor);
		productDetailPage.selectUserDropdownByName(driver, "product_attribute_2", ram);
		productDetailPage.clickToRadioButtonByLabel(driver, hdd);
		productDetailPage.clickToRadioButtonByLabel(driver, os);
		productDetailPage.checkToUserCheckboxByLabel(driver, software);
		productDetailPage.inputToUserTextboxByID(driver, "product_enteredQuantity_1", productQuantity);

		log.info("Wishlist_01 - Step 02: Click to 'Add to Wishlist button");
		productDetailPage.clickToButtonByText(driver, "Add to wishlist");

		log.info("Wishlist_01 - Step 03: Verify success message displayed");
		productDetailPage.isAddProductToPageSuccessMessageDisplayed(driver, "wishlist");

		log.info("Wishlist_01 - Step 04: Open Wishlist page");
		productDetailPage.clickToPageLinkInAddProductSuccessMessage(driver, "wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Wishlist_01 - Step 05: Verify Wishlist page displayed");
		wishlistPage.isPageTitleByTextDisplayed(driver, "Wishlist");

		log.info("Wishlist_02 - Step 06: Verify number displayed in Wishlist header link: " + productQuantity);
		wishlistPage.isProductQuantityByHeaderLabelDisplayed(driver, "Wishlist", productQuantity);

		log.info("Wishlist_01 - Step 07: Verify product info displayed in Wishlist");
		verifyEquals(wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "SKU", "1"), productSKU);
		verifyTrue(wishlistPage.isTableImageByProductNameDisplayed(driver, productTitle));

		productSpecifications = wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Product(s)", "1");
		verifyTrue(wishlistPage.isTextValueContainsMultipleKeywords(driver, productSpecifications, expectedSpecifications));

		verifyEquals(wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Price", "1"), productPrice);
		verifyTrue(wishlistPage.isTableQuantityInputByProductNameDisplayed(driver, productTitle, productQuantity));

		totalPrice = wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Total", "1");
		actualTotalPrice = wishlistPage.convertUserProductPriceToNumber(driver, totalPrice);
		expectedTotalPrice = wishlistPage.convertUserProductPriceToNumber(driver, productPrice) * wishlistPage.convertUserProductPriceToNumber(driver, productQuantity);
		verifyEquals(actualTotalPrice, expectedTotalPrice);

		log.info("Wishlist_01 - Step 08: Click to Wishlist sharing URL");
		wishlistPage.clickToShareWishlistLink();

		log.info("Wishlist_01 - Step 09: Verify Wishlist owner name");
		wishlistPage.isPageTitleByTextDisplayed(driver, "Wishlist of " + fullName);

		log.info("Wishlist_01 - Step 10: Verify product info displayed in Wishlist sharing");
		verifyEquals(wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "SKU", "1"), productSKU);
		verifyTrue(wishlistPage.isTableImageByProductNameDisplayed(driver, productTitle));

		productSpecifications = wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Product(s)", "1");
		verifyTrue(wishlistPage.isTextValueContainsMultipleKeywords(driver, productSpecifications, expectedSpecifications));

		verifyEquals(wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Price", "1"), productPrice);
		verifyEquals(wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Qty.", "1"), productQuantity);

		totalPrice = wishlistPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Total", "1");
		actualTotalPrice = wishlistPage.convertUserProductPriceToNumber(driver, totalPrice);
		expectedTotalPrice = wishlistPage.convertUserProductPriceToNumber(driver, productPrice) * wishlistPage.convertUserProductPriceToNumber(driver, productQuantity);
		verifyEquals(actualTotalPrice, expectedTotalPrice);
	}

	@Test
	public void Wishlist_Compare_Recent_View_02_Add_Product_To_Cart_From_Wishlist() {
		log.info("Wishlist_02 - Step 01: Click to HomePage logo");
		wishlistPage.clickToHomePageLogo(driver);
		homePage = PageGeneratorManager.getHomePage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Wishlist_02 - Step 02: Click to Wishlist header link");
		homePage.openHeaderPageByName(driver, "wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Wishlist_02 - Step 03: Click to Add to cart checkbox");
		wishlistPage.checkToAddToCartCheckbox();

		log.info("Wishlist_02 - Step 04: Click to Add to cart button");
		wishlistPage.clickToButtonByText(driver, "Add to cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Wishlist_02 - Step 05: Verify Shopping cart page displayed");
		shoppingCartPage.isPageTitleByTextDisplayed(driver, "Shopping cart");

		log.info("Wishlist_02 - Step 06: Verify number displayed in Shopping cart header link: " + productQuantity);
		shoppingCartPage.isProductQuantityByHeaderLabelDisplayed(driver, "Shopping cart", productQuantity);

		log.info("Wishlist_02 - Step 07: Verify number displayed in Wishlist header link equal to 0");
		shoppingCartPage.isProductQuantityByHeaderLabelDisplayed(driver, "Wishlist", "0");

		log.info("Wishlist_02 - Step 08: Verify product info displayed in Shopping cart");
		verifyEquals(shoppingCartPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "SKU", "1"), productSKU);
		verifyTrue(shoppingCartPage.isTableImageByProductNameDisplayed(driver, productTitle));

		productSpecifications = shoppingCartPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Product(s)", "1");
		verifyTrue(shoppingCartPage.isTextValueContainsMultipleKeywords(driver, productSpecifications, expectedSpecifications));

		verifyEquals(shoppingCartPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Price", "1"), productPrice);
		verifyTrue(shoppingCartPage.isTableQuantityInputByProductNameDisplayed(driver, productTitle, productQuantity));

		totalPrice = shoppingCartPage.getTextValueInUserTableAtColumnNameAndRowIndex(driver, "Total", "1");
		actualTotalPrice = shoppingCartPage.convertUserProductPriceToNumber(driver, totalPrice);
		expectedTotalPrice = shoppingCartPage.convertUserProductPriceToNumber(driver, productPrice) * shoppingCartPage.convertUserProductPriceToNumber(driver, productQuantity);
		verifyEquals(actualTotalPrice, expectedTotalPrice);

		log.info("Wishlist_02 - Step 09: Click to 'Wishlist' header link");
		shoppingCartPage.openHeaderPageByName(driver, "wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Wishlist_02 - Step 10: Verify message displayed 'The wishlist is empty!'");
		verifyTrue(wishlistPage.isNoDataMessageByTextDisplayed(driver, "The wishlist is empty!"));
	}

	@Test
	public void Wishlist_Compare_Recent_View_03_Remove_Product_In_Wishlist() {
		log.info("Wishlist_03 - Step 01: Open submenu " + submenuName);
		wishlistPage.openUserSubmenuPageByName(driver, menuName, submenuName);
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Wishlist_03 - Step 02: Click to Product title " + "'" + productTitle + "'");
		desktopsPage.clickToProductTitleByName(driver, productTitle);
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Wishlist_03 - Step 03: Select product specifications");
		productDetailPage.selectUserDropdownByName(driver, "product_attribute_1", processor);
		productDetailPage.selectUserDropdownByName(driver, "product_attribute_2", ram);
		productDetailPage.clickToRadioButtonByLabel(driver, hdd);
		productDetailPage.clickToRadioButtonByLabel(driver, os);
		productDetailPage.checkToUserCheckboxByLabel(driver, software);
		productDetailPage.inputToUserTextboxByID(driver, "product_enteredQuantity_1", productQuantity);

		log.info("Wishlist_03 - Step 04: Click to 'Add to Wishlist button");
		productDetailPage.clickToButtonByText(driver, "Add to wishlist");

		log.info("Wishlist_03 - Step 05: Verify success message displayed");
		productDetailPage.isAddProductToPageSuccessMessageDisplayed(driver, "wishlist");

		log.info("Wishlist_03 - Step 06: Open Wishlist page");
		productDetailPage.clickToPageLinkInAddProductSuccessMessage(driver, "wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Wishlist_03 - Step 07: Verify Wishlist page displayed");
		wishlistPage.isPageTitleByTextDisplayed(driver, "Wishlist");

		log.info("Wishlist_03 - Step 08: Click to Remove icon");
		wishlistPage.clickToRemoveIconInTableByProductName(driver, productTitle);

		log.info("Wishlist_03 - Step 08: Verify product image undisplayed");
		verifyTrue(wishlistPage.isTableImageByProductNameUndisplayed(driver, productTitle));

		log.info("Wishlist_03 - Step 09: Verify no data message displayed");
		verifyTrue(wishlistPage.isNoDataMessageByTextDisplayed(driver, "The wishlist is empty!"));
	}

	@Test
	public void Wishlist_Compare_Recent_View_04_Add_Product_To_Compare() {
		log.info("Compare_01 - Step 01: Open submenu " + submenuName);
		wishlistPage.openUserSubmenuPageByName(driver, menuName, submenuName);
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Compare_01 - Step 02: Get product price of compare products");
		actualProductPrice = desktopsPage.getProductPriceByProductName(driver, productTitle);
		actualComparePrice = desktopsPage.getProductPriceByProductName(driver, compareProductTitle);

		log.info("Compare_01 - Step 03: Click to 'Add to Compare' icon at " + productTitle);
		desktopsPage.clickToButtonByLabelAndProductName(driver, productTitle, "Add to compare list");

		log.info("Compare_01 - Step 04: Verify add success message displayed");
		desktopsPage.isAddProductToPageSuccessMessageDisplayed(driver, "product comparison");

		log.info("Compare_01 - Step 05: Click to 'Add to Compare' icon at " + compareProductTitle);
		desktopsPage.clickToButtonByLabelAndProductName(driver, compareProductTitle, "Add to compare list");

		log.info("Compare_01 - Step 06: Verify add success message displayed");
		desktopsPage.isAddProductToPageSuccessMessageDisplayed(driver, "product comparison");

		log.info("Compare_01 - Step 07: Open Compare page");
		desktopsPage.clickToPageLinkInAddProductSuccessMessage(driver, "product comparison");
		compareProductPage = PageGeneratorManager.getCompareProductPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Compare_01 - Step 08: Verify Compare page displayed");
		compareProductPage.isPageTitleByTextDisplayed(driver, "Compare products");

		log.info("Compare_01 - Step 09: Verify info displayed for product" + productTitle);
		verifyTrue(compareProductPage.isTableImageByProductNameDisplayed(driver, productTitle));
		verifyEquals(compareProductPage.getTextValueAtRowNameAndColumnIndex(productTitle, "product-name"), productTitle);
		verifyEquals(compareProductPage.getTextValueAtRowNameAndColumnIndex(productTitle, "product-price"), actualProductPrice);

		log.info("Compare_01 - Step 10: Verify info displayed for product" + compareProductTitle);
		verifyTrue(compareProductPage.isTableImageByProductNameDisplayed(driver, compareProductTitle));
		verifyEquals(compareProductPage.getTextValueAtRowNameAndColumnIndex(compareProductTitle, "product-name"), compareProductTitle);
		verifyEquals(compareProductPage.getTextValueAtRowNameAndColumnIndex(compareProductTitle, "product-price"), actualComparePrice);

		log.info("Compare_01 - Step 11: Click to Clear list button");
		compareProductPage.clickToClearListButton();

		log.info("Compare_01 - Step 12: Verify no data message displayed");
		verifyTrue(compareProductPage.isNoDataMessageByTextDisplayed(driver, "You have no items to compare."));

		log.info("Compare_01 - Step 13: Verify product images undisplayed");
		verifyTrue(compareProductPage.isTableImageByProductNameUndisplayed(driver, productTitle));
		verifyTrue(compareProductPage.isTableImageByProductNameUndisplayed(driver, compareProductTitle));
	}

	@Test
	public void Wishlist_Compare_Recent_View_05_Display_Recently_Viewed_Products() {
		log.info("Recent_View_01 - Step 01: Open submenu " + recentViewSubmenu);
		compareProductPage.openUserSubmenuPageByName(driver, menuName, recentViewSubmenu);
		notebooksPage = PageGeneratorManager.getNotebooksPage(driver);
		showBrowserConsoleLogs(driver);

		log.info("Recent_View_01 - Step 02: Click to all product titles");
		productTitles = notebooksPage.getAllProductTitles(driver);

		for (String productTitle : productTitles) {
			notebooksPage.clickToProductTitleByName(driver, productTitle);
			productDetailPage = PageGeneratorManager.getProductDetailPage(driver);

			productDetailPage.openUserSubmenuPageByName(driver, menuName, recentViewSubmenu);
			notebooksPage = PageGeneratorManager.getNotebooksPage(driver);
		}

		log.info("Recent_View_01 - Step 03: Verify 3 last viewed products displayed in Recently viewed products block");
		verifyTrue(notebooksPage.isRecentlyViewedProductsDisplayed(driver));
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
	WishlistPageObject wishlistPage;
	DesktopsPageObject desktopsPage;
	ShoppingCartPageObject shoppingCartPage;
	ProductDetailPageObject productDetailPage;
	NotebooksPageObject notebooksPage;
	CompareProductPageObject compareProductPage;
	String gender, firstName, lastName, fullName, emailAddress, password, menuName, submenuName, recentViewSubmenu, productTitle, compareProductTitle, productSKU, processor, ram, hdd, os, software, productSpecifications, productPrice,
			productQuantity, totalPrice, actualProductPrice, actualComparePrice;
	Float actualTotalPrice, expectedTotalPrice;
	List<String> productTitles, expectedSpecifications;
}
