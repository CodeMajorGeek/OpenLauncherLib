package com.openlauncherlib.test;

import java.io.*;
import java.net.*;
import java.security.*;

import com.openlauncherlib.*;
import com.openlauncherlib.launcher.*;
import com.openlauncherlib.mojang.*;
import com.openlauncherlib.utils.*;

public class Main {

	public static void main(String[] args) throws MalformedURLException, IOException, NoSuchAlgorithmException {

		final OpenLauncher openLauncher = new OpenLauncher(
				new LauncherInfos(new MojangAccount("mailExemple", "passwordExemple")),
				new LauncherLib(new URL("http://localhost"), "clientName"));
		
		openLauncher.update();
		UpdateBarUtil updateBarUtil = openLauncher.getUpdateBarUtil(100);
		Thread barThread = new Thread("#bar_Thread") {

			@Override
			public void run() {

				while (!updateBarUtil.isFinish())
					System.out.println(updateBarUtil.getCurrentBarValue());
			}
		};
		barThread.start();
		
		openLauncher.launch();
	}

}
