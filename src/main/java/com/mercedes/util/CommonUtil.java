package com.mercedes.util;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mercedes.aes.EncryptDecrypt;
import com.mercedes.model.FileInteractorImplementorRequest;
import com.mercedes.model.FileInteractorImplementorResponse;
import com.mercedes.config.ConfigProperties;

@Component
public class CommonUtil {
	
	@Autowired
	ConfigProperties configProperties;

	public FileInteractorImplementorRequest decryptInfo(String request) throws Exception {

		EncryptDecrypt encryptDecrypt = new EncryptDecrypt(configProperties.getCipherKey());
		return encryptDecrypt.decodeStringToBean(request);
	}
	
	public String encryptInfo(List<FileInteractorImplementorRequest> usersDetails) throws Exception {

		EncryptDecrypt encryptDecrypt = new EncryptDecrypt(configProperties.getCipherKey());
		return encryptDecrypt.encodeBeanToString(usersDetails);
	}
	
	public FileInteractorImplementorResponse buildResponse(String responseCode, String responseDesc, List<FileInteractorImplementorRequest> userDeatilsList){
		FileInteractorImplementorResponse response = new FileInteractorImplementorResponse();
		response.setResponseCode(responseCode);
		response.setResponseDesc(responseDesc);
		response.setUserDeatilsList(userDeatilsList);
		return response;
	}
	
}
