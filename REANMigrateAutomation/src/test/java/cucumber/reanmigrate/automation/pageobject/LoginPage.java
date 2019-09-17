package cucumber.reanmigrate.automation.pageobject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import cucumber.reanmigrate.automation.pageobject.Abstract;

import cucumber.reanmigrate.automation.pageobject.LoginPage;

import cucumber.reanmigrate.automation.utils.AutomationUtils;

@Component
@ConfigurationProperties(prefix = "loginpage")

public class LoginPage extends Abstract {

	@Value("${cucumber.reanmigrate.automation.common.platform_url}")
	String platformUrl;

	public LoginPage toPage() {
		fullScreen();
		navigate(platformUrl);
		waitUntilLogginPageLoaded();
		return this;
	}

	public void login(String userName, String userPassword) {
		editText("loginUserEmail", userName);
		editText("loginUserPassword", userPassword);
		waitUntilElementWithIdPresent("login-signin-btn");
		clickId("login-signin-btn");
	}

	public void waitUntilLogginPageLoaded() {
		waitUntilTextPresent("Welcome To REAN Platform", 20);
		AutomationUtils.sleepInSec(2);

	}

	public void logout() {
		clickElementWithJsUsingFluentWait("//i[contains(@class,'deploynow-new-icons fa user-icon')]");
		clickIdByJs("userLogoutlink");
	}

	public void verifyAdminLoggedIn() {
		isElementPresentAndContaintsText("//img[contains(@alt,'REAN Migrate')]", "REAN Migrate");
	}

	public void verifyEmptyFieldsLoggedIn() {
		isElementDisabled("login-signin-btn");
	}
}
