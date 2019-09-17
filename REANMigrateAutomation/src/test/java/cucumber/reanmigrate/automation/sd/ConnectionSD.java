package cucumber.reanmigrate.automation.sd;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cucumber.reanmigrate.automation.TestApplication;
import cucumber.reanmigrate.automation.pageobject.Abstract;
import cucumber.reanmigrate.automation.pageobject.BasePage;
import cucumber.reanmigrate.automation.pageobject.Connection;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest
public class ConnectionSD extends Abstract {

	@Autowired
	Connection connectionPage;
	@Autowired
	BasePage basePage;

	@Value("${cucumber.reanmigrate.automation.authentication.ssh_key}")
	String sshKey;

	@When("^User is at connection page$")
	public void user_is_at_connection_page() throws Throwable {
		connectionPage.navigate();
	}

	@When("^User enter the connection details$")
	public void user_enter_the_connection_details(DataTable dataTable) throws Throwable {
		isThere("Add/Edit Connection");
		List<String> list = dataTable.raw().get(0);
		String connectionName = list.get(0);
		String user = list.get(1);
		String sshKey = list.get(2);
		connectionPage.enterConnectionsDetails(connectionName, user, sshKey);

	}

	@Then("^User click on \"([^\"]*)\" edit icon$")
	public void user_click_on_edit_icon(String connectionName) throws Throwable {

		connectionPage.connectionsEditIconClick(connectionName);

	}

	@Then("^User Update the Connection details$")
	public void user_Update_the_Connection_details(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String connectionName = list.get(0);
		String user = list.get(1);
		connectionPage.updateConnectionsDetails(connectionName, user);
	}

	@When("^Delete connection: \"([^\"]*)\"$")
	public void delete_connection(String connectionName) throws Throwable {
		connectionPage.deleteConnection(connectionName);
	}

	@When("^User enter the winrm connection details$")
	public void user_enter_the_winrm_connection_details(DataTable dataTable) throws Throwable {
		isThere("Add/Edit Connection");
		List<String> list = dataTable.raw().get(0);
		String connectionName = list.get(0);
		String user = list.get(1);
		String password = list.get(2);
		connectionPage.enterWinrmConnectionsDetails(connectionName, user, password);
	}

	@Then("^User Update the winrm Connection details$")
	public void user_Update_the_winrm_Connection_details(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String connectionName = list.get(0);
		String user = list.get(1);
		String password = list.get(2);
		connectionPage.updateWinrmConnectionDetails(connectionName, user, password);
	}

}