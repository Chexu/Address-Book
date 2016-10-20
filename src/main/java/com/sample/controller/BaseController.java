package com.sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.sample.service.PersonService;
import com.sample.utility.Constants;

@Controller
public abstract class BaseController {
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	protected PersonService personService;
	
	@InitBinder
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatter, true));
    }
}
