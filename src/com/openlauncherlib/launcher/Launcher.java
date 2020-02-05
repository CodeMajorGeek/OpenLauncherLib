package com.openlauncherlib.launcher;

import java.io.*;
import java.util.*;

import com.openlauncherlib.utils.*;

public class Launcher {
	
	private LauncherLib openLauncherLib;
	private LauncherInfos launchInfos;

	public Launcher(LauncherLib openLauncherLib, LauncherInfos launchInfos) {

		this.openLauncherLib = openLauncherLib;
		this.launchInfos = launchInfos;
	}

	public Process launch() throws IOException {
		return Runtime.getRuntime().exec(getCommand());
	}

	private String getCommand() {

		List<String> commands = new ArrayList<String>();

		commands.add(JavaUtils.getJavaCommand());
		
		commands.add("-cp");

		String libsCommand = openLauncherLib.getMinecraftFile().getAbsolutePath();
		for (File file : openLauncherLib.getLibraries())
			libsCommand += ";" + file.getAbsolutePath();
		
		commands.add(libsCommand);
		
		commands.add("-Xincgc");
		commands.add("-Xmx2G");
		commands.add("-Xms1024M");
		
		commands.add("-Djava.library.path=" + openLauncherLib.getNativeDirectory());
			
		commands.add("net.minecraft.client.main.Main");
		
		commands.add("--username=" + launchInfos.getUsername());
		commands.add("--accessToken " + launchInfos.getAccessToken());
		commands.add("--clientToken" + launchInfos.getClientToken());
		commands.add("--version " + launchInfos.getVersionString());
		commands.add("--gameDir " + openLauncherLib.getRootDirectory().getAbsolutePath());
		commands.add("--assetsDir " + openLauncherLib.getAssetsDirectory().getAbsolutePath());
		commands.add("--assetIndex " + launchInfos.getVersionString());
		commands.add("--userProperties {}");
		commands.add("--uuid " + launchInfos.getSessionUUID());
		commands.add("--userType " + launchInfos.getUserType());

		String entireCommand = "";
		for (String command : commands)
			entireCommand += command + " ";

		return entireCommand;
	}
}
