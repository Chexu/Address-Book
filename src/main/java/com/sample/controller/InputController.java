package com.sample.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.exception.BusinessException;
import com.sample.model.Address;
import com.sample.model.AddressBook;
import com.sample.utility.AppLogger;
import com.sample.utility.Message;
import com.sample.validator.AddressBookValidator;

@Controller
public class InputController extends BaseController{
	
	private static final AppLogger LOGGER = new AppLogger(InputController.class);
	
	@Autowired
	private AddressBookValidator addressBookValidator;
	
	@RequestMapping(value = "addUpdateAddress", method = RequestMethod.POST)
	public ModelAndView addUpdateAddress(@ModelAttribute("addressBookInput") AddressBook addressBook) {
		LOGGER.debug("Entered in InputController.addUpdateAddress");
		ModelAndView modelAndView = new ModelAndView("input");
		boolean addrAlreadyExists = false;
		String operation = "";
		try {
			Iterator<Address> addresses = addressBook.getAddresses().iterator(); 
			Address address = null;
			while(addresses.hasNext()){
				address = addresses.next();
				if(address.getAddrId() == addressBook.getAddress().getAddrId()){
					BeanUtils.copyProperties(address, addressBook.getAddress());
					addrAlreadyExists = true;
					break;
				}
			}
			if(!addrAlreadyExists){
				addressBook.getAddresses().add(addressBook.getAddress());
			}
			addressBook.setAddress(new Address());
			modelAndView.addObject("addressBookInput", addressBook);
			operation = addrAlreadyExists ? "updated" : "added";
		} catch (Exception e) {
			LOGGER.fatal("Exception in InputController.addUpdateAddress :"+e.getMessage(), e);
		}
		modelAndView.addObject("message", Message.info(messageSource.getMessage("info.address.success", new Object[]{operation}, Locale.ENGLISH)));
		LOGGER.debug("Exit from InputController.addUpdateAddress");
		return modelAndView;
	}
	
	@RequestMapping(value = "editAddress/{addrId}", method = RequestMethod.POST	)
	public ModelAndView editAddress(@PathVariable("addrId")long addrId, @ModelAttribute("addressBookInput") AddressBook addressBook) {
		LOGGER.debug("Entered in InputController.editAddress");
		ModelAndView modelAndView = new ModelAndView("input");
		List<Address> addresses = addressBook.getAddresses(); 
		for(Address address : addresses){
			if(address.getAddrId() == addrId){
				addressBook.setAddress(address);
				break;
			}
		}
		modelAndView.addObject("addressBookInput", addressBook);
		LOGGER.debug("Exit from InputController.editAddress");
		return modelAndView;
	}
	
	@RequestMapping(value = "saveDetails", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute("addressBookInput") AddressBook addressBook, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		LOGGER.debug("Entered in InputController.saveContact");
		ModelAndView modelAndView = null;
		List<AddressBook> addressBooks = new ArrayList<>();
		try {
			addressBookValidator.validate(addressBook, result);
			if(result.hasErrors()) {
				modelAndView = new ModelAndView("input");
				modelAndView.addObject("addressBookInput", addressBook);
			}else{
				modelAndView = new ModelAndView("home");
				personService.saveOrUpdate(addressBook);
				addressBooks = personService.getAllRecords();
				modelAndView.addObject("addressBooks", addressBooks);
				modelAndView.addObject("addressBook", new AddressBook());
			}
		} catch (BusinessException e) {
			LOGGER.fatal("Exception in InputController.saveContact :"+e.getMessage(), e);
		}
		modelAndView.addObject("message", Message.success(messageSource.getMessage("info.save.success", new Object[] {}, Locale.ENGLISH)));
		LOGGER.debug("Exit from InputController.saveContact");
		return modelAndView;
	}

}