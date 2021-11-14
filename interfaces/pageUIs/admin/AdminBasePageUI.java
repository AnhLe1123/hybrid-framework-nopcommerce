package pageUIs.admin;

public class AdminBasePageUI {
	public static final String MENU_LINK_BY_NAME = "//ul[@role='menu']/li/a//p[contains(text(), '%s')]";
	public static final String SUBMENU_LINK_BY_NAME = "//ul[@class='nav nav-treeview' and @style]//p[contains(text(), '%s')]";
	public static final String UPLOAD_FILE_BY_CARD_NAME = "//div[@id='product-%s']//input[@type='file']";
	public static final String NO_DATA_MESSAGE_BY_TABLE_NAME = "//table[@id='%s-grid']//td[@class='dataTables_empty' and text()='No data available in table']";
	public static final String DYNAMIC_TABLE_HEADER_BY_NAME = "//div[@class='dataTables_scrollHead']//th[text()='%s']/preceding-sibling::th";
	public static final String DYNAMIC_TABLE_CELL_BY_ROW_INDEX_AND_COLUMN_INDEX = "//tbody/tr[%s]/td[%s]";
	public static final String DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String CHECKBOX_BY_ID = "//input[@id='%s']";
	public static final String TEXTBOX_BY_ID = "//input[@id='%s']";
}
