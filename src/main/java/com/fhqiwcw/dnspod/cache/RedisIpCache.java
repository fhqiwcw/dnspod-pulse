package com.fhqiwcw.dnspod.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisIpCache implements IpCache{

	private static String IP_CACHE_KEY = "ip_cache_key";

	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean compareAndPut(String ip) {
		String valuefromRedis = stringRedisTemplate.opsForValue().get(IP_CACHE_KEY);

		if (ip == null) {
			return false;
		}
		if (ip.equals(valuefromRedis)) {
			return false;
		} else {
			stringRedisTemplate.opsForValue().set(IP_CACHE_KEY, ip);
			return true;
		}
	}

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}
	
	
	
}
