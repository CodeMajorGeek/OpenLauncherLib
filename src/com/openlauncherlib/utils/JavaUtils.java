package com.openlauncherlib.utils;

public class JavaUtils {

	public static String getJavaCommand() {

		if (System.getProperty("os.name").toLowerCase().contains("win"))
			return "\"" + System.getProperty("java.home") + "\\bin\\java" + "\"";

		return System.getProperty("java.home") + "/bin/java";
	}

	public static String[] getSpecialArgs() {
		return new String[] { "-XX:-UseAdaptiveSizePolicy", "-XX:+UseConcMarkSweepGC" };
	}

	public static String macDockName(String name) {
		return "-Xdock:name=" + name;
	}
}
