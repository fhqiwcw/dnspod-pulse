package com.fhqiwcw.dnspod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class AppConfig {
	
	@Bean
	public RedisConnectionFactory getRedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("192.168.1.101", 6379);
	    return new JedisConnectionFactory(config);
	}
	
	// TODO 使用 beanFactory 声明IpCache对象
	@Bean(name="ipCacheFactoryBean")
	public IpCacheFactory getIpCacheFactory(StringRedisTemplate stringRedisTemplate) {
		return new IpCacheFactory("redis");
	}

}
