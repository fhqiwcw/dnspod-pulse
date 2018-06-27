package com.fhqiwcw.dnspod;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.fhqiwcw.dnspod.cache.IpCache;
import com.fhqiwcw.dnspod.cache.LocalIpCache;
import com.fhqiwcw.dnspod.cache.RedisIpCache;

/**
 * @author zhaojun
 * FactoryBean 生成IpCache对象
 * 可生成两种IpCache对象 LocalIpCache & RedisIpCache
 * 构造函数传入ipCacheStrategy来决定生成哪种实例的对象
 */
public class IpCacheFactory implements FactoryBean<IpCache> {

	private String ipCacheStrategy;

	/**
	 * @param ipCacheStrategy 可传local或redis
	 */
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
