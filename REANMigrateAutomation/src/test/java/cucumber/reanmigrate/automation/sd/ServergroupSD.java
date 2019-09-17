package cucumber.reanmigrate.automation.sd;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import cucumber.reanmigrate.automation.TestApplication;
import cucumber.reanmigrate.automation.pageobject.Abstract;
import cucumber.reanmigrate.automation.pageobject.BasePage;
import cucumber.reanmigrate.automation.pageobject.ServergroupPage;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest

public class ServergroupSD extends Abstract {

	@Autowired
	ServergroupPage ServergroupPage;
	@Autowired
	BasePage basePage;

	@When("^User is at group page$")
	public void user_is_at_group_page() throws Throwable {
		ServergroupPage.navigate_to_group();

	}

	@When("^User enter the server group details$")
	public void user_enter_the_server_group_details(DataTable dataTable) throws Throwable {
		isThere("Create Group");
		List<String> list = dataTable.raw().get(0);
		String groupName = list.get(0);
		String groupDiscription = list.get(1);
		ServergroupPage.enterServerGroupDetails(groupName, groupDiscription);
	}

	@When("^User enter the server details:$")
	public void user_enter_the_server_details(DataTable dataTable) throws Throwable {
		isThere("Create Group");
		List<String> list = dataTable.raw().get(0);
		String groupName = list.get(0);
		String groupDiscription = list.get(1);
		ServergroupPage.enterServerGroupDetails(groupName, groupDiscription);
	}

	@When("^Click Create Group button$")
	public void click_Create_Group_button() throws Throwable {
		ServergroupPage.verifyEmptyButtonClickOn();
	}

}
