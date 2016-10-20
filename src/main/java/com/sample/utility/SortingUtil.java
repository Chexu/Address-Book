package com.sample.utility;

import java.util.Comparator;
import java.util.Date;

import com.sample.model.AddressBook;

public class SortingUtil {
	
	public static Comparator<AddressBook> SORT_BY_FIRST_NAME = new Comparator<AddressBook>() {

		@Override
		public int compare(AddressBook o1, AddressBook o2) {
			int value = 0;
			if(o1 != null && o2 != null && o1.getPerson() != null && o2.getPerson() != null){
				value = o1.getPerson().getFirstName().compareTo(o2.getPerson().getFirstName());
			}
			return value;
		}
	};
	
	public static Comparator<AddressBook> SORT_BY_LAST_NAME = new Comparator<AddressBook>() {

		@Override
		public int compare(AddressBook o1, AddressBook o2) {
			int value = 0;
			if(o1 != null && o2 != null && o1.getPerson() != null && o2.getPerson() != null){
				value = o1.getPerson().getLastName().compareTo(o2.getPerson().getLastName());
			}
			return value;
		}
	};
	
	public static Comparator<AddressBook> SORT_BY_PHONE_NO = new Comparator<AddressBook>() {

		@Override
		public int compare(AddressBook o1, AddressBook o2) {
			int value = 0;
			if(o1 != null && o2 != null && o1.getPerson() != null && o2.getPerson() != null){
				String phoneNoOne = o1.getPerson().getPhoneNo();
				String phoneNoSecond = o2.getPerson().getPhoneNo();
				if(phoneNoOne != null && phoneNoSecond != null){
					value = phoneNoOne.compareTo(phoneNoSecond);
				}
			}
			return value;
		}
	};
	
	public static Comparator<AddressBook> SORT_BY_DOB = new Comparator<AddressBook>() {

		@Override
		public int compare(AddressBook o1, AddressBook o2) {
			int value = 0;
			if(o1 != null && o2 != null && o1.getPerson() != null && o2.getPerson() != null){
				Date dateOfBirthOne = o1.getPerson().getDateOfBirth();
				Date dateOfBirthSecond = o2.getPerson().getDateOfBirth();
				if(dateOfBirthOne != null && dateOfBirthSecond != null){
					value = dateOfBirthOne.compareTo(dateOfBirthSecond);
				}
			}
			return value;
		}
	};
	
	public static Comparator<AddressBook> SORT_BY_EMAIL = new Comparator<AddressBook>() {

		@Override
		public int compare(AddressBook o1, AddressBook o2) {
			int value = 0;
			if(o1 != null && o2 != null && o1.getPerson() != null && o2.getPerson() != null){
				value = o1.getPerson().getEmailId().compareTo(o2.getPerson().getEmailId());
			}
			return value;
		}
	};
}
