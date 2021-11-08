package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.CompareProductPageUI;

public class CompareProductPageObject extends BasePage {
	WebDriver driver;

	public CompareProductPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTextValueAtRowNameAndColumnIndex(String productName, String rowLabel) {
		int columnIndex = getElementSize(driver, CompareProductPageUI.DYNAMIC_TABLE_COLUMN_BY_PRODUCT, productName) + 1;
		waitForElementVisible(driver, CompareProductPageUI.DYNAMIC_TABLE_VALUE_BY_COLUMN_INDEX_AND_ROW_LABEL, rowLabel, String.valueOf(columnIndex));
		return getElementText(driver, CompareProductPageUI.DYNAMIC_TABLE_VALUE_BY_COLUMN_INDEX_AND_ROW_LABEL, rowLabel, String.valueOf(columnIndex));
	}
	
	public void clickToClearListButton() {
		waitForElementClickable(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
	}
}
