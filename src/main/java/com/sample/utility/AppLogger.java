package com.sample.utility;

import org.apache.log4j.Logger;

public class AppLogger extends Logger {

	private Logger logger = null;

	public AppLogger(Class<?> cls) {
		super(cls.getName());
		logger = Logger.getLogger(cls.getName());
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void debug(String message) {
		logger.debug(message);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void fatal(String message) {
		logger.fatal(message);
	}
}