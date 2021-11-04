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

public class Admin_00_Upload_Product_Picture_Sample extends BaseTest {
	WebDriver driver;
	String productName = "Flower Girl Bracelet";
	String productAvatarImg = "travel.png";
	String productAvatarAlt = "Avatar Alt";
	String productAvatarTitle = "Avatar Title";
	String productAvatarOrder = "1";
	String adminEmail, adminPassword;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.enterToEmailTextbox(adminEmail);
		loginPage.enterToPasswordTextbox(adminPassword);
		dashboardPage = loginPage.clickToLoginButton();

		dashboardPage.openAdminSubmenuPageByName(driver, "Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);

		productSearchPage.enterToProductNameTextbox(productName);
		productSearchPage.clickToSearchButton();
		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);
	}

	@Test
	public void Admin_01_Upload_File() {
		productDetailPage.clickToExpandPanelByName("Pictures");
		productDetailPage.sleepInSecond(2);
		productDetailPage.uploadFileAtCardName(driver, "pictures", productAvatarImg);
		verifyTrue(productDetailPage.isPictureUploadedSuccessByFileName(productAvatarImg));

		productDetailPage.enterToAltTextbox(productAvatarAlt);
		productDetailPage.enterToAltTitlebox(productAvatarTitle);
		productDetailPage.clickToUpDownInDisplayedOrderTextbox("Increase");
		productDetailPage.clickToAddPictureButton();
		verifyTrue(productDetailPage.isPictureImageDisplayed(productName, productAvatarOrder, productAvatarAlt, productAvatarTitle));

		productSearchPage = productDetailPage.clickToSaveButton();
		verifyTrue(productSearchPage.isSuccessMessageDisplayed("The product has been updated successfully."));

		productSearchPage.enterToProductNameTextbox(productName);
		productSearchPage.clickToSearchButton();
		verifyTrue(productSearchPage.isPictureImageUpdated(productName, productName));

		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);
		productDetailPage.clickToExpandPanelByName("Pictures");
		productDetailPage.sleepInSecond(2);
		productDetailPage.clickToDeleteButtonAtPictureName(productAvatarTitle);
		verifyTrue(productDetailPage.isMessageDisplayedInEmptyTable(driver, "productpictures"));

		productSearchPage = productDetailPage.clickToSaveButton();
		productSearchPage.enterToProductNameTextbox(productName);
		productSearchPage.clickToSearchButton();
		verifyTrue(productSearchPage.isPictureImageUpdated(productName, "default-image"));
	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		closeBrowserAndDriver();
	}

	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailPageObject productDetailPage;
}
