package com.lym.utils.wx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 公众号菜单工具类
 * Created by LIUYANMIN on 2018/4/20.
 */
public class MenuUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuUtil.class);

    /**
     * 自定义菜单创建接口
     * @param params 创建菜单的json串
     * @param accessToken
     * @return 正确返回: {"errcode":0,"errmsg":"ok"}
     */
    public static String createMenu(Map<String, Object> params, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自定义菜单查询接口
     * @param accessToken
     * @return
     */
    public static String selectMenu(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken;
        return HttpUtil.httpsRequest(url, "GET", null);
    }

    /**
     * 自定义菜单删除接口
     * @param accessToken
     * @return
     */
    public static String deleteMenu(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
        return HttpUtil.httpsRequest(url, "GET", null);
    }

    /**
     * 创建个性化菜单
     * @param params 创建菜单的json串
     * @param accessToken
     * @return 正确返回{"menuid":"208379533"}
     */
    public static String createIndividuationMenu(Map<String, Object> params, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=" + accessToken;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除个性化菜单
     * @param menuid 菜单id
     * @return 正确返回: {"errcode":0,"errmsg":"ok"}
     */
    public static String deleteIndividuationMenu(String menuid, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=" + accessToken;
        Map<String, String> params = new HashMap<>();
        params.put("menuid", menuid);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试个性化菜单匹配结果
     * @param userId user_id可以是粉丝的OpenID，也可以是粉丝的微信号
     * @return 正确返回: {"errcode":0,"errmsg":"ok"}
     */
    public static String individuationMenuMatchResult(String userId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=" + accessToken;
        Map<String, String> params = new HashMap<>();
        params.put("user_id", userId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取自定义菜单配置接口
     * @param accessToken
     * @return
     */
    public static String getCurrentSelfMenuInfo(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=" + accessToken;
        return HttpUtil.httpsRequest(url, "GET", null);
    }

}
