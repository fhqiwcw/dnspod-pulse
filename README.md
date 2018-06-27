# dnspod-pulse
update your outer internet IP address to the DNSPOD which is a DDNS service 


# 本教程说明如何使用Sring Scheduler


## @EnableScheduling 注解启用 Scheduler
```java
@Configuration
@EnableScheduling
public class AppConfig {
    
}

```

## 在需要定时调用的方法上打上 @Scheduled 注解，且所在类需要声明为bean，例如在类上打上 @Component

```java
@Component
public class Schedule {
    
    private Logger logger = LoggerFactory.getLogger(Schedule.class);
    
    @Autowired
    private DnsUpdater updater;
    
    @Scheduled(fixedDelay=60000)
    public void updateARecord() {
        String ip = IPUtils.getOutInternetIpAdderss();
        if(Cache.compareAndPut(ip)) {
            updater.update(ip);
            logger.info("running at:" + Calendar.getInstance().getTime());
        }
        logger.info("running but not updated");
    }

}
```

## @Autowired 生效范围：只要是Spring管理的Bean都会生产，例如@Component注解的Bean,实现FactoryBean的Bean等等。

```java
    @Bean
    @Primary
    public IpCacheFactory getIpCacheFactory(StringRedisTemplate stringRedisTemplate) {
        return new IpCacheFactory("redis");
    }
```

## @Primary 有时工程是定义了多个类型相同的Bean ，这时使用@Autowired注入时会报错有多个类型相同的Bean，这时只能对优先选择的Bean声明时加上@Primary注解，则注入时就会使用此Bean

## FactoryBean

```java
@Configuration
@EnableScheduling
public class AppConfig {
    
    @Bean
    public RedisConnectionFactory getRedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("192.168.1.101", 6379);
        return new JedisConnectionFactory(config);
    }
    
    @Bean
    @Primary
    public IpCacheFactory getIpCacheFactory(StringRedisTemplate stringRedisTemplate) {
        return new IpCacheFactory("redis");
    }

}
```
```java
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
```

## 启动应用即可看到日志输出

* [邮件]fhqiwcw@gmail.com
* [QQ]116520782
