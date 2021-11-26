package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.AdminBasePageUI;
import pageUIs.admin.CustomerSearchPageUI;

public class CustomerSearchPageObject extends BasePage {
	WebDriver driver;

	public CustomerSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToSearchButton() {
		waitForElementClickable(driver, CustomerSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, CustomerSearchPageUI.SEARCH_BUTTON);
		isJQueryAjaxLoadedSuccess(driver);
	}
	
	public boolean isCustomerInfoDisplayedAtTable(String cusFullName, String cusRole, String cusCompany, String isCusActive) {
		waitForElementVisible(driver, CustomerSearchPageUI.CUSTOMER_INFO_AT_TABLE, cusFullName, cusRole, cusCompany, isCusActive);
		return isElementDisplayed(driver, CustomerSearchPageUI.CUSTOMER_INFO_AT_TABLE, cusFullName, cusRole, cusCompany, isCusActive);
	}
	
	public boolean isActiveIconDisplayedByCustomerNameAndValue(String customerName, String columnName, String isActive) {
		int rowIndex = getElementSize(driver, AdminBasePageUI.DYNAMIC_TABLE_ROW_BY_KEYWORD, customerName) + 1;
		int columnIndex = getElementSize(driver, AdminBasePageUI.DYNAMIC_TABLE_HEADER_BY_NAME, columnName) + 1;
		waitForElementVisible(driver, CustomerSearchPageUI.IS_ACTIVE_ICON_BY_ROW_COLUMN_INDEX, String.valueOf(rowIndex), String.valueOf(columnIndex), isActive);
		return isElementDisplayed(driver, CustomerSearchPageUI.IS_ACTIVE_ICON_BY_ROW_COLUMN_INDEX, String.valueOf(rowIndex), String.valueOf(columnIndex), isActive);
	}

	public CustomerDetailPageObject clickToEditButtonByCustomerName(String cusFullname) {
		waitForElementClickable(driver, CustomerSearchPageUI.EDIT_BUTTON_BY_CUSTOMER_NAME, cusFullname);
		clickToElement(driver, CustomerSearchPageUI.EDIT_BUTTON_BY_CUSTOMER_NAME, cusFullname);
		isJQueryAjaxLoadedSuccess(driver);
		return PageGeneratorManager.getCustomerDetailPage(driver);
	}

}
