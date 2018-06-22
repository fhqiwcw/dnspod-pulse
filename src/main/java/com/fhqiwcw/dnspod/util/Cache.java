package com.fhqiwcw.dnspod.util;

public class Cache {
	
	private static String ip;
	
	public static void putIp(String ipAddress) {
		ip = ipAddress;
	}
	
	public static String getIp() {
		return ip;
	}
	
	public static boolean compareAndPut(String ipAddress) {
		if(ipAddress == ip) {
			return false;
		}
		if(ipAddress == null) {
			return false;
		}
		if(ipAddress.equals(ip)) {
			return false;
		} else {
			putIp(ipAddress);
			return true;
		}
		
	}

}
