package commons;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected static BasePage getBasePage() {
		return new BasePage();
	}
	
	protected void openUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	protected void getPageTitle(WebDriver driver) {
		driver.getTitle();
	}
	
	protected void getPageUrl(WebDriver driver) {
		driver.getCurrentUrl();
	}
	
	protected void getPageSource(WebDriver driver) {
		driver.getPageSource();
	}
	
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	protected Alert waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, timeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	protected void acceptAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.accept();
	}
	
	protected void cancelAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.dismiss();
	}
	
	protected void sendKeyToAlert(WebDriver driver, String value) {
		alert = waitAlertPresence(driver);
		alert.sendKeys(value);
	}
	
	protected String getTextAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		return alert.getText();
	}
	
	protected void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String id : allWindows) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	protected void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String id : allWindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
			if (windowTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	
	protected void closeAllWindowsWithoutParent(WebDriver driver, String ParentID) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String id : allWindows) {
			if (!id.equals(ParentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(ParentID);
	}
	
	protected void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Alert alert;
	private long timeout = 30;
	private WebDriverWait explicitWait;
}
