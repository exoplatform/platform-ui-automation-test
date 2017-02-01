package org.exoplatform.platform.ui.automation.test.commons;

import org.junit.Test;

import org.exoplatform.platform.ui.automation.test.config.Base;
import org.exoplatform.platform.ui.automation.test.commons.pageobject.Platform;

public final class LicenseTestIT extends Base {

  public LicenseTestIT() {
    super();

  }

  @Test
  public void acceptAgreement() {
    new Platform().open();

  }

}
