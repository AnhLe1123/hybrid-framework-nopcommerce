package pageUIs.admin;

public class CustomerSearchPageUI {
	public static final String SEARCH_BUTTON = "//button[@id='search-customers']";
	public static final String IS_ACTIVE_ICON_BY_ROW_COLUMN_INDEX = "//tbody/tr[%s]/td[%s]//i[@nop-value='%s']";
	public static final String EDIT_BUTTON_BY_CUSTOMER_NAME = "//td[text()='%s']/parent::tr//a[contains(string(),'Edit')]";
	public static final String CUSTOMER_INFO_AT_TABLE = "//tr//td[text()=\"%s\"]/following-sibling::td[text()='%s']/following-sibling::td[text()=\"%s\"]/following-sibling::td//i[@nop-value='%s']";
}
