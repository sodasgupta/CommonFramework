package cucumber.reanmigrate.automation.sd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
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
import cucumber.reanmigrate.automation.pageobject.DiscoveryPage;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest
public class DiscoverySD extends Abstract {

	@Autowired
	DiscoveryPage discoveryPage;

	@Autowired
	BasePage basepage;

	public static Map<String, String> discoveryNameCache = new HashMap<String, String>();

	@When("^User is at discovery page$")
	public void user_is_at_discovery_page() throws Throwable {
		discoveryPage.navigate();

	}

	@When("^User enter the discovery details$")
	public void user_enter_the_discovery_details(DataTable dataTable) throws Throwable {
		isThere("Create Discovery");
		List<String> list = dataTable.raw().get(0);
		String discoveryName = list.get(0);
		discoveryName = getNameFromMap(discoveryName);
		String description = list.get(1);
		String discoveryTool = list.get(2);
		discoveryPage.enterDiscoveryDetails(discoveryName, description, discoveryTool);

	}

	private String getNameFromMap(String name) {
		String dName = discoveryNameCache.get(name);
		if (dName == null) {
			dName = name + "_" + System.currentTimeMillis();
			discoveryNameCache.put(name, dName);
		}
		return dName;
	}

	@When("^Import csv file$")
	public void import__valid_csv_file(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String path = list.get(0);
		discoveryPage.uploadFileInTestData(path, "//input[@type='file']");
	}

	@When("^Import csv file with duplicate column validation$")
	public void import_csv_file_withduplicate_column(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String path = list.get(0);
		discoveryPage.uploadFileInTestData(path, "//input[@type='file']");
	}

	@When("^Import csv file with OS column validation$")
	public void import_csv_file(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String path = list.get(0);
		discoveryPage.uploadFileInTestData(path, "//input[@type='file']");
	}

	@When("^Import csv file empty row validation$")
	public void import_csv_file_with_empty_row(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String path = list.get(0);
		discoveryPage.uploadFileInTestData(path, "//input[@type='file']");
	}

	@When("^Verify same name error message for discovery$")
	public void Verify_same_name_error_message_for_discovery(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String str1 = list.get(0);
		String str2 = list.get(1);
		str2 = getNameFromMap(str2);
		String str3 = list.get(2);
		String error = str1 + " " + str2 + " " + str3;
		basepage.verifyErrorNotificationMessage(error);
	}

	@When("^click \"([^\"]*)\" button$")
	public void click_button(String arg1) throws Throwable {
		clickElementWithXPath("//button//span[@class='ng-binding ng-scope']");
	}
}
