package com.nopcommerce.admin;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import factoryEnvironment.EnvConfig;
import pageObjects.admin.DashboardPageObject;
import pageObjects.admin.LoginPageObject;
import pageObjects.admin.PageGeneratorManager;
import pageObjects.admin.ProductDetailPageObject;
import pageObjects.admin.ProductSearchPageObject;

public class Admin_00_Upload_Product_Picture_Sample extends BaseTest {
	@Parameters({ "envName", "serverName", "browser", "ipAddress", "port", "osName", "osVersion" })
	@BeforeClass
	public void initBrowser(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber,
			@Optional("Mac OS X") String osName, @Optional("10.16") String osVersion) {
		
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		productName = "Flower Girl Bracelet";
		productAvatarImg = "travel.png";
		productAvatarAlt = "Avatar Alt";
		productAvatarTitle = "Avatar Title";
		productAvatarOrder = "1";
		
		ConfigFactory.setProperty("env", serverName);
		environment = ConfigFactory.create(EnvConfig.class);
		driver = getBrowserDriver(envName, environment.adminUrl(), browserName, ipAddress, portNumber, osName, osVersion);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		showBrowserConsoleLogs(driver);

		loginPage.enterToEmailTextbox(adminEmail);
		loginPage.enterToPasswordTextbox(adminPassword);
		dashboardPage = loginPage.clickToLoginButton();

		dashboardPage.openAdminSubmenuPageByName(driver, "Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);
		showBrowserConsoleLogs(driver);

		productSearchPage.inputToProductNameTextbox(productName);
		productSearchPage.clickToSearchButton();
		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);
	}

	@Test
	public void Admin_01_Upload_File() {
		productDetailPage.clickToExpandPanelByName(driver, "Pictures");
		productDetailPage.sleepInSecond(2);
		productDetailPage.uploadFileAtCardName(driver, "pictures", productAvatarImg);
		verifyTrue(productDetailPage.isPictureUploadedSuccessByFileName(productAvatarImg));

		productDetailPage.inputToAltTextbox(productAvatarAlt);
		productDetailPage.inputToAltTitlebox(productAvatarTitle);
		productDetailPage.clickToUpDownInDisplayedOrderTextbox("Increase");
		productDetailPage.clickToAddPictureButton();
		verifyTrue(productDetailPage.isPictureImageDisplayed(productName, productAvatarOrder, productAvatarAlt, productAvatarTitle));

		productSearchPage = productDetailPage.clickToSaveButton();
		verifyTrue(productSearchPage.isSuccessMessageDisplayed("The product has been updated successfully."));

		productSearchPage.inputToProductNameTextbox(productName);
		productSearchPage.clickToSearchButton();
		verifyTrue(productSearchPage.isPictureImageUpdated(productName, productName));

		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);
		productDetailPage.clickToExpandPanelByName(driver, "Pictures");
		productDetailPage.sleepInSecond(2);
		productDetailPage.clickToDeleteButtonAtPictureName(productAvatarTitle);
		verifyTrue(productDetailPage.isMessageDisplayedInEmptyTable(driver, "productpictures"));

		productSearchPage = productDetailPage.clickToSaveButton();
		productSearchPage.inputToProductNameTextbox(productName);
		productSearchPage.clickToSearchButton();
		verifyTrue(productSearchPage.isPictureImageUpdated(productName, "default-image"));
	}

	@Parameters("envName")
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(@Optional("local") String envName) {
		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver(envName);
	}

	WebDriver driver;
	EnvConfig environment;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailPageObject productDetailPage;
	String adminEmail, adminPassword, productName, productAvatarImg, productAvatarAlt, productAvatarTitle, productAvatarOrder;
}
