package org.exoplatform.platform.ui.automation.test.config;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.exoplatform.platform.ui.automation.test.commons.selenium.TestBase;
import org.junit.Before;

import com.codeborne.selenide.Configuration;

/**
 * Created by mgreau on 20/01/2017.
 */
public class Base extends TestBase{

  @Before
  public void setup() throws SocketException {

    final String ipAddress = getIpAddress();

    Configuration.browser = "firefox";
    Configuration.baseUrl = "http://" + ipAddress + ":" + getPLFHTTPPort();
    Configuration.holdBrowserOpen = true;
    Configuration.remote = "http://" + ipAddress +":" + getHubHTTPPort() + "/wd/hub";

  }

  private String getPLFHTTPPort(){
    String defaultPort = "8080";

    return defaultPort;
  }

  private String getHubHTTPPort(){
    String defaultPort = "4444";

    return defaultPort;
  }


  private String getIpAddress() throws SocketException {
    String ipAddress = null;

    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
    for (; n.hasMoreElements();)
    {
      NetworkInterface e = n.nextElement();

      Enumeration<InetAddress> a = e.getInetAddresses();
      for (; a.hasMoreElements();)
      {
        InetAddress addr = a.nextElement();
        //Find local address
        if (addr.getHostAddress().contains("192")){
          ipAddress = addr.getHostAddress();
        }
      }
    }
    return ipAddress;
  }



}
