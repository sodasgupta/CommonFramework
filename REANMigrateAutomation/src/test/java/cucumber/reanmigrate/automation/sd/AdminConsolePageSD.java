package cucumber.reanmigrate.automation.sd;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.reanmigrate.automation.TestApplication;
import cucumber.reanmigrate.automation.pageobject.AdminConsolePage;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest

public class AdminConsolePageSD {
	@Autowired
	AdminConsolePage ac;
	
	@Given("^User is at the Admin Console$")
	public void user_is_at_the_Admin_Console() throws Throwable {
		ac.navigatetopage();
	    
	}
	
	@Then("^the user navigates to \"([^\"]*)\" page$")
	public void the_user_navigates_to_page(String options) throws Throwable {
	    
		ac.selectOption(options);
	    
	}
	
	@Then("^click on \"([^\"]*)\"$")
	public void click_on(String label) throws Throwable {
	    
		ac.click(label);
	}
	
	

}
