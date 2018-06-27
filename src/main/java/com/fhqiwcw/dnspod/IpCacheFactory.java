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
	public StringRedisTemplate stringRedisTemplate;

	public IpCache getIpCache() {
		if ("local".equalsIgnoreCase(ipCacheStrategy)) {
			return new LocalIpCache();
		} else if ("redis".equalsIgnoreCase(ipCacheStrategy)) {
			RedisIpCache redisIpCache = new RedisIpCache();
			//redisIpCache.setStringRedisTemplate(context.getBean(StringRedisTemplate.class));
			redisIpCache.setStringRedisTemplate(stringRedisTemplate);
			return redisIpCache;
		} else {
			return null;
		}
	}

	@Override
	public IpCache getObject() throws Exception {
		return getIpCache();
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
