package org.exoplatform.platform.ui.automation.test.commons;

import org.junit.Test;

import org.exoplatform.platform.ui.automation.test.config.BaseTest;
import org.exoplatform.platform.ui.automation.test.commons.pageobject.Login;
import org.exoplatform.platform.ui.automation.test.commons.pageobject.Platform;

public final class LoginTestIT extends BaseTest {

  public LoginTestIT() {
    super();

  }

  @Test
  public void signIn() {
    // Init instance for signInTest
    Platform plf = new Platform();
    plf.open();
    plf.ensureLicenseIsAccepted().ensureRegisterSoftwareIsSkipped().ensureAccountSetupIsSkipped();

    new Login().signIn();

  }

  @Test
  public void signOut() {
    // Init instance for signInTest
    Platform plf = new Platform();
    plf.open();
    plf.ensureLicenseIsAccepted()
       .ensureRegisterSoftwareIsSkipped()
       .ensureAccountSetupIsSkipped()
       .ensureUserIsLoggedIn();

    new Login().signOut();

  }

}
