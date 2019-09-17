package cucumber.reanmigrate.automation.pageobject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import cucumber.reanmigrate.automation.utils.AutomationUtils;


@Component
@ConfigurationProperties(prefix = "MigrationCredentialPage")

public class MigrationcredentialPage extends Abstract{
	
	
	public void navigate() {
        clickId("action-dropdown ");
        waitUntilElementWithIdPresent("cloudEndure");
        clickId("cloudEndure");
    }
	

	public void enterMigrationCredentialDetails(String credentialName, String user, String password) {
		editTextWithName("name", credentialName);
		clickElementWithXPath("//md-select[@placeholder='Migration Tool']");
		clickElementWithXPath("//md-select-menu[@role='presentation']//md-option[1]");
		editTextWithXPath("//input[@placeholder='Enter username' and @name='vmUser']", user);
		editTextWithXPath("//input[@placeholder='Enter password' and @name='vmPassword']", password);
		
	}


	public void connectionsEditIconClick(String credentialName) {
		clickElementWithXPath("//tr/td[text()='"+credentialName+"']//following::span[1]");
		
	}


	public void updateConnectionsDetails(String credentialName, String user, String password) {
		waitUntilElementWithXPathPresent("//h4[text()='Add/Edit Migration Credentials Details']");
		editTextWithName("name", credentialName);
		editTextWithXPath("//input[@placeholder='Enter username' and @name='vmUser']", user);
		editTextWithXPath("//input[@placeholder='Enter password' and @name='vmPassword']", password);
		
	}


	public void deleteConnection(String credentialName) {
		AutomationUtils.sleepInSec(2);
		clickElementWithXPath("//tr/td[text()='"+credentialName+"']//following::span[2]");
		AutomationUtils.sleepInSec(2);
		
	}


	public void verifyEmptyFields() {
		isElementByXPathDisabled("//button[@class='md-button md-cornered rean-btn']");
		
	}
}
