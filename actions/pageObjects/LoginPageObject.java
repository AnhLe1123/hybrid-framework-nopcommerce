package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextbox(String value) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, value);
	}

	public void inputToPasswordTextbox(String value) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, value);
	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}

}
