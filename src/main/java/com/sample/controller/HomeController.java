package com.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sample.exception.BusinessException;
import com.sample.model.AddressBook;
import com.sample.utility.AppLogger;
import com.sample.utility.Constants;

@Controller
public class HomeController extends BaseController{
	
	private static final AppLogger LOGGER = new AppLogger(HomeController.class);
	
	@RequestMapping(value = {"/","/home","/resetSearchCriteria"} , method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addressBookHome()throws Exception {
		LOGGER.debug("Entered in HomeController.addressBookHome");
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("addressBook", new AddressBook());
		LOGGER.debug("Exit from HomeController.addressBookHome");
		return modelAndView;
	}
	
	@RequestMapping(value = "/searchContact", method = RequestMethod.POST)
	public ModelAndView searchAddressBook(AddressBook addressBook) {
		LOGGER.debug("Entered in HomeController.searchAddressBook");
		ModelAndView modelAndView = new ModelAndView("home");
		
		List<AddressBook> addressBooks = new ArrayList<>();
		try {
			addressBooks = personService.searchBy(addressBook);
			modelAndView.addObject("addressBooks", addressBooks);
		} catch (BusinessException e) {
			LOGGER.fatal("Exception in HomeController.searchAddressBook :"+e.getMessage(), e);
		}
		LOGGER.debug("Exit from HomeController.searchAddressBook");
		return modelAndView;
	}
	
	@RequestMapping(value = "/edit/{personId}", method = RequestMethod.GET)
	public ModelAndView editContact(@PathVariable("personId")int personId, Model model) {
		LOGGER.debug("Entered in HomeController.editContact");
		ModelAndView modelAndView = new ModelAndView("input");
		AddressBook addressBook = new AddressBook();
		try {
			addressBook = personService.findById(personId);
			modelAndView.addObject("addressBookInput", addressBook);
		} catch (BusinessException e) {
			LOGGER.fatal("Exception in HomeController.editContact :"+e.getMessage(), e);
		}
		LOGGER.debug("Exit from HomeController.editContact");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public ModelAndView addContact() {
		LOGGER.debug("Entered in HomeController.addContact");
		ModelAndView modelAndView = new ModelAndView("input");
		AddressBook addressBook = new AddressBook();
		modelAndView.addObject("addressBookInput", addressBook);
		LOGGER.debug("Exit from HomeController.addContact");
		return modelAndView;
	}
	
	@RequestMapping(value = "/sortBy/{sortByField}", method = RequestMethod.POST)
	public ModelAndView sortBy(@PathVariable("sortByField") Constants.SortByField sortByField, AddressBook addressBook) {
		LOGGER.debug("Entered in HomeController.sortBy");
		ModelAndView modelAndView = new ModelAndView("home");
		try {
			List<AddressBook> addressBooks = new ArrayList<>();
			addressBooks = personService.searchBy(addressBook);
			addressBooks = (ArrayList<AddressBook>) personService.sortBy(addressBooks, sortByField);
			modelAndView.addObject("addressBooks", addressBooks);
		} catch (BusinessException e) {
			LOGGER.fatal("Exception in HomeController.sortBy :"+e.getMessage(), e);
		}
		LOGGER.debug("Exit from HomeController.sortBy");
		return modelAndView;
	}
}
