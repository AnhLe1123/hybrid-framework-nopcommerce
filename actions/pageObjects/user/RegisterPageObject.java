package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.SUCCESS_MESSAGE);
	}

	public boolean isErrorMessageForExistingEmailDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_EXISTING_EMAIL);
		return isElementDisplayed(driver, RegisterPageUI.ERROR_MESSAGE_EXISTING_EMAIL);
	}

	public Object getErrorMessageByFieldName(String fieldName) {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_BY_FIELD_NAME, fieldName);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE_BY_FIELD_NAME, fieldName);
	}
}
