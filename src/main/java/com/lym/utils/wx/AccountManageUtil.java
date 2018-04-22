package com.lym.utils.wx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 账号管理工具类
 * Created by LIUYANMIN on 2018/4/21.
 */
public class AccountManageUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManageUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 创建永久二维码
     * @param scene 场景值，只能是字符串、整数类型
     *              scene_id: 临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     *              scene_str: 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @param accessToken 接口调用凭证
     * @return 成功返回: {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","expire_seconds":60,"url":"http://weixin.qq.com/q/kZgfwMTm72WWPkovabbI"}
     */
    public static String createPermanentQrcode(Object scene, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        if (scene instanceof Integer) {
            params.put("action_name", "QR_LIMIT_SCENE");
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("scene", new HashMap<>().put("scene_id", (Integer) scene));
            params.put("action_info", tmp);
        } else {
            params.put("action_name", "QR_LIMIT_STR_SCENE");
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("scene", new HashMap<>().put("scene_str", scene.toString()));
            params.put("action_info", tmp);
        }
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 创建临时二维码
     * @param expireSeconds 过期时间，单位: 秒， 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @param scene 场景值，只能是字符串、整数类型
     * @param accessToken
     * @return
     */
    public static String createInterimQrcode(int expireSeconds, Object scene, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        if (scene instanceof Integer) {
            params.put("expire_seconds", expireSeconds);
            params.put("action_name", "QR_SCENE");
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("scene", new HashMap<>().put("scene_id", (Integer) scene));
            params.put("action_info", tmp);
        } else {
            params.put("expire_seconds", expireSeconds);
            params.put("action_name", "QR_STR_SCENE");
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("scene", new HashMap<>().put("scene_str", scene.toString()));
            params.put("action_info", tmp);
        }
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过ticket换取二维码，图片类型: image/jpg
     * @param ticket
     * @return
     */
    public static byte[] getQrcodeImage(String ticket) {
        String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
        return HttpUtil.httpsRequestResByte(url, "GET", null);
    }


    /**
     * 长链接转短链接
     * @param longUrl 长连接
     * @param accessToken 调用接口凭证
     * @return 成功返回: {"errcode":0,"errmsg":"ok","short_url":"http:\/\/w.url.cn\/s\/AvCo6Ih"}
     */
    public static String long2shortLink(String longUrl, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("action", "long2short");
        params.put("long_url", longUrl);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
