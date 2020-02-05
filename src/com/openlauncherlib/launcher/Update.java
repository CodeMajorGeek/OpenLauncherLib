package com.openlauncherlib.launcher;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

import org.apache.commons.io.*;

import com.openlauncherlib.json.*;
import com.openlauncherlib.request.*;
import com.openlauncherlib.utils.*;

public class Update {

	private LauncherLib launcherLib;
	private FileRequest fileRequest;

	private HashMap<File, String> fileHash = new HashMap<File, String>();

	public Update(LauncherLib launcherLib) throws IOException, NoSuchAlgorithmException {

		this.launcherLib = launcherLib;

		final MessageDigest md5Digest = MessageDigest.getInstance("MD5");

		for (File file : FileUtils.listFiles(launcherLib.getRootDirectory(), null, true))
			fileHash.put(file, FileUtil.getFileChecksum(md5Digest, file));
	}

	public void sendRequestToServer() throws IOException {

		final ServerRequest httpRequest = new ServerRequest(launcherLib);
		fileRequest = httpRequest.sendJsonUpdate(new UpdateRequest(getFileHash()));
	}

	public void doUpdate() throws MalformedURLException, IOException {
		
		fileRequest.updateFiles();
	}
	
	public int getUpdateSize() {
		return fileRequest.getSize();
	}
	
	public int getCurrentUpdatePosition() {
		return fileRequest.getCurrentUpdatePosition();
	}
	
	private HashMap<String, String> getFileHash() {

		final HashMap<String, String> fileHash = new HashMap<String, String>();

		for (Map.Entry<File, String> entry : this.fileHash.entrySet())
			fileHash.put(entry.getKey().getAbsolutePath().replace(launcherLib.getRootDirectory().getAbsolutePath(), ""),
					entry.getValue());

		return fileHash;
	}
}
