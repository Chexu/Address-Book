package com.sample.validator;

import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sample.model.AddressBook;
import com.sample.utility.Constants;

@Component
public class AddressBookValidator implements Validator {
	
	private Matcher matcher;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddressBook addressBook = (AddressBook) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.firstName", "error.field.required", new Object[]{"First Name"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "person.lastName", "error.field.required", new Object[]{"Last Name"});
		if(null != addressBook.getPerson()){
			if(StringUtils.isNotEmpty(addressBook.getPerson().getEmailId())){
				matcher = Constants.EMAIL_PATTERN.matcher(addressBook.getPerson().getEmailId());
				if(!matcher.matches()){
					errors.rejectValue("person.emailId", "error.field.invalid", new Object[]{"Email Id"}, "");
				}
			}
			if(StringUtils.isNotEmpty(addressBook.getPerson().getPhoneNo())){
				matcher = Constants.MOBILE_PATTERN.matcher(addressBook.getPerson().getPhoneNo());
				if(!matcher.matches()){
					errors.rejectValue("person.phoneNo", "error.field.invalid", new Object[]{"Phone No."}, "");
				}
			}
		}
		if(null != addressBook.getAddresses() && addressBook.getAddresses().size() == 0){
			errors.reject("error.field.atleast.one", "");
		}
	}
}
