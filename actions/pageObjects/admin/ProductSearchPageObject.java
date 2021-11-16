package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.ProductSearchPageUI;

public class ProductSearchPageObject extends BasePage {
	WebDriver driver;

	public ProductSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToProductNameTextbox(String productName) {
		waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX);
		sendkeyToElementByJS(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX, productName);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, ProductSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, ProductSearchPageUI.SEARCH_BUTTON);
		isJQueryAjaxLoadedSuccess(driver);
	}

	public ProductDetailPageObject clickToEditButtonByProductName(String productName) {
		waitForElementClickable(driver, ProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, ProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		return PageGeneratorManager.getProductDetailPage(driver);
	}

	public boolean isSuccessMessageDisplayed(String messageName) {
		waitForElementVisible(driver, ProductSearchPageUI.SUCCESS_MESSAGE_NAME, messageName);
		return isElementDisplayed(driver, ProductSearchPageUI.SUCCESS_MESSAGE_NAME, messageName);
	}

	public boolean isPictureImageUpdated(String productName, String imageName) {
		imageName = imageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_PICTURE_BY_PRODUCT_NAME, productName, imageName);
		return isElementDisplayed(driver, ProductSearchPageUI.PRODUCT_PICTURE_BY_PRODUCT_NAME, productName, imageName);
	}

	public boolean isPublishedIconDisplayedByValue(String isPublished) {
		waitForElementVisible(driver, ProductSearchPageUI.PUBLISHED_ICON_BY_VALUE, isPublished);
		return isElementDisplayed(driver, ProductSearchPageUI.PUBLISHED_ICON_BY_VALUE, isPublished);
	}
	
	public void inputToGoSkuTextbox(String sku) {
		waitForElementVisible(driver, ProductSearchPageUI.SKU_GO_TEXTBOX);
		sendkeyToElementByJS(driver, ProductSearchPageUI.SKU_GO_TEXTBOX, sku);
	}
	
	public ProductDetailPageObject clickToSkuGoButton() {
		waitForElementClickable(driver, ProductSearchPageUI.SKU_GO_BUTTON);
		clickToElementByJS(driver, ProductSearchPageUI.SKU_GO_BUTTON);
		isJQueryAjaxLoadedSuccess(driver);
		return PageGeneratorManager.getProductDetailPage(driver);
	}

}
