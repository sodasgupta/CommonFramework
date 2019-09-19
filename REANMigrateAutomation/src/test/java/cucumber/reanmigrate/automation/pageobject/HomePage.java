package cucumber.reanmigrate.automation.pageobject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import junit.framework.Assert;


@Component
@ConfigurationProperties(prefix = "HomePage")
public class HomePage extends Abstract {
	
	
	@Value("${cucumber.reanmigrate.automation.common.platform_url}")
	String platformUrl;
	
	
	
	public HomePage toPage(){
		fullScreen();
		navigate(platformUrl);
		//waitUntilLogginPageLoaded();
		return this;
	}
	
	@SuppressWarnings({ "deprecation" })
	public void checkHomePage(String Header) {
		fullScreen();
		navigate(platformUrl);
		
		//boolean result = isThere(Header);
		
		String header = pageTitle();
		
		//Assert.assertEquals(header, Header);
		
		System.out.println("Test");
		
		
	}

}
