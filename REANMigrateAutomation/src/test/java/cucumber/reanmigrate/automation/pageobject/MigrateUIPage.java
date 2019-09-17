package cucumber.reanmigrate.automation.pageobject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import cucumber.reanmigrate.automation.utils.AutomationUtils;

@Component
@ConfigurationProperties(prefix = "MigrateUIPage")
public class MigrateUIPage extends Abstract {

	public void navigateToPage() throws InterruptedException {

		clickElementWithXPath("(//input[@type='text'])[2]");
		AutomationUtils.sleepInSec(5);
		clickElementWithXPath("//li[contains(@uib-popover,'Goto Migration View')]");
		waitUntilElementWithXPathPresent("//span[contains(text(),'Migration View')]");

	}

}
