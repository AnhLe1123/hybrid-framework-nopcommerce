package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class WishlistPageObject extends BasePage {
	WebDriver driver;

	public WishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
