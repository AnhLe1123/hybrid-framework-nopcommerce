package pageUIs.admin;

public class CustomerDetailPageUI {
	public static final String CUSTOMER_ROLE_SELECTED_ITEM_BY_TEXT = "//ul[@id='SelectedCustomerRoleIds_taglist']//li[@aria-selected='true']//span[text()='%s']";
	public static final String BACK_TO_CUSTOMER_LIST_LINK = "//a[text()='back to customer list']";
	public static final String SAVE_BUTTON = "//button[@name='save']";
	public static final String ADD_NEW_ADDRESS_BUTTON = "//button[contains(text(),'Add new address')]";
	public static final String TABLE_ADDRESS_INFO = "//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td//div[contains(string(),'%s') and contains(string(),'%s') and contains(string(),'%s') and contains(string(),'%s')]";
	public static final String EDIT_BUTTON_BY_CUSTOMER_EMAIL = "//td[text()='%s']/parent::tr//a[contains(string(),'Edit')]";
	public static final String DELETE_BUTTON_BY_CUSTOMER_EMAIL = "//td[text()='%s']/parent::tr//a[contains(string(),'Delete')]";
}
