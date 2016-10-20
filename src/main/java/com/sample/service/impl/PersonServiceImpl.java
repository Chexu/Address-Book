package com.sample.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sample.dao.PersonDao;
import com.sample.exception.BusinessException;
import com.sample.model.AddressBook;
import com.sample.model.Person;
import com.sample.service.PersonService;
import com.sample.utility.AppLogger;
import com.sample.utility.Constants;
import com.sample.utility.SortingUtil;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements PersonService {

	private static final AppLogger LOGGER = new AppLogger(PersonServiceImpl.class);
	
	@Autowired
	private PersonDao personDao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BusinessException.class)
    public void saveOrUpdate(AddressBook addressBook) throws BusinessException {
		LOGGER.debug("Entered in PersonServiceImpl.saveOrUpdate");
		try{
			Person person = addressBook.getPerson();
			person.setAddresses(new HashSet<>(addressBook.getAddresses()));
			personDao.saveOrUpdate(person);
		}catch(Exception e){
			LOGGER.fatal("Exception in PersonServiceImpl.saveOrUpdate :"+e.getMessage(), e);
			throw new BusinessException("Exception in PersonServiceImpl.saveOrUpdate :"+e.getMessage(), e);
		}
		LOGGER.debug("Exit from PersonServiceImpl.saveOrUpdate");
    }
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AddressBook> getAllRecords() throws BusinessException {
		LOGGER.debug("Entered in PersonServiceImpl.getAllRecords");
		List<AddressBook> addressBooks = new ArrayList<>();
		try{
			AddressBook addressBook = null;
			List<Person> persons = new ArrayList<>();
			persons = personDao.getAll();
			for(Person person : persons){
				addressBook = new AddressBook();
				addressBook.setPerson(person);
				addressBook.setAddresses(new ArrayList<>(person.getAddresses()));
				person.setAddresses(null);
				addressBooks.add(addressBook);
			}
		}catch(Exception e){
			LOGGER.fatal("Exception in PersonServiceImpl.getAllRecords :"+e.getMessage(), e);
			throw new BusinessException("Exception in PersonServiceImpl.getAllRecords :"+e.getMessage(), e);
		}
		LOGGER.debug("Exit from PersonServiceImpl.getAllRecords");
        return addressBooks;
    }
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public AddressBook findById(long personId) throws BusinessException {
		LOGGER.debug("Entered in PersonServiceImpl.findById");
		AddressBook addressBook = new AddressBook();
		try{
			Person person = new Person();
			person = personDao.find(personId);
			if(person != null){
				addressBook.setPerson(person);
				addressBook.setAddresses(new ArrayList<>(person.getAddresses()));
				person.setAddresses(null);
			}
		}catch(Exception e){
			LOGGER.fatal("Exception in PersonServiceImpl.findById :"+e.getMessage(), e);
			throw new BusinessException("Exception in PersonServiceImpl.findById :"+e.getMessage(), e);
		}
		LOGGER.debug("Exit from PersonServiceImpl.findById");
		return addressBook;
	}
	
	public List<AddressBook> sortBy(List<AddressBook> addressBooks, Constants.SortByField sortByField) 
			throws BusinessException{
		LOGGER.debug("Entered in PersonServiceImpl.sortBy");
		switch(sortByField){
			case FIRST_NAME: 
				Collections.sort(addressBooks, SortingUtil.SORT_BY_FIRST_NAME);
				break;
			case LAST_NAME: 
				Collections.sort(addressBooks, SortingUtil.SORT_BY_LAST_NAME);
				break;
			case PHONE_NO:
				Collections.sort(addressBooks, SortingUtil.SORT_BY_PHONE_NO);
				break;
			case DOB:
				Collections.sort(addressBooks, SortingUtil.SORT_BY_DOB);
				break;
			case EMAIL:
				Collections.sort(addressBooks, SortingUtil.SORT_BY_EMAIL);
				break;
			default:
				break;
		}
		LOGGER.debug("Exit from PersonServiceImpl.sortBy");
		return addressBooks;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<AddressBook> searchBy(AddressBook addressBook) throws BusinessException {
		LOGGER.debug("Entered in PersonServiceImpl.searchBy");
		List<AddressBook> addressBooks = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		AddressBook addressBookTemp = null;
		try{
			persons = personDao.searchBy(addressBook.getPerson());
			for(Person person : persons){
				addressBookTemp = new AddressBook();
				addressBookTemp.setPerson(person);
				addressBooks.add(addressBookTemp);
			}
		}catch(Exception e){
			LOGGER.fatal("Exception in PersonServiceImpl.searchBy :"+e.getMessage(), e);
			throw new BusinessException("Exception in PersonServiceImpl.searchBy :"+e.getMessage(), e);
		}
		LOGGER.debug("Exit from PersonServiceImpl.searchBy");
		return addressBooks;
	}
	
	public String[] convertFrom(AddressBook addressBook){
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
		List<String> addressBookArr = new ArrayList<>();
		addressBookArr.add(StringUtils.isEmpty(addressBook.getPerson().getPersonId()) ? "" :  String.valueOf(addressBook.getPerson().getPersonId()));
		addressBookArr.add(StringUtils.isEmpty(addressBook.getPerson().getFirstName()) ? "" :  addressBook.getPerson().getFirstName());
		addressBookArr.add(StringUtils.isEmpty(addressBook.getPerson().getLastName()) ? "" :  addressBook.getPerson().getLastName());
		addressBookArr.add(StringUtils.isEmpty(addressBook.getPerson().getEmailId()) ? "" :  addressBook.getPerson().getEmailId());
		addressBookArr.add(addressBook.getPerson().getDateOfBirth() == null ? "" :  sdf.format(addressBook.getPerson().getDateOfBirth()));
		addressBookArr.add(StringUtils.isEmpty(addressBook.getPerson().getPhoneNo()) ? "" :  addressBook.getPerson().getPhoneNo());
		return addressBookArr.toArray(new String[addressBookArr.size()]);
	}
}
