package com.pacman.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check whether an IP address is valid. *.*.*.* or "localhost" are accepted.
 * 
 * @author siyuanliu
 * @version 1.0
 */
public final class IPAddrCheck {
	public static boolean isValidIP(String ip) {
		Pattern pattern = Pattern
				.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ip);
		if (matcher.matches() || ip.equals("localhost")) {
			return true;
		}
		return false;

	}
}
