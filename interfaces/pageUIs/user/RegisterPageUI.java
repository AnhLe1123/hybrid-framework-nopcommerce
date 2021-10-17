package pageUIs.user;

public class RegisterPageUI {

	public static final String SUCCESS_MESSAGE = "//div[@class='result' and text()='Your registration completed']";
	public static final String ERROR_MESSAGE_BY_FIELD_NAME = "//span[@id='%s-error']";
	public static final String ERROR_MESSAGE_EXISTING_EMAIL = "//div[contains(@class, 'message-error')]//li[text()='The specified email already exists']";
}
