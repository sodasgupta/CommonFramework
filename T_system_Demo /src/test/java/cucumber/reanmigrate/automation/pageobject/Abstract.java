package cucumber.reanmigrate.automation.pageobject;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.reanmigrate.automation.DriverFactory;
import cucumber.reanmigrate.automation.utils.AutomationUtils;

public abstract class Abstract {

	@Autowired
	DriverFactory driverFactory;

	Boolean found = false;

	public static List<WebElement> allOptions;
	public static final int TIMEOUT = 70;

	String errorNofificationDiv = "//div[@class = 'ui-notification ng-scope error']";

	String successNofificationDiv = "//div[@class = 'ui-notification ng-scope success']";

	String warningNotificationDiv = "//div[@class = 'ui-notification ng-scope warning']";

	public void fullScreen() {
		getDriver().manage().window().setSize(new Dimension(1600, 900));
	}

	public void navigate(final String value) {
		getDriver().navigate().to(value);
	}

	protected String pageTitle() {
		return getDriver().getTitle();
	}

	protected void acceptAlert() {
		(new FluentWait<>(getDriver())).withTimeout(10, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class).until(ExpectedConditions.alertIsPresent());
		getDriver().switchTo().alert().accept();
	}

	protected boolean isThere(final String name) {
		final List<WebElement> listTitles = getDriver()
				.findElements(By.xpath("//*[contains(text(), ' " + name + " ')]"));
		return listTitles.size() == 1 && listTitles.get(0).isDisplayed();
	}

	protected boolean isThere(final String name, String element) {
		final List<WebElement> listTitles = getDriver()
				.findElements(By.xpath(element + "//*[contains(text(), ' " + name + " ')]"));
		return listTitles.size() == 1 && listTitles.get(0).isDisplayed();
	}

	protected void editText(final String id, final String value) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		element.clear();
		element.sendKeys(value);
	}


	protected void editTextByXpath(final String xpath, final String value) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		element.clear();
		element.sendKeys(value);
	}

	protected void editTextAndClick(final String id, final String value) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		element.click();
	}

	protected void editTextById(final String id, String groupName) {
		(new FluentWait<>(getDriver())).withTimeout(10, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)))
				.sendKeys(groupName);

	}

	protected void editTextAndClickSend(final String id, final String value) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		element.click();

	}

	protected void clickId(final String id) {
		waitUntilElementWithIdPresent(id);
		getDriver().findElement(By.id(id)).click();
	}

	protected void clickXpath(final String value) {
		getDriver().findElement(By.xpath("//*[contains(text(), '" + value + "')]")).click();
	}

	protected void clickByJsWithText(final String value) {
		final WebElement button = getDriver().findElement(By.xpath("//*[contains(text(), '" + value + "')]"));
		final JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", button);
	}

	/**
	 * This API should be used only when you are sure that the text occurreance the
	 * page is only once
	 * 
	 * @param value
	 */
	protected void clickElementWithText(final String value) {
		getDriver().findElement(By.xpath("//*[contains(text(), '" + value + "')]")).click();
	}

	protected void clickElementWithText(final String value, String element) {
		getDriver().findElement(By.xpath("//" + element + "[text()='" + value + "']")).click();
	}

	protected boolean hasErrors() {
		final List<WebElement> errors = getDriver().findElements(By.className("error"));
		return (errors.size() > 0) && errors.get(0).isDisplayed();
	}

	public void quit() {
		getDriver().quit();
	}

	public boolean isElementDisabled(String id) {
		WebElement ele = getDriver().findElement(By.id(id));
		return ele.getAttribute("disabled") != null;
	}

	public boolean isElementByXPathDisabled(String xpath) {
		WebElement ele = getDriver().findElement(By.xpath(xpath));
		return ele.getAttribute("disabled") != null;
	}

	public void clickElementHavingClass(String c, String element) {
		getDriver().findElement(By.xpath("//" + element + "[contains(@class, '" + c + "')]")).click();
	}

	protected boolean isElementPresentAndContaintsText(String xpath, String text) {
		final WebElement ele = getDriver().findElement(By.xpath(xpath));
		return ele != null && ele.getText().contains(text);
	}

	protected WebDriver getDriver() {
		return driverFactory.getDriver();
	}

	protected boolean isElementPresentWithXPATH(String xpath) {
		final WebElement ele = getDriver().findElement(By.xpath(xpath));
		return ele != null;
	}

	protected void waitUntilElementWithXPathVisible(String xpath) {
		waitUntilElementWithXPathVisible(xpath, 120);
	}

	protected void waitUntilElementWithXPathVisible(String xpath, int timeInSec) {
		WebElement element = (new FluentWait<>(getDriver())).withTimeout(timeInSec, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	protected void waitUntilElementWithXPathPresent(String xpath) {
		waitUntilElementWithXPathPresent(xpath, 30);

	}

	protected void waitUntilElementWithXPathPresent(String xpath, int sec) {
		(new WebDriverWait(getDriver(), sec)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	protected void waitUntilElementWithIdPresent(String id) {
		waitUntilElementWithIdPresent(id, 30);
	}

	protected void waitUntilElementWithIdPresent(String id, int sec) {
		(new WebDriverWait(getDriver(), sec)).until(ExpectedConditions.elementToBeClickable(By.id(id)));
	}

	protected void waitUntilTextPresent(String text, int sec) {
		(new WebDriverWait(getDriver(), 20))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '" + text + "')]")));
	}

	protected void clickElementWithXPath(String xpath) {
		waitUntilElementWithXPathPresent(xpath);
		getDriver().findElement(By.xpath(xpath)).click();
	}

	protected void clickElementWithXPath(String xpath, int wait) {
		waitUntilElementWithXPathPresent(xpath, wait);
		getDriver().findElement(By.xpath(xpath)).click();
	}

	protected void editTextWithXPath(final String xpath, final String value) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		element.clear();
		element.sendKeys(value);
	}

	protected void editTextWithName(final String name, final String value) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
		element.clear();
		element.sendKeys(value);
	}

	protected List<String> getTextWebElements(String xpath) {
		List<String> getData = new ArrayList<String>();
		List<WebElement> myElements = getDriver().findElements(By.xpath(xpath));
		for (WebElement e : myElements) {
			System.out.println(e.getText());
			getData.add(e.getText());
		}
		return getData;

	}

	protected void enterInJSONEditor(String jsonData, String id) {
		try {
			waitUntilElementWithXPathPresent("//*[@class='ace_cursor']");
			StringSelection selec = new StringSelection(jsonData);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selec, selec);
			String OS = System.getProperty("os.name").toLowerCase();
			Actions action = new Actions(getDriver());
			action.moveToElement(getDriver().findElement(By.xpath("//*[@class='ace_cursor']")));
			if (OS.contains("mac")) {

				action.click().keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(jsonData)
						.sendKeys(Keys.BACK_SPACE).perform();
			} else {
				action.click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(jsonData)
						.sendKeys(Keys.BACK_SPACE).perform();
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AutomationUtils.sleepInSec(2);
	}

	public void verifyErrorNotificationMessage(String error) {
		waitUntilElementWithXPathVisible("//*[@class='message ng-binding']");
		AutomationUtils.sleepInSec(1);
		List<WebElement> message = driverFactory.getDriver().findElements(By.xpath("//*[@class='message ng-binding']"));
		Assert.assertTrue((message.get(message.size() - 1).getAttribute("innerHTML")).contains(error));
		try {
			clickElementWithXPath(errorNofificationDiv + "//*[contains(text(), '" + error + "')]");
		} catch (Exception e) {
			AutomationUtils.sleep(500);
		}

	}

	public void verifySuccessNotificationMessage(String success) {
		waitUntilElementWithXPathVisible("//*[@class='message ng-binding']");
		List<WebElement> message = driverFactory.getDriver().findElements(By.xpath("//*[@class='message ng-binding']"));
		Assert.assertTrue((message.get(message.size() - 1).getAttribute("innerHTML")).contains(success));
		try {
			clickElementWithXPath(successNofificationDiv + "//*[contains(text(), '" + success + "')]");
		} catch (Exception e) {
			AutomationUtils.sleep(500);
		}
	}

	protected void clickByCssselector(final String css) {
		getDriver().findElement(By.cssSelector(css)).click();
	}

	public void verifyWarningNotificationMessage(String warning) {
		waitUntilElementWithXPathVisible("//*[@class='message ng-binding']");
		AutomationUtils.sleepInSec(1);
		List<WebElement> message = driverFactory.getDriver().findElements(By.xpath("//*[@class='message ng-binding']"));
		Assert.assertTrue((message.get(message.size() - 1).getAttribute("innerHTML")).contains(warning));
		try {
			clickElementWithXPath(warningNotificationDiv + "//*[contains(text(), '" + warning + "')]");
		} catch (Exception e) {
			AutomationUtils.sleep(500);
		}
	}

	public void clickOnValueInSelectBox(String selectBoxId, String value) {
		clickId(selectBoxId);
		try {
			clickElementWithXPath(
					"//div[contains(@class, 'md-clickable')]//md-option/div[contains(text(),'" + value + "')]");
		} catch (Exception e) {
		}
	}

	public void clickOnValueInSelectBoxWithXpath(String selectBoxXpath, String value) {
		clickElementWithXPath(selectBoxXpath);
		clickElementWithXPath(
				"//div[contains(@class, 'md-clickable')]//md-option/div[contains(text(),'" + value + "')]");
	}

	public void clickMdButtonWithLabel(String label) {
		String xpath = "//button/span[text()='" + label + "']";
		clickElementWithXPath(xpath);

	}

	public void clickNormalButtonWithLabel(String label) {
		String xpath = "//button[text()='" + label + "']";
		clickElementWithXPath(xpath);

	}

	public void verifyMdButtonWithLabelDisabled(String label, String message) {
		String xpath = "//button/span[text()='" + label + "']/parent::button";
		assert isElementByXPathDisabled(xpath) : message;

	}

	protected void waitForButtonToBeEnabledAndClick(String text) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + text + "')]")));
		element.click();
	}

	protected String getElementTextWithXPath(String xpath, int sec) {
		WebElement element = (new WebDriverWait(getDriver(), sec))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element.getText();
	}

	protected void clickIdByJs(final String id) {
		final WebElement element = getDriver().findElement(By.id(id));
		final JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	protected void clickElementWithJs(final String xpath) {
		final WebElement element = getDriver().findElement(By.xpath(xpath));
		final JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	protected void clickElementWithJsUsingFluentWait(final String xpath) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(50, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		final JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	protected void clickElementUsingFluentWait(final String xpath) {
		final WebElement element = (new FluentWait<>(getDriver())).withTimeout(50, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		element.click();
	}

	protected void clickElementWithJsByCSS(final String CSS) {
		final WebElement element = getDriver().findElement(By.cssSelector(CSS));
		final JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	protected void winHandle() {

		Set<String> winlist = getDriver().getWindowHandles();
		for (String win : winlist) {
			getDriver().switchTo().window(win);
		}
	}

	protected void moveTo(final String xpath) {
		Actions action = new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath(xpath))).build().perform();
	}

	protected void clickAndHold(final String xpath) {
		Actions action = new Actions(getDriver());
		action.clickAndHold(getDriver().findElement(By.xpath(xpath)));
		action.build().perform();
	}

	protected void clickByActions(final String xpath) {
		Actions action = new Actions(getDriver());
		action.click(getDriver().findElement(By.xpath(xpath)));
		action.build().perform();
	}

	protected void dismissAlert() {
		WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
		wait.until(ExpectedConditions.alertIsPresent());
		getDriver().switchTo().alert().dismiss();
	}

	protected void implicitwait(int i, TimeUnit arg1) {
		try {
			getDriver().manage().timeouts().implicitlyWait(i, arg1);
		} catch (Exception e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}

	protected boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
			wait.until(ExpectedConditions.alertIsPresent());
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			throw new NoAlertPresentException();
		}
	}

	protected void scrollDownToElement(final String xpath) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(false);", getDriver().findElement(By.xpath(xpath)));
	}

	protected void scrollUpToElement(final String xpath) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath(xpath)));
	}

	// This will scroll the web page till end.
	protected void scrollPageToBottom(final int position) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	protected void verifyDropdown(final List<WebElement> elements, String value) {

		for (final WebElement element : elements) {
			if (element.getText().contains(value)) {
				found = true;
				element.click();
				break;
			}

		}

	}

	protected void waitForInvisibiltyOfElementWithXpath(String XPath) {
		(new FluentWait<>(getDriver())).withTimeout(1200, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(XPath)));
	}

	public boolean isElementPresent(String xpath) {
		try {
			getDriver().findElement(By.xpath(xpath));
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickOnCrossButtonIcon() {
		clickElementWithXPath("//span[contains(text(),'×')]");
	}

	protected void refresh() {
		driverFactory.getDriver().navigate().refresh();
	}

	protected void waitForInvisibiltyOfElementWithXpath(String XPath, int timeInSecond) {
		(new FluentWait<>(getDriver())).withTimeout(1200, TimeUnit.SECONDS)
				.pollingEvery(timeInSecond, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(XPath)));
	}

	public void clickOnCanvas() {
		Actions actions = new Actions(driverFactory.getDriver());
		actions.moveToElement(driverFactory.getDriver().findElement(By.xpath("//div[@id='canvas']")), 150, 100);
	}

	public WebElement getElement(String xpath) {
		return driverFactory.getDriver().findElement(By.xpath(xpath));
	}

	public WebElement getElementIfExists(String xpath) {
		try {
			return driverFactory.getDriver().findElement(By.xpath(xpath));
		} catch (Exception e) {
		}
		return null;
	}

	public void waitUntillAllElementsLocatedBy(String xpath) {
		(new FluentWait<>(getDriver())).withTimeout(10, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
	}

	public void waitForPopup() {
		isElementPresent("//md-dialog");
		AutomationUtils.sleep(1000);
	}

	public void waitPopupClose() {
		waitForInvisibiltyOfElementWithXpath("//md-dialog");
		AutomationUtils.sleep(1000);
	}

	public void hideNotificationIfExists() {
		WebElement element = getElementIfExists("//div[contains(@class, 'ui-notification')]");
		if (element != null) {
			System.out.println("hiding notification");
			clickElementWithXPath("//div[contains(@class, 'ui-notification')]");
			AutomationUtils.sleep(1500);
		}

	}

	public boolean isElementWithXpathClickable(String xpath) {
		Boolean isClickable;
		try {
			(new WebDriverWait(getDriver(), 4)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			isClickable = true;
		} catch (Exception e) {
			e.printStackTrace();
			isClickable = false;
		}
		return isClickable;
	}

}
