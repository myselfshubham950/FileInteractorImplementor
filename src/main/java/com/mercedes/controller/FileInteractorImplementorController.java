package com.mercedes.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mercedes.service.FileInteractorImplementorService;
import com.mercedes.util.CommonUtil;

@RestController
public class FileInteractorImplementorController {
	
	@Autowired
	private FileInteractorImplementorService fileInteractorImplementorService;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	Gson gson;
	
	public static final Logger log = LogManager.getLogger();
	
	@PostMapping("/storeData")
	public void storeData(@RequestBody String request) {
		
		try {
			log.info("Request received to store data");
			fileInteractorImplementorService.storeDataIntoFile(request);
		} catch(Exception e) {
		}
	}
	
	@PostMapping("/updateData")
	public void updateData(@RequestBody String request) {
		
		try {
			fileInteractorImplementorService.updateCSV(request);
		} catch(Exception e) {
		}
	}
	
	@GetMapping("/readData")
	public String readData() {
		
		String response = null;
		try {
			response = fileInteractorImplementorService.readDataFromCsv();
		} catch(Exception e) {
			System.out.println(e);
		}
		log.info(gson.toJson(response));
		return response;
	}
	
	
}
