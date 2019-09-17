package cucumber.reanmigrate.automation.sd;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.reanmigrate.automation.pageobject.Abstract;
import cucumber.reanmigrate.automation.pageobject.BasePage;
import cucumber.reanmigrate.automation.pageobject.Connection;
import cucumber.reanmigrate.automation.utils.*;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;

public class BaseActionsSD extends Abstract {

	@Autowired
	BasePage basePage;

	@Autowired
	Connection connectionPage;

	@When("^Verify success message$")
	public void verifySuccessMessage(DataTable dataTable) {
		List<List<String>> raw = dataTable.raw();
		List<String> list = raw.get(0);
		basePage.verifySuccessNotificationMessage(list.get(0));
	}

	@When("^Verify warning message$")
	public void verifyWarningMessage(DataTable dataTable) throws InterruptedException {
		List<List<String>> raw = dataTable.raw();
		List<String> list = raw.get(0);
		basePage.verifyWarningNotificationMessage(list.get(0));
	}

	@When("^Verify error message$")
	public void verifyErroMessage(DataTable dataTable) {
		List<List<String>> raw = dataTable.raw();
		List<String> list = raw.get(0);
		basePage.verifyErrorNotificationMessage(list.get(0));
	}

	@When("^Click \"([^\"]*)\" button$")
	public void clickButton(String label) {
		basePage.clickNormalButtonWithLabel(label);
	}

	@When("^Click \"([^\"]*)\" span$")
	public void clickSpan(String label) {
		basePage.clickMdButtonWithLabel(label);
		waitForPopup();
	}

	@When("^User move to user profile$")
	public void user_move_to_user_profile() throws Throwable {
		basePage.moveToUserProfile();
	}

	@When("^User click on Submit button$")
	public void user_click_on_Submit_button() throws Throwable {
		basePage.clickOnSubmitButton();
		AutomationUtils.sleepInSec(3);
	}

}
