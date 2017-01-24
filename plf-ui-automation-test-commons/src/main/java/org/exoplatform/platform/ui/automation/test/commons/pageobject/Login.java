package org.exoplatform.platform.ui.automation.test.commons.pageobject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import org.exoplatform.platform.ui.automation.test.PLFData;

/**
 * Created by mgreau on 23/01/2017.
 */
public class Login {

  private final SelenideElement container = $(By.cssSelector("loginContainer"));

  public Login() {
  }


  public boolean isUserLogged(){
    return $(By.id("UIUserPlatformToolBarPortlet")).exists();

  }

  /**
   * SignIn with default eXo Root Credentials
   */
  public Login signIn() {
    return this.signIn(PLFData.username, PLFData.password);
  }

  /**
   * @param user
   * @param password
   */
  public Login signIn(final String user, final String password) {
    $(By.name("username")).setValue("root");
    $(By.name("password")).setValue("gtn");
    $(By.className("button")).click();

    return this;
  }

  public Login signOut() {

    $(By.id("UIUserPlatformToolBarPortlet")).click();
    $(By.className("uiIconPLFLogout")).click();

    return this;
  }

}
