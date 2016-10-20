package com.sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencsv.CSVWriter;
import com.sample.exception.BusinessException;
import com.sample.model.AddressBook;
import com.sample.utility.AppLogger;

@Controller
public class CsvController extends BaseController{
	
	private static final AppLogger LOGGER = new AppLogger(CsvController.class);
	
	@RequestMapping(value = "/exportToCSV")
	public void exportToCsvAndDownload(HttpServletResponse response, AddressBook addressBook) throws IOException {
		LOGGER.debug("Entered in CsvController.exportToCsvAndDownload");
		String csvFileName = "addressBook.csv";
		List<AddressBook> addressBooks = new ArrayList<>();
		CSVWriter csvWriter = null;
		String[] data = null;
		try {
			addressBooks = personService.searchBy(addressBook);
			response.setContentType("text/csv");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
			response.setHeader(headerKey, headerValue);
			csvWriter = new CSVWriter(response.getWriter());
			String[] headers = new String[]{"Person Id", "First Name", "Last Name", "Email", "Date of Birth", "Phone No."};
			csvWriter.writeNext(headers);
			for(AddressBook book : addressBooks){
				data = personService.convertFrom(book);
				csvWriter.writeNext(data);
			}
		} catch (BusinessException e) {
			LOGGER.fatal("Exception in CsvController.exportToCsvAndDownload :"+e.getMessage(), e);
		} finally{
			try {
				if(csvWriter != null){
					csvWriter.close();
				}
			} catch (IOException e) {
				LOGGER.fatal("Exception in CsvController.exportToCsvAndDownload during closing stream :: " + e.getMessage(), e);
			}
		}
		LOGGER.debug("Exit from CsvController.exportToCsvAndDownload");
	}
}
