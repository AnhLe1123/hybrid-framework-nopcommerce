package pageUIs.user;

public class MyOrdersPageUI {
	public static final String ORDER_TITLE_BY_NUMBER = "//div[@class='section order-item']//strong[text()='Order Number: %s']";
	public static final String ORDER_INFO_BY_ORDER_NUMBER_AND_LABEL_AT_MY_ORDERS = "//strong[text()='Order Number: %s']/parent::div/following-sibling::ul//li[contains(string(),'%s')]//span";
	public static final String DETAILS_BUTTON_BY_ORDER_NUMBER = "//strong[text()='Order Number: %s']//parent::div//following-sibling::div//button[text()='Details']";
	public static final String ORDER_INFO_BY_ORDER_NUMBER_AND_LABEL_AT_ORDER_DETAILS = "//strong[text()='Order #%s']/parent::div/following-sibling::ul//li[@class='%s']";
	public static final String ORDER_NUMBER_AT_ORDER_DETAILS = "//div[@class='order-number']//strong[text()='Order #%s']";
}
