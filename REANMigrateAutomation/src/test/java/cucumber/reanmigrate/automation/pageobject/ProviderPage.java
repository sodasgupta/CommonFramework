package cucumber.reanmigrate.automation.pageobject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ProviderPage")
public class ProviderPage extends Abstract {

	

	public void enterProviderName(String providerName) {
		
		editTextWithName(providerName, providerName);

	}

	public void selectOption_provider(String provider) {
		
		
		
		switch (provider.toLowerCase().replace(" ", "")) {

		case "aws":
			clickElementWithXPath("//span[contains(text(),'AWS')]");
			break;
		case "google":
			clickId("//span[contains(text(),'google')]");
			break;
		case "digitalocean":
			clickId("//span[contains(text(),'DigitalOcean')]");
			break;
		case "vsphere ":
			clickId("//span[contains(text(),'vSphere')]");
			break;
		case "azure ":
			clickId("//span[contains(text(),'Azure')]");
			break;
		default:
			System.out.println("Out of bounds. Please enter the correct options");

		}

		
	}

}
