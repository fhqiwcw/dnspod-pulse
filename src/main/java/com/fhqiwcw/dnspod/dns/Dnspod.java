package com.fhqiwcw.dnspod.dns;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaojun
 * Dnspod API
 *
 */
@Component
public class Dnspod {
    private Logger logger = LoggerFactory.getLogger(Dnspod.class);

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 用域名查找域名Id
     * @param loginToken 登录参数-id,token
     * @param type  all：所有域名
     *              mine：我的域名
     *              share：共享给我的域名
     *              ismark：星标域名
     *              pause：暂停域名
     *              vip：VIP域名
     *              recent：最近操作过的域名
     *              share_out：我共享出去的域名
     * @param domain 域名 如：mydomain.com
     * @return domainId
     */
    public String searchDomainId(String loginToken,String type,String domain){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://dnsapi.cn/Domain.List";
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("login_token", loginToken);
        postParameters.add("format", "json");
        postParameters.add("type", type);
        String result  = restTemplate.postForObject(url, postParameters, String.class);
        logger.debug("Domain.List result ===>{}", result);
        JSONObject jsonObject = JSON.parseObject(result);
        // 从结果中找出域名的ID
        String domainId = JSONPath.eval(jsonObject,"$.domains[0].id").toString();
        return domainId;
    }

    /**
     * 查找记录Id
     * @param loginToken 登录参数-id,token
     * @param domainId
     * @param subDomain 二级域名 如二级域名是sub.mydomain.com 这里传sub
     * @return recordId
     */
    public String searchRecordId(String loginToken,String domainId, String subDomain){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://dnsapi.cn/Record.List";
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("login_token", loginToken);
        postParameters.add("format", "json");
        postParameters.add("domain_id", domainId);
        postParameters.add("sub_domain", subDomain);

        String result  = restTemplate.postForObject(url, postParameters, String.class);
        JSONObject jsonObject = JSON.parseObject(result);
        logger.debug("Record.List result ===>{}", result);
        String recordId = JSONPath.eval(jsonObject,"$.records[0].id").toString();
        return recordId;
    }

    /**
     * 更新记录的IP
     * @param loginToken
     * @param domainId
     * @param recordId
     * @param subDomain
     * @param ip
     * @param recordLineId
     */
    public void updateRecored(String loginToken,String domainId, String recordId, String subDomain, String ip, String recordLineId) {
        String url = "https://dnsapi.cn/Record.Modify";
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("login_token", loginToken);
        postParameters.add("format", "json");
        postParameters.add("domain_id", domainId);
        postParameters.add("record_id", recordId);
        postParameters.add("sub_domain", subDomain);
        postParameters.add("value", ip);
        postParameters.add("record_type", "A");
        postParameters.add("record_line_id", recordLineId);
        String result  = restTemplate.postForObject(url, postParameters, String.class);
        logger.info("ip: {} ,update result:{}", ip, result);
    }

}
