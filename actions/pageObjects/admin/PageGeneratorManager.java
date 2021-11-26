package pageObjects.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}

	public static ProductSearchPageObject getProductSearchPage(WebDriver driver) {
		return new ProductSearchPageObject(driver);
	}

	public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
		return new ProductDetailPageObject(driver);
	}
	
	public static CustomerSearchPageObject getCustomerSearchPage(WebDriver driver) {
		return new CustomerSearchPageObject(driver);
	}
	
	public static CreateCustomerPageObject getCreateCustomerPage(WebDriver driver) {
		return new CreateCustomerPageObject(driver);
	}
	
	public static CustomerDetailPageObject getCustomerDetailPage(WebDriver driver) {
		return new CustomerDetailPageObject(driver);
	}
	
	public static CustomerAddressPageObject getCustomerAddressPage(WebDriver driver) {
		return new CustomerAddressPageObject(driver);
	}
}
