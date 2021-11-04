package pageObjects.user;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static HomePageObject homePage;
	private static LoginPageObject loginPage;
	private static RegisterPageObject registerPage;
	private static SearchPageObject searchPage;
	private static SiteMapPageObject siteMapPage;
	private static WishlistPageObject wishlistPage;
	private static MyAccountPageObject myAccountPage;
	private static ShippingAndReturnPageObject shippingAndReturnPage;
	private static MyAddressPageObject myAddressPage;
	private static MyPasswordPageObject myPasswordPage;
	private static MyProductReviewPageObject myProductReviewPage;
	private static ProductDetailPageObject productDetailPage;
	private static ProductReviewPageObject productReviewPage;
	private static NotebooksPageObject notebooksPage;
	
	private PageGeneratorManager() {
		
	}

	public static HomePageObject getHomePage(WebDriver driver) {
		if(homePage == null) {
			homePage = new HomePageObject(driver);
		}
		return homePage;
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		if(loginPage == null) {
			loginPage = new LoginPageObject(driver);
		}
		return loginPage;
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		if(registerPage == null) {
			registerPage = new RegisterPageObject(driver);
		}
		return registerPage;
	}
	
	public static SearchPageObject getSearchPage(WebDriver driver) {
		if(searchPage == null) {
			searchPage = new SearchPageObject(driver);
		}
		return searchPage;
	}
	
	public static SiteMapPageObject getSiteMapPage(WebDriver driver) {
		if(siteMapPage == null) {
			siteMapPage = new SiteMapPageObject(driver);
		}
		return siteMapPage;
	}
	
	public static WishlistPageObject getWishlistPage(WebDriver driver) {
		if(wishlistPage == null) {
			wishlistPage = new WishlistPageObject(driver);
		}
		return wishlistPage;
	}
	
	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		if(myAccountPage == null) {
			myAccountPage = new MyAccountPageObject(driver);
		}
		return myAccountPage;
	}
	
	public static ShippingAndReturnPageObject getShippingAndReturnPage(WebDriver driver) {
		if(shippingAndReturnPage == null) {
			shippingAndReturnPage = new ShippingAndReturnPageObject(driver);
		}
		return shippingAndReturnPage;
	}
	
	public static MyAddressPageObject getMyAddressPage(WebDriver driver) {
		if(myAddressPage == null) {
			myAddressPage = new MyAddressPageObject(driver);
		}
		return myAddressPage;
	}
	
	public static MyPasswordPageObject getMyPasswordPage(WebDriver driver) {
		if(myPasswordPage == null) {
			myPasswordPage = new MyPasswordPageObject(driver);
		}
		return myPasswordPage;
	}
	
	public static MyProductReviewPageObject getMyProductReviewPage(WebDriver driver) {
		if(myProductReviewPage == null) {
			myProductReviewPage = new MyProductReviewPageObject(driver);
		}
		return myProductReviewPage;
	}
	
	public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
		if(productDetailPage == null) {
			productDetailPage = new ProductDetailPageObject(driver);
		}
		return productDetailPage;
	}
	
	public static ProductReviewPageObject getProductReviewPage(WebDriver driver) {
		if(productReviewPage == null) {
			productReviewPage = new ProductReviewPageObject(driver);
		}
		return productReviewPage;
	}
	
	public static NotebooksPageObject getNotebooksPage(WebDriver driver) {
		if(notebooksPage == null) {
			notebooksPage = new NotebooksPageObject(driver);
		}
		return notebooksPage;
	}
}
