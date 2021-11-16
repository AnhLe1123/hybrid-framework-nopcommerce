package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.CustomerDetailPageUI;

public class CustomerDetailPageObject extends BasePage {
	WebDriver driver;

	public CustomerDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isItemSelectedAtCustomerRolesDropdownByLabel(String textOption) {
		waitForElementVisible(driver, CustomerDetailPageUI.CUSTOMER_ROLE_SELECTED_ITEM_BY_TEXT, textOption);
		return isElementDisplayed(driver, CustomerDetailPageUI.CUSTOMER_ROLE_SELECTED_ITEM_BY_TEXT, textOption);
	}

	public CustomerSearchPageObject checkToBackToCustomerListLink() {
		waitForElementClickable(driver, CustomerDetailPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		clickToElement(driver, CustomerDetailPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		return PageGeneratorManager.getCustomerSearchPage(driver);
	}

	public CustomerSearchPageObject clickToSaveButton() {
		waitForElementClickable(driver, CustomerDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, CustomerDetailPageUI.SAVE_BUTTON);
		return PageGeneratorManager.getCustomerSearchPage(driver);
	}
	
	public CustomerAddressPageObject clickToAddNewAddressButton() {
		waitForElementClickable(driver, CustomerDetailPageUI.ADD_NEW_ADDRESS_BUTTON);
		clickToElement(driver, CustomerDetailPageUI.ADD_NEW_ADDRESS_BUTTON);
		return PageGeneratorManager.getCustomerAddressPage(driver);
	}

	public boolean isAddressInfoDisplayed(String firstname, String lastname, String email, String phone, String fax, String company, String address, String cityStateZip, String country) {
		waitForElementVisible(driver, CustomerDetailPageUI.TABLE_ADDRESS_INFO, firstname, lastname, email, phone, fax, company, address, cityStateZip, country);
		return isElementDisplayed(driver, CustomerDetailPageUI.TABLE_ADDRESS_INFO, firstname, lastname, email, phone, fax, company, address, cityStateZip, country);
	}
	
	public CustomerAddressPageObject clickToEditAddressButtonByCustomerEmail(String email) {
		waitForElementClickable(driver, CustomerDetailPageUI.EDIT_BUTTON_BY_CUSTOMER_EMAIL, email);
		clickToElement(driver, CustomerDetailPageUI.EDIT_BUTTON_BY_CUSTOMER_EMAIL, email);
		return PageGeneratorManager.getCustomerAddressPage(driver);
	}

	public void clickToDeleteAddressButtonByCustomerEmail(String email) {
		waitForElementClickable(driver, CustomerDetailPageUI.DELETE_BUTTON_BY_CUSTOMER_EMAIL, email);
		clickToElement(driver, CustomerDetailPageUI.DELETE_BUTTON_BY_CUSTOMER_EMAIL, email);
		sleepInSecond(1);
		acceptAlert(driver);
	}
}
