package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage {
	WebDriver driver;

	public ProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPictureUploadedSuccessByFileName(String fileName) {
		fileName = fileName.split("\\.")[0];
		waitForElementVisible(driver, ProductDetailPageUI.PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME, fileName);
		return isElementDisplayed(driver, ProductDetailPageUI.PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME, fileName);
	}

	public void inputToAltTextbox(String altProductName) {
		waitForElementVisible(driver, ProductDetailPageUI.ALT_TEXTBOX_ADD_NEW);
		sendkeyToElement(driver, ProductDetailPageUI.ALT_TEXTBOX_ADD_NEW, altProductName);
	}

	public void inputToAltTitlebox(String titleProductName) {
		waitForElementVisible(driver, ProductDetailPageUI.TITLE_TEXTBOX_ADD_NEW);
		sendkeyToElement(driver, ProductDetailPageUI.TITLE_TEXTBOX_ADD_NEW, titleProductName);

	}

	public void clickToUpDownInDisplayedOrderTextbox(String selectValue) {
		waitForElementClickable(driver, ProductDetailPageUI.DISPLAY_ORDER_TEXTBOX_UP_DOWN, selectValue);
		clickToElement(driver, ProductDetailPageUI.DISPLAY_ORDER_TEXTBOX_UP_DOWN, selectValue);
	}

	public void clickToAddPictureButton() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_NEW_PRODUCT_PICTURE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_NEW_PRODUCT_PICTURE_BUTTON);
	}

	public boolean isPictureImageDisplayed(String imageName, String displayOrder, String imageAlt, String imageTitle) {
		imageName = imageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, ProductDetailPageUI.PICTURE_TABLE_BY_NAME_ORDER_ALT_TITLE, imageName, displayOrder, imageAlt, imageTitle);
		return isElementDisplayed(driver, ProductDetailPageUI.PICTURE_TABLE_BY_NAME_ORDER_ALT_TITLE, imageName, displayOrder, imageAlt, imageTitle);
	}

	public ProductSearchPageObject clickToSaveButton() {
		waitForElementClickable(driver, ProductDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.SAVE_BUTTON);
		return PageGeneratorManager.getProductSearchPage(driver);
	}

	public void clickToDeleteButtonAtPictureName(String imageTitle) {
		waitForElementVisible(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, imageTitle);
		clickToElement(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, imageTitle);
		sleepInSecond(2);
		acceptAlert(driver);
	}
	
	public boolean isTitleByProductNameDisplayed(String productName) {
		waitForElementVisible(driver, ProductDetailPageUI.PAGE_TITLE_BY_PRODUCT_NAME, productName);
		return isElementDisplayed(driver, ProductDetailPageUI.PAGE_TITLE_BY_PRODUCT_NAME, productName);
	}
	
	public boolean isCategoryTagByLabelDisplayed(String categoryText) {
		waitForElementVisible(driver, ProductDetailPageUI.CATEGORY_TAG_BY_TEXT, categoryText);
		return isElementDisplayed(driver, ProductDetailPageUI.CATEGORY_TAG_BY_TEXT, categoryText);
	}
	
	public String getValueInDropdownByID(String dropdownID) {
		waitForElementVisible(driver, ProductDetailPageUI.DROPDOWN_VALUE_BY_DROPDOWN_ID, dropdownID);
		return getElementAttribute(driver, ProductDetailPageUI.DROPDOWN_VALUE_BY_DROPDOWN_ID, "aria-valuenow", dropdownID);
	}
}
