package com.fhqiwcw.dnspod;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.fhqiwcw.dnspod.cache.IpCache;
import com.fhqiwcw.dnspod.cache.LocalIpCache;
import com.fhqiwcw.dnspod.cache.RedisIpCache;

public class IpCacheFactory implements FactoryBean<IpCache> {

	private String ipCacheStrategy;

	public IpCacheFactory(String ipCacheStrategy) {
		this.ipCacheStrategy = ipCacheStrategy;
	}

	@Autowired
	private LocalIpCache localIpCache;

	@Autowired
	private RedisIpCache redisIpCache;

	@Autowired
	public StringRedisTemplate stringRedisTemplate;

	@Override
	public IpCache getObject() throws Exception {
		if ("local".equalsIgnoreCase(ipCacheStrategy)) {
			return localIpCache;
		} else if ("redis".equalsIgnoreCase(ipCacheStrategy)) {
			return redisIpCache;
		} else {
			return null;
		}
	}

	@Override
	public Class getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		// return FactoryBean.super.isSingleton();
		return true;
	}

}
