package com.fhqiwcw.dnspod.dns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaojun
 * 调用Dnspod API更新IP地址
 *
 */
@Component
public class DnsUpdater {

	private Logger logger = LoggerFactory.getLogger(DnsUpdater.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Dnspod dnspod;
	
	@Value("${dnsupdate.enabled}")
	private boolean updateEnabled;

	@Value("${dnspod.login_token}")
	private String loginToken;

	@Value("${dnspod.domain}")
	private String domain;

	@Value("${dnspod.sub_domain}")
	private String subDomain;

	@Value("${dnspod.record_line_id}")
	private String recordLineId;


	public void update(String ip) {

		String domainId = dnspod.searchDomainId(loginToken,"all", domain);
		String recordId = dnspod.searchRecordId(loginToken,domainId,subDomain);

		logger.debug("update switch:{}", updateEnabled);
		if(updateEnabled) {
			dnspod.updateRecored(loginToken,domainId,recordId,subDomain,ip,recordLineId);
		}

	}

}
