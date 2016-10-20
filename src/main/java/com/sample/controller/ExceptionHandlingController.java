package com.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.sample.utility.AppLogger;

@ControllerAdvice
public class ExceptionHandlingController {
	
	private AppLogger logger = new AppLogger(ExceptionHandlingController.class);
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		ModelAndView mav = new ModelAndView();
		logger.fatal("URL : " + req.getRequestURI() + " caused exception " + ex);
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURI());
		mav.setViewName("error");
		return mav;
	}
}
