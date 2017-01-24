package org.exoplatform.platform.ui.automation.test.calendar.pageobject;

import static com.codeborne.selenide.Selenide.$;

import org.exoplatform.platform.ui.automation.test.commons.pageobject.Platform;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import org.exoplatform.platform.ui.automation.test.PLFData;

/**
 * Created by mgreau on 23/01/2017.
 */
public class Calendar {

  public final static String URI = "/calendar";

  /** Container for Calendar Component */
  public final SelenideElement container = $(By.id("UICalendarPortlet"));

  public Calendar() {
  }

  public Calendar open(){
    $(Platform.URI + URI);

    return this;
  }


  public void addEvent() {

  }

}
