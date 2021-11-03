package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.SearchPageUI;

public class SearchPageObject extends BasePage {
	WebDriver driver;

	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isErrorMessageDisplayed(String textMessage) {
		waitForElementVisible(driver, SearchPageUI.SEARCH_ERROR_MESSAGE, textMessage);
		return isElementDisplayed(driver, SearchPageUI.SEARCH_ERROR_MESSAGE, textMessage);
	}
	
	public void clickToFormSearchButton() {
		waitForElementClickable(driver, SearchPageUI.FORM_SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.FORM_SEARCH_BUTTON);
	}

}
