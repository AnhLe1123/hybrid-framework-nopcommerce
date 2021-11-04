package pageUIs.user;

public class NotebooksPageUI {
	public static final String PAGING = "//div[@class='pager']";
	public static final String PAGING_ICON_BY_CURRENT_PAGE = "//li[@class='current-page' and string()= '%s']/parent::ul/li[@class='%s-page']";
	public static final String CURRENT_PAGE = "//li[@class='current-page']/span";
	public static final String NOT_CURRENT_PAGE_NUMBER = "//li[@class='individual-page']//a[@data-page='%s']";
}
