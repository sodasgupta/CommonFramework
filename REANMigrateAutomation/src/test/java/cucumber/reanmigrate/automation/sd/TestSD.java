package cucumber.reanmigrate.automation.sd;

import java.util.Map;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.itextpdf.text.log.SysoCounter;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.reanmigrate.automation.TestApplication;
import cucumber.reanmigrate.automation.pageobject.Abstract;
import cucumber.reanmigrate.automation.pageobject.HomePage;
import cucumber.reanmigrate.automation.utils.AutomationUtils;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest

public class TestSD extends Abstract {
	@Autowired
	HomePage hp;

	@Given("^User is at home page$")
	public void user_is_at_home_page() throws Throwable {

		hp.checkHomePage("REAN Demo");

	}

	@Given("^User enter data for the following fields and click on \"([^\"]*)\"$")
	public void user_enter_data_for_the_following_fields_and_click_on(String Button, DataTable inputData)
			throws Throwable {
		for (Map<String, String> data : inputData.asMaps(String.class, String.class)) {

			editTextByXpath("//label[contains(text(),'Name')]/following::input", data.get("Name"));
			editTextByXpath("//label[contains(text(),'Age')]/following::input", data.get("Age"));
			editTextByXpath("//label[contains(text(),'Salary')]/following::input", data.get("Salary"));

			clickElementWithXPath("//input[@value='Add']");

		}
	}

	@Given("^User click on Edit for below resource$")
	public void user_click_on_Edit_for_below_resource(DataTable verifyData) throws Throwable {
		for (Map<String, String> data : verifyData.asMaps(String.class, String.class)) {

			clickElementWithXPath(
					"//td//child::*[text()='" + data.get("Name") + "']//following::button[contains(text(),'Edit')]");

		}
	}

	@Then("^enter \"([^\"]*)\" in \"([^\"]*)\" text field$")
	public void enter_in_text_field(String textField, String Field) throws Throwable {
		AutomationUtils.sleepInSec(5);
		editTextWithXPath("//label[text()='" + Field + "']//following-sibling::div//input", textField);
	}

	@Then("^click on \"([^\"]*)\"$")
	public void click_on(String arg1) throws Throwable {
		AutomationUtils.sleepInSec(5);
		clickElementWithXPath("//input[@value='Update']");

	}

	@Given("^User click on Remove for below resource$")
	public void user_click_on_Remove_for_below_resource(DataTable verifyData) throws Throwable {
		for (Map<String, String> data : verifyData.asMaps(String.class, String.class)) {

			clickElementWithXPath(
					"//td//child::*[text()='" + data.get("Name") + "']//following::button[contains(text(),'Remove')]");

		}
	}
	
	
	@Then("^user wait \"([^\"]*)\" sec$")
	public void user_wait_min(String waitTime) throws Throwable {
		AutomationUtils.sleepInSec(Integer.valueOf(waitTime));
	}

}
