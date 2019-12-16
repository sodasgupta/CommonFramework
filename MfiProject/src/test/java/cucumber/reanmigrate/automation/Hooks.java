package cucumber.reanmigrate.automation;



import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
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
	
	
	/*@After
	public void readDown() {
		logger.info("Tearing down selenium drivers");
		driverFactory.removeDriver();
	}*/
	
	
	@After
    public void onTestFailure(Scenario tr) {
        if(tr.isFailed())
        {
            String path=System.getProperty("user.dir");
            File screenshot = new File("target"+File.separator+"reports"+File.separator+"screenshots" + File.separator + System.currentTimeMillis() + "_" + tr.getName() + ".png");
            if (!screenshot.exists()) {
            new File(screenshot.getParent()).mkdirs();
            try {
                screenshot.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            try {
            new FileOutputStream(screenshot).write(((TakesScreenshot) driverFactory.getDriver()).getScreenshotAs(OutputType.BYTES));
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            }
            System.out.println("Written screenshot to " + screenshot.getAbsolutePath());
            try{
            Reporter.addScreenCaptureFromPath(screenshot.getPath().toString().replace("target/reports/", "")); 
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
        driverFactory.removeDriver();
    }

	

}
