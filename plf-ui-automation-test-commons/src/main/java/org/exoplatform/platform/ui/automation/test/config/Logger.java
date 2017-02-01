package org.exoplatform.platform.ui.automation.test.config;

import org.slf4j.LoggerFactory;

/**
 *
 */
public class Logger {

	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(org.exoplatform.platform.ui.automation.test.config.Logger.class);

	private Logger(){

	}

	public static String log(String message) {
		Throwable t = new Throwable();
		String logMessage = message;
		StackTraceElement[] elements = t.getStackTrace();
		String Filename = elements[2].getFileName();
		String sClassName = Filename.substring(0, Filename.length() - 5);//remove .java
		String sMethodName = elements[2].getMethodName();
		logMessage = String.format("[%-10s][%s] %s", sClassName, sMethodName, message);
		return logMessage;
	}


	public static void trace(String message) {
		logger.trace(log(message));
	}

	public static void debug(String message) {
		logger.debug(log(message));
	}

	public static void info(String message) {
		logger.info(log(message));
	}

	public static void warn(String message) {
		logger.warn(log(message));
	}

	public static void error(String message) {
		logger.error(log(message));
	}

	public static void error(String message, Exception ex) {
		logger.error(log(message), ex);
	}
}
