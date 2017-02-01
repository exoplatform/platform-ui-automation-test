package org.exoplatform.platform.ui.automation.test.commons;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.exoplatform.platform.ui.automation.test.config.Base;
import org.exoplatform.platform.ui.automation.test.commons.pageobject.Platform;
import org.exoplatform.platform.ui.automation.test.commons.pageobject.RegisterSoftware;

public final class RegisterSoftwareTestIT extends Base {

  public RegisterSoftwareTestIT() {
    super();

  }

  /**
   * Skip Register Software. This test should be executed on a PLF instance
   * where this feature has not be skipped more than 3 times.
   */
  @Test
  public void skipRegisterSoftware() {
    new Platform().open();
    // ensure that LicenseAgreement is accepted
    final RegisterSoftware rg = new RegisterSoftware();
    assertTrue("Actions for Register Software not found, Be sure to have a fresh PLF installation.", rg.element.exists());

    rg.skip();
    assertTrue("Actions for Register Software should not be found.", !rg.element.exists());
  }

}
