package org.exoplatform.platform.ui.automation.test.calendar;

import com.codeborne.selenide.Selenide;

import org.junit.After;
import org.junit.Before;

import org.exoplatform.platform.ui.automation.test.commons.pageobject.Platform;
import org.exoplatform.platform.ui.automation.test.config.UIATBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CalendarUIATBase extends UIATBase {

  @Before
  public void init() {

    Platform plf = new Platform();
    plf.open();
    plf.ensureLicenseIsAccepted()
       .ensureRegisterSoftwareIsSkipped()
       .ensureAccountSetupIsSkipped()
       .ensureUserIsLoggedIn();

    // Go to Calendar Page
    $(By.className("uiIconPLFCalendar")).click();

  }

  @After
  public void stop(){

    Selenide.close();

  }


}
