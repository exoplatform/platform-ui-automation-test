package org.exoplatform.platform.ui.automation.test.commons.pageobject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

/**
 * Created by mgreau on 23/01/2017.
 */
public class License {

  private final SelenideElement element = $(By.id("agreement"));

  public License() {
  }

  public License accept() {

    element.parent().click();
    $("#continueButton").click();

    return this;

  }
}
