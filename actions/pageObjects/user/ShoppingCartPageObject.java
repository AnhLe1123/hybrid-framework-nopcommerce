package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.ShoppingCartPageUI;

public class ShoppingCartPageObject extends BasePage {
	WebDriver driver;

	public ShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToEditProductLink() {
		waitForElementClickable(driver, ShoppingCartPageUI.EDIT_PRODUCT_LINK);
		clickToElement(driver, ShoppingCartPageUI.EDIT_PRODUCT_LINK);
	}
	
	public void clickToEstimateShippingButton() {
		waitForElementClickable(driver, ShoppingCartPageUI.ESTIMATE_SHIPPING_BUTTON);
		clickToElement(driver, ShoppingCartPageUI.ESTIMATE_SHIPPING_BUTTON);
	}
	
	public void selectOptionInGiftWrappingDropdown(String itemText) {
		waitForElementVisible(driver, ShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN);
		selectItemInDropdownByText(driver, ShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN, itemText);
	}
	
	public void checkToServiceTermAgreementCheckbox() {
		waitForElementClickable(driver, ShoppingCartPageUI.TERM_OF_SERVICE_CHECKBOX);
		checkToCheckboxOrRadio(driver, ShoppingCartPageUI.TERM_OF_SERVICE_CHECKBOX);
	}
}
