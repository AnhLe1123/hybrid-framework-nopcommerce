package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.ProductSearchPageUI;

public class ProductSearchPageObject extends BasePage {
	WebDriver driver;

	public ProductSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToProductNameTextbox(String productName) {
		waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX);
		sendkeyToElementByJS(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX, productName);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, ProductSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, ProductSearchPageUI.SEARCH_BUTTON);
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

}
