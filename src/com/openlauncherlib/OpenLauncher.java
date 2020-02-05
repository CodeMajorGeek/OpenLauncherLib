package com.openlauncherlib;

import java.io.*;
import java.security.*;

import com.openlauncherlib.launcher.*;
import com.openlauncherlib.utils.*;

public class OpenLauncher {
	
	private LauncherInfos launcherInfos;
	private LauncherLib launcherLib;
	
	private Update update;
	
	public OpenLauncher(LauncherInfos launcherInfos, LauncherLib launcherLib) {
		
		this.launcherInfos = launcherInfos;
		this.launcherLib = launcherLib;
	}
	
	public void update() throws NoSuchAlgorithmException, IOException {
		
		update = new Update(launcherLib);
		update.sendRequestToServer();
		
		update.doUpdate();
	}
	
	public void launch() throws IOException {
		
		final Launcher launcher = new Launcher(launcherLib, launcherInfos);
		launcher.launch();
	}
	
	public UpdateBarUtil getUpdateBarUtil(int maxBarValue) {
		return new UpdateBarUtil(update, maxBarValue);
	}
}
