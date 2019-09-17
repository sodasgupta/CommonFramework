package cucumber.reanmigrate.automation.sd;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.reanmigrate.automation.pageobject.Abstract;
import cucumber.reanmigrate.automation.pageobject.BasePage;
import cucumber.reanmigrate.automation.pageobject.MigrationcredentialPage;

public class MigrationCredentialsSD extends Abstract {

	@Autowired
	MigrationcredentialPage MigrationCredentialPage;

	@Autowired
	BasePage basePage;

	@When("^User is at migration credentials page$")
	public void user_is_at_migration_credentials_page() throws Throwable {
		MigrationCredentialPage.navigate();
	}

	@When("^User enter the migration credentials details$")
	public void user_enter_the_migration_credentials_details(DataTable dataTable) throws Throwable {
		isThere("Add/Edit Migration Credentials Details");
		List<String> list = dataTable.raw().get(0);
		String credentialName = list.get(0);
		String user = list.get(1);
		String password = list.get(2);
		MigrationCredentialPage.enterMigrationCredentialDetails(credentialName, user, password);

	}

	@Then("^User click on edit: \"([^\"]*)\"$")
	public void user_click_on_edit_icon(String credentialName) throws Throwable {

		MigrationCredentialPage.connectionsEditIconClick(credentialName);

	}

	@When("^User update the credentials details$")
	public void user_update_the_credentials_details(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String credentialName = list.get(0);
		String user = list.get(1);
		String password = list.get(2);
		MigrationCredentialPage.updateConnectionsDetails(credentialName, user, password);
	}

	@When("^User click on delete: \"([^\"]*)\"$")
	public void user_click_on_delete(String credentialName) throws Throwable {
		MigrationCredentialPage.deleteConnection(credentialName);
	}

	@When("^Click on : \"([^\"]*)\" button$")
	public void click_on_button(String arg1) throws Throwable {
		MigrationCredentialPage.verifyEmptyFields();
	}

	@Then("^Verify user \"([^\"]*)\" is present$")
	public void verify_user_is_present(String arg1) throws Throwable {
		Assert.assertTrue(isElementPresentWithXPATH("//td[contains(text(),'edit_credentials_name')]"));
	}
}
