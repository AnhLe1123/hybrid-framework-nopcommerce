package pageObjects.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static LoginPageObject loginPage;
	private static DashboardPageObject dashboardPage;
	private static ProductSearchPageObject productSearchPage;
	private static ProductDetailPageObject productDetailPage;
	private static CustomerSearchPageObject customerSearchPage;
	private static CreateCustomerPageObject createCustomerPage;
	private static CustomerDetailPageObject customerDetailPage;
	private static CustomerAddressPageObject customerAddressPage;

	private PageGeneratorManager() {

	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		if (loginPage == null) {
			loginPage = new LoginPageObject(driver);
		}
		return loginPage;
	}

	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		if (dashboardPage == null) {
			dashboardPage = new DashboardPageObject(driver);
		}
		return dashboardPage;
	}

	public static ProductSearchPageObject getProductSearchPage(WebDriver driver) {
		if (productSearchPage == null) {
			productSearchPage = new ProductSearchPageObject(driver);
		}
		return productSearchPage;
	}

	public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
		if (productDetailPage == null) {
			productDetailPage = new ProductDetailPageObject(driver);
		}
		return productDetailPage;
	}
	
	public static CustomerSearchPageObject getCustomerSearchPage(WebDriver driver) {
		if (customerSearchPage == null) {
			customerSearchPage = new CustomerSearchPageObject(driver);
		}
		return customerSearchPage;
	}
	
	public static CreateCustomerPageObject getCreateCustomerPage(WebDriver driver) {
		if (createCustomerPage == null) {
			createCustomerPage = new CreateCustomerPageObject(driver);
		}
		return createCustomerPage;
	}
	
	public static CustomerDetailPageObject getCustomerDetailPage(WebDriver driver) {
		if (customerDetailPage == null) {
			customerDetailPage = new CustomerDetailPageObject(driver);
		}
		return customerDetailPage;
	}
	
	public static CustomerAddressPageObject getCustomerAddressPage(WebDriver driver) {
		if (customerAddressPage == null) {
			customerAddressPage = new CustomerAddressPageObject(driver);
		}
		return customerAddressPage;
	}
}
