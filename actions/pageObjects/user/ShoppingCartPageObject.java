package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class ShoppingCartPageObject extends BasePage {
	WebDriver driver;

	public ShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
