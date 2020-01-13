package com.instant.utils.platform;

import org.apache.commons.lang3.StringUtils;

public class JudgePlatform {
	public static String getPlatform(String s1) {
		String platform = "other00";
		if (StringUtils.isNotBlank(s1)) {
			if (s1.contains("Android") || s1.contains("android")) {
				platform = "android";
			} else if (s1.contains("iPhone") || s1.contains("ios")) {
				platform = "iPhone0";
			} else if (s1.contains("iPad")) {
				platform = "iPad000";
			} else {
				platform = "WEB000";
			}
		}
		return platform;
	}
}
