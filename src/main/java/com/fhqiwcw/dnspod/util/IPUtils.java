package com.fhqiwcw.dnspod.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class IPUtils {

	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

	public static String getOutInternetIpAdderss() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://ip.taobao.com/service/getIpInfo.php?ip=myip", String.class);
		String ipAddress = parseIpAddress(result);
		logger.debug("ip:{} result:{}", ipAddress, result);
		return ipAddress;
	}

	public static String parseIpAddress(String json) {
		String ip = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(json);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}

}
