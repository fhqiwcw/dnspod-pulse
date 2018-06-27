package com.fhqiwcw.dsnpod.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fhqiwcw.dnspod.cache.RedisIpCache;
import com.fhqiwcw.dsnpod.MainTest;

public class RedisIpCacheTest extends MainTest{
	
	@Autowired
	private RedisIpCache redisIpCache;
	
	@Test
	public void testCompareAndPut() {
		redisIpCache.compareAndPut("192.168.1.1");
	}

}
