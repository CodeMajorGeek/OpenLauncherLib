package com.openlauncherlib.utils;

import java.util.*;

public class ListUtils {

	public static String toString(List<String> list) {
		
		String output = "";
		for(String str : list)
			output += str;
		
		return output;
	}
}
