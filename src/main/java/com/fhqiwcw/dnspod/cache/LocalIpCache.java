package com.fhqiwcw.dnspod.cache;

import org.springframework.stereotype.Component;

@Component
public class LocalIpCache implements IpCache{
	
	private static String ip;
	
	public static void putIp(String ipAddress) {
		ip = ipAddress;
	}
	
	public static String getIp() {
		return ip;
	}
	
	@Override
	public boolean compareAndPut(String ipAddress) {
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
