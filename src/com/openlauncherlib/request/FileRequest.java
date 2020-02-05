package com.openlauncherlib.request;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.io.*;

import com.openlauncherlib.utils.*;

public class FileRequest {

	private LauncherLib openLauncherLib;

	private boolean latest;

	private List<String> fileToUpdate = new ArrayList<String>();
	private List<String> fileToRemove = new ArrayList<String>();

	private int currentUpdatePosition;

	public FileRequest(LauncherLib openLauncherLib) {

		this.openLauncherLib = openLauncherLib;
	}

	public void jsonUpdateRequest(HashMap<String, String> updateRequest) {

		if (updateRequest.isEmpty()) {

			latest = true;
			return;
		}
		
		for (Map.Entry<String, String> entry : updateRequest.entrySet()) {
			
			if (entry.getValue().equals("update"))
				fileToUpdate.add(entry.getKey());
			else if (entry.getValue().equals("remove"))
				fileToRemove.add(entry.getKey());
		}
	}

	public void updateFiles() throws MalformedURLException, IOException {

		currentUpdatePosition = 0;

		if (latest)
			return;

		for (String filePath : fileToRemove) {
			
			new File(openLauncherLib.getRootDirectory(), filePath).delete();
			currentUpdatePosition++;
		}

		for (String filePath : fileToUpdate) {

			FileUtils.copyURLToFile(new URL(openLauncherLib.getServerClientURL().toString() + "/" + filePath),
					new File(openLauncherLib.getRootDirectory(), filePath.replace("\\", "/")));
			currentUpdatePosition++;
		}
	}

	public void setLatest(boolean latest) {
		this.latest = latest;
	}
	
	public boolean isLatest() {
		return latest;
	}
	
	public int getCurrentUpdatePosition() {
		return currentUpdatePosition;
	}

	public int getSize() {
		return fileToUpdate.size() + fileToRemove.size();
	}
}
