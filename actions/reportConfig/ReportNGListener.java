package reportConfig;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import commons.BaseTest;

public class ReportNGListener extends BaseTest implements ITestListener {
	String projectImgPath = System.getProperty("user.dir") + "/screenshotReportNG/";

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		Object testClass = result.getInstance();
		WebDriver driver = ((BaseTest) testClass).getWebDriver();

		String screenshotPath = captureScreenshot(driver, result.getName());
		Reporter.getCurrentTestResult();
		Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
		Reporter.setCurrentTestResult(null);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public String captureScreenshot(WebDriver driver, String screenshotName) {
		String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return screenshotBase64;
	}

}
