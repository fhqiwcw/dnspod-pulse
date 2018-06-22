package com.fhqiwcw.dnspod.dns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class DnsUpdater {

	private Logger logger = LoggerFactory.getLogger(DnsUpdater.class);

	public void update(String ip) {
		RestTemplate restTemplate = new RestTemplate();

		// curl -X POST https://dnsapi.cn/Record.Modify -d
		// 'login_token=59103,88cae451d59b7a7d6f6a4cec6bf75aed&format=json
		// &domain_id=64543736&record_id=365196349&sub_domain=home&value=3.2.2.2&record_type=A&record_line_id=0'
		String url = "https://dnsapi.cn/Record.Modify";
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("login_token", "59103,88cae451d59b7a7d6f6a4cec6bf75aed");
		postParameters.add("format", "json");
		postParameters.add("domain_id", "64543736");
		postParameters.add("record_id", "365196349");
		postParameters.add("sub_domain", "home");
		postParameters.add("value", ip);
		postParameters.add("record_type", "A");
		postParameters.add("record_line_id", "0");
		String result = restTemplate.postForObject(url, postParameters, String.class);
		logger.info("update result:{}", result);

	}

}
