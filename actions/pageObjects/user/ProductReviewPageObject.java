package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.ProductReviewPageUI;

public class ProductReviewPageObject extends BasePage {
	WebDriver driver;

	public ProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToReviewTextArea(String reviewText) {
		waitForElementVisible(driver, ProductReviewPageUI.REVIEW_TEXT_AREA);
		sendkeyToElement(driver, ProductReviewPageUI.REVIEW_TEXT_AREA, reviewText);
	}

	public void clickToRatingRatioByValue(String ratingValue) {
		waitForElementClickable(driver, ProductReviewPageUI.RATING_RATIO_BY_VALUE, ratingValue);
		clickToElement(driver, ProductReviewPageUI.RATING_RATIO_BY_VALUE, ratingValue);
	}

	public boolean isAddReviewSuccessMessageDisplayed() {
		waitForElementVisible(driver, ProductReviewPageUI.ADD_REVIEW_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, ProductReviewPageUI.ADD_REVIEW_SUCCESS_MESSAGE);
	}
}
