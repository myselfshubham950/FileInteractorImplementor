package com.mercedes.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.mercedes.model.FileInteractorImplementorRequest;
import com.mercedes.util.CommonUtil;
import com.mercedes.util.Constants;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

@Service
public class FileInteractorImplementorService {

	@Autowired
	private CommonUtil commonUtil;

	@Autowired
	Gson gson;

	FileWriter fileWriter;
	public static final Logger log = LogManager.getLogger();

	public void storeDataIntoFile(String request) throws Exception {

		log.info("Sending request for decryption");
		FileInteractorImplementorRequest decryptedRequest = commonUtil.decryptInfo(request);
		log.info("Request after decryption: "+gson.toJson(decryptedRequest));
		try {
			if (fileWriter == null) {
				fileWriter = new FileWriter(Constants.File_Name);
				fileWriter.append("Name, DOB, Salary, Age\n");
			}
			fileWriter.append(decryptedRequest.getName());
			fileWriter.append(",");
			fileWriter.append(decryptedRequest.getDob());
			fileWriter.append(",");
			fileWriter.append(decryptedRequest.getSalary());
			fileWriter.append(",");
			fileWriter.append(String.valueOf(decryptedRequest.getAge()));
			fileWriter.append("\n");
		} catch (Exception ex) {
			log.error("Exception occured: "+ex);
		} finally {
			fileWriter.flush();
//			fileWriter.close();
		}
	}

	public String readDataFromCsv() throws Exception {
		BufferedReader reader = null;
		try {
			List<FileInteractorImplementorRequest> usersDetails = new ArrayList<FileInteractorImplementorRequest>();
			String line = "";
			reader = new BufferedReader(new FileReader(Constants.File_Name));
			reader.readLine();

			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");

				if (fields.length > 0) {
					FileInteractorImplementorRequest userDetails = new FileInteractorImplementorRequest();
					userDetails.setName(fields[0]);
					userDetails.setDob(fields[1]);
					userDetails.setSalary(fields[2]);
//					String age = fields[3];
//					userDetails.setAge(Double.valueOf(age));
//					usersDetails.add(userDetails);
				}
			}
			return commonUtil.encryptInfo(usersDetails);
		} catch (Exception ex) {
			log.error("Exception occured: "+ex);
		} finally {
			reader.close();
		}
		return null;
	}
	
	public void updateCSV(String request) throws Exception {

		log.info("Sending request for decryption");
		FileInteractorImplementorRequest decryptedRequest = commonUtil.decryptInfo(request);
		log.info("Request after decryption: "+gson.toJson(decryptedRequest));
		CSVWriter writer = null;
		try {
			File inputFile = new File(Constants.File_Name);
			CSVReader reader = new CSVReader(new FileReader(inputFile));
			List<String[]> csvBody = reader.readAll();
			for(int i=0; i<csvBody.size(); i++){
				String[] strArray = csvBody.get(i);
				for(int j=0; j<strArray.length; j++){
					if(strArray[j].equalsIgnoreCase(decryptedRequest.getName())){ 
						csvBody.get(i)[j] = decryptedRequest.getUpdatedName();
					}
					if(strArray[j].equalsIgnoreCase(decryptedRequest.getDob())){ 
						csvBody.get(i)[j] = decryptedRequest.getUpdatedDob();
					}
					if(strArray[j].equalsIgnoreCase(decryptedRequest.getSalary())){ 
						csvBody.get(i)[j] = decryptedRequest.getUpdatedSalary();
					}
					if(strArray[j].equalsIgnoreCase(String.valueOf(decryptedRequest.getAge()))){ 
						csvBody.get(i)[j] = String.valueOf(decryptedRequest.getAge());
					}
				}
			}
			reader.close();
			writer = new CSVWriter(new FileWriter(inputFile));
			writer.writeAll(csvBody);
		} catch(Exception ex) {
			log.error("Exception occured: "+ex);
		}
		finally {
			writer.flush();
			writer.close();
		}
    }

}
