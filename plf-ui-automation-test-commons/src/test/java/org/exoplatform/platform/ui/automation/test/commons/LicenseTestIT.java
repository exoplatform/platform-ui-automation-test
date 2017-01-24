package org.exoplatform.platform.ui.automation.test.commons;

import org.junit.Test;

import org.exoplatform.platform.ui.automation.test.config.BaseTest;
import org.exoplatform.platform.ui.automation.test.commons.pageobject.Platform;

public final class LicenseTestIT extends BaseTest {

  public LicenseTestIT() {
    super();

  }

  @Test
  public void acceptAgreement() {
    new Platform().open();

  }

}
