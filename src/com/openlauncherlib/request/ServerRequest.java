package com.openlauncherlib.request;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.io.*;

import com.google.gson.*;
import com.openlauncherlib.json.*;
import com.openlauncherlib.utils.*;

public class ServerRequest {
	
	private LauncherLib openLauncherLib;

	public ServerRequest(LauncherLib openLauncherLib) {

		this.openLauncherLib = openLauncherLib;
	}

	public FileRequest sendJsonUpdate(UpdateRequest updateRequest) throws IOException {
		
		final Gson gson = new GsonBuilder().create();
		
		final HttpURLConnection connection = (HttpURLConnection)openLauncherLib.getServerServiceUpdateURL().openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestProperty("Accept", "application/json");
		
		connection.setDoOutput(true);
		connection.setDoInput(true);
		
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(gson.toJson(updateRequest));
		wr.flush();
		wr.close();
		
		FileRequest fileRequest = new FileRequest(openLauncherLib);
		List<String> inputLines = IOUtils.readLines(connection.getInputStream(), "UTF-8");
		
		System.out.println(ListUtils.toString(inputLines));
		
		if(inputLines.isEmpty())
			fileRequest.setLatest(true);
		else
			fileRequest.jsonUpdateRequest(gson.fromJson(ListUtils.toString(inputLines), HashMap.class));
		
		return fileRequest;
	}
}
