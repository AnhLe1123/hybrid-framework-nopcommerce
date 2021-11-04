package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.HomePageObject;
import pageObjects.user.NotebooksPageObject;
import pageObjects.user.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_05_Sort_Display_Paging extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	NotebooksPageObject notebooksPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String appUrl) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		log.info("Pre-condition - Step 02: Verify HomePage displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
		log.info("Pre-condition - Step 03: Open submenu Notebooks");
		homePage.openUserSubmenuPageByName(driver, "Computers", "Notebooks");
		notebooksPage = PageGeneratorManager.getNotebooksPage(driver);
	}

	@Test
	public void Sort_01_Sort_With_Name_A_To_Z() {
		log.info("Sort_01 - Step 01: Select 'Name: A to Z' in Sort by dropdown");
		notebooksPage.selectDropdownByName(driver, "products-orderby", "Name: A to Z");
		
		log.info("Sort_01 - Step 02: Verify products name sorted A to Z");
		verifyTrue(notebooksPage.isProductNameSortAscending(driver));
	}

	@Test
	public void Sort_02_Sort_With_Name_Z_To_A() {
		log.info("Sort_02 - Step 01: Select 'Name: Z to A' in Sort by dropdown");
		notebooksPage.selectDropdownByName(driver, "products-orderby", "Name: Z to A");
		
		log.info("Sort_02 - Step 02: Verify products name sorted Z to A");
		verifyTrue(notebooksPage.isProductNameSortDescending(driver));
	}
	
	@Test
	public void Sort_03_Sort_With_Price_Low_To_High() {
		log.info("Sort_03 - Step 01: Select 'Price: Low to High' in Sort by dropdown");
		notebooksPage.selectDropdownByName(driver, "products-orderby", "Price: Low to High");
		
		log.info("Sort_03 - Step 02: Verify products sorted by Low to High price");
		verifyTrue(notebooksPage.isProductPriceSortAscending(driver));
	}
	
	@Test
	public void Sort_04_Sort_With_Price_High_To_Low() {
		log.info("Sort_04 - Step 01: Select 'Price: High to Low' in Sort by dropdown");
		notebooksPage.selectDropdownByName(driver, "products-orderby", "Price: High to Low");
		
		log.info("Sort_04 - Step 02: Verify products sorted by High to Low price");
		verifyTrue(notebooksPage.isProductPriceSortDescending(driver));
	}
	
	@Test
	public void Display_01_Three_Products_Per_Page() {
		log.info("Display_01 - Step 01: Select '3' in dropdown display per page");
		notebooksPage.selectDropdownByName(driver, "products-pagesize", "3");
		
		log.info("Display_01 - Step 02: Verify number of products displayed <= 3");
		verifyTrue(notebooksPage.isNumberOfProductsDisplayedLessOrEqual(driver, 3));
		
		log.info("Display_01 - Step 04: Click to Paging 1");
		notebooksPage.clickToPageNumber("1");
		
		log.info("Display_01 - Step 05: Verify Previous paging icon displayed");
		verifyTrue(notebooksPage.isPagingIconByCurrentPage("1", "next"));
		
		log.info("Display_01 - Step 06: Click to Paging 1");
		notebooksPage.clickToPageNumber("2");
		
		log.info("Display_01 - Step 07: Verify Next paging icon displayed");
		verifyTrue(notebooksPage.isPagingIconByCurrentPage("2", "previous"));
	}
	
	@Test
	public void Display_02_Six_Products_Per_Page() {
		log.info("Display_02 - Step 01: Select '6' in dropdown display per page");
		notebooksPage.selectDropdownByName(driver, "products-pagesize", "6");
		
		log.info("Display_02 - Step 02: Verify number of products displayed <= 6");
		verifyTrue(notebooksPage.isNumberOfProductsDisplayedLessOrEqual(driver, 6));
		
		log.info("Display_02 - Step 03: Verify Paging not displayed");
		verifyTrue(notebooksPage.isPagingUndisplayed());
	}
	
	@Test
	public void Display_03_Nine_Products_Per_Page() {
		log.info("Display_03 - Step 01: Select '9' in dropdown display per page");
		notebooksPage.selectDropdownByName(driver, "products-pagesize", "9");
		
		log.info("Display_03 - Step 02: Verify number of products displayed <= 9");
		verifyTrue(notebooksPage.isNumberOfProductsDisplayedLessOrEqual(driver, 9));
		
		log.info("Display_03 - Step 03: Verify Paging not displayed");
		verifyTrue(notebooksPage.isPagingUndisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		log.info("Post-condition - Close browser and driver");
		closeBrowserAndDriver();
	}
}