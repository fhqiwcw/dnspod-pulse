package com.fhqiwcw.dnspod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhaojun
 * 
 * 配置类，基础bean声明
 *
 */
@Configuration
@EnableScheduling
public class AppConfig {
	
	/**
	 * @return
	 * Redis配置
	 */
	@Bean
	public RedisConnectionFactory getRedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("192.168.1.101", 6379);
	    return new JedisConnectionFactory(config);
	}
	
	/**
	 * @param stringRedisTemplate
	 * @return
	 * 使用 beanFactory 声明IpCache对象
	 * 应用中有三个IpCache对象，此对象为Autowired注入的对象，其它两个注册为Spring容器为了使用Spring注入的特性
	 */
	@Bean
	@Primary
	public IpCacheFactory getIpCacheFactory(StringRedisTemplate stringRedisTemplate) {
		return new IpCacheFactory("redis");
	}

}
