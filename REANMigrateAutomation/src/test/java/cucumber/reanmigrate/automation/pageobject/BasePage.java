
package cucumber.reanmigrate.automation.pageobject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "common")
public class BasePage extends Abstract {

	public void moveToUserProfile() {
		clickIdByJs("userProfilelink");
	}

	public void clickOnSubmitButton() {
		clickElementWithJs("//button[contains(text(),'SUBMIT')]");
	}

}
