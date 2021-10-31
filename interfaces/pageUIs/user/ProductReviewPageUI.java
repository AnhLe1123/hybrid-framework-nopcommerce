package pageUIs.user;

public class ProductReviewPageUI {
	public static final String REVIEW_TEXT_AREA = "//textarea[@id='AddProductReview_ReviewText']";
	public static final String RATING_RATIO_BY_VALUE = "//div[@class='rating-options']//input[@value='%s']";
	public static final String ADD_REVIEW_SUCCESS_MESSAGE = "//div[@class='result' and contains(string(), 'Product review is successfully added.')]";
}
