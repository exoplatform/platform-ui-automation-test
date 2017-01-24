package org.exoplatform.platform.ui.automation.test.commons.pageobject;

import com.codeborne.selenide.Selenide;

/**
 * Created by mgreau on 23/01/2017.
 */
public class Platform {

  public static final String URI = "/portal";

  public void open() {

    Selenide.open(URI);
    return;

  }

  /**
   * It ensures that, if the license screen appears, the license agreement will
   * be accepted. If this screen doesn't appear, there is no exception.
   */
  public Platform ensureLicenseIsAccepted() {

    return this;
  }

  /**
   * Ensure that the Register Software UI is skipped.
   */
  public Platform ensureRegisterSoftwareIsSkipped() {

    boolean alreadySkipped = false;
    try {
      alreadySkipped = !RegisterSoftware.element.exists();
    } catch (Exception ex) {
      System.out.print("RG skip exception " + ex.getStackTrace());
    }
    if (!alreadySkipped) {
      System.out.print("Skip the UI ");
      new RegisterSoftware().skip();
    }

    return this;

  }

  /**
   * Ensure that the Account Setup UI is skipped.
   */
  public Platform ensureAccountSetupIsSkipped() {

    boolean alreadySkipped = false;
    try {
      alreadySkipped = !AccountSetup.element.exists();
    } catch (Exception ex) {
      System.out.print(" ");
    }
    if (!alreadySkipped) {
      new AccountSetup().skip();
    }

    return this;
  }

  public Platform ensureUserIsLoggedIn(){

    boolean alreadyLogged = false;
    try {
      alreadyLogged = new Login().isUserLogged();
    } catch (Exception ex) {
      System.out.print(" ");
    }
    if (!alreadyLogged) {
      new Login().signIn();
    }

    return this;


  }

}
