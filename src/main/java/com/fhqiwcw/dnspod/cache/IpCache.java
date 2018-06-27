package com.fhqiwcw.dnspod.cache;

public interface IpCache {

	public boolean compareAndPut(String ipAddress);

}
