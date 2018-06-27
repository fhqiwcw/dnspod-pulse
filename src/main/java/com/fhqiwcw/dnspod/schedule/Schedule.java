package com.fhqiwcw.dnspod.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fhqiwcw.dnspod.cache.IpCache;
import com.fhqiwcw.dnspod.dns.DnsUpdater;
import com.fhqiwcw.dnspod.util.IPUtils;

@Component
public class Schedule {
	
	private Logger logger = LoggerFactory.getLogger(Schedule.class);
	
	@Autowired
	private DnsUpdater updater;
	
	@Autowired
	private IpCache ipCache;
	
	@Scheduled(fixedDelayString="${dnsupdate.interval}")
	public void updateARecord() {
		String ip = IPUtils.getOutInternetIpAdderss();
		
		if(ipCache.compareAndPut(ip)) {
			updater.update(ip);
		}else {
			logger.info("running but not updated , current ip: {}" , ip);
		}
	}

}
