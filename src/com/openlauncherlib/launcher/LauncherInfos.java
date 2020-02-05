package com.openlauncherlib.launcher;

import com.openlauncherlib.mojang.*;

public class LauncherInfos {
	
	private boolean exist = false;
	
	private String username;
	private String sessionUUID;
	private String clientToken;
	private String accessToken;
	private boolean paid;
	
	private String version;
	
	public LauncherInfos(String userName, String sessionUUID) {
		
		this.username = userName;
		this.sessionUUID = sessionUUID;
		clientToken = "0";
		accessToken = "0";
		paid = false;
		
		exist = true;
		
		this.version = "1.7.10";
	}
	
	public LauncherInfos(MojangAccount mojangAccount) {
		
		this.username = mojangAccount.getUsername();
		this.sessionUUID = mojangAccount.getSessionUUID();
		clientToken = mojangAccount.getClientToken();
		accessToken = mojangAccount.getAccessToken();
		paid = mojangAccount.isPremiumPlayer();
		
		exist = mojangAccount.isAccountExist();
		
		this.version = "1.7.10";
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
	
	public String getUserType() {
		return paid ? "legacy" : "demo";
	}
	
	public boolean isAccountExist() {
		return exist;
	}
	
	public boolean isPaid() {
		return paid;
	}
	
	public String getVersionString() {
		return version;
	}
}
