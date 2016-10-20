package com.sample.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sample.dao.PersonDao;
import com.sample.exception.DaoException;
import com.sample.model.Person;
import com.sample.utility.AppLogger;
import com.sample.utility.ConcatSqlUtils;

@Repository
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao{
	
	private static final AppLogger LOGGER = new AppLogger(PersonDaoImpl.class);
	
	private static final String SEARCH_BY = " FROM Person P "; 
	
	@SuppressWarnings("unchecked")
	public List<Person> searchBy(Person person) throws DaoException {
		LOGGER.debug("Entered in PersonDaoImpl.searchBy");
		List<Person> persons = new ArrayList<Person>();
		StringBuilder queryStringBuilder = new StringBuilder();
		Map<String, Object> arguments = new HashMap<>();
		try{
			if(person != null){
				if(!StringUtils.isEmpty(person.getFirstName())){
					queryStringBuilder.append(ConcatSqlUtils.concatSqlQuery(queryStringBuilder.toString(), 
											  "P.firstName = :FNAME"));
					arguments.put("FNAME", person.getFirstName());
				}
				if(!StringUtils.isEmpty(person.getLastName())){
					queryStringBuilder.append(ConcatSqlUtils.concatSqlQuery(queryStringBuilder.toString(), 
							  				  "P.lastName = :LNAME"));
					arguments.put("LNAME", person.getLastName());
				}
				if(!StringUtils.isEmpty(person.getEmailId())){
					queryStringBuilder.append(ConcatSqlUtils.concatSqlQuery(queryStringBuilder.toString(), 
			  				  				  "P.emailId = :EMAIL"));
					arguments.put("EMAIL", person.getEmailId());
				}
			}
			Query query = currentSession().createQuery(SEARCH_BY + queryStringBuilder.toString());
			for (Map.Entry<String, Object> entry : arguments.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			persons = query.list();
		}catch(Exception e){
			LOGGER.fatal("Exception in PersonDaoImpl.searchBy :"+e.getMessage(), e);
			throw new DaoException("Exception in PersonDaoImpl.searchBy :"+e.getMessage(), e);
		}
		LOGGER.debug("Exit from PersonDaoImpl.searchBy");
		return persons;
	}

}
