package cucumber.reanmigrate.automation.pageobject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import cucumber.reanmigrate.automation.utils.AutomationUtils;

@Component
@ConfigurationProperties(prefix = "ServergroupPage")

public class ServergroupPage extends Abstract {

	public void navigate_to_group() {
		clickElementWithXPath("//i[@class='migrate-icons migrate-create-icon']");

		AutomationUtils.sleepInSec(5);
		waitUntilElementWithXPathPresent("//li/a[contains(text(),'Group')]");
		AutomationUtils.sleepInSec(5);
		clickElementWithXPath("//li/a[contains(text(),'Group')]");
	}

	public void enterServerGroupDetails(String groupName, String groupDiscription) throws InterruptedException {
		editTextWithXPath("//input[@id='input_2' and @name='name']", groupName);
		AutomationUtils.sleepInSec(1);
		editTextWithXPath("//input[@id='input_3' and @name='description']", groupDiscription);
		// clickElementWithXPath("(//td[@title='IM-CDHCP01_OK'])[2]/preceding-sibling::td/md-checkbox");
		clickElementWithXPath("(//td[@title='komal_cloud_endure'])[2]/preceding-sibling::td/md-checkbox");
		// clickElementWithXPath("//td[@title='komal_cloud_endure']//preceding-sibling::td//md-checkbox");
	}

	public void enterServerGroupDetailsWithoutServer(String groupName, String groupDiscription) {

		editTextWithXPath("//input[@id='input_2' and @name='name']", groupName);
		AutomationUtils.sleepInSec(1);
		editTextWithXPath("//input[@id='input_3' and @name='description']", groupDiscription);
	}

	public void verifyEmptyButtonClickOn() {
		String xpath = "//button[text()='Create Group']";
		isElementByXPathDisabled(xpath);

	}
}