package org.exoplatform.platform.ui.automation.test.commons.selenium.testbase;

import org.openqa.selenium.By;

/**
 * Created by mgreau on 03/02/2017.
 */
public class LocatorTestBase {

  /*======= Welcome Screen (Term and Conditions)   =====*/
  public static final By ELEMENT_REGISTER_SKIP_BUTTON          =By.xpath(".//*[@id='UIPortalLoginFormAction']/input[contains(@value,'Skip')]");
  public static final By ELEMENT_REGISTER_YOUR_SOFTWARE_BUTTON =By.xpath(".//*[@id='UIPortalLoginFormAction']/a");
  /*======= Register Screen =====*/
  public static final By ELEMENT_REGISTER_SKIP_BTN             = By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@name='btnSkip']");

  /*======= Welcome Screen (Term and Conditions) =====*/
  public static final By ELEMENT_FIRSTNAME_ACCOUNT = By.name("firstNameAccount");
  public static final By ELEMENT_LASTNAME_ACCOUNT = By.name("lastNameAccount");
  public static final By ELEMENT_EMAIL_ACCOUNT = By.name("emailAccount");
  public static final By ELEMENT_CONFIRM_PASS_ACCOUNT = By.name("confirmUserPasswordAccount");
  public static final By ELEMENT_ROOT_PASS_ACCOUNT = By.name("adminPassword");
  public static final By ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT = By.name("confirmAdminPassword");
  public static final By ELEMENT_AGREEMENT_CHECKBOX = By.xpath("//*[@id = 'agreement']");
  public static final By ELEMENT_INPUT_USERNAME = By.name("username");
  public static final String ELEMENT_INPUT_USERNAME_ID = "username";
  public static final By ELEMENT_CONTINUE_BUTTON = By.xpath("//button[text()='Continue' and @class='btn active']");
  public static final By ELEMENT_START_BUTTON = By.xpath("//button[text()='Start']");
  public static final By ELEMENT_SUBMIT_BUTTON = By.xpath("//*[text()='Submit']");
  public static final By ELEMENT_INPUT_PASSWORD = By.name("password");
  public static final String ELEMENT_INPUT_PASSWORD_ID="UIPortalLoginFormControl";
  public static final By ELEMENT_ACCOUNT_NAME_LINK = By.xpath("//*[@id='UIUserPlatformToolBarPortlet']/a/img");
  public static final By ELEMENT_PLF_INFORMATION = By.id("platformInfoDiv");

  public static final String ELEMENT_TERM_CONDITION_BOX = "//div[@class='header' and text()='Terms and Conditions Agreement']/..";
  public static final By ELEMENT_CONTINUE_BUTTON_DISABLE = By.xpath("//button[text()='Continue' and @class='btn inactive']");
  public static final By ELEMENT_TERM_CONDITION_CONTENT = By.xpath("//div[@id='AccountSetup' and @class='content']");

  public static final By ELEMENT_ACCOUNT_SETUP = By.xpath("//div[@class='header' and text()='Account Setup']");
  public static final By ELEMENT_USER_ADMIN = By.id("adminFirstName");
  public static final By ELEMENT_SKIP_BUTTON = By.xpath("//button[text()='Skip']");
  public static final By ELEMENT_YOUR_ACCOUNT_LABEL = By.xpath("//h5[contains(text(), 'Create your account')]");
  public static final By ELEMENT_ADMIN_PASS_LABEL = By.xpath("//h5[contains(text(), 'Admin Password')]");
  public static final By ELEMENT_ACCOUNT_ERROR = By.xpath("//*[@class='accountSetupError']");

  //SSO Login with OpenAM
  public static final By ELEMENT_INPUT_PASSWORD_OPENAM = By.name("IDToken2");
  public static final By ELEMENT_INPUT_USERNAME_OPENAM = By.name("IDToken1");
  public static final By ELEMENT_SIGN_IN_BUTTON_OPENAM = By.xpath("//*[@class='button primary' and @value='Log In']");

  //SSO Login with CAS
  public static final By ELEMENT_INPUT_PASSWORD_CAS = By.id("password");
  public static final By ELEMENT_INPUT_USERNAME_CAS = By.id("username");
  public static final By ELEMENT_SIGN_IN_BUTTON_CAS = By.xpath(".//*[contains(@class,'btn-submit')]");

  //Upload file popup
  public static final By ELEMENT_UPLOAD_SELECT_BUTTON = By.xpath("//*[@class='uploadButton']/*[@class='btn']");
  public static final By ELEMENT_UPLOAD_POPUP_FILE = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Attach File']");
  public static final By ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_INPUT = By.name("file");
  public static final By ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_SAVE_BUTTON = By.xpath(".//*[@id='UIAttachFileForm']//button[text()='Save']");
  public static final String ELEMENT_UPLOAD_POPUP_NAMEFILE = "//*[@class='fileNameLabel' and contains(text(),'${fileName}')]";

  public static final By ELEMENT_SAVE_BTN = By.xpath("//*[text()='Save']");


	/*======== End of Term and conditions =====*/

  public static final By ELEMENT_SKIP_REGISTER_BTN = By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@value = 'Skip']");
  public static final By ELEMENT_CONTINUE_BTN = By.xpath(".//*[@id='UIPortalLoginFormAction']//*[@value = 'Continue']");
}
