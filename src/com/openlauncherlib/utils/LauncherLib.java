package com.openlauncherlib.utils;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.io.*;

public class LauncherLib {
	
	private String clientName;
	private URL serverUrl;
	private File clientRootDirectory;

	public LauncherLib(URL serverUrl, String clientName, File clientRootDirectory) {
		
		this.serverUrl = serverUrl;
		this.clientName = clientName;
		this.clientRootDirectory = clientRootDirectory;
	}

	public LauncherLib(URL serverUrl, String clientName) throws IOException {

		this.serverUrl = serverUrl;
		this.clientName = clientName;
		this.clientRootDirectory = new File(System.getenv("APPDATA"), "/." + clientName.toLowerCase());
		this.clientRootDirectory.mkdir();
	}

	public URL getServerURL() {
		return serverUrl;
	}

	public File getRootDirectory() {
		return clientRootDirectory;
	}
	
	public File getNativeDirectory() {
		return new File(clientRootDirectory, "natives");
	}
	
	public File getLibrarieDirectory() {
		return new File(clientRootDirectory, "libraries");
	}
	
	public File getAssetsDirectory() {
		return new File(clientRootDirectory, "assets");
	}
	
	public File getMinecraftFile() {
		return new File(clientRootDirectory, clientName + ".jar");
	}
	
	public URL getServerServiceUpdateURL() throws MalformedURLException {
		return new URL(serverUrl.toString() + "/services/update.php");
	}
	
	public URL getServerClientURL() throws MalformedURLException {
		return new URL(serverUrl.toString() + "/client");
	}
	
	public Collection<File> getLibraries() {
		return FileUtils.listFiles(getLibrarieDirectory(), null, true);
	}
}
