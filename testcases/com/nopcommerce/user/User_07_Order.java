package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.CheckoutPageObject;
import pageObjects.user.DesktopsPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.MyOrdersPageObject;
import pageObjects.user.NotebooksPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.ProductDetailPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.ShoppingCartPageObject;
import utilities.DataUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_07_Order extends BaseTest {
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
		productTitle = "Build your own computer";

		productSKU = "COMP_CUST";
		processor = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ram = "8GB [+$60.00]";
		hdd = "400 GB [+$100.00]";
		os = "Vista Premium [+$60.00]";
		softwareMS = "Microsoft Office [+$50.00]";
		softwareAcrobat = "Acrobat Reader [+$10.00]";
		softwareCommander = "Total Commander [+$5.00]";
		productPrice = "$1,500.00";
		productQuantity = "1";
		expectedSpecifications = Arrays.asList(productTitle, processor, ram, hdd, os, softwareMS, softwareAcrobat, softwareCommander, productPrice, productQuantity);

		editProcessor = "2.2 GHz Intel Pentium Dual-Core E2200";
		editRam = "4GB [+$20.00]";
		editHdd = "320 GB";
		editOS = "Vista Home [+$50.00]";
		editProductPrice = "$1,320.00";
		editProductQuantity = "2";
		editExpectedSpecifications = Arrays.asList(productTitle, editProcessor, editRam, editHdd, editOS, softwareMS, editProductPrice, editProductQuantity);

		updateProductTitle = "Lenovo IdeaCentre 600 All-in-One PC";
		updateProductPrice = "$500.00";
		updateProductQuantiy = "5";

		orderSubMenu = "Notebooks";
		orderProductTitle = "Apple MacBook Pro 13-inch";
		orderSKU = "AP_MBP_13";
		orderProductQuantity = "2";
		orderProductPrice = "$1,800.00";
		orderProductSubTotal = "$3,600.00";
		earnPoint = "360";

		country = "United States";
		state = "California";
		city = fakeData.getCityName();
		address = fakeData.getFirstAddress();
		zip = fakeData.getZipCode();
		shipMethod = "Next Day Air";
		shipMethodWithFee = "Next Day Air ($0.00)";
		phone = fakeData.getPhoneNumber();
		giftWrapping = "No";
		chequePayment = "Check / Money Order";
		cardPayment = "Credit Card";
		orderStatus = "Pending";
		shipStatus = "Not yet shipped";
		existAddressInfo = fullName + ", " + address + ", " + city + ", " + state + " " + zip + ", " + country;

		cardType = "Visa";
		cardHolder = fullName.toUpperCase();
		cardNumber = "4242424242424242";
		expiredMonth = "10";
		expiredYear = "2030";
		cardCode = "123";

		reorderQuantity = "10";
		reorderSubtotal = "$18,000.00";
		reorderFirstName = fakeData.getFirstName();
		reorderLastName = fakeData.getLastName();
		reorderFullName = reorderFirstName + " " + reorderLastName;
		reorderEmail = fakeData.getEmailAddress();
		reorderCountry = "Australia";
		reorderState = "Other";
		reorderCity = fakeData.getCityName();
		reorderAddress = fakeData.getFirstAddress();
		reorderZip = fakeData.getZipCode();
		reorderPhone = fakeData.getPhoneNumber();
		reorderShipMethod = "Ground";
		reorderShipMethodWithFee = "Ground ($0.00)";
		reorderEarnPoint = "1800";

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
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("Pre-condition - Step 06: Enter to Lastname textbox with value: " + lastName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);

		log.info("Pre-condition - Step 07: Enter to Email textbox with value: " + emailAddress);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("Pre-condition - Step 08: Enter to Password textbox with value: " + password);
		registerPage.inputToTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 09: Enter to Confirm Password textbox with value: " + password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Pre-condition - Step 10: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Pre-condition - Step 11: Verify success message displayed");
		verifyTrue(registerPage.isSuccessMessageDisplayed());

		log.info("Pre-condition - Step 12: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "logout");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Pre-condition - Step 13: Verify HomePage displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 14: Click to Login link");
		homePage.openHeaderPageByName(driver, "login");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 15: Enter to Email textbox with value: " + emailAddress);
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("Pre-condition - Step 16: Enter to Password textbox with value: " + password);
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 17: Click to Login link");
		loginPage.clickToButtonByText(driver, "Log in");

		log.info("Pre-condition - Step 18: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());

		log.info("Pre-condition - Step 19: Open submenu " + submenuName);
		homePage.openUserSubmenuPageByName(driver, menuName, submenuName);
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);

		log.info("Pre-condition - Step 20: Click to Product title " + "'" + productTitle + "'");
		desktopsPage.clickToProductTitleByName(driver, productTitle);
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);
	}

	@Test
	public void Order_01_Add_Product_To_Cart() {
		log.info("Order_01 - Step 01: Select option in processor dropdown with value: " + processor);
		productDetailPage.selectDropdownByName(driver, "product_attribute_1", processor);
		productDetailPage.sleepInSecond(1);

		log.info("Order_01 - Step 02: Select option in ram dropdown with value: " + ram);
		productDetailPage.selectDropdownByName(driver, "product_attribute_2", ram);
		productDetailPage.sleepInSecond(1);

		log.info("Order_01 - Step 03: Check to hdd radio with value: " + hdd);
		productDetailPage.clickToRadioButtonByLabel(driver, hdd);
		productDetailPage.sleepInSecond(1);

		log.info("Order_01 - Step 04: Check to  os radio with value: " + os);
		productDetailPage.clickToRadioButtonByLabel(driver, os);
		productDetailPage.sleepInSecond(1);

		log.info("Order_01 - Step 05: Check to software checkbox with value: " + softwareMS);
		productDetailPage.checkToCheckboxByLabel(driver, softwareMS);
		productDetailPage.sleepInSecond(1);

		log.info("Order_01 - Step 06: Check to software checkbox with value: " + softwareAcrobat);
		productDetailPage.checkToCheckboxByLabel(driver, softwareAcrobat);
		productDetailPage.sleepInSecond(1);

		log.info("Order_01 - Step 07: Check to software checkbox with value: " + softwareCommander);
		productDetailPage.checkToCheckboxByLabel(driver, softwareCommander);
		productDetailPage.sleepInSecond(1);

		log.info("Order_01 - Step 08: Input to Quanity textbox with value: " + productQuantity);
		productDetailPage.inputToTextboxByID(driver, "product_enteredQuantity_1", productQuantity);
		productDetailPage.sleepInSecond(1);

		log.info("Order_01 - Step 09: Verify product price displayed with value: " + productPrice);
		verifyEquals(productDetailPage.getProductPrice(), productPrice);

		log.info("Order_01 - Step 10: Click to Add to cart button");
		productDetailPage.clickToButtonByText(driver, "Add to cart");

		log.info("Order_01 - Step 11: Verify add success message displayed");
		productDetailPage.isAddProductToPageSuccessMessageDisplayed(driver, "shopping cart");

		log.info("Order_01 - Step 12: Verify number displayed in Shopping cart header link: " + productQuantity);
		productDetailPage.clickToCloseButtonAtMessage(driver);
		productDetailPage.isProductQuantityByHeaderLabelDisplayed(driver, "Shopping cart", productQuantity);

		log.info("Order_01 - Step 13: Hover to Shopping cart header link");
		productDetailPage.hoverToHeaderLink(driver, "cart");

		log.info("Order_01 - Step 14: Verify count item in cart with value: " + productQuantity);
		verifyTrue(productDetailPage.getItemsCountAtMiniCart(driver).contains(productQuantity));

		log.info("Order_01 - Step 15: Verify product image displayed in mini shopping cart");
		verifyTrue(productDetailPage.isImageByProductDisplayedAtMiniCart(driver, productTitle));

		log.info("Order_01 - Step 16: Verify product info displayed in mini shopping cart");
		productSpecifications = productDetailPage.getProductTextValueAtMiniCart(driver);
		verifyTrue(productDetailPage.isTextValueContainsMultipleKeywords(driver, productSpecifications, expectedSpecifications));

		log.info("Order_01 - Step 17: Verify sub-total price displayed in mini shopping cart");
		subTotalPrice = productDetailPage.getSubTotalPriceAtMiniCart(driver);
		actualSubTotalPrice = productDetailPage.convertProductPriceToNumber(driver, subTotalPrice);
		expectedSubTotalPrice = productDetailPage.convertProductPriceToNumber(driver, productPrice) * productDetailPage.convertProductPriceToNumber(driver, productQuantity);
		verifyEquals(actualSubTotalPrice, expectedSubTotalPrice);
	}

	@Test
	public void Order_02_Edit_Product_In_Shopping_Cart() {
		log.info("Order_02 - Step 01: Open Shopping cart page");
		productDetailPage.openHeaderPageByName(driver, "cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Order_02 - Step 02: Verify Shopping cart displayed");
		shoppingCartPage.isPageTitleByTextDisplayed(driver, "Shopping cart");

		log.info("Order_02 - Step 03: Click to Edit link");
		shoppingCartPage.clickToEditProductLink();
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);

		log.info("Order_02 - Step 04: Reselect option in processor dropdown with value: " + editProcessor);
		productDetailPage.selectDropdownByName(driver, "product_attribute_1", editProcessor);
		productDetailPage.sleepInSecond(1);

		log.info("Order_02 - Step 05: Reselect option in ram dropdown with value: " + editRam);
		productDetailPage.selectDropdownByName(driver, "product_attribute_2", editRam);
		productDetailPage.sleepInSecond(1);

		log.info("Order_02 - Step 06: Recheck to hdd radio with value: " + editHdd);
		productDetailPage.clickToRadioButtonByLabel(driver, editHdd);
		productDetailPage.sleepInSecond(1);

		log.info("Order_02 - Step 07: Recheck to hdd radio with value: " + editOS);
		productDetailPage.clickToRadioButtonByLabel(driver, editOS);
		productDetailPage.sleepInSecond(1);

		log.info("Order_02 - Step 08: Check to software checkbox with value: " + softwareMS);
		productDetailPage.checkToCheckboxByLabel(driver, softwareMS);
		productDetailPage.sleepInSecond(1);

		log.info("Order_02 - Step 09: Uncheck to software checkbox with value: " + softwareAcrobat);
		productDetailPage.uncheckToCheckboxByLabel(driver, softwareAcrobat);
		productDetailPage.sleepInSecond(1);

		log.info("Order_02 - Step 10: Uncheck to software checkbox with value: " + softwareCommander);
		productDetailPage.uncheckToCheckboxByLabel(driver, softwareCommander);
		productDetailPage.sleepInSecond(1);

		log.info("Order_02 - Step 11: Verify new product price displayed with value: " + editProductPrice);
		verifyEquals(productDetailPage.getProductPrice(), editProductPrice);

		log.info("Order_02 - Step 12: Input to Quanity textbox with new value: " + editProductQuantity);
		productDetailPage.inputToTextboxByID(driver, "product_enteredQuantity_1", editProductQuantity);

		log.info("Order_02 - Step 13: Click to Update button");
		productDetailPage.clickToButtonByText(driver, "Update");

		log.info("Order_02 - Step 14: Verify add success message displayed");
		productDetailPage.isAddProductToPageSuccessMessageDisplayed(driver, "shopping cart");

		log.info("Order_02 - Step 15: Verify number displayed in Shopping cart header link: " + editProductQuantity);
		productDetailPage.clickToCloseButtonAtMessage(driver);
		productDetailPage.isProductQuantityByHeaderLabelDisplayed(driver, "Shopping cart", editProductQuantity);

		log.info("Order_02 - Step 16: Hover to Shopping cart header link");
		productDetailPage.hoverToHeaderLink(driver, "cart");

		log.info("Order_02 - Step 17: Verify count item in cart with value: " + editProductQuantity);
		verifyTrue(productDetailPage.getItemsCountAtMiniCart(driver).contains(editProductQuantity));

		log.info("Order_02 - Step 18: Verify product image displayed in mini shopping cart");
		verifyTrue(productDetailPage.isImageByProductDisplayedAtMiniCart(driver, productTitle));

		log.info("Order_02 - Step 19: Verify product info displayed in mini shopping cart");
		editProductSpecifications = productDetailPage.getProductTextValueAtMiniCart(driver);
		verifyTrue(productDetailPage.isTextValueContainsMultipleKeywords(driver, editProductSpecifications, editExpectedSpecifications));

		log.info("Order_02 - Step 20: Verify sub-total price displayed in mini shopping cart");
		editSubTotalPrice = productDetailPage.getSubTotalPriceAtMiniCart(driver);
		editActualSubTotalPrice = productDetailPage.convertProductPriceToNumber(driver, editSubTotalPrice);
		editExpectedSubTotalPrice = productDetailPage.convertProductPriceToNumber(driver, editProductPrice) * productDetailPage.convertProductPriceToNumber(driver, editProductQuantity);
		verifyEquals(editActualSubTotalPrice, editExpectedSubTotalPrice);
	}

	@Test
	public void Order_03_Remove_Product_From_Cart() {
		log.info("Order_03 - Step 01: Open Shopping cart page");
		productDetailPage.openHeaderPageByName(driver, "cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Order_03 - Step 02: Click to Remove icon");
		shoppingCartPage.clickToRemoveIconInTableByProductName(driver, productTitle);

		log.info("Order_03 - Step 03: Verify Empty data message displayed");
		shoppingCartPage.isNoDataMessageByTextDisplayed(driver, "Your Shopping Cart is empty!");

		log.info("Order_03 - Step 04: Verify product image undisplayed");
		verifyTrue(shoppingCartPage.isTableImageByProductNameUndisplayed(driver, productTitle));
	}

	@Test
	public void Order_04_Checkout_By_Cheque() throws Exception {
		log.info("Order_04 - Step 01: Open submenu " + orderSubMenu);
		shoppingCartPage.openUserSubmenuPageByName(driver, menuName, orderSubMenu);
		notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

		log.info("Order_04 - Step 02: Click to Product title " + "'" + orderProductTitle + "'");
		notebooksPage.clickToProductTitleByName(driver, orderProductTitle);
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);

		log.info("Order_04 - Step 03: Click to Add to cart button");
		productDetailPage.clickToButtonByText(driver, "Add to cart");

		log.info("Order_04 - Step 04: Verify add success message displayed");
		productDetailPage.isAddProductToPageSuccessMessageDisplayed(driver, "shopping cart");

		log.info("Order_04 - Step 05: Open Shopping cart page");
		productDetailPage.clickToPageLinkInAddProductSuccessMessage(driver, "shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Order_04 - Step 06: Verify product info in table");
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "SKU", "1"), orderSKU);
		verifyTrue(shoppingCartPage.isTableImageByProductNameDisplayed(driver, orderProductTitle));
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "Product(s)", "1"), orderProductTitle);
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "Price", "1"), orderProductPrice);
		verifyTrue(shoppingCartPage.isTableQuantityInputByProductNameDisplayed(driver, orderProductTitle, orderProductQuantity));
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "Total", "1"), orderProductSubTotal);

		log.info("Order_04 - Step 07: Select gift wrapping dropdown with value " + giftWrapping);
		shoppingCartPage.selectOptionInGiftWrappingDropdown(giftWrapping);

		log.info("Order_04 - Step 08: Verify gift wrapping info displayed");
		verifyEquals(shoppingCartPage.getGiftWrappingCheckoutConfirmation(driver), "Gift wrapping: " + giftWrapping);

		log.info("Order_04 - Step 09: Verify Sub-total at cart total with value: " + orderProductSubTotal);
		verifyEquals(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "Sub-Total:"), orderProductSubTotal);

		log.info("Order_04 - Step 10: Verify Shipping fee at cart total with value: $0.00");
		verifyEquals(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "Shipping:"), "$0.00");

		log.info("Order_04 - Step 11: Verify Tax fee at cart total with value: $0.00");
		verifyEquals(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "Tax:"), "$0.00");

		log.info("Order_04 - Step 12: Verify Total at cart total with value: " + orderProductSubTotal);
		verifyEquals(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "Total:"), orderProductSubTotal);

		log.info("Order_04 - Step 13: Verify earn point at cart total");
		verifyTrue(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "You will earn:").contains(earnPoint));

		log.info("Order_04 - Step 14: Check to Agree term checkbox");
		shoppingCartPage.checkToServiceTermAgreementCheckbox();

		log.info("Order_04 - Step 15: Click to Checkout button");
		shoppingCartPage.clickToButtonByText(driver, "Checkout");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);

		log.info("Order_04 - Step 16: Verify Checkout page displayed");
		checkoutPage.isPageTitleByTextDisplayed(driver, "Checkout");
		checkoutPage.isActiveTabByName("Billing address");

		log.info("Order_04 - Step 17: Select option in Billing Country dropdown with value: " + country);
		checkoutPage.selectDropdownByName(driver, "BillingNewAddress.CountryId", country);

		log.info("Order_04 - Step 18: Select option in Billing State dropdown with value: " + state);
		checkoutPage.selectDropdownByName(driver, "BillingNewAddress.StateProvinceId", state);

		log.info("Order_04 - Step 19: Input to Billing City textbox with value: " + city);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_City", city);

		log.info("Order_04 - Step 20: Input to Billing Address textbox with value: " + address);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", address);

		log.info("Order_04 - Step 21: Input to Billing Zipcode textbox with value: " + zip);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", zip);

		log.info("Order_04 - Step 22: Input to Billing Phone textbox with value: " + phone);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phone);

		log.info("Order_04 - Step 23: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Billing address");

		log.info("Order_04 - Step 24: Verify Shipping method tab active");
		checkoutPage.isActiveTabByName("Shipping method");

		log.info("Order_04 - Step 25: Click to shipping method radio button with value: " + shipMethodWithFee);
		checkoutPage.clickToRadioButtonByLabel(driver, shipMethodWithFee);

		log.info("Order_04 - Step 26: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Shipping method");

		log.info("Order_04 - Step 27: Verify Payment method tab active");
		checkoutPage.isActiveTabByName("Payment method");

		log.info("Order_04 - Step 28: Click to shipping method radio button with value: " + chequePayment);
		checkoutPage.clickToRadioButtonByLabel(driver, chequePayment);

		log.info("Order_04 - Step 29: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Payment method");

		log.info("Order_04 - Step 30: Verify Payment information tab active");
		checkoutPage.isActiveTabByName("Payment information");

		log.info("Order_04 - Step 31: Verify Payement info");
		verifyTrue(checkoutPage.getPaymentInfo().contains("Mail Personal or Business Check, Cashier's Check or money order to:"));
		verifyTrue(checkoutPage.getPaymentInfo().contains("NOP SOLUTIONS"));
		verifyTrue(checkoutPage.getPaymentInfo().contains("your address here,"));
		verifyTrue(checkoutPage.getPaymentInfo().contains("New York, NY 10001"));
		verifyTrue(checkoutPage.getPaymentInfo().contains("USA"));
		verifyTrue(checkoutPage.getPaymentInfo().contains("Notice that if you pay by Personal or Business Check, your order may be held for up to 10 days after we receive your check to allow enough time for the check to clear."));
		verifyTrue(checkoutPage.getPaymentInfo().contains("If you want us to ship faster upon receipt of your payment, then we recommend your send a money order or Cashier's check."));
		verifyTrue(checkoutPage.getPaymentInfo().contains("P.S. You can edit this text from admin panel."));

		log.info("Order_04 - Step 32: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Payment information");

		log.info("Order_04 - Step 33: Verify Confirm order tab active");
		checkoutPage.isActiveTabByName("Confirm order");

		log.info("Order_04 - Step 34: Verify Billing Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "name"), fullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "email").contains(emailAddress));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "phone").contains(phone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "address1").contains(address));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "city-state-zip"), city + "," + state + "," + zip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "country"), country);

		log.info("Order_04 - Step 35: Verify Payment info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method").contains(chequePayment));

		log.info("Order_04 - Step 36: Verify Shipping Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "name"), fullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "email").contains(emailAddress));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "phone").contains(phone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "address1").contains(address));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "city-state-zip"), city + "," + state + "," + zip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "country"), country);

		log.info("Order_04 - Step 37: Verify Shipping method info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-method").contains(shipMethod));

		log.info("Order_04 - Step 38: Verify Product info");
		verifyTrue(checkoutPage.isProductInfoDisplayedAtTable(driver, orderSKU, orderProductTitle, orderProductPrice, orderProductQuantity, orderProductSubTotal));
		verifyTrue(checkoutPage.isTableImageByProductNameDisplayed(driver, orderProductTitle));

		log.info("Order_04 - Step 39: Verify checkout info");
		verifyEquals(checkoutPage.getGiftWrappingCheckoutConfirmation(driver), "Gift wrapping: " + giftWrapping);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Sub-Total:"), orderProductSubTotal);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Shipping:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Tax:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Total:"), orderProductSubTotal);
		verifyTrue(checkoutPage.getValueAtCheckoutCartByLabel(driver, "You will earn:").contains(earnPoint));

		log.info("Order_04 - Step 40: Click to Confirm button");
		checkoutPage.clickToButtonByText(driver, "Confirm");

		log.info("Order_04 - Step 41: Verify Thank you title displayed");
		checkoutPage.isPageTitleByTextDisplayed(driver, "Thank you");

		log.info("Order_04 - Step 42: Verify order success message displayed");
		verifyTrue(checkoutPage.isOrderSuccessMessageDisplayed());
		orderNumber = checkoutPage.getOrderNumber();

		log.info("Order_04 - Step 43: Click to Continue button");
		checkoutPage.clickToButtonByText(driver, "Continue");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Order_04 - Step 44: Click to My account header");
		homePage.openHeaderPageByName(driver, "account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		log.info("Order_04 - Step 45: Click to Orders link at sidebar");
		myAccountPage.openSidebarPageByName(driver, "Orders");
		myOrdersPage = PageGeneratorManager.getMyOrdersPage(driver);

		log.info("Order_04 - Step 46: Verify My Orders page displayed");
		verifyTrue(myOrdersPage.isPageTitleByTextDisplayed(driver, "My account - Orders"));

		log.info("Order_04 - Step 47: Verify order number displayed with value: " + orderNumber);
		verifyTrue(myOrdersPage.isOrderNumberDisplayedAtMyOrders(orderNumber));

		log.info("Order_04 - Step 48: Verify order total with " + orderProductSubTotal);
		verifyEquals(myOrdersPage.getInfoByOrderNumberAndLabelAtMyOrders(orderNumber, "Order Total"), orderProductSubTotal);

		log.info("Order_04 - Step 49: Verify order status with " + orderStatus);
		verifyEquals(myOrdersPage.getInfoByOrderNumberAndLabelAtMyOrders(orderNumber, "Order status"), orderStatus);

		log.info("Order_04 - Step 50: Get order date");
		orderDate = myOrdersPage.getOrderDateByOrderNumberAndLabelAtMyOrders(orderNumber, "Order Date");

		log.info("Order_04 - Step 51: Click to Details button");
		myOrdersPage.clickToDetailsButtonByOrderNumber(orderNumber);

		log.info("Order_04 - Step 52: Verify Order Infomation title displayed");
		verifyTrue(myOrdersPage.isPageTitleByTextDisplayed(driver, "Order information"));

		log.info("Order_04 - Step 53: Verify order number displayed with " + orderNumber);
		verifyTrue(myOrdersPage.isOrderNumberDisplayedAtOrderDetails(orderNumber));

		log.info("Order_04 - Step 54: Verify order date displayed");
		verifyEquals(myOrdersPage.getOrderDateByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-date"), orderDate);

		log.info("Order_04 - Step 55: Verify order status displayed with " + orderStatus);
		verifyTrue(myOrdersPage.getInfoByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-status").contains(orderStatus));

		log.info("Order_04 - Step 56: Verify order total displayed with " + orderProductSubTotal);
		verifyTrue(myOrdersPage.getInfoByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-total").contains(orderProductSubTotal));

		log.info("Order_04 - Step 57: Verify Billing Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "name"), fullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "email").contains(emailAddress));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "phone").contains(phone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "address1").contains(address));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "city-state-zip"), city + "," + state + "," + zip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "country"), country);

		log.info("Order_04 - Step 58: Verify Payment info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method").contains(chequePayment));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method-status").contains(orderStatus));

		log.info("Order_04 - Step 59: Verify Shipping Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "name"), fullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "email").contains(emailAddress));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "phone").contains(phone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "address1").contains(address));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "city-state-zip"), city + "," + state + "," + zip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "country"), country);

		log.info("Order_04 - Step 60: Verify Shipping method info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-method").contains(shipMethod));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-status").contains(shipStatus));

		log.info("Order_04 - Step 61: Verify Product info");
		verifyTrue(checkoutPage.isProductInfoDisplayedAtTable(driver, orderSKU, orderProductTitle, orderProductPrice, orderProductQuantity, orderProductSubTotal));

		log.info("Order_04 - Step 62: Verify checkout info");
		verifyEquals(checkoutPage.getGiftWrappingCheckoutConfirmation(driver), "Gift wrapping: " + giftWrapping);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Sub-Total:"), orderProductSubTotal);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Shipping:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Tax:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Order Total:"), orderProductSubTotal);
	}

	@Test
	public void Order_05_Checkout_By_Card() throws Exception {
		log.info("Order_05 - Step 01: Open submenu " + orderSubMenu);
		checkoutPage.openUserSubmenuPageByName(driver, menuName, orderSubMenu);
		notebooksPage = PageGeneratorManager.getNotebooksPage(driver);

		log.info("Order_05 - Step 02: Click to Product title " + "'" + orderProductTitle + "'");
		notebooksPage.clickToProductTitleByName(driver, orderProductTitle);
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);

		log.info("Order_05 - Step 03: Click to Add to cart button");
		productDetailPage.clickToButtonByText(driver, "Add to cart");

		log.info("Order_05 - Step 04: Verify add success message displayed");
		productDetailPage.isAddProductToPageSuccessMessageDisplayed(driver, "shopping cart");

		log.info("Order_05 - Step 05: Open Shopping cart page");
		productDetailPage.clickToPageLinkInAddProductSuccessMessage(driver, "shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Order_05 - Step 06: Verify product info in table");
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "SKU", "1"), orderSKU);
		verifyTrue(shoppingCartPage.isTableImageByProductNameDisplayed(driver, orderProductTitle));
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "Product(s)", "1"), orderProductTitle);
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "Price", "1"), orderProductPrice);
		verifyTrue(shoppingCartPage.isTableQuantityInputByProductNameDisplayed(driver, orderProductTitle, orderProductQuantity));
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "Total", "1"), orderProductSubTotal);

		log.info("Order_05 - Step 07: Select gift wrapping dropdown with value " + giftWrapping);
		shoppingCartPage.selectOptionInGiftWrappingDropdown(giftWrapping);

		log.info("Order_05 - Step 08: Verify gift wrapping info displayed");
		verifyEquals(shoppingCartPage.getGiftWrappingCheckoutConfirmation(driver), "Gift wrapping: " + giftWrapping);

		log.info("Order_05 - Step 09: Verify Sub-total at cart total with value: " + orderProductSubTotal);
		verifyEquals(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "Sub-Total:"), orderProductSubTotal);

		log.info("Order_05 - Step 10: Verify Shipping fee at cart total with value: $0.00");
		verifyEquals(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "Shipping:"), "$0.00");

		log.info("Order_05 - Step 11: Verify Tax fee at cart total with value: $0.00");
		verifyEquals(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "Tax:"), "$0.00");

		log.info("Order_05 - Step 12: Verify Total at cart total with value: " + orderProductSubTotal);
		verifyEquals(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "Total:"), orderProductSubTotal);

		log.info("Order_05 - Step 13: Verify earn point at cart total");
		verifyTrue(shoppingCartPage.getValueAtCheckoutCartByLabel(driver, "You will earn:").contains(earnPoint));

		log.info("Order_05 - Step 14: Check to Agree term checkbox");
		shoppingCartPage.checkToServiceTermAgreementCheckbox();

		log.info("Order_05 - Step 15: Click to Checkout button");
		shoppingCartPage.clickToButtonByText(driver, "Checkout");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);

		log.info("Order_05 - Step 16: Verify Checkout page displayed");
		checkoutPage.isPageTitleByTextDisplayed(driver, "Checkout");
		checkoutPage.isActiveTabByName("Billing address");

		log.info("Order_05 - Step 17: Select option in Billing Address dropdown with value: " + existAddressInfo);
		checkoutPage.selectDropdownByName(driver, "billing_address_id", existAddressInfo);

		log.info("Order_05 - Step 18: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Billing address");

		log.info("Order_05 - Step 19: Verify Shipping method tab active");
		checkoutPage.isActiveTabByName("Shipping method");

		log.info("Order_05 - Step 20: Click to shipping method radio button with value: " + shipMethodWithFee);
		checkoutPage.clickToRadioButtonByLabel(driver, shipMethodWithFee);

		log.info("Order_05 - Step 21: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Shipping method");

		log.info("Order_05 - Step 22: Verify Payment method tab active");
		checkoutPage.isActiveTabByName("Payment method");

		log.info("Order_05 - Step 23: Click to shipping method radio button with value: " + cardPayment);
		checkoutPage.clickToRadioButtonByLabel(driver, cardPayment);

		log.info("Order_05 - Step 24: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Payment method");

		log.info("Order_05 - Step 25: Verify Payment information tab active");
		checkoutPage.isActiveTabByName("Payment information");

		log.info("Order_05 - Step 26: Select option in Credit card dropdown with value: " + cardType);
		checkoutPage.selectDropdownByName(driver, "CreditCardType", cardType);

		log.info("Order_05 - Step 27: Input to Cardholder name with value: " + cardHolder);
		checkoutPage.inputToTextboxByID(driver, "CardholderName", cardHolder);

		log.info("Order_05 - Step 28: Input to Card number with value: " + cardNumber);
		checkoutPage.inputToTextboxByID(driver, "CardNumber", cardNumber);

		log.info("Order_05 - Step 29: Select option in Expired month dropdown with value: " + expiredMonth);
		checkoutPage.selectDropdownByName(driver, "ExpireMonth", expiredMonth);

		log.info("Order_05 - Step 30: Select option in Expired year dropdown with value: " + expiredYear);
		checkoutPage.selectDropdownByName(driver, "ExpireYear", expiredYear);

		log.info("Order_05 - Step 31: Input to Card code textbox with value: " + cardCode);
		checkoutPage.inputToTextboxByID(driver, "CardCode", cardCode);

		log.info("Order_05 - Step 32: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Payment information");

		log.info("Order_05 - Step 33: Verify Confirm order tab active");
		checkoutPage.isActiveTabByName("Confirm order");

		log.info("Order_05 - Step 34: Verify Billing Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "name"), fullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "email").contains(emailAddress));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "phone").contains(phone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "address1").contains(address));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "city-state-zip"), city + "," + state + "," + zip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "country"), country);

		log.info("Order_05 - Step 35: Verify Payment info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method").contains(cardPayment));

		log.info("Order_05 - Step 36: Verify Shipping Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "name"), fullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "email").contains(emailAddress));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "phone").contains(phone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "address1").contains(address));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "city-state-zip"), city + "," + state + "," + zip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "country"), country);

		log.info("Order_05 - Step 37: Verify Shipping method info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-method").contains(shipMethod));

		log.info("Order_05 - Step 38: Verify Product info");
		verifyTrue(checkoutPage.isProductInfoDisplayedAtTable(driver, orderSKU, orderProductTitle, orderProductPrice, orderProductQuantity, orderProductSubTotal));
		verifyTrue(checkoutPage.isTableImageByProductNameDisplayed(driver, orderProductTitle));

		log.info("Order_05 - Step 39: Verify checkout info");
		verifyEquals(checkoutPage.getGiftWrappingCheckoutConfirmation(driver), "Gift wrapping: " + giftWrapping);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Sub-Total:"), orderProductSubTotal);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Shipping:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Tax:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Total:"), orderProductSubTotal);
		verifyTrue(checkoutPage.getValueAtCheckoutCartByLabel(driver, "You will earn:").contains(earnPoint));

		log.info("Order_05 - Step 40: Click to Confirm button");
		checkoutPage.clickToButtonByText(driver, "Confirm");

		log.info("Order_05 - Step 41: Verify Thank you title displayed");
		checkoutPage.isPageTitleByTextDisplayed(driver, "Thank you");

		log.info("Order_05 - Step 42: Verify order success message displayed");
		verifyTrue(checkoutPage.isOrderSuccessMessageDisplayed());
		orderNumber = checkoutPage.getOrderNumber();

		log.info("Order_05 - Step 43: Click to Continue button");
		checkoutPage.clickToButtonByText(driver, "Continue");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Order_05 - Step 44: Click to My account header");
		homePage.openHeaderPageByName(driver, "account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		log.info("Order_05 - Step 45: Click to Orders link at sidebar");
		myAccountPage.openSidebarPageByName(driver, "Orders");
		myOrdersPage = PageGeneratorManager.getMyOrdersPage(driver);

		log.info("Order_05 - Step 46: Verify My Orders page displayed");
		verifyTrue(myOrdersPage.isPageTitleByTextDisplayed(driver, "My account - Orders"));

		log.info("Order_05 - Step 47: Verify order number displayed with value: " + orderNumber);
		verifyTrue(myOrdersPage.isOrderNumberDisplayedAtMyOrders(orderNumber));

		log.info("Order_05 - Step 48: Verify order total with " + orderProductSubTotal);
		verifyEquals(myOrdersPage.getInfoByOrderNumberAndLabelAtMyOrders(orderNumber, "Order Total"), orderProductSubTotal);

		log.info("Order_05 - Step 49: Verify order status with " + orderStatus);
		verifyEquals(myOrdersPage.getInfoByOrderNumberAndLabelAtMyOrders(orderNumber, "Order status"), orderStatus);

		log.info("Order_05 - Step 50: Get order date");
		orderDate = myOrdersPage.getOrderDateByOrderNumberAndLabelAtMyOrders(orderNumber, "Order Date");

		log.info("Order_05 - Step 51: Click to Details button");
		myOrdersPage.clickToDetailsButtonByOrderNumber(orderNumber);

		log.info("Order_05 - Step 52: Verify Order Infomation title displayed");
		verifyTrue(myOrdersPage.isPageTitleByTextDisplayed(driver, "Order information"));

		log.info("Order_05 - Step 53: Verify order number displayed with " + orderNumber);
		verifyTrue(myOrdersPage.isOrderNumberDisplayedAtOrderDetails(orderNumber));

		log.info("Order_05 - Step 54: Verify order date displayed");
		verifyEquals(myOrdersPage.getOrderDateByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-date"), orderDate);

		log.info("Order_05 - Step 55: Verify order status displayed with " + orderStatus);
		verifyTrue(myOrdersPage.getInfoByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-status").contains(orderStatus));

		log.info("Order_05 - Step 56: Verify order total displayed with " + orderProductSubTotal);
		verifyTrue(myOrdersPage.getInfoByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-total").contains(orderProductSubTotal));

		log.info("Order_05 - Step 57: Verify Billing Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "name"), fullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "email").contains(emailAddress));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "phone").contains(phone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "address1").contains(address));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "city-state-zip"), city + "," + state + "," + zip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "country"), country);

		log.info("Order_05 - Step 58: Verify Payment info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method").contains(cardPayment));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method-status").contains(orderStatus));

		log.info("Order_05 - Step 59: Verify Shipping Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "name"), fullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "email").contains(emailAddress));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "phone").contains(phone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "address1").contains(address));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "city-state-zip"), city + "," + state + "," + zip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "country"), country);

		log.info("Order_05 - Step 60: Verify Shipping method info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-method").contains(shipMethod));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-status").contains(shipStatus));

		log.info("Order_05 - Step 61: Verify Product info");
		verifyTrue(checkoutPage.isProductInfoDisplayedAtTable(driver, orderSKU, orderProductTitle, orderProductPrice, orderProductQuantity, orderProductSubTotal));

		log.info("Order_05 - Step 62: Verify checkout info");
		verifyEquals(checkoutPage.getGiftWrappingCheckoutConfirmation(driver), "Gift wrapping: " + giftWrapping);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Sub-Total:"), orderProductSubTotal);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Shipping:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Tax:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Order Total:"), orderProductSubTotal);
	}
	
	@Test
	public void Order_06_Updating_Shopping_Cart() {
		log.info("Order_06 - Step 01: Open submenu " + submenuName);
		shoppingCartPage.openUserSubmenuPageByName(driver, menuName, submenuName);
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);

		log.info("Order_06 - Step 02: Click to Product title " + "'" + updateProductTitle + "'");
		desktopsPage.clickToProductTitleByName(driver, updateProductTitle);
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);

		log.info("Order_06 - Step 03: Verify product price displayed with value: " + updateProductPrice);
		verifyEquals(productDetailPage.getProductPrice(), updateProductPrice);

		log.info("Order_06 - Step 04: Input to Quanity textbox with value: " + productQuantity);
		productDetailPage.inputToTextboxByID(driver, "product_enteredQuantity_3", productQuantity);

		log.info("Order_06 - Step 05: Click to Add to cart button");
		productDetailPage.clickToButtonByText(driver, "Add to cart");

		log.info("Order_06 - Step 06: Verify add success message displayed");
		productDetailPage.isAddProductToPageSuccessMessageDisplayed(driver, "shopping cart");

		log.info("Order_06 - Step 07: Open Shopping cart page");
		productDetailPage.clickToCloseButtonAtMessage(driver);
		productDetailPage.openHeaderPageByName(driver, "cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Order_06 - Step 08: Edit Quantity textbox with value: " + updateProductQuantiy);
		shoppingCartPage.inputToQuantityTextboxAtTableByProductName(driver, updateProductTitle, updateProductQuantiy);

		log.info("Order_06 - Step 09: Click to Update shopping cart button");
		shoppingCartPage.clickToButtonByText(driver, "Update shopping cart");

		log.info("Order_06 - Step 10: Verify new Total price displayed");
		totalPrice = shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "Total", "1");
		actualTotalPrice = shoppingCartPage.convertProductPriceToNumber(driver, totalPrice);
		expectedTotalPrice = shoppingCartPage.convertProductPriceToNumber(driver, updateProductPrice) * productDetailPage.convertProductPriceToNumber(driver, updateProductQuantiy);
		verifyEquals(actualTotalPrice, expectedTotalPrice);

		log.info("Order_06 - Step 11: Click to Remove icon");
		shoppingCartPage.clickToRemoveIconInTableByProductName(driver, updateProductTitle);
		shoppingCartPage.isNoDataMessageByTextDisplayed(driver, "Your Shopping Cart is empty!");
	}

	@Test
	public void Order_07_Reorder() throws Exception {
		log.info("Order_07 - Step 01: Click to My account header");
		checkoutPage.openHeaderPageByName(driver, "account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		log.info("Order_07 - Step 02: Click to Orders link at sidebar");
		myAccountPage.openSidebarPageByName(driver, "Orders");
		myOrdersPage = PageGeneratorManager.getMyOrdersPage(driver);

		log.info("Order_07 - Step 03: Verify My Orders page displayed");
		verifyTrue(myOrdersPage.isPageTitleByTextDisplayed(driver, "My account - Orders"));

		log.info("Order_07 - Step 04: Verify order number displayed with value: " + orderNumber);
		verifyTrue(myOrdersPage.isOrderNumberDisplayedAtMyOrders(orderNumber));

		log.info("Order_07 - Step 05: Click to Details button");
		myOrdersPage.clickToDetailsButtonByOrderNumber(orderNumber);

		log.info("Order_07 - Step 06: Verify Order Infomation title displayed");
		verifyTrue(myOrdersPage.isPageTitleByTextDisplayed(driver, "Order information"));

		log.info("Order_07 - Step 07: Click to Re-order button");
		myOrdersPage.clickToButtonByText(driver, "Re-order");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Order_07 - Step 08: Input to Quantity textbox with value: " + reorderQuantity);
		shoppingCartPage.inputToQuantityTextboxAtTableByProductName(driver, orderProductTitle, reorderQuantity);

		log.info("Order_07 - Step 09: Click to Update shopping cart button");
		shoppingCartPage.clickToButtonByText(driver, "Update shopping cart");

		log.info("Order_07 - Step 10: Verify Total is updated with value: " + reorderSubtotal);
		verifyEquals(shoppingCartPage.getTextValueInTableAtColumnNameAndRowIndex(driver, "Total", "1"), reorderSubtotal);

		log.info("Order_07 - Step 11: Check to Agree term checkbox");
		shoppingCartPage.checkToServiceTermAgreementCheckbox();

		log.info("Order_07 - Step 12: Click to Checkout button");
		shoppingCartPage.clickToButtonByText(driver, "Checkout");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);

		log.info("Order_07 - Step 13: Verify Checkout page displayed");
		checkoutPage.isPageTitleByTextDisplayed(driver, "Checkout");
		checkoutPage.isActiveTabByName("Billing address");

		log.info("Order_07 - Step 14: Select option 'New Address' in Billing Country dropdown");
		checkoutPage.selectDropdownByName(driver, "billing_address_id", "New Address");

		log.info("Order_07 - Step 15: Input to Firstname textbox with value: " + reorderFirstName);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_FirstName", reorderFirstName);
		
		log.info("Order_07 - Step 16: Input to Lastname textbox with value: " + reorderLastName);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_LastName", reorderLastName);
		
		log.info("Order_07 - Step 17: Input to Email textbox with value: " + reorderEmail);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_Email", reorderEmail);
		
		log.info("Order_07 - Step 18: Select option in Country dropdown with value: " + reorderCountry);
		checkoutPage.selectDropdownByName(driver, "BillingNewAddress.CountryId", reorderCountry);
		
		log.info("Order_07 - Step 19: Select option in State dropdown with value: " + reorderState);
		checkoutPage.selectDropdownByName(driver, "BillingNewAddress.StateProvinceId", reorderState);
		
		log.info("Order_07 - Step 20: Input to City textbox with value: " + reorderCity);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_City", reorderCity);
		
		log.info("Order_07 - Step 21: Input to Address 1 textbox with value: " + reorderAddress);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", reorderAddress);
		
		log.info("Order_07 - Step 22: Input to Zipcode textbox with value: " + reorderZip);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", reorderZip);
		
		log.info("Order_07 - Step 23: Input to Phone number textbox with value: " + reorderPhone);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", reorderPhone);
		
		log.info("Order_07 - Step 24: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Billing address");
		
		log.info("Order_07 - Step 25: Verify Shipping method tab active");
		checkoutPage.isActiveTabByName("Shipping method");
		
		log.info("Order_07 - Step 26: Click to shipping method radio button with value: " + reorderShipMethodWithFee);
		checkoutPage.clickToRadioButtonByLabel(driver, reorderShipMethodWithFee);
		
		log.info("Order_07 - Step 27: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Shipping method");

		log.info("Order_07 - Step 28: Verify Payment method tab active");
		checkoutPage.isActiveTabByName("Payment method");
		
		log.info("Order_07 - Step 29: Click to shipping method radio button with value: " + chequePayment);
		checkoutPage.clickToRadioButtonByLabel(driver, chequePayment);
		
		log.info("Order_07 - Step 30: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Payment method");

		log.info("Order_07 - Step 31: Verify Payment information tab active");
		checkoutPage.isActiveTabByName("Payment information");
		
		log.info("Order_07 - Step 32: Click to Continue button");
		checkoutPage.clickToContinueButtonByTabName("Payment information");

		log.info("Order_07 - Step 33: Verify Confirm order tab active");
		checkoutPage.isActiveTabByName("Confirm order");

		log.info("Order_07 - Step 34: Verify Billing Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "name"), reorderFullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "email").contains(reorderEmail));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "phone").contains(reorderPhone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "address1").contains(reorderAddress));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "city-state-zip"), reorderCity + "," + reorderZip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "country"), reorderCountry);

		log.info("Order_07 - Step 35: Verify Payment info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method").contains(chequePayment));

		log.info("Order_07 - Step 36: Verify Shipping Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "name"), reorderFullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "email").contains(reorderEmail));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "phone").contains(reorderPhone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "address1").contains(reorderAddress));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "city-state-zip"), reorderCity + "," + reorderZip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "country"), reorderCountry);

		log.info("Order_07 - Step 37: Verify Shipping method info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-method").contains(reorderShipMethod));

		log.info("Order_07 - Step 38: Verify Product info");
		verifyTrue(checkoutPage.isProductInfoDisplayedAtTable(driver, orderSKU, orderProductTitle, orderProductPrice, reorderQuantity, reorderSubtotal));
		verifyTrue(checkoutPage.isTableImageByProductNameDisplayed(driver, orderProductTitle));

		log.info("Order_07 - Step 39: Verify checkout info");
		verifyEquals(checkoutPage.getGiftWrappingCheckoutConfirmation(driver), "Gift wrapping: " + giftWrapping);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Sub-Total:"), reorderSubtotal);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Shipping:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Tax:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Total:"), reorderSubtotal);
		verifyTrue(checkoutPage.getValueAtCheckoutCartByLabel(driver, "You will earn:").contains(reorderEarnPoint));

		log.info("Order_07 - Step 40: Click to Confirm button");
		checkoutPage.clickToButtonByText(driver, "Confirm");
		checkoutPage.sleepInSecond(2);
		
		log.info("Order_07 - Step 41: Verify Thank you title displayed");
		verifyTrue(checkoutPage.isPageTitleByTextDisplayed(driver, "Thank you"));
		
		log.info("Order_07 - Step 42: Verify order success message displayed");
		verifyTrue(checkoutPage.isOrderSuccessMessageDisplayed());
		orderNumber = checkoutPage.getOrderNumber();
		
		log.info("Order_07 - Step 43: Click to Continue button");
		checkoutPage.clickToButtonByText(driver, "Continue");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Order_07 - Step 44: Click to My account header");
		homePage.openHeaderPageByName(driver, "account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		
		log.info("Order_07 - Step 45: Click to Orders link at sidebar");
		myAccountPage.openSidebarPageByName(driver, "Orders");
		myOrdersPage = PageGeneratorManager.getMyOrdersPage(driver);

		log.info("Order_07 - Step 46: Verify My Orders page displayed");
		verifyTrue(myOrdersPage.isPageTitleByTextDisplayed(driver, "My account - Orders"));

		log.info("Order_07 - Step 47: Verify order number displayed with value: " + orderNumber);
		verifyTrue(myOrdersPage.isOrderNumberDisplayedAtMyOrders(orderNumber));

		log.info("Order_07 - Step 48: Verify order total with " + reorderSubtotal);
		verifyEquals(myOrdersPage.getInfoByOrderNumberAndLabelAtMyOrders(orderNumber, "Order Total"), reorderSubtotal);

		log.info("Order_07 - Step 49: Verify order status with " + orderStatus);
		verifyEquals(myOrdersPage.getInfoByOrderNumberAndLabelAtMyOrders(orderNumber, "Order status"), orderStatus);

		log.info("Order_07 - Step 50: Get order date");
		orderDate = myOrdersPage.getOrderDateByOrderNumberAndLabelAtMyOrders(orderNumber, "Order Date");

		log.info("Order_07 - Step 51: Click to Details button");
		myOrdersPage.clickToDetailsButtonByOrderNumber(orderNumber);

		log.info("Order_07 - Step 52: Verify Order Infomation title displayed");
		verifyTrue(myOrdersPage.isPageTitleByTextDisplayed(driver, "Order information"));

		log.info("Order_07 - Step 53: Verify order number displayed with " + orderNumber);
		verifyTrue(myOrdersPage.isOrderNumberDisplayedAtOrderDetails(orderNumber));

		log.info("Order_07 - Step 54: Verify order date displayed");
		verifyEquals(myOrdersPage.getOrderDateByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-date"), orderDate);

		log.info("Order_07 - Step 55: Verify order status displayed with " + orderStatus);
		verifyTrue(myOrdersPage.getInfoByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-status").contains(orderStatus));

		log.info("Order_07 - Step 56: Verify order total displayed with " + reorderSubtotal);
		verifyTrue(myOrdersPage.getInfoByOrderNumberAndLabelAtOrderDetails(orderNumber, "order-total").contains(reorderSubtotal));

		log.info("Order_07 - Step 57: Verify Billing Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "name"), reorderFullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "email").contains(reorderEmail));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "phone").contains(reorderPhone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "address1").contains(reorderAddress));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "city-state-zip"), reorderCity + "," + reorderZip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Billing Address", "country"), reorderCountry);

		log.info("Order_07 - Step 58: Verify Payment info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method").contains(chequePayment));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Payment", "payment-method-status").contains(orderStatus));

		log.info("Order_07 - Step 59: Verify Shipping Address info");
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "name"), reorderFullName);
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "email").contains(reorderEmail));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "phone").contains(reorderPhone));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "address1").contains(reorderAddress));
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "city-state-zip"), reorderCity + "," + reorderZip);
		verifyEquals(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping Address", "country"), reorderCountry);

		log.info("Order_07 - Step 60: Verify Shipping method info");
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-method").contains(reorderShipMethod));
		verifyTrue(checkoutPage.getOrderInfoByTitleAndFieldName(driver, "Shipping", "shipping-status").contains(shipStatus));

		log.info("Order_07 - Step 61: Verify Product info");
		verifyTrue(checkoutPage.isProductInfoDisplayedAtTable(driver, orderSKU, orderProductTitle, orderProductPrice, reorderQuantity, reorderSubtotal));

		log.info("Order_07 - Step 62: Verify checkout info");
		verifyEquals(checkoutPage.getGiftWrappingCheckoutConfirmation(driver), "Gift wrapping: " + giftWrapping);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Sub-Total:"), reorderSubtotal);
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Shipping:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Tax:"), "$0.00");
		verifyEquals(checkoutPage.getValueAtCheckoutCartByLabel(driver, "Order Total:"), reorderSubtotal);
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
	DesktopsPageObject desktopsPage;
	CheckoutPageObject checkoutPage;
	MyOrdersPageObject myOrdersPage;
	NotebooksPageObject notebooksPage;
	MyAccountPageObject myAccountPage;
	ShoppingCartPageObject shoppingCartPage;
	ProductDetailPageObject productDetailPage;
	String gender, firstName, lastName, fullName, emailAddress, password, menuName, submenuName, productTitle, productSKU, processor, ram, hdd, os, softwareMS, softwareAcrobat, softwareCommander, productSpecifications, productPrice,
			productQuantity, subTotalPrice, totalPrice, editProcessor, editRam, editHdd, editOS, editProductPrice, editProductQuantity, editSubTotalPrice, editProductSpecifications, updateProductTitle, updateProductPrice,
			updateProductQuantiy, orderSubMenu, orderProductTitle, orderProductQuantity, orderProductPrice, orderProductSubTotal, orderSKU, country, state, city, address, existAddressInfo, zip, phone, shipMethod, shipMethodWithFee,
			giftWrapping, earnPoint, chequePayment, cardPayment, orderNumber, orderStatus, shipStatus, cardType, cardHolder, cardNumber, expiredMonth, expiredYear, cardCode, reorderQuantity, reorderSubtotal, reorderFirstName,
			reorderLastName, reorderFullName, reorderEmail, reorderCountry, reorderState, reorderCity, reorderAddress, reorderZip, reorderPhone, reorderShipMethod, reorderShipMethodWithFee, reorderEarnPoint;
	Float actualSubTotalPrice, expectedSubTotalPrice, editActualSubTotalPrice, editExpectedSubTotalPrice, actualTotalPrice, expectedTotalPrice;
	List<String> expectedSpecifications, editExpectedSpecifications;
	Date orderDate;
}
