package com.sample.utility;

public enum MessageType {
	
	INFO, SUCCESS, WARNING, ERROR;
	
	private final String cssClass;

	private MessageType() {
		cssClass = name().toLowerCase();
	}

	/**
	 * 
	 * The css class for styling messages of this type.
	 */

	public String getCssClass() {
		return cssClass;
	}
}
