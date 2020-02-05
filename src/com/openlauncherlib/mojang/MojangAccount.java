package com.openlauncherlib.mojang;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.io.*;

import com.google.gson.*;
import com.openlauncherlib.utils.*;

public class MojangAccount {
	
	private static final String MOJANG_URL =  "https://authserver.mojang.com";
	
	private boolean exist = true;
	
	private String username;
	private String sessionUUID;
	private String accessToken;
	private String clientToken;
	private boolean paid;
	
	public MojangAccount(String user, String password) throws MalformedURLException, IOException {
		
		requestMojangServer(user, password);
	}
	
	public void requestMojangServer(String user, String password) throws MalformedURLException, IOException {
		
		final Gson gson = new GsonBuilder().create();
		
		final HttpURLConnection connection = (HttpURLConnection) new URL(MOJANG_URL + "/authenticate").openConnection();
		connection.setRequestMethod("POST");
		
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestProperty("Accept", "application/json");
		
		connection.setDoOutput(true);
		connection.setDoInput(true);
			
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes("{ \"agent\": { \"name\":\"Minecraft\", \"version\":1 }, \"username\":\"" + user + "\", \"password\":\"" + password + "\"}");
		wr.flush();
		wr.close();
		
		if(connection.getResponseCode() == 403) {
			
			exist = false;
			return;
		}
		
		List<String> inputLines = IOUtils.readLines(connection.getInputStream(), "UTF-8");
		JsonObject jsonObject = gson.fromJson(ListUtils.toString(inputLines), JsonObject.class);
		
		accessToken = jsonObject.get("accessToken").getAsString();
		clientToken = jsonObject.get("clientToken").getAsString();
		sessionUUID = jsonObject.get("selectedProfile").getAsJsonObject().get("id").getAsString();
		username = jsonObject.get("selectedProfile").getAsJsonObject().get("name").getAsString();
		paid = jsonObject.get("selectedProfile").getAsJsonObject().get("paid").getAsBoolean();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getSessionUUID() {
		return sessionUUID;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public String getClientToken() {
		return clientToken;
	}
	
	public boolean isPremiumPlayer() {
		return paid;
	}
	
	public boolean isAccountExist() {
		return exist;
	}
}
