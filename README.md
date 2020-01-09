# dnspod-pulse
定时检测公网IP，如有变化调用DDNS API将新的公网IP更新到域名


docker run \
-e LOGIN_TOKEN=id,token \
-e DOMAIN=your-domain.com \
-e SUB_DOMAIN=sub-domain \
-e RECORD_LINE_ID=0 \
-e CHECK_INTERVAL=6000 \
fhqiwcw/dnspod-pulse-docker

配置说明：
* LOGIN_TOKEN 为ddns的鉴权token,格式为id,token 详见：https://support.dnspod.cn/Kb/showarticle/tsid/227/
* DOMAIN 你的域名，如baidu.com
* SUB_DOMAIN 二级域名，如你的域名是www.baidu.com，二级域名为www
* RECORD_LINE_ID 默认为0
* CHECK_INTERVAL 间隔多长时间检测一次IP变化，单位毫秒，建议配置检测间隔大于6000（一分钟），太小会导致请求失败，因检测IP使用阿里的API，对访问频率有限制

---
* [邮件]fhqiwcw@gmail.com
* [QQ]116520782
