package com.fhqiwcw.dsnpod.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fhqiwcw.dnspod.util.IPUtils;
import com.fhqiwcw.dsnpod.MainTest;


public class IpUtilsTest extends MainTest{

	private Logger logger = LoggerFactory.getLogger(IpUtilsTest.class);


	@Test
	public void testGetOutInternetIpAdderss() {
		String result = IPUtils.getOutInternetIpAdderss();
		logger.debug("result:{}", result);

	}

}
