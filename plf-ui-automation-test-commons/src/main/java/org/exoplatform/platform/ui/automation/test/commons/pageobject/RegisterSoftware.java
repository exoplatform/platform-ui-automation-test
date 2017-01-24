package org.exoplatform.platform.ui.automation.test.commons.pageobject;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

/**
 * Created by mgreau on 23/01/2017.
 */
public class RegisterSoftware {

  /** This Element is here only for the Registration Software Page */
  public static SelenideElement element = $(By.cssSelector("plf-registration"));

  public RegisterSoftware() {

  }

  /**
   * Skip the Software Registration screen.
   * 
   * @return
   */
  public RegisterSoftware skip() {

    // Click on the label because the checkbox is not visible
    // '<input class="checkbox" id="agreement" name="checktc"
    // onclick="toggleState();" type="checkbox" value="false"
    // displayed:false></input>'

    $(By.name("btnSkip")).click();
    $(By.name("setupbutton")).click();

    return this;
  }

}
