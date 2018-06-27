package com.fhqiwcw.dsnpod.dns;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fhqiwcw.dnspod.dns.DnsUpdater;
import com.fhqiwcw.dsnpod.MainTest;

public class DnsUpdaterTest extends MainTest{

	@Autowired
	private DnsUpdater updater;
	
	@Test
	public void testUpdate() {
		updater.update("3.3.3.3");
	}
}
