package commons;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	private enum BROWSER {
		CHROME, FIREFOX, EDGE_CHRONIUM, EDGE_LEGACY, H_CHROME, H_FIREFOX; 
	}
	
	private enum OS {
		WINDOW, MAC_OSX, LINUX;
	}
	
	private enum PLATFORM {
		ANDROID, IOS, WINDOW_PHONE;
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		
		switch (browser) {
		case FIREFOX:
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver", projectPath + getSlash("browserDrivers") + "geckodriver");
			driver = new FirefoxDriver();
			break;
			
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case EDGE_CHRONIUM:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			
		default:
			throw new RuntimeException("Please input the correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		
		switch (browser) {
		case FIREFOX:
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver", projectPath + getSlash("browserDrivers") + "geckodriver");
			driver = new FirefoxDriver();
			break;
			
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case EDGE_CHRONIUM:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			
		default:
			throw new RuntimeException("Please input the correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		
		return driver;
	}
	
	protected String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.com";
	}
	
	private String getSlash(String folderName) {
		String seperator = File.separator;
		return seperator + folderName + seperator;
	}
}
