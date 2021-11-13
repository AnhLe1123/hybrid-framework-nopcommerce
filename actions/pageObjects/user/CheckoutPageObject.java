package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.CheckoutPageUI;

public class CheckoutPageObject extends BasePage {
	WebDriver driver;

	public CheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isActiveTabByName(String tabName) {
		waitForElementInvisible(driver, CheckoutPageUI.LOADING_NEXT_STEP);
		waitForElementVisible(driver, CheckoutPageUI.ACTIVE_TAB_BY_NAME, tabName);
		return isElementDisplayed(driver, CheckoutPageUI.ACTIVE_TAB_BY_NAME, tabName);
	}
	
	public void clickToContinueButtonByTabName(String tabName) {
		waitForElementClickable(driver, CheckoutPageUI.CONTINUE_BUTTON_BY_TAB_NAME, tabName);
		clickToElement(driver, CheckoutPageUI.CONTINUE_BUTTON_BY_TAB_NAME, tabName);
	}
	
	public String getPaymentInfo() {
		waitForElementVisible(driver, CheckoutPageUI.PAYMENT_INFO_SECTION);
		return getElementText(driver, CheckoutPageUI.PAYMENT_INFO_SECTION);
	}
	
	public boolean isOrderSuccessMessageDisplayed() {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, CheckoutPageUI.ORDER_SUCCESS_MESSAGE);
	}
	
	public String getOrderNumber() {
		waitForElementVisible(driver, CheckoutPageUI.ORDER_NUMBER);
		String orderNumberText = getElementText(driver, CheckoutPageUI.ORDER_NUMBER);
		String orderNumberTexts[] = orderNumberText.split(" ");
		return orderNumberTexts[2];
	}
}
