package org.exoplatform.platform.ui.automation.test.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;

import com.codeborne.selenide.Configuration;

/**
 * Created by mgreau on 20/01/2017.
 */
public class BaseTest {

  public String ipAddress = "192.168.3.143";

  @Before
  public void setup() {
    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getLocalHost();
      ipAddress = inetAddress.getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    Configuration.browser = "chrome";
    Configuration.baseUrl = "http://" + ipAddress + ":8080";
    Configuration.holdBrowserOpen = true;
    // Configuration.remote = "http://" + ipAddress +":4444/wd/hub";

  }



}
