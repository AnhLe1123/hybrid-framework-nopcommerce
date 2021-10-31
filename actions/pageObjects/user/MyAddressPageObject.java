package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class MyAddressPageObject extends BasePage {
	WebDriver driver;

	public MyAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
