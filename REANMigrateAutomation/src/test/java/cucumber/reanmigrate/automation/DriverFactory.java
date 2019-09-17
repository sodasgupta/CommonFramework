package cucumber.reanmigrate.automation;

import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.STRING;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.bonigarcia.wdm.WebDriverManager;

@Component
public class DriverFactory {
	
	Logger logger = LoggerFactory.getLogger(DriverFactory.class);
	
	@Value("${cucumber.reanmigrate.automation.common.browser}")
	String browser;
	

	public enum Browsers {
		Chrome, Firefox
	}


	// thread local driver object for webdriver
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {
			return initiateDriver();
		}
	};

	public WebDriver initiateDriver() {
		String browserEnv = System.getenv("BROWSER");
		if (StringUtils.isEmpty(browserEnv)) {
			browserEnv = "Chrome";
		}
		logger.info("Loading driver for browser: " + browserEnv + " " + this.browser);
		Browsers browser = Browsers.valueOf(browserEnv);
		

		WebDriver webDriver = null;
		switch (browser) {
		case Chrome:
	        WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
		//	options.addArguments("--start-maximized");
			//options.addArguments("disable-infobars"); 
			webDriver  = new ChromeDriver();
			webDriver.manage().window().setSize(new Dimension(1600,900));
			break;
		case Firefox:
			webDriver  = new FirefoxDriver();
			webDriver.manage().window().maximize();
			break;

		default:
			break;
		}
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return webDriver; // or other browser drivers
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void removeDriver() {
		driver.get().quit();
		driver.remove();
	}
}
