package com.fhqiwcw.dnspod.schedule;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fhqiwcw.dnspod.dns.DnsUpdater;
import com.fhqiwcw.dnspod.util.Cache;
import com.fhqiwcw.dnspod.util.IPUtils;

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
