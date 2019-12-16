package cucumber.reanmigrate.automation.sd;

import java.util.List;
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

	@Given("^User is at login page$")
	public void user_is_at_home_page() throws Throwable {

		hp.checkHomePage("Manufacturing Solutions");

	}

	@Then("^enter \"([^\"]*)\" in \"([^\"]*)\" text field$")
	public void enter_in_text_field(String textField, String Field) throws Throwable {
		//AutomationUtils.sleepInSec(5);
		editTextWithXPath("//label[text()='"+Field+"']//following-sibling::input", textField);
	}

	@Then("^click on \"([^\"]*)\"$")
	public void click_on(String arg1) throws Throwable {
		AutomationUtils.sleepInSec(5);
		clickElementWithXPath("//input[@value='"+arg1+"']");

	}

	@Then("^the user validates text \"([^\"]*)\"$")
	public void the_user_validates_text(String text) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		List<String> lst = getTextWebElements("//*[text()='"+text+"']");
		
		if(lst.size()>0) {
			
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		
	}

	
	
	@Then("^user wait \"([^\"]*)\" sec$")
	public void user_wait_min(String waitTime) throws Throwable {
		AutomationUtils.sleepInSec(Integer.valueOf(waitTime));
	}

}
