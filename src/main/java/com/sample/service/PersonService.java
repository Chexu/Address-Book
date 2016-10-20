package com.sample.service;

import java.util.List;

import com.sample.exception.BusinessException;
import com.sample.model.AddressBook;
import com.sample.model.Person;
import com.sample.utility.Constants;

public interface PersonService extends GenericService<Person, Long>{
	void saveOrUpdate(AddressBook addressBook) throws BusinessException;
	List<AddressBook> searchBy(AddressBook addressBook) throws BusinessException;
	AddressBook findById(long personId) throws BusinessException;
	List<AddressBook> sortBy(List<AddressBook> addressBooks, Constants.SortByField sortByField) 
			throws BusinessException;
	List<AddressBook> getAllRecords() throws BusinessException;
	String[] convertFrom(AddressBook addressBook);
}
