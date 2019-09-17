package cucumber.reanmigrate.automation.pageobject;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import cucumber.reanmigrate.automation.pageobject.Abstract;

@Component
@ConfigurationProperties(prefix = "discoveryPage")
public class DiscoveryPage extends Abstract {

	public void navigate() {
		clickElementWithXPath("//i[@class='migrate-icons migrate-create-icon']");
		waitUntilElementWithXPathPresent("//a[@ui-sref='discovery.create']");
		clickElementWithXPath("//a[@ui-sref='discovery.create']");
	}

	public void enterDiscoveryDetails(String discoveryName, String description, String discoveryTool) {
		editTextWithName("name", discoveryName);
		editTextWithXPath("//input[@name='description']", description);
		clickElementWithXPath("//md-select");
		clickElementWithXPath("//md-select-menu[@role='presentation']//md-option[@value='" + discoveryTool + "']");
	}

	public void uploadFileInTestData(String path, String uploadButtonXpath) {
		try {
			path = "/testdata/" + path;
			WebElement element = getElement(uploadButtonXpath);
			URL resource = Abstract.class.getResource(path);
			String OS = System.getProperty("os.name").toLowerCase();
			String path2;
			if (OS.contains("win")) {
				path2 = resource.getPath().substring(1);
				System.out.printf("it's windows and path is " + path2);
			} else {
				path2 = resource.getPath();
				System.out.printf("it's mac and path is " + path2);
			}
			element.sendKeys(path2);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
