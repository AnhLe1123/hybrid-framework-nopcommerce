package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage {
	WebDriver driver;

	public ProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToAddReviewLink() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_REVIEW_LINK);
		clickToElement(driver, ProductDetailPageUI.ADD_REVIEW_LINK);
	}
}
