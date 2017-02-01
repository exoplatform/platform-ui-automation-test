package org.exoplatform.platform.ui.automation.test.config;

import org.exoplatform.platform.ui.automation.test.commons.selenium.testbase.DefaultDataTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;

import static org.exoplatform.platform.ui.automation.test.config.Logger.info;

/**
 * This class intend to manage the Selenide and Selenium Driver configuration for the whole UI Automation Tests.
 *
 * <p>
 *   <i>Refactoring:</i><br/>
 *   This class contains the old code from TestBase about Driver configuration.
 * </p>
 */
public class Driver {

  private WebDriver driver;

  private String browser;

  private String server;

  private String baseUrl;

  public Driver() {
    browser = System.getProperty("browser");
    server = System.getProperty("server");
    baseUrl = System.getProperty("baseUrl");
  }

  public WebDriver createWebDriver() {

    return initNewDriver(browser);
  }

  /**
   * change lanugage of browser
   *
   * @param language English: "en"
   *                 French: "fr"
   */
  public void initFFBrowserWithSetLanguageBrowser(String language) {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("intl.accept_languages", language);
    driver = new FirefoxDriver(profile);
    baseUrl = System.getProperty("baseUrl");
    if (baseUrl == null)
      baseUrl = DefaultDataTestBase.DEFAULT_BASEURL;
    action = new Actions(driver);
  }

  /**
   * init browser
   *
   * @param opParams
   */
  public void initSeleniumTest(Object... opParams) {
    initSeleniumTestWithOutTermAndCondition();
    driver.manage().window().maximize();
    driver.navigate().refresh();
    termsAndConditions(opParams);
  }

  /**
   * initNewIEBrowserWithNativeEvent
   */
  public void initNewIEBrowserWithNativeEvent() {
    info("initNewIEBrowserWithNativeEvent");
    System.setProperty("webdriver.ie.driver", ieDriver);
    DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
    capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
    capabilitiesIE.setCapability("nativeEvents", true);
    capabilitiesIE.setCapability("ignoreZoomSetting", true);
    capabilitiesIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
    capabilitiesIE.setCapability("initialBrowserUrl", baseUrl);
    newDriver = new InternetExplorerDriver(capabilitiesIE);
  }

  public void initSeleniumTestWithOutTermAndCondition(Object... opParams) {

    if ("chrome".equals(browser)) {
      driver = initChromeDriver();
      chromeFlag = true;
    } else if ("iexplorer".equals(browser)) {
      driver = initIEDriver();
      ieFlag = true;
    } else {
      driver = initFFDriver();
    }
    action = new Actions(driver);
  }

  /**
   * init newDriver
   */
  public WebDriver initNewDriver(final String browser) {
    WebDriver newDriver;
    if ("chrome".equals(browser)) {
      newDriver = initChromeDriver();
    } else if ("iexplorer".equals(browser)) {
      newDriver = initIEDriver();
      ieFlag = true;
    } else {
      newDriver = initFFDriver();
    }
    newDriver.manage().window().maximize();
  }

  /**
   * Start new driver without cache
   */
  public void startNewDriver() {
    if ("chrome".equals(browser)) {
      newDriver = new ChromeDriver();
    } else if ("iexplorer".equals(browser)) {
      newDriver = new InternetExplorerDriver();
      ieFlag = true;
    } else {
      newDriver = new FirefoxDriver();
    }
    newDriver.manage().window().maximize();
    newDriver.navigate().refresh();
    newDriver.navigate().to(baseUrl);
  }

  /**
   * Init Chrome driver
   */
  public ChromeDriver initChromeDriver() {
    info("Init chrome driver");
    String pathFile = "";
    String fs = File.separator;
    String temp = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput";
    ;
    pathFile = temp.replace("/", fs).replace("\\", fs);

    if (server.contains("ubuntu")) {
      System.setProperty("webdriver.chrome.driver", chromeDriverUbuntu);
    } else if (server.contains("win")) {
      System.setProperty("webdriver.chrome.driver", chromeDriver);
    } else {
      System.setProperty("webdriver.chrome.driver", chromeDriverUbuntu);
    }

    // Add the WebDriver proxy capability.
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    String[] switches = { "start-maximized", "remote-debugging-port=9222" };
    capabilities.setCapability("chrome.switches", switches);
    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
    chromePrefs.put("profile.default_content_settings.popups", 0);
    chromePrefs.put("download.default_directory", pathFile);
    ChromeOptions options = new ChromeOptions();
    HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
    options.setExperimentalOption("prefs", chromePrefs);
    options.addArguments("--test-type");
    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    return new ChromeDriver(capabilities);
  }

  /**
   * Init IE driver
   */
  public WebDriver initIEDriver() {
    info("Init IE driver");
    System.setProperty("webdriver.ie.driver", ieDriver);
    DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
    capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
    if ("true".equals(nativeEvent)) {
      info("Set nativeEvent is TRUE");
      capabilitiesIE.setCapability("nativeEvents", true);
    } else {
      info("Set nativeEvent is FALSE");
      capabilitiesIE.setCapability("nativeEvents", false);
    }
    capabilitiesIE.setCapability("javascriptEnabled", true);
    capabilitiesIE.setCapability("requireWindowFocus", true);
    capabilitiesIE.setCapability("enablePersistentHover", false);
    capabilitiesIE.setCapability("ignoreZoomSetting", true);
    capabilitiesIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
    capabilitiesIE.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
    capabilitiesIE.setCapability("initialBrowserUrl", baseUrl);
    return new InternetExplorerDriver(capabilitiesIE);
  }

  /**
   * Init FF driver
   */
  public WebDriver initFFDriver() {
    String pathFile = "";
    if ("win".equals(server)) {
      pathFile = System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\TestOutput";
    } else {
      pathFile = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput";
    }
    info("Init FF driver");
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("plugins.hide_infobar_for_missing_plugin", true);
    profile.setPreference("dom.max_script_run_time", 0);
    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    info("Save file to " + pathFile);
    profile.setPreference("browser.download.manager.showWhenStarting", false);
    profile.setPreference("browser.download.dir", pathFile);
    profile.setPreference("browser.download.folderList", 2);
    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-xpinstall;" +
            "application/x-zip;application/x-zip-compressed;application/x-winzip;application/zip;" +
            "gzip/document;multipart/x-zip;application/x-gunzip;application/x-gzip;application/x-gzip-compressed;" +
            "application/x-bzip;application/gzipped;application/gzip-compressed;application/gzip" +
            "application/octet-stream" +
            ";application/pdf;application/msword;text/plain;" +
            "application/octet;text/calendar;text/x-vcalendar;text/Calendar;" +
            "text/x-vCalendar;image/jpeg;image/jpg;image/jp_;application/jpg;" +
            "application/x-jpg;image/pjpeg;image/pipeg;image/vnd.swiftview-jpeg;image/x-xbitmap;image/png;application/xml;text/xml;text/icalendar;");

    profile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");
    profile.setPreference("pref.downloads.disable_button.edit_actions", true);
    profile.setPreference("pdfjs.disabled", true);
    profile.setPreference("browser.helperApps.alwaysAsk.force", false);
    return new FirefoxDriver(profile);
  }
}



