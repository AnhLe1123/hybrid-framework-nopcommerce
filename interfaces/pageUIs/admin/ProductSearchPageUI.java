package pageUIs.admin;

public class ProductSearchPageUI {
	public static final String PRODUCT_NAME_TEXTBOX = "//input[@id='SearchProductName']";
	public static final String SEARCH_BUTTON = "//button[@id='search-products']";
	public static final String SKU_GO_TEXTBOX = "//input[@id='GoDirectlyToSku']";
	public static final String SKU_GO_BUTTON = "//button[@id='go-to-product-by-sku']";
	public static final String SEARCH_ROW = "//div[contains(@class, 'search-row')]";
	
	public static final String EDIT_BUTTON_BY_PRODUCT_NAME = "//td[text()='%s']/following-sibling::td/a[contains(text(), 'Edit')]";
	public static final String SUCCESS_MESSAGE_NAME = "//div[contains(@class, 'alert-success') and contains(string(), '%s')]";
	public static final String PRODUCT_PICTURE_BY_PRODUCT_NAME = "//td[text()='%s']/preceding-sibling::td/img[contains(@src, '%s')]";
	public static final String DATA_TABLE_INFO_BY_TEXT = "//div[@id='products-grid_info' and text()='%s']";
	public static final String PUBLISHED_ICON_BY_VALUE = "//i[contains(@class, 'fa-check') and @nop-value='%s']";
}
