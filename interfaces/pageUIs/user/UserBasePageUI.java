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
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[contains(text(), '%s')]";
	public static final String DYNAMIC_PAGE_TITLE_BY_TEXT = "//div[@class='page-title']//h1[text()='%s']";
	public static final String DYNAMIC_INFO_BY_FIELD_NAME = "//ul[@class='info']//li[@class='%s' and contains(string(),'%s')]";
	public static final String DYNAMIC_PRODUCT_TITLE_BY_TEXT = "//h2[@class='product-title']//a[text()='%s']";
	public static final String DYNAMIC_REVIEW_PRODUCT_BY_NAME = "//*[contains(string(), 'Product review')]//a[contains(string(), '%s')]";
	public static final String DYNAMIC_REVIEW_TITLE_CONTENT_BY_PRODUCT_NAME = "//div[@class='%s' and contains(string(), '%s')]";
	public static final String DYNAMIC_MENU_BY_NAME = "//ul[@class='top-menu notmobile']//a[contains(text(), '%s')]";
	public static final String DYNAMIC_SUBMENU_BY_NAME = "//ul[@class='top-menu notmobile']//a[contains(text(), '%s')]//following-sibling::ul//a[contains(text(), '%s')]";
	public static final String DYNAMIC_ADD_PRODUCT_SUCCESS_MESSAGE_BY_PAGENAME = "//p[contains(text(), 'The product has been added to your')]//a[text()='%s']";
	public static final String DYNAMIC_QUANTITY_BY_HEADER_LABEL = "//span[text()='%s']//following-sibling::span[text()='(%s)']";
	public static final String DYNAMIC_TABLE_HEADER_BY_NAME = "//thead//th[contains(string(),'%s')]/preceding-sibling::th";
	public static final String DYNAMIC_TABLE_CELL_BY_ROW_INDEX_AND_COLUMN_INDEX = "//tbody/tr[%s]/td[%s]";
	public static final String DYNAMIC_TABLE_IMG_BY_PRODUCT_NAME = "//tbody//*[@class='product-picture']//img[contains(@title, '%s')]";
	public static final String DYNAMIC_IMG_BY_PRODUCT_NAME_AT_MINICART = "//div[@class='mini-shopping-cart']//div[@class='picture']//img[contains(@title, '%s')]";
	public static final String DYNAMIC_TABLE_QTY_INPUT_BY_PRODUCT_NAME = "//td[@class='product' and contains(string(), '%s')]/following-sibling::td//input[@value='%s']";
	public static final String DYNAMIC_NO_DATA_MESSAGE_BY_TEXT = "//div[@class='no-data' and contains(string(), '%s')]";
	public static final String DYNAMIC_TABLE_REMOVE_ICON_BY_PRODUCT_NAME = "//td[@class='product' and contains(string(), '%s')]/following-sibling::td//button[@class='remove-btn']";
	public static final String DYNAMIC_BUTTON_BY_PRODUCT_NAME_AND_BUTTON_LABEL = "//h2[@class='product-title']//a[text()='%s']//ancestor::div[@class='details']//div[@class='buttons']//button[text()='%s']";
	public static final String DYNAMIC_PRICE_BY_PRODUCT_NAME = "//a[text()='%s']/parent::h2//following-sibling::div[@class='add-info']//span[@class='price actual-price']";
	public static final String QUANTITY_TEXTBOX_AT_TABLE_BY_PRODUCT_NAME = "//td[@class='product' and contains(string(), '%s')]/following-sibling::td[@class='quantity']//input";
	public static final String DYNAMIC_ORDER_INFO_BY_TITLE_AND_FIELD_NAME = "//strong[text()='%s']//parent::div//following-sibling::ul//li[@class='%s']";
	public static final String DYNAMIC_SKU_PRODUCT_NAME_PRICE_QUANTITY_SUBTOTAL_AT_TABLE = "//td[@class='sku' and contains(string(), '%s')]//following-sibling::td[contains(string(), '%s')]//following-sibling::td[@class='unit-price' and contains(string(), '%s')]//following-sibling::td[@class='quantity' and contains(string(), '%s')]//following-sibling::td[contains(string(), '%s')]";
	public static final String DYNAMIC_VALUE_BY_LABEL = "//label[text()='%s']//parent::td//following-sibling::td//span";
	
	//Hard Locator
	public static final String HOME_PAGE_LOGO = "//img[@alt='nopCommerce demo store']";
	public static final String PRODUCTS_TITLE = "//h2[@class='product-title']//a";
	public static final String PRODUCTS_PRICE = "//div[@class='prices']/span";
	public static final String RECENTLY_VIEWED_PRODUCT_TITLES = "//div[contains(@class, 'block-recently-viewed-products')]//a[@class='product-name']";
	public static final String CLOSE_BUTTON_AT_MESSAGE = "//div[@id='bar-notification']//span[@class='close']";
	public static final String COUNT_ITEMS_AT_MINI_CART = "//div[@class='mini-shopping-cart']//div[@class='count']//a";
	public static final String PRODUCT_INFO_AT_MINI_CART = "//div[@class='mini-shopping-cart']//div[@class='product']";
	public static final String SUBTOTAL_AT_MINICART = "//div[@class='mini-shopping-cart']//div[@class='totals']//strong";
	public static final String GIFT_WRAPPING_CHECKOUT = "//div[@class='selected-checkout-attributes']";
}
