package com.fhqiwcw.dnspod.cache;

/**
 * @author zhaojun
 * IP缓存
 */
public interface IpCache {

	/**
	 * @param ipAddress
	 * @return
	 * 传入的IP地址与缓存中的对比，如果不一致返回true，否则返回false
	 * 入参为空时返回false
	 * 不相等时将传入的IP地址更新到缓存中，并返回true
	 */
	public boolean compareAndPut(String ipAddress);

}
