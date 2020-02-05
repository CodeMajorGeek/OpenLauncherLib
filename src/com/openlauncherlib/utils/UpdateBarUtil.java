package com.openlauncherlib.utils;

import com.openlauncherlib.launcher.*;

public class UpdateBarUtil {
	
	private Update update;
	
	private int maxBarValue;
	
	public UpdateBarUtil(Update update, int maxBarValue) {
		
		this.update = update;
		this.maxBarValue = maxBarValue;
	}
	
	public double getCurrentBarValue() {
		return (update.getCurrentUpdatePosition() * getMaxBarValue()) / update.getUpdateSize();
	}
	
	public int getMaxBarValue() {
		return maxBarValue;
	}
	
	public boolean isFinish() {
		return update.getCurrentUpdatePosition() == update.getUpdateSize();
	}
}
