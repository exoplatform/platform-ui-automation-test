package org.exoplatform.platform.ui.automation.test.commons.selenium.testbase;

import org.exoplatform.platform.ui.automation.test.commons.selenium.TestBase;
import org.exoplatform.platform.ui.automation.test.commons.selenium.Utils;

import static org.exoplatform.platform.ui.automation.test.config.Logger.*;
import static org.exoplatform.platform.ui.automation.test.commons.selenium.testbase.LocatorTestBase.*;

public class TermsAndConditionsTestBase {
  private final TestBase testBase;

  private ElementEventTestBase evt;

  private AccountSetupTestBase acc;

  public TermsAndConditionsTestBase(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.acc = new AccountSetupTestBase(testBase);
  }

  /**
   * Check term and conditions
   * @param opParams
   */
  public void termsAndConditions(Object... opParams){
    info("Term and conditions");
    Boolean isCreateAccount = (Boolean)(opParams.length>0 ? opParams[0]:true);
    testBase.getSeleniumDriver();
    info("Agreement page");
    if (evt.waitForAndGetElement(ELEMENT_REGISTER_SKIP_BTN, testBase.getDefaultTimeout(), 2) != null) {
      info("Skipp register");
      evt.clickByJavascript(ELEMENT_REGISTER_SKIP_BTN, 2);
      Utils.pause(5000);
    }
    if (evt.waitForAndGetElement(ELEMENT_AGREEMENT_CHECKBOX, 3000, 0, 2) != null) {
      info("-- Checking the terms and conditions agreement... --");
      evt.click(ELEMENT_AGREEMENT_CHECKBOX, 2);
      evt.click(ELEMENT_CONTINUE_BUTTON);
      evt.waitForTextNotPresent("terms and conditions agreement");

      if(evt.waitForAndGetElement(ELEMENT_REGISTER_SKIP_BUTTON,3000,0,2)!=null){
        info("-- Skipping register account--");
        evt.click(ELEMENT_REGISTER_SKIP_BUTTON);
        evt.waitForElementNotPresent(ELEMENT_REGISTER_SKIP_BUTTON);
      }

    }else if(evt.waitForAndGetElement(ELEMENT_REGISTER_SKIP_BUTTON,3000,0,2)!=null){
      info("-- Skipping register account--");
      info("Click on Continue button");
      if(evt.waitForAndGetElement(ELEMENT_CONTINUE_BTN,3000,0,2)!=null){
        evt.click(ELEMENT_CONTINUE_BTN);
        evt.waitForElementNotPresent(ELEMENT_CONTINUE_BTN);
      }else{
        evt.click(ELEMENT_REGISTER_SKIP_BUTTON);
        evt.waitForElementNotPresent(ELEMENT_REGISTER_SKIP_BUTTON);
      }

    }
    if (evt.waitForAndGetElement(ELEMENT_ROOT_PASS_ACCOUNT, 3000, 0, 2) != null){
      info("-- Creating an Admin account: FQA... --");
      if(isCreateAccount==true){
        acc.accountSetup();
        info("-- Administrator account (FQA) has been created successfully... --");
        testBase.getSeleniumDriver().navigate().refresh();
      }
    }
    Utils.pause(3000);
    info("End of term and conditions");
  }
}
