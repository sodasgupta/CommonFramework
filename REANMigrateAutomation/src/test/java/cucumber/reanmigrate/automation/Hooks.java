package cucumber.reanmigrate.automation;



import java.awt.Toolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	
	@Autowired
	DriverFactory driverFactory;
	
	Logger logger = LoggerFactory.getLogger(Hooks.class);
	
	
	@Before
	public void setup() {
		System.setProperty("java.awt.headless", "false");
		Toolkit.getDefaultToolkit().getSystemClipboard();
		logger.info("Loading selenium drivers ");
		driverFactory.getDriver();
	}
	
	
	@After
	public void readDown() {
		logger.info("Tearing down selenium drivers");
		driverFactory.removeDriver();
	}

	

}
