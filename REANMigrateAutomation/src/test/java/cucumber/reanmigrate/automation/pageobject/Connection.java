package cucumber.reanmigrate.automation.pageobject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import cucumber.reanmigrate.automation.utils.AutomationUtils;

@Component
@ConfigurationProperties(prefix = "connectionPage")
public class Connection extends Abstract {

	// Click on more action
	public void navigate() {
		clickId("action-dropdown ");
		waitUntilElementWithIdPresent("connections");
		clickId("connections");
		clickElementWithXPath("//*[@name='vmConnectionName']");
	}

	public void enterConnectionsDetails(String connectionName, String user, String sshKey) throws InterruptedException {
		editTextWithName("vmConnectionName", connectionName);
		clickElementWithXPath("//md-select[@placeholder='Select connection Type']");
		clickElementWithXPath("//md-select-menu[@role='presentation']//md-option[1]");
		editTextWithXPath("//input[@placeholder='Enter user' and @name='vmUser']", user);
		editTextWithXPath("//textarea[@id='SSHKey']", sshKey);
	}

	public void connectionsEditIconClick(String connectionName) {
		clickElementWithXPath("//tr/td[text()='" + connectionName + "']//following::span[1]");

	}

	public void updateConnectionsDetails(String connectionName, String user) {
		waitUntilElementWithXPathPresent("//h4[text()='Add/Edit Connection']");
		editTextWithName("vmConnectionName", connectionName);
		editTextWithXPath("//input[@placeholder='Enter user' and @name='vmUser']", user);

	}

	public void deleteConnection(String connectionName) {
		AutomationUtils.sleepInSec(2);
		clickElementWithXPath("//tr/td[text()='" + connectionName + "']//following::span[2]");
		AutomationUtils.sleepInSec(2);
		clickMdButtonWithLabel("Yes!");
		AutomationUtils.sleepInSec(1);

	}

	public void enterWinrmConnectionsDetails(String connectionName, String user, String password) {
		editTextWithName("vmConnectionName", connectionName);
		clickElementWithXPath("//md-select[@placeholder='Select connection Type']");
		clickElementWithXPath("//md-select-menu[@role='presentation']//md-option[2]");
		editTextWithXPath("//input[@placeholder='Enter user' and @name='vmUser']", user);
		editTextWithXPath("//input[@placeholder='Enter connection password' and @name='vmPassword']", password);
	}

	public void updateWinrmConnectionDetails(String connectionName, String user, String password) {
		waitUntilElementWithXPathPresent("//h4[text()='Add/Edit Connection']");
		editTextWithName("vmConnectionName", connectionName);
		editTextWithXPath("//input[@placeholder='Enter user' and @name='vmUser']", user);
		editTextWithXPath("//input[@placeholder='Enter connection password' and @name='vmPassword']", password);

	}

}
