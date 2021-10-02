package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToMaleGenderCheckbox() {
		waitForElementClickable(driver, RegisterPageUI.MALE_GENDER_CHECKBOX);
		clickToElement(driver, RegisterPageUI.MALE_GENDER_CHECKBOX);
	}

	public void inputToFirstNameTextbox(String value) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, value);
	}

	public void inputToLastNameTextbox(String value) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, value);
	}
	
	public void inputToEmailTextbox(String value) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, value);
	}

	public void selectDayOfBirthDropdown(String itemText) {
		waitForElementClickable(driver, RegisterPageUI.BIRTH_DAY_DROPDOWN);
		selectItemInDropdownByText(driver, RegisterPageUI.BIRTH_DAY_DROPDOWN, itemText);
	}

	public void selectMonthOfBirthDropdown(String itemText) {
		waitForElementClickable(driver, RegisterPageUI.BIRTH_MONTH_DROPDOWN);
		selectItemInDropdownByText(driver, RegisterPageUI.BIRTH_MONTH_DROPDOWN, itemText);
	}

	public void selectYearOfBirthDropdown(String itemText) {
		waitForElementClickable(driver, RegisterPageUI.BIRTH_YEAR_DROPDOWN);
		selectItemInDropdownByText(driver, RegisterPageUI.BIRTH_YEAR_DROPDOWN, itemText);
	}

	public void inputToCompanyNameTextbox(String value) {
		waitForElementVisible(driver, RegisterPageUI.COMPANY_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.COMPANY_TEXTBOX, value);
	}

	public void inputToPasswordTextbox(String value) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, value);
	}

	public void inputToConfirmPasswordTextbox(String value) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, value);
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.SUCCESS_MESSAGE);
	}

	public HomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}
}
