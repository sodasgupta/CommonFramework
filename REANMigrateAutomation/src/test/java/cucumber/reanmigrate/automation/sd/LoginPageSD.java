package cucumber.reanmigrate.automation.sd;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.reanmigrate.automation.TestApplication;
import cucumber.reanmigrate.automation.pageobject.LoginPage;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@SpringBootTest
public class LoginPageSD {

	@Value("${cucumber.reanmigrate.automation.authentication.admin_user}")
	String adminUser;

	@Value("${cucumber.reanmigrate.automation.authentication.admin_password}")
	String adminPassword;

	@Autowired
	LoginPage loginPage;

	@Given("^User is at sign in page$")
	public void user_is_at_sign_in_page() throws Throwable {
		loginPage.toPage();
	}

	@Then("^Verify user is at migrate console$")
	public void verify_user_is_at_migrate_console() throws Throwable {
		loginPage.waitUntilLogginPageLoaded();
	}

	@When("^Admin sign in$")
	public void admin_sign_in() throws Throwable {
		loginPage.login(adminUser, adminPassword);
	}

	@Then("^Verify admin is logged in$")
	public void verify_admin_is_logged_in() throws Throwable {
		loginPage.verifyAdminLoggedIn();
	}

	@Then("^Logout user$")
	public void logout_user() throws Throwable {
		loginPage.logout();
	}

	@When("^User sign in$")
	public void user_sign_in(DataTable dataTable) throws Throwable {
		List<String> list = dataTable.raw().get(0);
		String userName = list.get(0);
		String userPasswrod = list.get(1);
		loginPage.login(userName, userPasswrod);
	}

	@Then("^Verify authentication failed error$")
	public void verifyInvalidUserMessage(DataTable dataTable) {
		List<String> list = dataTable.raw().get(0);
		String errorMessage = list.get(0);
		loginPage.verifyErrorNotificationMessage(errorMessage);
	}

	@When("^user click on sign in$")
	public void user_click_on_sign_in() throws Throwable {
		loginPage.verifyEmptyFieldsLoggedIn();
	}

}
