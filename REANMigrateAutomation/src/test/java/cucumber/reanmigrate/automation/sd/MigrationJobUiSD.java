package cucumber.reanmigrate.automation.sd;

import java.util.Map;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.reanmigrate.automation.TestApplication;
import cucumber.reanmigrate.automation.pageobject.Abstract;
import cucumber.reanmigrate.automation.pageobject.MigrateUIPage;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest

public class MigrationJobUiSD extends Abstract {

	@Autowired
	MigrateUIPage migratePage;

	@Given("^the user is on the Migration Status UI page$")
	public void the_user_is_on_the_Migration_Status_UI_page() throws Throwable {

		migratePage.navigateToPage();
	}

	@Then("^the user validates the dashboard labels$")
	public void the_user_validates_the_dashboard_labels(DataTable DashLabels) throws Throwable {

		for (Map<String, String> data : DashLabels.asMaps(String.class, String.class)) {

			Assert.assertTrue(isElementPresentWithXPATH("//label[contains(text(),'" + data.get("Label") + "')]"));

		}

	}

	@Then("^the user validates column header$")
	public void the_user_validates_column_header(DataTable Headers) throws Throwable {
		for (Map<String, String> data : Headers.asMaps(String.class, String.class)) {

			Assert.assertTrue(isElementPresentWithXPATH("//th[contains(text(),'" + data.get("Header") + "')]"));

		}
	}

}
