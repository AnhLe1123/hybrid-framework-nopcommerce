package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.CreateCustomerPageUI;

public class CreateCustomerPageObject extends BasePage {
	WebDriver driver;

	public CreateCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public CustomerDetailPageObject clickToSaveAndContinueEditButton() {
		waitForElementClickable(driver, CreateCustomerPageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
		clickToElement(driver, CreateCustomerPageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
		return PageGeneratorManager.getCustomerDetailPage(driver);
	}

}
