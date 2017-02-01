package org.exoplatform.platform.ui.automation.test.commons.selenium;


import static org.exoplatform.platform.ui.automation.test.config.Logger.info;
import static org.exoplatform.platform.ui.automation.test.config.Logger.error;
import static org.exoplatform.platform.ui.automation.test.config.Logger.debug;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Utils {



	/**
	 * Pause
	 * @param timeInMillis
	 */
	public static void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	/*public static void clearCache(){
		Actions actionObject = new Actions(driver);
		try{
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		} catch(WebDriverException e){	
			debug("Retrying clear cache...");
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		}
	}*/

	/**
	 * Capture the screen of the current graphics device
	 * @param fileName
	 * 					input an image name (String)
	 */
	public static void captureScreen(String fileName){
		String path;
		BufferedImage screenCapture;
		//		Thread.sleep(3000);
		pause(3000);
		try {
			Robot robot = new Robot();
			Rectangle screenSize = getScreenSize();
			screenCapture = robot.createScreenCapture(screenSize);
			// Save as PNG
			String curDir = System.getProperty("user.dir");
			path = curDir + "/target/screenshoot/";
			File f = new File(path);
			if (!f.exists()) f.mkdir(); 
			ImageIO.write(screenCapture, "png", new File(path + fileName));

		}catch (AWTException e) {
			error("Failed to capture screenshot");
		}catch(IOException e)
		{
			path = "Failed to capture screenshot: " + e.getMessage();
		}
	}

	/**
	 * the size of the default screen
	 * @return
	 * 			the size of the default screen
	 */
	public static Rectangle getScreenSize() {
		GraphicsEnvironment graphE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphD = graphE.getDefaultScreenDevice();
		Window displayM = graphD.getFullScreenWindow();
		if(displayM != null)
			return new Rectangle(displayM.getWidth(), displayM.getHeight());
		else
			return new Rectangle(1000,1000);
	}

	/**
	 * Simulating keyboard presses 
	 * @param firstKey
	 * 					send the first key (type: KeyEvent)
	 * @param params
	 * 					send the second key (type: KeyEvent)  
	 */
	public static void javaSimulateKeyPress(int firstKey, Object... params){
		int secondKey = (Integer) (params.length > 0 ? params[0]: KeyEvent.VK_ENTER);
		int thirdKey = (Integer) (params.length > 1 ? params[1]: KeyEvent.VK_ENTER); 
		try {
			Robot robot = new Robot();
			// Simulate a key press
			robot.keyPress(firstKey);
			if(params.length > 0)
				robot.keyPress(secondKey);
			if(params.length > 1)
				robot.keyPress(thirdKey);
			pause(3000);
			if(params.length > 0)
				robot.keyRelease(secondKey);
			robot.keyRelease(firstKey);
			if(params.length > 1)
				robot.keyRelease(thirdKey);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*---- Auxiliary functions ----
	public static void captureScreen(String fileName){
		String path;
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//			relativeFilePath = "target/screenshot/" + fileName;
			String curDir = System.getProperty("user.dir");
			path = curDir + "/target/screenshoot/" + fileName;
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}catch(UnknownServerException e)
		{
			error("Failed to capture screenshot");
		}
	}
	 */


	/**
	 * ipV4 of local machine
	 * @return
	 * 			ipV4 of local machine
	 */
	public static String getIPOfLocal(){
		info("Get IP of localhost");
		String interName = "";
		Map <String, String> inter = getInterfaces();
		for (String key: inter.keySet()){
			if (key.contains("eth")){
				interName = inter.get(key);
				break;
			}
		}
		info(interName);
		return interName;
	}

	/**
	 * map of interface name and Ip of local machine
	 * @return
	 * 			map of interface name and Ip of local machine
	 */
	public static Map<String, String> getInterfaces(){
		Map<String,String> inter = new HashMap <String,String>();
		String IP = "";
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();

			while(e.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e.nextElement();
				info("Net interface: " + ni.getName()); 

				Enumeration<InetAddress> e2 = ni.getInetAddresses(); 
				while (e2.hasMoreElements()){
					InetAddress ip = (InetAddress) e2.nextElement();
					if(!ip.isLinkLocalAddress()) {
						IP = ip.getHostAddress();
					}
				}
				info("IP address: "+ IP.toString());
				inter.put(ni.getName(), IP.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return inter;
	}
	
	/**
	 *  This function returns a absolute path from a relative path that get from excel file
	 * @param relativeFilePath
	 * @return absolutePath
	 */
	public static String getAbsoluteFilePathFromFile(String relativeFilePath) {
		String curDir = System.getProperty("user.home");
		String absolutePath = curDir + relativeFilePath;
		info("absolutePath:" + absolutePath);
		return absolutePath;
	}
}
