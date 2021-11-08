package pageObjects.user;


import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.NotebooksPageUI;

public class NotebooksPageObject extends BasePage {
	WebDriver driver;

	public NotebooksPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPagingUndisplayed() {
		return isElementUndisplayed(driver, NotebooksPageUI.PAGING);
	}

	public boolean isPagingIconByCurrentPage(String currentPage, String pagingIconLabel) {
		waitForElementVisible(driver, NotebooksPageUI.PAGING_ICON_BY_CURRENT_PAGE, currentPage, pagingIconLabel);
		return isElementDisplayed(driver, NotebooksPageUI.PAGING_ICON_BY_CURRENT_PAGE, currentPage, pagingIconLabel);
	}

	public void clickToPageNumber(String pageNum) {
		waitForElementVisible(driver, NotebooksPageUI.PAGING);
		String currentPageNumber = getElementText(driver, NotebooksPageUI.CURRENT_PAGE);
		if (!currentPageNumber.equals(pageNum)) {
			clickToElement(driver, NotebooksPageUI.NOT_CURRENT_PAGE_NUMBER, pageNum);
		}
	}
}
