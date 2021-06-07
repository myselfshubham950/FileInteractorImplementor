package com.mercedes.model;

import java.util.List;

public class FileInteractorImplementorResponse {

	private String responseCode;
	private String responseDesc;
	private List<FileInteractorImplementorRequest> userDeatilsList;
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	public List<FileInteractorImplementorRequest> getUserDeatilsList() {
		return userDeatilsList;
	}
	public void setUserDeatilsList(List<FileInteractorImplementorRequest> userDeatilsList) {
		this.userDeatilsList = userDeatilsList;
	}
	
}
