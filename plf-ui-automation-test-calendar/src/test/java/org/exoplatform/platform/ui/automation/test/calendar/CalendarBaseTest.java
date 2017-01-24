package org.exoplatform.platform.ui.automation.test.calendar;

import static org.exoplatform.platform.ui.automation.test.config.LoggerTest.info;

import org.exoplatform.platform.ui.automation.test.calendar.pageobject.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.exoplatform.platform.ui.automation.test.commons.pageobject.Platform;
import org.exoplatform.platform.ui.automation.test.config.BaseTest;

public class CalendarBaseTest extends BaseTest{

  @Before
  public void init() {

    Platform plf = new Platform();
    plf.open();
    plf.ensureLicenseIsAccepted()
       .ensureRegisterSoftwareIsSkipped()
       .ensureAccountSetupIsSkipped()
       .ensureUserIsLoggedIn();

    new Calendar().open();

  }

  @After
  public void stop(){

  }


}
