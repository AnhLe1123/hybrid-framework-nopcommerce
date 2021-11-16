package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.admin.AdminBasePageUI;
import pageUIs.user.UserBasePageUI;

public class BasePage {

	public static BasePage getBasePage() {
		return new BasePage();
	}

	protected void openUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected void getPageTitle(WebDriver driver) {
		driver.getTitle();
	}

	protected void getPageUrl(WebDriver driver) {
		driver.getCurrentUrl();
	}

	protected void getPageSource(WebDriver driver) {
		driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	protected void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}

	protected Alert waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.accept();
		sleepInSecond(2);
	}

	protected void cancelAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		alert.dismiss();
	}

	protected void sendKeyToAlert(WebDriver driver, String value) {
		alert = waitAlertPresence(driver);
		alert.sendKeys(value);
	}

	protected String getTextAlert(WebDriver driver) {
		alert = waitAlertPresence(driver);
		return alert.getText();
	}

	protected void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String id : allWindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
			if (windowTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	protected void closeAllWindowsWithoutParent(WebDriver driver, String ParentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String id : allWindows) {
			if (!id.equals(ParentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(ParentID);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private By getByXpath(String locator) {
		return By.xpath(locator);
	}

	private WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	private WebElement getElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(locator, params)));
	}

	private List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	private String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	protected void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	protected void clickToElement(WebDriver driver, String locator, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}

	protected void sendkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	protected void sendkeyToElement(WebDriver driver, String locator, String value, String... params) {
		locator = getDynamicLocator(locator, params);
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	protected void selectItemInDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	protected void selectItemInDropdownByText(WebDriver driver, String locator, String itemText, String... params) {
		locator = getDynamicLocator(locator, params);
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	protected String getSelectedItemInDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	protected String getSelectedItemInDropdown(WebDriver driver, String locator, String... params) {
		select = new Select(getElement(driver, getDynamicLocator(locator, params)));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				if (!item.isDisplayed()) {
					jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
				}

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	protected void enterAndSelectItemInCustomDropdown(WebDriver driver, String parentXpath, String textboxXpath, String childExpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedItem);
		sleepInSecond(1);
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childExpath)));
		
		for(WebElement item : allItems) {
			if(item.getText().trim().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				item.click();
				break;
			}
		}
	}
	
	protected void enterAndTabToCustomDropdown(WebDriver driver, String textboxXpath, String expectedItem) {
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedItem);
		sleepInSecond(1);
		
		driver.findElement(By.xpath(textboxXpath)).sendKeys(Keys.TAB);
		sleepInSecond(1);
	}

	protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String locator, String attributeName, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).getAttribute(attributeName);
	}

	protected String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	protected String getElementText(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).getText();
	}

	protected String getCssValue(WebDriver driver, String locator, String value) {
		return getElement(driver, locator).getCssValue(value);
	}

	protected String convertRgbaToHexa(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}
	
	protected int getElementSize(WebDriver driver, String locator, String... params) {
		return getElements(driver, getDynamicLocator(locator, params)).size();
	}

	protected void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	protected void checkToCheckboxOrRadio(WebDriver driver, String locator, String... params) {
		if (!isElementSelected(driver, getDynamicLocator(locator, params))) {
			getElement(driver, getDynamicLocator(locator, params)).click();
		}
	}

	protected void uncheckToCheckbox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}
	
	protected void uncheckToCheckbox(WebDriver driver, String locator, String... params) {
		if (isElementSelected(driver, getDynamicLocator(locator, params))) {
			getElement(driver, getDynamicLocator(locator, params)).click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
		try {
			return getElement(driver, getDynamicLocator(locator, params)).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, longTimeout);

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible in UI");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible in UI");
			return false;
		}
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locator, String... params) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, getDynamicLocator(locator, params));
		overrideGlobalTimeout(driver, longTimeout);

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible in UI");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible in UI");
			return false;
		}
	}

	protected void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	protected boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}
	
	protected boolean isElementSelected(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).isSelected();
	}

	protected boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	protected WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	protected WebDriver switchToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}

	protected void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}
	
	protected void hoverToElement(WebDriver driver, String locator, String... params) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, getDynamicLocator(locator, params))).perform();
	}

	protected void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	protected void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	protected void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	protected Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	protected String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	protected void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	protected void scrollToTopPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	protected void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	protected void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	protected void clickToElementByJS(WebDriver driver, String locator, String... params) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, getDynamicLocator(locator, params)));
	}

	protected void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	protected void scrollToElement(WebDriver driver, String locator, String... params) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, getDynamicLocator(locator, params)));
	}

	protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}
	
	protected void sendkeyToElementByJS(WebDriver driver, String locator, String value, String... params) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, getDynamicLocator(locator, params)));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}
	
	protected boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery !=null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	protected boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	protected void waitForElementVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocator(locator, params))));
	}

	protected void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	protected void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));
	}

	protected void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}

	protected void waitForElementInvisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}

	// User - NopCommerce

	public void clickToHomePageLogo(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.HOME_PAGE_LOGO);
		clickToElement(driver, UserBasePageUI.HOME_PAGE_LOGO);
	}

	public void openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_LINK_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_LINK_FOOTER, pageName);
	}

	public void openHeaderPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_LINK_HEADER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_LINK_HEADER, pageName);
	}
	
	public void openSidebarPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_LINK_SIDEBAR, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_LINK_SIDEBAR, pageName);
	}
	
	public void openUserSubmenuPageByName(WebDriver driver, String menuPageName, String submenuPageName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_MENU_BY_NAME, menuPageName);
		hoverToElement(driver, UserBasePageUI.DYNAMIC_MENU_BY_NAME, menuPageName);
		
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_SUBMENU_BY_NAME, menuPageName, submenuPageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_SUBMENU_BY_NAME, menuPageName, submenuPageName);
	}

	public void clickToRadioButtonByLabel(WebDriver driver, String labelText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_TEXT, labelText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_TEXT, labelText);
	}
	
	public void checkToUserCheckboxByLabel(WebDriver driver, String labelText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_CHECKBOX_BY_TEXT, labelText);
		checkToCheckboxOrRadio(driver, UserBasePageUI.DYNAMIC_CHECKBOX_BY_TEXT, labelText);
	}
	
	public void uncheckToUserCheckboxByLabel(WebDriver driver, String labelText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_CHECKBOX_BY_TEXT, labelText);
		uncheckToCheckbox(driver, UserBasePageUI.DYNAMIC_CHECKBOX_BY_TEXT, labelText);
	}
	
	public boolean isRadioButtonByLabelSelected(WebDriver driver, String labelText) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_TEXT, labelText);
		return isElementSelected(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_TEXT, labelText);
	}

	public void inputToUserTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}
	
	public String getValueInUserTextboxByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}

	public void selectUserDropdownByName(WebDriver driver, String dropdownName, String itemText) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		selectItemInDropdownByText(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropdownName);
		isJQueryAjaxLoadedSuccess(driver);
	}
	
	public String getSelectedItemInDropdownByName(WebDriver driver, String dropdownName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		return getSelectedItemInDropdown(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
	}

	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		isJQueryAjaxLoadedSuccess(driver);
	}
	
	public void clickToButtonByLabelAndProductName(WebDriver driver, String productName, String buttonText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_PRODUCT_NAME_AND_BUTTON_LABEL, productName, buttonText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_PRODUCT_NAME_AND_BUTTON_LABEL, productName, buttonText);
		isJQueryAjaxLoadedSuccess(driver);
	}
	
	public boolean isPageTitleByTextDisplayed(WebDriver driver, String pageTitleText) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_PAGE_TITLE_BY_TEXT, pageTitleText);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_PAGE_TITLE_BY_TEXT, pageTitleText);
	}
	
	public boolean isInfoDisplayedByFieldName(WebDriver driver, String fieldName, String textValue) {
		waitForAllElementsVisible(driver, UserBasePageUI.DYNAMIC_INFO_BY_FIELD_NAME, fieldName, textValue);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_INFO_BY_FIELD_NAME, fieldName, textValue);
	}
	
	public void clickToProductTitleByName(WebDriver driver, String productName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PRODUCT_TITLE_BY_TEXT, productName);
		scrollToElement(driver, UserBasePageUI.DYNAMIC_PRODUCT_TITLE_BY_TEXT, productName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PRODUCT_TITLE_BY_TEXT, productName);
	}
	
	public boolean isReviewProductNameDisplayed(WebDriver driver, String productName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_REVIEW_PRODUCT_BY_NAME, productName);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_REVIEW_PRODUCT_BY_NAME, productName);
	}
	
	public boolean isReviewByTitleContentDisplayed(WebDriver driver, String reviewPart, String productName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_REVIEW_TITLE_CONTENT_BY_PRODUCT_NAME, reviewPart, productName);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_REVIEW_TITLE_CONTENT_BY_PRODUCT_NAME, reviewPart, productName);
	}
	
	public int getDisplayedProductsNumber(WebDriver driver) {
		waitForAllElementsVisible(driver, UserBasePageUI.PRODUCTS_TITLE);
		return getElementSize(driver, UserBasePageUI.PRODUCTS_TITLE);
	}
	
	public List<String> getAllProductTitles(WebDriver driver) {
		waitForAllElementsVisible(driver, UserBasePageUI.PRODUCTS_TITLE);
		List<WebElement> allProductTitleElements = getElements(driver, UserBasePageUI.PRODUCTS_TITLE);
		List<String> actualProductTitles = new ArrayList<>();
		
		for(WebElement productTitleElement: allProductTitleElements) {
			actualProductTitles.add(productTitleElement.getText().trim());
		}
		return actualProductTitles;
	}
	
	public boolean isProductTitlesContainKeyword(WebDriver driver, String keyword) {
		List<String> productTitles = getAllProductTitles(driver);
	    for (String title : productTitles) {
	        if (!title.toLowerCase().contains(keyword.toLowerCase())) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public boolean isProductNameSortAscending(WebDriver driver) {
		List<WebElement> productNameElements = getElements(driver, UserBasePageUI.PRODUCTS_TITLE);
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		
		List<String> productNameTextClone = new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);
		return productNameText.equals(productNameTextClone);
	}
	
	public boolean isProductNameSortDescending(WebDriver driver) {
		List<WebElement> productNameElements = getElements(driver, UserBasePageUI.PRODUCTS_TITLE);
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		
		List<String> productNameTextClone = new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);
		return productNameText.equals(productNameTextClone);
	}
	
	public boolean isProductPriceSortAscending(WebDriver driver) {
		List<WebElement> productPriceElements = getElements(driver, UserBasePageUI.PRODUCTS_PRICE);
		List<Float> productPriceValue = new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().replaceAll("[$,]", "")));
		}
		
		List<Float> productPriceValueClone = new ArrayList<Float>(productPriceValue);
		Collections.sort(productPriceValueClone);
		return productPriceValue.equals(productPriceValueClone);
	}
	
	public boolean isProductPriceSortDescending(WebDriver driver) {
		List<WebElement> productPriceElements = getElements(driver, UserBasePageUI.PRODUCTS_PRICE);
		List<Float> productPriceValue = new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().replaceAll("[$,]", "")));
		}
		
		List<Float> productPriceValueClone = new ArrayList<Float>(productPriceValue);
		Collections.sort(productPriceValueClone);
		Collections.reverse(productPriceValueClone);
		return productPriceValue.equals(productPriceValueClone);
	}
	
	public boolean isNumberOfProductsDisplayedLessOrEqual(WebDriver driver, int displayPerPageNumber) {
		if (getDisplayedProductsNumber(driver) <= displayPerPageNumber) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAddProductToPageSuccessMessageDisplayed(WebDriver driver, String pageLink) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_ADD_PRODUCT_SUCCESS_MESSAGE_BY_PAGENAME, pageLink);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_ADD_PRODUCT_SUCCESS_MESSAGE_BY_PAGENAME, pageLink);
	}
	
	public void clickToCloseButtonAtMessage(WebDriver driver) {
		waitForElementVisible(driver, UserBasePageUI.CLOSE_BUTTON_AT_MESSAGE);
		clickToElement(driver, UserBasePageUI.CLOSE_BUTTON_AT_MESSAGE);
		waitForElementInvisible(driver, UserBasePageUI.CLOSE_BUTTON_AT_MESSAGE);
	}
	
	public void clickToPageLinkInAddProductSuccessMessage(WebDriver driver, String pageLink) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_ADD_PRODUCT_SUCCESS_MESSAGE_BY_PAGENAME, pageLink);
		clickToElement(driver, UserBasePageUI.DYNAMIC_ADD_PRODUCT_SUCCESS_MESSAGE_BY_PAGENAME, pageLink);
	}
	
	public boolean isProductQuantityByHeaderLabelDisplayed(WebDriver driver, String pageName, String quantity) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_QUANTITY_BY_HEADER_LABEL, pageName, quantity);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_QUANTITY_BY_HEADER_LABEL, pageName, quantity);
	}
	
	public String getTextValueInUserTableAtColumnNameAndRowIndex(WebDriver driver, String columnName, String rowIndex) {
		int columnIndex = getElementSize(driver, UserBasePageUI.DYNAMIC_TABLE_HEADER_BY_NAME, columnName) + 1;
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TABLE_CELL_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, UserBasePageUI.DYNAMIC_TABLE_CELL_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
	}
	
	public boolean isTableImageByProductNameDisplayed(WebDriver driver, String productName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TABLE_IMG_BY_PRODUCT_NAME, productName);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_TABLE_IMG_BY_PRODUCT_NAME, productName);
	}
	
	public boolean isImageByProductDisplayedAtMiniCart(WebDriver driver, String productName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_IMG_BY_PRODUCT_NAME_AT_MINICART, productName);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_IMG_BY_PRODUCT_NAME_AT_MINICART, productName);
	}
	
	public boolean isTableImageByProductNameUndisplayed(WebDriver driver, String productName) {
		waitForElementInvisible(driver, UserBasePageUI.DYNAMIC_TABLE_IMG_BY_PRODUCT_NAME, productName);
		return isElementUndisplayed(driver, UserBasePageUI.DYNAMIC_TABLE_IMG_BY_PRODUCT_NAME, productName);
	}
	
	public boolean isTableQuantityInputByProductNameDisplayed(WebDriver driver, String productName, String qtyValue) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TABLE_QTY_INPUT_BY_PRODUCT_NAME, productName, qtyValue);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_TABLE_QTY_INPUT_BY_PRODUCT_NAME, productName, qtyValue);
	}
	
	public void inputToQuantityTextboxAtTableByProductName(WebDriver driver, String productName, String qtyValue) {
		waitForElementVisible(driver, UserBasePageUI.QUANTITY_TEXTBOX_AT_TABLE_BY_PRODUCT_NAME, productName);
		sendkeyToElement(driver, UserBasePageUI.QUANTITY_TEXTBOX_AT_TABLE_BY_PRODUCT_NAME, qtyValue, productName);
	}
	
	public Float convertUserProductPriceToNumber(WebDriver driver, String productPrice) {
		return Float.parseFloat(productPrice.replaceAll("[$,]", ""));
	}
	
	public boolean isNoDataMessageByTextDisplayed(WebDriver driver, String textMessage) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_NO_DATA_MESSAGE_BY_TEXT, textMessage);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_NO_DATA_MESSAGE_BY_TEXT, textMessage);
	}
	
	public void clickToRemoveIconInTableByProductName(WebDriver driver, String productName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_TABLE_REMOVE_ICON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_TABLE_REMOVE_ICON_BY_PRODUCT_NAME, productName);
	}
	
	public String getProductPriceByProductName(WebDriver driver, String productName) {
		waitForAllElementsVisible(driver, UserBasePageUI.DYNAMIC_PRICE_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserBasePageUI.DYNAMIC_PRICE_BY_PRODUCT_NAME, productName);
	}
	
	public List<String> getRecentViewProductTitles(WebDriver driver) {
		List<WebElement> recentViewElements = getElements(driver, UserBasePageUI.RECENTLY_VIEWED_PRODUCT_TITLES);
		List<String> recentViewProductsText = new ArrayList<String>();
		
		for (WebElement element : recentViewElements) {
			recentViewProductsText.add(element.getText());
		}
		return recentViewProductsText;
	}
	
	public boolean isRecentlyViewedProductsDisplayed(WebDriver driver) {
		List<String> actualRecentViewProducts = getRecentViewProductTitles(driver);
		List<String> expectedRecentViewProducts = new ArrayList<String>();
		List<String> displayedProductTitles = getAllProductTitles(driver);
		Collections.reverse(displayedProductTitles);
		
		int productSize = displayedProductTitles.size();
		
		if (productSize > 0 && productSize <= 3) {
			for (String productTitle : displayedProductTitles) {
				expectedRecentViewProducts.add(productTitle);
			}
		} else if (productSize > 3) {
			expectedRecentViewProducts.add(displayedProductTitles.get(0));
			expectedRecentViewProducts.add(displayedProductTitles.get(1));
			expectedRecentViewProducts.add(displayedProductTitles.get(2));
		}
		return actualRecentViewProducts.equals(expectedRecentViewProducts);
	}
	
	public boolean isTextValueContainsMultipleKeywords(WebDriver driver, String textValue, List<String> items) {
	    boolean found = true;
	    for (String item : items) {
	        if (!textValue.contains(item)) {
	            found = false;
	            break;
	        }
	    }
	    return found;
	}
	
	public void hoverToHeaderLink(WebDriver driver, String pageName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_LINK_HEADER, pageName);
		scrollToElement(driver, UserBasePageUI.DYNAMIC_LINK_HEADER, pageName);
		hoverToElement(driver, UserBasePageUI.DYNAMIC_LINK_HEADER, pageName);
	}
	
	public String getItemsCountAtMiniCart(WebDriver driver) {
		waitForElementVisible(driver, UserBasePageUI.COUNT_ITEMS_AT_MINI_CART);
		return getElementText(driver, UserBasePageUI.COUNT_ITEMS_AT_MINI_CART);
	}
	
	public String getProductTextValueAtMiniCart(WebDriver driver) {
		waitForElementVisible(driver, UserBasePageUI.PRODUCT_INFO_AT_MINI_CART);
		return getElementText(driver, UserBasePageUI.PRODUCT_INFO_AT_MINI_CART);
	}
	
	public String getSubTotalPriceAtMiniCart(WebDriver driver) {
		waitForElementVisible(driver, UserBasePageUI.SUBTOTAL_AT_MINICART);
		return getElementText(driver, UserBasePageUI.SUBTOTAL_AT_MINICART);
	}
	
	public String getOrderInfoByTitleAndFieldName(WebDriver driver, String orderTitle, String fieldLabel) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_ORDER_INFO_BY_TITLE_AND_FIELD_NAME, orderTitle, fieldLabel);
		return getElementText(driver, UserBasePageUI.DYNAMIC_ORDER_INFO_BY_TITLE_AND_FIELD_NAME, orderTitle, fieldLabel);
	}
	
	public boolean isProductInfoDisplayedAtTable(WebDriver driver, String sku, String productName, String productPrice, String quantity, String subtotal) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_SKU_PRODUCT_NAME_PRICE_QUANTITY_SUBTOTAL_AT_TABLE, sku, productName, productPrice, quantity, subtotal);
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_SKU_PRODUCT_NAME_PRICE_QUANTITY_SUBTOTAL_AT_TABLE, sku, productName, productPrice, quantity, subtotal);
	}
	
	public String getGiftWrappingCheckoutConfirmation(WebDriver driver) {
		waitForElementVisible(driver, UserBasePageUI.GIFT_WRAPPING_CHECKOUT);
		return getElementText(driver, UserBasePageUI.GIFT_WRAPPING_CHECKOUT);
	}
	
	public String getValueAtCheckoutCartByLabel(WebDriver driver, String labelName) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_VALUE_BY_LABEL, labelName);
		return getElementText(driver, UserBasePageUI.DYNAMIC_VALUE_BY_LABEL, labelName);
	}

	// Admin - NopCommerce

	public void openAdminSubmenuPageByName(WebDriver driver, String menuPageName, String submenuPageName) {
		waitForElementClickable(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		clickToElementByJS(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);

		waitForElementClickable(driver, AdminBasePageUI.SUBMENU_LINK_BY_NAME, submenuPageName);
		clickToElement(driver, AdminBasePageUI.SUBMENU_LINK_BY_NAME, submenuPageName);
		isJQueryAjaxLoadedSuccess(driver);
	}

	public void uploadFileAtCardName(WebDriver driver, String cardName, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(driver, AdminBasePageUI.UPLOAD_FILE_BY_CARD_NAME, cardName).sendKeys(fullFileName);
	}
	
	public boolean isMessageDisplayedInEmptyTable(WebDriver driver, String tableName) {
		waitForElementVisible(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
		return isElementDisplayed(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
	}
	
	public String getTextValueInAdminTableAtColumnNameAndRowIndex(WebDriver driver, String columnName, String rowIndex) {
		int columnIndex = getElementSize(driver, AdminBasePageUI.DYNAMIC_TABLE_HEADER_BY_NAME, columnName) + 1;
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_TABLE_CELL_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, AdminBasePageUI.DYNAMIC_TABLE_CELL_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
	}
	
	public void selectAdminDropdownByName(WebDriver driver, String dropdownName, String itemText) {
		waitForElementVisible(driver, AdminBasePageUI.DROPDOWN_BY_NAME, dropdownName);
		selectItemInDropdownByText(driver, AdminBasePageUI.DROPDOWN_BY_NAME, itemText, dropdownName);
		isJQueryAjaxLoadedSuccess(driver);
	}
	
	public void checkToAdminCheckboxByID(WebDriver driver, String checkboxID) {
		waitForElementClickable(driver, AdminBasePageUI.CHECKBOX_BY_ID, checkboxID);
		checkToCheckboxOrRadio(driver, AdminBasePageUI.CHECKBOX_BY_ID, checkboxID);
		sleepInSecond(1);
	}
	
	public void uncheckToAdminCheckboxByID(WebDriver driver, String checkboxID) {
		waitForElementClickable(driver, AdminBasePageUI.CHECKBOX_BY_ID, checkboxID);
		uncheckToCheckbox(driver, AdminBasePageUI.CHECKBOX_BY_ID, checkboxID);
	}
	
	public boolean isAdminCheckboxByIDSelected(WebDriver driver, String checkboxID) {
		waitForElementVisible(driver, AdminBasePageUI.CHECKBOX_BY_ID, checkboxID);
		return isElementSelected(driver, AdminBasePageUI.CHECKBOX_BY_ID, checkboxID);
	}
	
	public void inputToAdminTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, AdminBasePageUI.TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, AdminBasePageUI.TEXTBOX_BY_ID, value, textboxID);
	}
	
	public void inputToAdminTextAreaByID(WebDriver driver, String textAreaID, String value) {
		waitForElementVisible(driver, AdminBasePageUI.TEXTAREA_BY_ID, textAreaID);
		sendkeyToElement(driver, AdminBasePageUI.TEXTAREA_BY_ID, value, textAreaID);
	}
	
	public String getValueInAdminTextboxByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, AdminBasePageUI.TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, AdminBasePageUI.TEXTBOX_BY_ID, "value", textboxID);
	}
	
	public String getValueInAdminTextAreaByID(WebDriver driver, String textAreaID) {
		waitForElementVisible(driver, AdminBasePageUI.TEXTAREA_BY_ID, textAreaID);
		return getElementText(driver, AdminBasePageUI.TEXTAREA_BY_ID, textAreaID);
	}
	
	public void clickToExpandSearchPanel(WebDriver driver) {
		waitForElementVisible(driver, AdminBasePageUI.SEARCH_ROW);
		String searchIconStatus = getElementAttribute(driver, AdminBasePageUI.SEARCH_ROW, "class");
		
		if (!searchIconStatus.contains("opened")) {
			waitForElementClickable(driver, AdminBasePageUI.SEARCH_ROW);
			clickToElement(driver, AdminBasePageUI.SEARCH_ROW);
		}
	}
	
	public void clickToAddNewButton(WebDriver driver) {
		waitForElementVisible(driver, AdminBasePageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminBasePageUI.ADD_NEW_BUTTON);
	}
	
	public void clickToAdminRadioButtonByLabel(WebDriver driver, String labelName) {
		waitForElementVisible(driver, AdminBasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
		clickToElement(driver, AdminBasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
	}
	
	public boolean isAdminRadioButtonSelectedByLabel(WebDriver driver, String labelName) {
		waitForElementVisible(driver, AdminBasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
		return isElementSelected(driver, AdminBasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
	}
	
	public void unselectAllOptionsAtCustomerRolesDropdown(WebDriver driver) {
		List<WebElement> closeIcons = getElements(driver, AdminBasePageUI.CLOSE_ICONS_AT_DROPDOWN_OPTION);

		for (WebElement closeIcon : closeIcons) {
			if (closeIcons.size() > 0) {
				closeIcon.click();
			}
		}
	}
	
	public void selectOptionAtCustomerRolesField(WebDriver driver, String expectedItem) {
		unselectAllOptionsAtCustomerRolesDropdown(driver);
		sleepInSecond(1);
		selectItemInCustomDropdown(driver, AdminBasePageUI.CUSTOMER_ROLE_DROPDOWN, AdminBasePageUI.CUSTOMER_ROLE_OPTIONS, expectedItem);
		sleepInSecond(1);
	}
	
	public void clickToExpandPanelByName(WebDriver driver, String panelName) {
		waitForElementVisible(driver, AdminBasePageUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
		scrollToElement(driver, AdminBasePageUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
		String toogleIconStatus = getElementAttribute(driver, AdminBasePageUI.TOOGLE_ICON_BY_CARD_NAME, "class", panelName);

		if (toogleIconStatus.contains("fa-plus")) {
			waitForElementClickable(driver, AdminBasePageUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
			clickToElement(driver, AdminBasePageUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
		}
		sleepInSecond(1);
	}
	
	public String getDisplayedAminSuccessMessage(WebDriver driver) {
		waitForElementVisible(driver, AdminBasePageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AdminBasePageUI.SUCCESS_MESSAGE);
	}
	
	public boolean isItemsNumberAtTableDisplayed(WebDriver driver, String tableName, String dataTableInfo) {
		waitForElementVisible(driver, AdminBasePageUI.DATA_TABLE_INFO_BY_TABLE_NAME_AND_ITEMS_NUMBER_TEXT, tableName, dataTableInfo);
		return isElementDisplayed(driver, AdminBasePageUI.DATA_TABLE_INFO_BY_TABLE_NAME_AND_ITEMS_NUMBER_TEXT, tableName, dataTableInfo);
	}

	private Alert alert;
	private Select select;
	private Actions action;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
