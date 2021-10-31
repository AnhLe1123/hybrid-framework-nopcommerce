package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.MyPasswordPageUI;

public class MyPasswordPageObject extends BasePage {
	WebDriver driver;

	public MyPasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, MyPasswordPageUI.CHANGE_PASSWORD_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, MyPasswordPageUI.CHANGE_PASSWORD_SUCCESS_MESSAGE);
	}
	
	public void clickToCloseButtonAtNotificationBar() {
		waitForElementClickable(driver, MyPasswordPageUI.CLOSE_BUTTON_AT_NOTIFICATION_BAR);
		clickToElement(driver, MyPasswordPageUI.CLOSE_BUTTON_AT_NOTIFICATION_BAR);
	}
}
