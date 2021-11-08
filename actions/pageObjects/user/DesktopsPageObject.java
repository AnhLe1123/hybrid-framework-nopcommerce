package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DesktopsPageObject extends BasePage {
	WebDriver driver;

	public DesktopsPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
