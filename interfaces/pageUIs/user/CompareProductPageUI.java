package pageUIs.user;

public class CompareProductPageUI {
	public static final String DYNAMIC_TABLE_COLUMN_BY_PRODUCT = "//td[contains(string(), '%s')]/preceding-sibling::td";
	public static final String DYNAMIC_TABLE_VALUE_BY_COLUMN_INDEX_AND_ROW_LABEL = "//tbody//tr[@class='%s']//td[%s]";
	public static final String CLEAR_LIST_BUTTON = "//a[text()='Clear list']";
}
