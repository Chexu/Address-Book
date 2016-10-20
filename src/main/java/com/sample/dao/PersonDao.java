package com.sample.dao;

import java.util.List;

import com.sample.exception.DaoException;
import com.sample.model.Person;

public interface PersonDao extends GenericDao<Person, Long>{
	List<Person> searchBy(Person person) throws DaoException; 
}
