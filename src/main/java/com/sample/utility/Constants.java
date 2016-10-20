package com.sample.utility;

import java.util.regex.Pattern;

public interface Constants {

	static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	static final Pattern MOBILE_PATTERN = Pattern.compile("[0-9]{10}");
	static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
	static enum SortByField{
		FIRST_NAME("FIRST_NAME"),
		LAST_NAME("LAST_NAME"),
		PHONE_NO("PHONE_NO"),
		DOB("DOB"),
		EMAIL("EMAIL");
		
		private String value;
		
		SortByField(String value){
			this.setValue(value);
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
