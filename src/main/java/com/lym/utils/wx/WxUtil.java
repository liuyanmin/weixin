package com.lym.utils.wx;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.constant.WeiXinConstant;
import com.lym.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信工具类
 * Created by LIUYANMIN on 2018/4/10.
 */
public class WxUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 获取access_token
     * @param appName 公众号名称
     * @return {"access_token":"8_8xCfh2v3skpEozhwrNGA5gTezAdvBc3RMEeedqeUBSbJDPUbz7XovtNK_HuOuwSGFr9AzMUe5r-vnCJBVd-t7rpr469o8EYMfKOjeKimSEX0RUoxDoFdx7g0JcbStwUz4gU8SftKwxzHSqieKXFbAFAVIQ","expires_in":7200}
     */
    public static String getAccessToken(String appName) {
        String appId = "";
        String secret = "";
        String accessToken = "";
        String url = "";
        switch (appName) {
            case "test":
                appId = WeiXinConstant.WEIXIN_DAWN_READ_APP_ID;
                secret = WeiXinConstant.WEIXIN_DAWN_READ_SECRET;
                break;
            default:
                break;
        }

        url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;
        accessToken = HttpUtil.httpsRequest(url, "GET", null);
        String token = "";
        if (StringUtils.isNotBlank(accessToken)) {
            JSONObject json = JSONObject.parseObject(accessToken);
            token = json.get("access_token").toString();
        }
        return token;
    }

}
