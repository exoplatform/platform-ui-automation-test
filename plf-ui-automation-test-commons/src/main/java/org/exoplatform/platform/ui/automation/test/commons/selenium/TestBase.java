package org.exoplatform.platform.ui.automation.test.commons.selenium;

import static org.exoplatform.platform.ui.automation.test.config.Logger.info;
import static org.exoplatform.platform.ui.automation.test.config.Logger.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.exoplatform.platform.ui.automation.test.commons.selenium.testbase.DateTestBase;
import org.exoplatform.platform.ui.automation.test.commons.selenium.testbase.DefaultDataTestBase;
import org.exoplatform.platform.ui.automation.test.commons.selenium.testbase.LocatorTestBase;
import org.exoplatform.platform.ui.automation.test.commons.selenium.testbase.ManageFileTestBase;
import org.exoplatform.platform.ui.automation.test.config.Driver;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class TestBase {

  /** REFACTORING: Use delegate to new classes  */
  private final ManageFileTestBase manageFileTestBase = new ManageFileTestBase(this);
  private final DateTestBase       dateTestBase       = new DateTestBase(this);

  public        WebDriver          driver             = new Driver().createWebDriver();
	public WebDriver newDriver;
	public Boolean isDriver = true;

	protected int DEFAULT_TIMEOUT = 30000; //milliseconds = 30 seconds
	protected int WAIT_INTERVAL = 1000; //milliseconds
	public int loopCount = 0;
	protected boolean ieFlag;
	protected boolean chromeFlag;

	protected String plfVersion = "";
	public final int ACTION_REPEAT = 5;
	public static boolean firstTimeLogin = false;
	public Actions action;

	//Driver path
	public String uploadfile= manageFileTestBase.getAbsoluteFilePath("TestData\\attachFile.exe");
	public String downloadfile= manageFileTestBase.getAbsoluteFilePath("TestData\\downloadIE9.exe");
	public String ieDriver= manageFileTestBase.getAbsoluteFilePath("TestData\\IEDriverServer.exe");
	public String chromeDriver= manageFileTestBase.getAbsoluteFilePath("TestData\\chromedriver.exe");
	public String chromeDriverUbuntu= manageFileTestBase.getAbsoluteFilePath("TestData\\chromedriver");

	/*========System Property====================*/
	public static String baseUrl;
	public static String browser;
	public static String server;

	protected Boolean isRandom;
	protected Boolean isUseFile;

	protected String jdbcDriver;
	protected String dbUrl;
	protected String user;
	protected String pass;

	protected String sqlWiki;
	protected String sqlAttach;
	protected String sqlUser;
	protected String sqlContent;

	protected String defaultSheet;

  protected String nativeEvent;

	protected static String ssoType;

	/**
	 * Get System Property
	 */
	public void getSystemProperty(){
		nativeEvent = System.getProperty("nativeEvent");

		ssoType = System.getProperty("ssoType");

		jdbcDriver = System.getProperty("jdbcDriver");
		dbUrl = System.getProperty("dbUrl");
		user = System.getProperty("user");
		pass = System.getProperty("pass");
		sqlWiki = System.getProperty("sqlWiki");
		sqlAttach = System.getProperty("sqlAttach");
		sqlUser = System.getProperty("sqlUser");

		defaultSheet = System.getProperty("defaultSheet");

		if (ssoType==null) ssoType = DefaultDataTestBase.DEFAULT_SSOTYPE;

		if (nativeEvent==null) nativeEvent = DefaultDataTestBase.DEFAULT_NATIVE_EVENT;
		if (browser==null) browser = DefaultDataTestBase.DEFAULT_BROWSER;
		if (baseUrl==null) baseUrl = DefaultDataTestBase.DEFAULT_BASEURL;
		if (server==null) server = DefaultDataTestBase.DEFAULT_SERVER;

		if (isRandom==null) isRandom = DefaultDataTestBase.DEFAULT_ISRANDOM;
		if (isUseFile==null) isUseFile = DefaultDataTestBase.DEFAULT_ISUSEFILE;

		if (jdbcDriver==null) jdbcDriver = DefaultDataTestBase.DEFAULT_JDBCDRIVER;
		if (dbUrl==null) dbUrl = DefaultDataTestBase.DEFAULT_DBURL;
		if (user==null) user = DefaultDataTestBase.DEFAULT_USERMYSQL;
		if (pass==null) pass = DefaultDataTestBase.DEFAULT_USERPASS;

		if (sqlWiki==null) sqlWiki = DefaultDataTestBase.DEFAULT_SQLWIKI;
		if (sqlAttach==null) sqlAttach = DefaultDataTestBase.DEFAULT_SQLATTACHMENT;
		if (sqlUser==null) sqlUser = DefaultDataTestBase.DEFAULT_SQLUSER;
		if (sqlContent==null) sqlContent = DefaultDataTestBase.DEFAULT_SQLCONTENT;

		if (defaultSheet==null) defaultSheet = DefaultDataTestBase.DEFAULT_SHEET;

	}

	/**
	 * Create new first account
	 */
	public void accountSetupWithoutGreeting(){
		click(LocatorTestBase.ELEMENT_INPUT_USERNAME);
		type(LocatorTestBase.ELEMENT_INPUT_USERNAME, "fqa", true);
		type(LocatorTestBase.ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(LocatorTestBase.ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(LocatorTestBase.ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);
		type(LocatorTestBase.ELEMENT_INPUT_PASSWORD, "gtngtn", true);
		type(LocatorTestBase.ELEMENT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
		type(LocatorTestBase.ELEMENT_ROOT_PASS_ACCOUNT, "gtngtn", true);
		type(LocatorTestBase.ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
		//click(LocatorTestBase.ELEMENT_SUBMIT_BUTTON);
		clickByJavascript(LocatorTestBase.ELEMENT_SUBMIT_BUTTON, 2);
		waitForTextNotPresent("Create your account");
	}

	/**
	 * Account setup
	 */
	public void accountSetup(){
		accountSetupWithoutGreeting();
		click(LocatorTestBase.ELEMENT_START_BUTTON,0,true);
		waitForAndGetElement(LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK);
	}

	/**
	 * Get element
	 * @param locator
	 * @param opParams
	 * @return an element
	 */
	public WebElement getElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By)locator : By.xpath(locator.toString());
		WebDriver wDriver;
		if(isDriver)
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: driver);
		else
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: newDriver);
		WebElement elem = null;
		try {
			elem = wDriver.findElement(by);
		} catch (NoSuchElementException e) {

		}
		return elem;
	}

	/**
	 * get an element
	 * @param locator
	 * @param opParams
	 * @return element
	 */
	public WebElement getDisplayedElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By)locator : By.xpath(locator.toString());
		WebDriver wDriver;
		if(isDriver)
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: driver);
		else
			wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: newDriver);
		WebElement e = null;
		try {
			if(by != null)
				e = wDriver.findElement(by);
			if (e != null){
				if (isDisplay(by)) return e;
			}
		} catch (NoSuchElementException ex) {
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		}
		finally{
			loopCount=0;
		}
		return null;
	}

	/**
	 * verify element exists or not
	 * @param locator
	 * @return true if element exists
	 * 			false if element doesn't exist
	 */
	public boolean isElementPresent(Object locator) {
		return getElement(locator) != null;
	}

	/**
	 * verify element exists or not
	 * @param locator
	 * @return true if element doesn't exists
	 * 			false if element exist
	 */
	public boolean isElementNotPresent(Object locator) {
		return !isElementPresent(locator);
	}

	/**
	 * Get element
	 * @param locator
	 * 					locator of element
	 * @param opParams
	 * 					opPram[0]: timeout
	 * 					opPram[1]: 0,1
	 * 					0: No Assert
	 * 					1: Assert
	 * @return an element
	 */
	public WebElement waitForAndGetElement(Object locator, Object... opParams) {
		WebElement elem = null;
		int timeout = (Integer) (opParams.length>0 ? opParams[0] : DEFAULT_TIMEOUT);
		int isAssert = (Integer) (opParams.length > 1 ? opParams[1]: 1);
		int notDisplayE = (Integer) (opParams.length > 2 ? opParams[2]: 0);
		WebDriver wDriver;
		if(isDriver)
			wDriver = (WebDriver) (opParams.length > 3 ? opParams[3]: driver);
		else
			wDriver = (WebDriver) (opParams.length > 3 ? opParams[3]: newDriver);
		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2){
				elem = getElement(locator,wDriver);
			}else{
				elem = getDisplayedElement(locator,wDriver);
			}
			if (null != elem) return elem;
			Utils.pause(WAIT_INTERVAL);
		}
		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element present: " + locator);
		info("cannot find element after " + timeout/1000 + "s.");
		return null;
	}

	/**
	 * Get element
	 * @param locator
	 * 					locator of element
	 * @param opParams
	 * 					opPram[0]: timeout
	 * 					opPram[1]: 0,1
	 * 					0: No Assert
	 * 					1: Assert
	 * @return	an element
	 */
	public WebElement waitForElementNotPresent(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1]: 1;
		int notDisplayE = opParams.length > 2 ? opParams[2]: 0;

		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2){
				elem = getElement(locator);
				//elem = getDisplayedElement(locator);
			}else{
				elem = getDisplayedElement(locator);
			}
			if (null == elem) return null;
			Utils.pause(WAIT_INTERVAL);
		}

		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element not present: " + locator);
		info("Element doesn't disappear after " + timeout/1000 + "s.");
		return elem;
	}

	/**
	 *
	 * @param text
	 * @param opts
	 * @return 	true if text exist
	 * 			false if test is not exist
	 */
	public boolean isTextPresent(String text, int...opts) {
		int display = opts.length > 0 ? opts[0] : 1;
		Utils.pause(500);
		String allVisibleTexts = getText(By.xpath("//body"),display);
		return allVisibleTexts.contains(text);
	}

	/**
	 * get text of element
	 * @param locator
	 * @param opts
	 * @return text of element
	 */
	public String getText(Object locator,int...opts) {
		WebElement element = null;
		int display = opts.length > 0 ? opts[0] : 1;
		try {
			element = waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,display);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			return getText(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get list of element
	 * @param xpath
	 * @return list of elements
	 */
	public List<WebElement> getElements(String xpath) {
		try {
			return driver.findElements(By.xpath(xpath));
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			return getElements(xpath);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * verify text exists or noet
	 * @param text
	 * @return true if text exists
	 * 			false if text doesn't exits
	 */
	public boolean isTextNotPresent(String text) {
		return !isTextPresent(text);
	}

	/**
	 * drag and drop element
	 * @param sourceLocator
	 * @param targetLocator
	 */
	public void dragAndDropToObject(Object sourceLocator, Object targetLocator) {
		info("--Drag and drop to object--");
		Actions action = new Actions(driver);
		try {
			WebElement source = waitForAndGetElement(sourceLocator);
			WebElement target = waitForAndGetElement(targetLocator);

			action.dragAndDrop(source, target).build().perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			dragAndDropToObject(sourceLocator, targetLocator);
		}catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				switchToParentWindow();
			} catch (NoAlertPresentException eNoAlert) {
			}
		}

		finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}
	/**
	 * Drag an object
	 * @param sourceLocator
	 * @param targetLocator
	 */
	public void dragObject(String sourceLocator, String targetLocator){
		info("--Drag an object--");
		Actions action = new Actions(this.driver);
		WebElement source = waitForAndGetElement(sourceLocator);
		WebElement target = waitForAndGetElement(targetLocator);

		try {
			action.clickAndHold(source).moveToElement(target).release().build().perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			action.clickAndHold(source).moveToElement(target).release().build().perform();
		}catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				switchToParentWindow();
			} catch (NoAlertPresentException eNoAlert) {
			}
		}

		finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}
	/**
	 * Click by using javascript
	 * @param locator
	 * @param opParams
	 */
	public void clickByJavascript(Object locator, Object... opParams){
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		WebElement e = null;
		if(locator instanceof WebElement){
			e=(WebElement) locator;
		}
		else{
			info("wait and get element");
			e = waitForAndGetElement(locator,DEFAULT_TIMEOUT, 1, notDisplay);
		}
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", e);
	}
	/**
	 * Type by java script
	 * @param locatorById
	 * @param value
	 * @param opParams
	 */
	public void typeByJavascript(Object locatorById, String value,Object... opParams){
		Utils.pause(3000);
		((JavascriptExecutor)driver).executeScript("document.getElementById('"+locatorById+"').value='"+value+"'");
	}



	/**
	 * click action
	 * @param locator
	 * @param opParams
	 */
	public void click(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		WebElement element = null;
		Actions actions = new Actions(driver);
		try {
			if(browser.contains("iexplorer")||browser.contains("chrome")){
				info("use javasript to click");
				clickByJavascript(locator, notDisplay);
			}
			else{
				if (!locator.getClass().getName().contains("WebElement")) {
					info("wait and get elements");
					element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
				}
				else{
					element=(WebElement) locator;
				}
				if (browser.contains("chrome")) {
					scrollToElement(element, driver);
				}
				if(element.isEnabled()){
					info("click element");
					actions.click(element).perform();
				}
				else {
					info("Element is not enabled");
					info("click element by javascript");
					clickByJavascript(locator, notDisplay);
				}
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			info("click element by javascript");
			clickByJavascript(locator, notDisplay);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			info("click element by javascript");
			clickByJavascript(locator, notDisplay);
		} finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}


	/**
	 * clear cache
	 */
	public void clearCache(){
		Actions actionObject = new Actions(driver);
		try{
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		} catch(WebDriverException e){
			debug("Retrying clear cache...");
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		}
	}

	/**
	 * Use this function to verify if a check-box is checked (using when creating a portal/publicMode)
	 * @param locator
	 * @param opParams
	 */
	public void check(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
			if (browser.contains("chrome")) {
				scrollToElement(element, driver);
			}
			if (!element.isSelected()) {
				actions.click(element).perform();
				if(waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE).getAttribute("type")!=null && waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE).getAttribute("type")!="checkbox"){
					info("Checkbox is not checked");
					if (!element.isSelected()) {
						info("check by javascript");
						waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
						//mouseOver(locator, true);
						clickByJavascript(locator, notDisplayE);
					}
				}
			} else {
				info("Element " + locator + " is already checked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			check(locator, opParams);
		} finally {
			loopCount = 0;
		}
		Utils.pause(2000);
	}

	/**
	 * get value attribute
	 * @param locator
	 * @return value of element
	 */
	public String getValue(Object locator) {
		try {
			return waitForAndGetElement(locator).getAttribute("value");
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Mouse hover by Javascript
	 * @param locator
	 * @param opParams
	 */
	public void mouseHoverByJavaScript(Object locator, Object...opParams)
	{
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		WebElement targetElement;
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		targetElement = waitForAndGetElement(locator,5000, 1, notDisplay);
		((JavascriptExecutor)driver).executeScript(mouseOverScript, targetElement);
	}

	/**
	 * mouse over action
	 * @param locator
	 * @param safeToSERE
	 * @param opParams
	 */
	public void mouseOver(Object locator, boolean safeToSERE, Object...opParams) {
		WebElement element;
		Actions actions = new Actions(driver);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		try {
			if (safeToSERE) {
				for (int i = 1; i < ACTION_REPEAT; i++){
					if (!locator.getClass().getName().contains("WebElement")) {
						element = waitForAndGetElement(locator, 5000, 0, notDisplay);
					}
					else{
						element=(WebElement) locator;
					}
					if (element == null){
						Utils.pause(WAIT_INTERVAL);
					} else {
						actions.moveToElement(element).perform();
						break;
					}
				}
			} else {
				if (!locator.getClass().getName().contains("WebElement")) {
					element = waitForAndGetElement(locator);
				}
				else{
					element=(WebElement) locator;
				}
				actions.moveToElement(element).perform();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			mouseOver(locator, safeToSERE);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * mouse over and clic
	 * @param locator
	 */
	public void mouseOverAndClick(Object locator) {
		WebElement element;
		Actions actions = new Actions(driver);
		if (ieFlag) {
			element = getDisplayedElement(locator);
		} else {
			element = waitForAndGetElement(locator);
		}
		actions.moveToElement(element).click(element).build().perform();
	}

	/**
	 * wait for text present
	 * @param text
	 * @param opts
	 */
	public void waitForTextPresent(String text, int...opts) {
		int waitTime = opts.length > 0 ? opts[0] : DEFAULT_TIMEOUT;
		int display = opts.length > 1 ? opts[1] : 1;
		for (int second = 0;; second++) {
			if (second >= waitTime/WAIT_INTERVAL) {
				Assert.fail("Timeout at waitForTextPresent: " + text);
			}
			if (isTextPresent(text,display)) {
				break;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	/**
	 * wait for text not present
	 * @param text
	 * @param wait
	 */
	public void waitForTextNotPresent(String text,int...wait) {
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		for (int second = 0;; second++) {
			if (second >= waitTime/WAIT_INTERVAL) {
				Assert.fail("Timeout at waitForTextNotPresent: " + text);
			}
			if (isTextNotPresent(text)) {
				break;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	/**
	 * wait for msg
	 * @param message
	 * @param wait
	 */
	public void waitForMessage(String message,int...wait) {
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		Utils.pause(500);
		waitForAndGetElement("//*[contains(text(),'"+message+"')]",waitTime);
	}

	/**
	 * type to textbox
	 * @param locator
	 * @param value
	 * @param validate
	 * @param opParams
	 */
	public void type(Object locator, String value, boolean validate, Object...opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		try {
			for (int loop = 1;; loop++) {
				if (loop >= ACTION_REPEAT) {
					Assert.fail("Timeout at type: " + value + " into " + locator);
				}
				WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
				if (element != null){
					if (validate) element.clear();
					element.click();
					element.sendKeys(value);
					if (!validate || value.equals(getValue(locator))) {
						break;
					}
				}
				info("Repeat action..." + loop + "time(s)");
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Select option from combo box
	 * @param locator
	 * @param option
	 * @param display
	 */
	public void select(Object locator, String option, int...display) {
		int isDisplay = display.length > 0 ? display[0] : 1;
		try {
			for (int second = 0;; second++) {
				if (second >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
					Assert.fail("Timeout at select: " + option + " into " + locator);
				}
				Select select = new Select(waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,isDisplay));
				select.selectByVisibleText(option);
				if (option.equals(select.getFirstSelectedOption().getText())) {
					break;
				}
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			select(locator, option);
		} finally {
			loopCount = 0;
		}
		Utils.pause(500);
	}

	/**
	 * un-check a checked-box
	 * @param locator
	 * @param opParams
	 */
	public void uncheck(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);

			if (element.isSelected()) {
				actions.click(element).perform();
				if (element.isSelected()) {
					info("uncheck by javascript");
					waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
					clickByJavascript(locator, notDisplayE);
				}
			} else {
				info("Element " + locator + " is already unchecked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			uncheck(locator, opParams);
		} finally {
			loopCount = 0;
		}
		Utils.pause(2000);
	}

	/**
	 * rightClickOnElement
	 * @param locator
	 * @param opParams
	 */
	public void rightClickOnElement(Object locator, int...opParams) {
		int display = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		Utils.pause(500);
		try {
			WebElement element = waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,display);
			actions.contextClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			rightClickOnElement(locator);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			click(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * doubleClickOnElement
	 * @param locator
	 */
	public void doubleClickOnElement(Object locator) {
		Actions actions = new Actions(driver);
		WebElement element;
		try {
			if (!locator.getClass().getName().contains("WebElement")) {
				element = waitForAndGetElement(locator);
			}
			else{
				element=(WebElement) locator;
			}
			actions.doubleClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			doubleClickOnElement(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * checkCycling
	 * @param e
	 * @param loopCountAllowed
	 */
	public void checkCycling(Exception e, int loopCountAllowed) {
		info("Exception:" + e.getClass().getName());
		if (loopCount > loopCountAllowed) {
			Assert.fail("Cycled: " + e.getMessage());
		}
		info("Repeat... " + loopCount + "time(s)");
		loopCount++;
	}

	/**
	 * function to switch to parent windows
	 */
	public void switchToParentWindow (){
		try
		{
			Set<String> availableWindows = driver.getWindowHandles();
			String WindowIdParent= null;
			int counter = 1;
			for (String windowId : availableWindows) {
				if (counter == 1){
					WindowIdParent = windowId;
				}
				counter++;
			}
			driver.switchTo().window(WindowIdParent);
			Utils.pause(1000);
		}
		catch (WebDriverException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * check element displays or net
	 * @param locator
	 * @return true if element displays
	 * 			false if element doesn't display
	 */
	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try{
			if (e!=null)
				bool = e.isDisplayed();
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			isDisplay(locator);
		}
		finally{
			loopCount=0;
		}
		return bool;
	}

	/**
	 * function set driver to auto open new window when click link
	 */
	public void getDriverAutoOpenWindow(){
		FirefoxProfile fp = new FirefoxProfile();
		fp.setPreference("browser.link.open_newwindow.restriction", 2);
		driver = new FirefoxDriver(fp);
		baseUrl = System.getProperty("baseUrl");
		if (baseUrl==null) baseUrl = DefaultDataTestBase.DEFAULT_BASEURL;
		action = new Actions(driver);
		termsAndConditions();
	}

  /**
	 *
	 * define language
	 *
	 */
	public enum Language{
		en, fr, vi, lo;
	}

	/**
	 * set language
	 * @param language
	 */
	public void getDriverSetLanguage(Language language){
		String locale = language.toString();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", locale);
		driver = new FirefoxDriver(profile);
		baseUrl = System.getProperty("baseUrl");
		if (baseUrl==null) baseUrl = DefaultDataTestBase.DEFAULT_BASEURL;
		action = new Actions(driver);
		termsAndConditions();
	}

  /**
	 * Change attribute "display" of HTML tag from "none" to "block"
	 * @param locator
	 */
	public void changeDisplayAttributeHTML(Object locator){
		WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, 2);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';",element);
	}


	/**
	 * setPreferenceRunTime
	 */
	public void setPreferenceRunTime(){
		FirefoxProfile fp = new FirefoxProfile();

		fp.setPreference("dom.max_script_run_time", 30);
	}



	/**
	 * get random string
	 * @return random string
	 */
	public String getRandomString(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * get a list of random numbers
	 * @return random numbers
	 */
	public String getRandomNumber() {
		char[] chars = "0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
	/**
	 * Create a String list by size
	 * @param name
	 *             is the name of array's members
	 * @param size
	 * @return value
	 */
	public ArrayList<String> getListData(String name,int size){
		ArrayList<String> array = new ArrayList<String>();
		for(int i=1;i<size;i++){
			String item = name+" "+String.valueOf(i);
			array.add(item);
		}
		return array;
	}

	/**
	 * Copy and paste a string from one locator to other
	 *
	 * @param origin
	 * @param target
	 * @param value
	 */
	public void copyPasteString(By origin, By target, String value) {
		WebElement element1 = driver.findElement(origin);
		WebElement element2 = driver.findElement(target);

		info("Type into the first locator");
		element1.clear();
		element1.click();
		element1.sendKeys(value);

		info("Copy from the first locator");
		element1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element1.sendKeys(Keys.chord(Keys.CONTROL, "c"));

		info("Paste to the second locator");
		element2.click();
		element2.sendKeys(Keys.chord(Keys.CONTROL, "v"));
	}

  /**
	 * @param object
	 * @return = true: if there is not scroll bar on element
	 *         = false: if there is scroll bar on element
	 */
	public boolean checkExitScrollBar(By object){
		WebElement element = waitForAndGetElement(object);
		String scrollHeight = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].scrollHeight;", element));
		String offsetHeight = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].offsetHeight;", element));
		info("scrollHeight: " + scrollHeight);
		info("offsetHeight: " + offsetHeight);
		int scroll = Integer.parseInt(scrollHeight);
		int offset = Integer.parseInt(offsetHeight);
		return scroll == offset;
	}

	/**
	 * function get an element from link text when cannot get by text in xpath
	 * @param text
	 * @return an element from link text
	 */
	public WebElement getElementFromTextByJquery(String text){

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Utils.pause(2000);
		try{
			WebElement web = (WebElement) js.executeScript("return $(\"a:contains('" + text + "')\").get(0);");
			return web;
		}catch(org.openqa.selenium.WebDriverException e){
			WebElement web = (WebElement) js.executeScript("return $(\"a:contains('" + text + "')\").get(0);");
			return web;
		}
	}

	/**
	 * scrollBarToGetElement
	 * @param object
	 * @param opParams
	 */
	public void scrollBarToGetElement(By object, int...opParams) {
		int display = opParams.length > 0 ? opParams[0]: 0;
		WebElement element = waitForAndGetElement(object,5000,1,display);
		JavascriptExecutor jse;
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * inputDataToCKEditor
	 * @param framelocator
	 * @param data
	 */
	public void inputDataToCKEditor(By framelocator, String data){
		info("input data to ckeditor");
		Utils.pause(2000);
		try {
			WebElement inputsummary = null;
			WebElement e = waitForAndGetElement(framelocator,DEFAULT_TIMEOUT,1,2);
			driver.switchTo().frame(e);
			inputsummary = driver.switchTo().activeElement();
			inputsummary.click();
			inputsummary.clear();
			if ("iexplorer".equals(browser)){
				if ("true".equals(nativeEvent)){
					info("Set nativeEvent is TRUE");
					((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + data + "' + document.body.innerHTML;");
				}
				else{
					info("Set nativeEvent is FALSE");
					//inputsummary.sendKeys(data);
					((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + data + "' + document.body.innerHTML;");
				}
			} else {
				((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + data + "' + document.body.innerHTML;");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			inputDataToCKEditor (framelocator, data);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			inputDataToCKEditor (framelocator,data);
		}catch (WebDriverException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			inputDataToCKEditor (framelocator,data);
		}
		switchToParentWindow();
	}
	/**
	 * Press Enter key
	 */
	public void pressEnterKey(){
		action.sendKeys(Keys.ENTER).perform();
		action.release();
	}
	/**
	 * Press End Key
	 * @param driver
	 */
	public void pressEndKey(WebDriver driver){
		info("Press End key");
		action = new Actions(driver);
		action.sendKeys(Keys.END).perform();
		action.release();
	}

	public void pressHomeKey(WebDriver driver){
		info("Press Home key");
		action = new Actions(driver);
		action.sendKeys(Keys.HOME).perform();
		action.release();
	}

  /**
	 * This function returns a absolute path from a relative path
	 * @param relativeFilePath
	 * @return - FQA-2092: Run and check calendar sniff on IE and FF
	 */
	public String getAbsoluteFilePath(String relativeFilePath){
    return manageFileTestBase.getAbsoluteFilePath(relativeFilePath);
  }

	/**
	 * Get a File Content
	 * @param filePath
	 * @return fileContent
	 */
	public String getFileContent(String filePath){
    return manageFileTestBase.getFileContent(filePath);
  }

	/**
	 * Get a file name from current Url
	 * @param driver
	 * @param params
	 * @return fileName
	 */
	public String getFileNameFromCurrentUrl(WebDriver driver, Object...params){

    return manageFileTestBase.getFileNameFromCurrentUrl(driver, params);
  }

	/**
	 * Attach file in attach popup
	 * @param pathFile
	 * @param fileName
	 */
	public void attachFile(String pathFile, String fileName) {
    manageFileTestBase.attachFile(pathFile, fileName, DEFAULT_TIMEOUT, driver);
  }

	/**
	 * Upload file using AutoIt
	 * @param file
	 */
	public void uploadFileUsingAutoIt(String file){
    manageFileTestBase.uploadFileUsingAutoIt(file);
  }

	/**
	 * Download file using autoit
	 * @param file
	 */
	public void downloadFileUsingAutoIt(String file){
    manageFileTestBase.downloadFileUsingAutoIt(file);
  }

	/**
	 * Download file using Robot class
	 * @param element
	 * @throws Exception
	 */
	public void downloadFileUsingRobot(WebElement element) throws Exception {

    // Get the focus on the element..don't use click since it stalls the driver

    //simulate pressing enter

    // Wait for the download manager to open
    // Switch to download manager tray via Alt+N

    // Press S key to save

    // Switch back to download manager tray via Alt+N

    // Tab to X exit key

    // Press Enter to close the Download Manager
    manageFileTestBase.downloadFileUsingRobot(element);
  }

	/**
	 * Download file using Robot class via URL download link
	 * @throws Exception
	 */
	public void downloadFileUsingRobotViaURL() throws Exception {

    //simulate pressing enter

    // Wait for the download manager to open
    // Switch to download manager tray via Alt+N

    // Press S key to save

    // Switch back to download manager tray via Alt+N

    // Tab to X exit key

    // Press Enter to close the Download Manager
    manageFileTestBase.downloadFileUsingRobotViaURL();
  }




	/**
	 * uploadFileUsingRobot
	 * @param fileLocation
	 */
	public void uploadFileUsingRobot(String fileLocation) {
    manageFileTestBase.uploadFileUsingRobot(fileLocation);
  }

  /**
	 * uploadFileUsingRobot using for Document preview
	 * @param fileLocation
	 */
	public void uploadFileUsingRobotDocumentPreview(String fileLocation) {
    //String path=getAbsoluteFilePath(fileLocation.replace("/", fs));
    manageFileTestBase.uploadFileUsingRobotDocumentPreview(fileLocation);
  }

  /**
	 * Scroll to a element on the website
	 * @param element
	 * @param driver
	 */
	public static void scrollToElement(WebElement element, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	/**
	 * Scroll to bottom of the page of website
	 * @param driver
	 */
	public static void scrollToBottomPage(WebDriver driver){
		info("Scroll to the bottom of the page");
		JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight," +
        "document.body.scrollHeight,document.documentElement.clientHeight));");
	}

	/**
	 *This function will try to get an element. if after timeout, the element is not found.
	 *The function will refresh the page and find the element again.
	 * @param element
	 */
	public void waitElementAndTryGetElement(Object element,Boolean... isClicked){
		info("-- Starting finding element --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				if(waitForAndGetElement(element,3000,0)!=null);
				break;
			}
			if (waitForAndGetElement(element, 5000, 0) != null){
				info("Element "+element+" is displayed");
				if(isClicked.length>0 && isClicked[0]==true)
					click(element);
				break;
			}
			info("Retry...[" + repeat + "]");
			this.driver.navigate().refresh();
		}
		Utils.pause(2000);
		info("The elemnt is shown successfully");
	}
	
	/**
	 * Check if a checkbox is checked or not
	 * @Author: QuyenNT
	 * Date: Oct 30, 2015
	 */
	public boolean checkCheckBoxAttribute(String checkedElement){
		info("Check checkbox attribute");
		WebElement checkBox= waitForAndGetElement(checkedElement,2000,2,1);
		if (checkBox != null && !checkBox.isSelected()) {
			info("Checkbox is NOT selected");
			return false;
		} else if (checkBox != null && checkBox.isSelected()){
			info("Checkbox IS SELECTED");
			return true;
		}
		
		return false;
	}
}
