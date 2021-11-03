package pageUIs.user;

public class UserBasePageUI {

	//Dynamic Locator
	public static final String DYNAMIC_LINK_FOOTER = "//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_LINK_HEADER = "//a[@class='ico-%s']";
	public static final String DYNAMIC_LINK_SIDEBAR = "//div[@class='side-2']//a[text()='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_TEXT = "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_BY_TEXT = "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[text()='%s']";
	public static final String DYNAMIC_PAGE_TITLE_BY_TEXT = "//div[@class='page-title']//h1[text()='%s']";
	public static final String DYNAMIC_INFO_BY_FIELD_NAME = "//ul[@class='info']//li[@class='%s' and contains(string(),'%s')]";
	public static final String DYNAMIC_PRODUCT_TITLE_BY_TEXT = "//h2[@class='product-title']//a[text()='%s']";
	public static final String DYNAMIC_REVIEW_PRODUCT_BY_NAME = "//*[contains(string(), 'Product review')]//a[contains(string(), '%s')]";
	public static final String DYNAMIC_REVIEW_TITLE_CONTENT_BY_PRODUCT_NAME = "//div[@class='%s' and contains(string(), '%s')]";
	
	//Hard Locator
	public static final String HOME_PAGE_LOGO = "//img[@alt='nopCommerce demo store']";
	public static final String PRODUCTS_TITLE = "//h2[@class='product-title']//a";
}
