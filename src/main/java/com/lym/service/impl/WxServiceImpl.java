package com.lym.service.impl;

import com.lym.constant.Constants;
import com.lym.service.IWxService;
import com.lym.utils.RedisTemplate;
import com.lym.utils.wx.WxUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
@Service
public class WxServiceImpl implements IWxService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxServiceImpl.class);

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 根据用户传的appName 获取对应公众号的access_token
     * @param request
     * @return
     */
    @Override
    public String getAccessToken(HttpServletRequest request) {
        String appName = request.getParameter("appName");
        String accessToken = redisTemplate.hget(Constants.REDIS_ACCESS_TOKEN, appName);
        if (StringUtils.isBlank(accessToken)) {
            accessToken = WxUtil.getAccessToken(appName);
            redisTemplate.hset(Constants.REDIS_ACCESS_TOKEN, appName, accessToken);
            redisTemplate.expire(Constants.REDIS_ACCESS_TOKEN, 3600);
        }
        return accessToken;
    }

}
