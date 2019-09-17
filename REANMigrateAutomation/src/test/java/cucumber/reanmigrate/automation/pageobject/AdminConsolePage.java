package cucumber.reanmigrate.automation.pageobject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
@ConfigurationProperties(prefix = "AdminConsolePage")

public class AdminConsolePage extends Abstract {

	public void selectOption(String option) {

		clickId("action-dropdown ");

		switch (option.toLowerCase().replace(" ", "")) {

		case "providers":
			clickId("providers");
			break;
		case "users":
			clickId("userListlink");
			break;
		case "groups":
			clickId("groupListlink");
			break;
		case "ldapConfiguration ":
			clickId("adConfiglink");
			break;
		default:
			System.out.println("Out of bounds. Please enter the correct options");

		}

		waitUntilTextPresent("Add Provider", 10);

	}

	public void navigatetopage() {

		clickId("accelerator-dropdown");
		clickId("reanadminconsole");
		waitUntilElementWithXPathPresent("//*[@id='rean-platform-logo']/a/span/img", 10);
		Assert.assertEquals("Home|REANPlatform", pageTitle().trim().toString().replace(" ", ""));

	}

	public void selectProvider(String provider) {

		clickElementWithXPath("//span[contains(text(),'select provider')]");
		clickElementWithXPath("//span[contains(text(),'" + provider + "')]");

	}

	public void click(String label) {

		clickElementWithXPath("//md-select[contains(@name,'" + label + "')]");

	}

}
