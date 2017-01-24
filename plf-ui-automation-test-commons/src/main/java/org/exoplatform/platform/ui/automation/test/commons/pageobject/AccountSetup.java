package org.exoplatform.platform.ui.automation.test.commons.pageobject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

/**
 * Created by mgreau on 23/01/2017.
 */
public class AccountSetup {

  public static SelenideElement element = $(By.id("AccountSetup"));

  /**
   * Skip the Account Setup UI.
   * 
   * @return
   */
  public AccountSetup skip() {

    $(By.name("setupbutton")).click();

    return this;
  }

}
