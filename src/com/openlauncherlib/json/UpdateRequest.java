package com.openlauncherlib.json;

import java.util.*;

public class UpdateRequest {

	private HashMap<String, String> fileHashs = new HashMap<String, String>();
	
	public UpdateRequest(HashMap<String, String> fileHashs) {
		
		this.fileHashs = fileHashs;
	}
	
	public  HashMap<String, String> getFileHashs() {
		return fileHashs;
	}
}
