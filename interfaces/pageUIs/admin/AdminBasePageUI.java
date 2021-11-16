package pageUIs.admin;

public class AdminBasePageUI {
	public static final String MENU_LINK_BY_NAME = "//ul[@role='menu']/li/a//p[contains(text(), '%s')]";
	public static final String SUBMENU_LINK_BY_NAME = "//ul[@class='nav nav-treeview' and @style]//p[contains(text(), '%s')]";
	public static final String UPLOAD_FILE_BY_CARD_NAME = "//div[@id='product-%s']//input[@type='file']";
	public static final String NO_DATA_MESSAGE_BY_TABLE_NAME = "//table[@id='%s-grid']//td[@class='dataTables_empty' and text()='No data available in table']";
	public static final String DYNAMIC_TABLE_HEADER_BY_NAME = "//div[@class='dataTables_scrollHead']//th[text()='%s']/preceding-sibling::th";
	public static final String DYNAMIC_TABLE_ROW_BY_KEYWORD = "//td[text()='%s']/parent::tr/preceding-sibling::tr";
	public static final String DYNAMIC_TABLE_CELL_BY_ROW_INDEX_AND_COLUMN_INDEX = "//tbody/tr[%s]/td[%s]";
	public static final String DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String CHECKBOX_BY_ID = "//input[@id='%s']";
	public static final String TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String TEXTAREA_BY_ID = "//textarea[@id='%s']";
	public static final String SEARCH_ROW = "//div[contains(@class, 'search-row')]";
	public static final String ADD_NEW_BUTTON = "//a[contains(string(), 'Add new')]";
	public static final String RADIO_BUTTON_BY_LABEL = "//label[contains(text(), '%s')]/preceding-sibling::input";
	public static final String CUSTOMER_ROLE_DROPDOWN = "//div[@class='form-group row' and contains(string(), 'Customer roles')]//div[contains(@class, 'k-widget k-multiselect')]";
	public static final String CUSTOMER_ROLE_OPTIONS = "//ul[@id='SelectedCustomerRoleIds_listbox']//li";
	public static final String CLOSE_ICONS_AT_DROPDOWN_OPTION = "//label[text()='Customer roles']//ancestor::div[contains(@class, 'col-md')]/following-sibling::div//span[@class='k-select']";
	public static final String TOOGLE_ICON_BY_CARD_NAME = "//div[@class='card-title' and contains(string(), '%s')]/following-sibling::div//i";
	public static final String SUCCESS_MESSAGE = "//div[contains(@class, 'alert')]";
}
