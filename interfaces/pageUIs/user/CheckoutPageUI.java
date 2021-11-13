package pageUIs.user;

public class CheckoutPageUI {
	public static final String ACTIVE_TAB_BY_NAME = "//h2[text()='%s']//ancestor::li[@class='tab-section allow active']";
	public static final String CONTINUE_BUTTON_BY_TAB_NAME = "//h2[text()='%s']/ancestor::li//button[text()='Continue']";
	public static final String LOADING_NEXT_STEP = "//span[text()='Loading next step...']";
	public static final String PAYMENT_INFO_SECTION = "//div[@class='section payment-info']";
	public static final String ORDER_SUCCESS_MESSAGE = "//div[@class='section order-completed']//strong[text()='Your order has been successfully processed!']";
	public static final String ORDER_NUMBER = "//div[@class='order-number']//strong";
}
