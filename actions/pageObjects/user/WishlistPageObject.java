package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.WishlistPageUI;

public class WishlistPageObject extends BasePage {
	WebDriver driver;

	public WishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToShareWishlistLink() {
		waitForElementClickable(driver, WishlistPageUI.SHARE_WISHLIST_LINK);
		clickToElement(driver, WishlistPageUI.SHARE_WISHLIST_LINK);
	}
	
	public void checkToAddToCartCheckbox() {
		waitForElementClickable(driver, WishlistPageUI.ADD_TO_CART_CHECKBOX);
		checkToCheckboxOrRadio(driver, WishlistPageUI.ADD_TO_CART_CHECKBOX);
	}
}
