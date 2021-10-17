package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public Object getEmailErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public boolean isUnccessfulErrorMessageByTextDisplayed(String textMessage) {
		waitForElementVisible(driver, LoginPageUI.DYNAMIC_UNCCESSFUL_ERROR_MESSAGE_BY_TEXT, textMessage);
		return isElementDisplayed(driver, LoginPageUI.DYNAMIC_UNCCESSFUL_ERROR_MESSAGE_BY_TEXT, textMessage);
	}
}
