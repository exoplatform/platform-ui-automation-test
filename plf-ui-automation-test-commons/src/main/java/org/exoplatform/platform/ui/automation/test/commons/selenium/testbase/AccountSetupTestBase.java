package org.exoplatform.platform.ui.automation.test.commons.selenium.testbase;

import org.exoplatform.platform.ui.automation.test.commons.selenium.TestBase;

public class AccountSetupTestBase {
  private final TestBase testBase;

  public AccountSetupTestBase(TestBase testBase) {
    this.testBase = testBase;
  }

  /**
   * Create new first account
   */
  public void accountSetupWithoutGreeting() {
    testBase.getElementEventTestBase().click(LocatorTestBase.ELEMENT_INPUT_USERNAME);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_INPUT_USERNAME, "fqa", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_LASTNAME_ACCOUNT, "VN", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_INPUT_PASSWORD, "gtngtn", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_ROOT_PASS_ACCOUNT, "gtngtn", true);
    testBase.getElementEventTestBase().type(LocatorTestBase.ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
    //click(LocatorTestBase.ELEMENT_SUBMIT_BUTTON);
    testBase.getElementEventTestBase().clickByJavascript(LocatorTestBase.ELEMENT_SUBMIT_BUTTON, 2);
    testBase.getElementEventTestBase().waitForTextNotPresent("Create your account");
  }

  /**
   * Account setup
   */
  public void accountSetup() {
    accountSetupWithoutGreeting();
    testBase.getElementEventTestBase().click(LocatorTestBase.ELEMENT_START_BUTTON, 0, true);
    testBase.getElementEventTestBase().waitForAndGetElement(LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK);
  }
}
