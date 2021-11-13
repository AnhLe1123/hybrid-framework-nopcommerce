package pageObjects.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.MyOrdersPageUI;

public class MyOrdersPageObject extends BasePage {
	WebDriver driver;

	public MyOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isOrderNumberDisplayedAtMyOrders(String orderNumber) {
		waitForElementVisible(driver, MyOrdersPageUI.ORDER_TITLE_BY_NUMBER, orderNumber);
		return isElementDisplayed(driver, MyOrdersPageUI.ORDER_TITLE_BY_NUMBER, orderNumber);
	}
	
	public String getInfoByOrderNumberAndLabelAtMyOrders(String orderNumber, String labelName) {
		waitForElementVisible(driver, MyOrdersPageUI.ORDER_INFO_BY_ORDER_NUMBER_AND_LABEL_AT_MY_ORDERS, orderNumber, labelName);
		return getElementText(driver, MyOrdersPageUI.ORDER_INFO_BY_ORDER_NUMBER_AND_LABEL_AT_MY_ORDERS, orderNumber, labelName);
	}
	
	public void clickToDetailsButtonByOrderNumber(String orderNumber) {
		waitForElementClickable(driver, MyOrdersPageUI.DETAILS_BUTTON_BY_ORDER_NUMBER, orderNumber);
		clickToElement(driver, MyOrdersPageUI.DETAILS_BUTTON_BY_ORDER_NUMBER, orderNumber);
	}
	
	public Date getOrderDateByOrderNumberAndLabelAtMyOrders(String orderNumber, String labelName) throws Exception {
		String dates[] = getInfoByOrderNumberAndLabelAtMyOrders(orderNumber, labelName).split(" ");
		String date = dates[0];
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.parse(date);
	}
	
	public boolean isOrderNumberDisplayedAtOrderDetails(String orderNumber) {
		waitForElementVisible(driver, MyOrdersPageUI.ORDER_NUMBER_AT_ORDER_DETAILS, orderNumber);
		return isElementDisplayed(driver, MyOrdersPageUI.ORDER_NUMBER_AT_ORDER_DETAILS, orderNumber);
	}
	
	public String getInfoByOrderNumberAndLabelAtOrderDetails(String orderNumber, String labelName) {
		waitForElementVisible(driver, MyOrdersPageUI.ORDER_INFO_BY_ORDER_NUMBER_AND_LABEL_AT_ORDER_DETAILS, orderNumber, labelName);
		return getElementText(driver, MyOrdersPageUI.ORDER_INFO_BY_ORDER_NUMBER_AND_LABEL_AT_ORDER_DETAILS, orderNumber, labelName);
	}
	
	public Date getOrderDateByOrderNumberAndLabelAtOrderDetails(String orderNumber, String labelName) throws Exception {
		String dates[] = getInfoByOrderNumberAndLabelAtOrderDetails(orderNumber, labelName).split(", ");
		String date = dates[1] + ", " + dates[2];
		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		return dateFormat.parse(date);
	}
}
