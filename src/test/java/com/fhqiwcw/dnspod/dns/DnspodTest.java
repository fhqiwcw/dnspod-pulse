package com.fhqiwcw.dnspod.dns;

import com.fhqiwcw.dsnpod.MainTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;

public class DnspodTest extends MainTest {

    @Autowired
    private Dnspod dnspod;


    private Logger logger = LoggerFactory.getLogger(DnspodTest.class);

    @Test
    public void searchDomainId() {
        String domainId = dnspod.searchDomainId("59103,88cae451d59b7a7d6f6a4cec6bf75aed","all","fhqiwcw.com");
        logger.info("domainId:{}", domainId);
    }

    @Test
    public void searchRecordId() {
        String recordId = dnspod.searchRecordId("59103,88cae451d59b7a7d6f6a4cec6bf75aed","64543736","home");
        logger.info("recordId:{}", recordId);

    }
}
