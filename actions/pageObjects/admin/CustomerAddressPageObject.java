package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.CustomerAddressPageUI;

public class CustomerAddressPageObject extends BasePage {
	WebDriver driver;

	public CustomerAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToSaveButton() {
		waitForElementClickable(driver, CustomerAddressPageUI.SAVE_BUTTON);
		clickToElement(driver, CustomerAddressPageUI.SAVE_BUTTON);
	}

	public CustomerDetailPageObject clickToBackToCustomerDetailsLink() {
		waitForElementClickable(driver, CustomerAddressPageUI.BACK_TO_CUSTOMER_DETAILS_LINK);
		clickToElement(driver, CustomerAddressPageUI.BACK_TO_CUSTOMER_DETAILS_LINK);
		isJQueryAjaxLoadedSuccess(driver);
		return PageGeneratorManager.getCustomerDetailPage(driver);
	}
}
