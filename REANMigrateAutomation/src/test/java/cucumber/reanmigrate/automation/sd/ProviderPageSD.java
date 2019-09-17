package cucumber.reanmigrate.automation.sd;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cucumber.api.java.en.Then;
import cucumber.reanmigrate.automation.TestApplication;
import cucumber.reanmigrate.automation.pageobject.Abstract;
import cucumber.reanmigrate.automation.pageobject.ProviderPage;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest
public class ProviderPageSD extends Abstract {

	@Autowired
	ProviderPage ProPage;

	@Then("^the user selects provider \"([^\"]*)\"$")
	public void the_user_selects_provider(String provider) throws Throwable {

		ProPage.selectOption_provider(provider);

	}

	@Then("^the user enters provider name \"([^\"]*)\"$")
	public void the_user_enters_provider_name(String providerName) throws Throwable {

		ProPage.enterProviderName(providerName);

	}

	@Then("^the save button should be \"([^\"]*)\"$")
	public void the_save_button_should_be(String arg1) throws Throwable {

		// isElementByXPathDisabled();

	}

}
