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

## 启动应用即可看到日志输出

* [邮件]fhqiwcw@gmail.com
* [QQ]116520782
