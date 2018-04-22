package com.lym.utils.wx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 客服消息工具类
 * Created by LIUYANMIN on 2018/4/20.
 */
public class CustomServiceUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomServiceUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 消息类型-文本
     */
    private static final String MSGTYPE_TEXT = "text";
    /**
     * 消息类型-图片
     */
    private static final String MSGTYPE_IMAGE = "image";
    /**
     * 消息类型-语音
     */
    private static final String MSGTYPE_VOICE = "voice";
    /**
     * 消息类型-视频
     */
    private static final String MSGTYPE_VIDEO = "video";
    /**
     * 消息类型-音乐
     */
    private static final String MSGTYPE_MUSIC = "music";
    /**
     * 消息类型-图文消息（点击跳转到外链）
     */
    private static final String MSGTYPE_NEWS = "news";
    /**
     * 消息类型-图文消息（点击跳转到图文消息页面）
     */
    private static final String MSGTYPE_MPNEWS = "mpnews";
    /**
     * 消息类型-卡券
     */
    private static final String MSGTYPE_WXCARD = "wxcard";


    /**
     * 发送文本客服消息
     * @param openId 接收用户openId
     * @param content 发送内容
     * @param accessToken 接口调用凭证
     * @return
     */
    public static String sendTextCustomMsg(String openId, String content, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("msgtype", MSGTYPE_TEXT);
        params.put("text", new HashMap<>().put("content", content));
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送图片客服消息
     * @param openId
     * @param mediaId
     * @param accessToken
     * @return
     */
    public static String sendImageCustomMsg(String openId, String mediaId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("msgtype", MSGTYPE_IMAGE);
        params.put("image", new HashMap<>().put("media_id", mediaId));
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送语音客服消息
     * @param openId
     * @param mediaId
     * @param accessToken
     * @return
     */
    public static String sendVoiceCustomMsg(String openId, String mediaId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("msgtype", MSGTYPE_VOICE);
        params.put("voice", new HashMap<>().put("media_id", mediaId));
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送视频客服消息
     * @param openId
     * @param mediaId 发送的图片/语音/视频/图文消息（点击跳转到图文消息页）的媒体ID
     * @param thumbMediaId 缩略图
     * @param title 图文消息/视频消息/音乐消息/小程序卡片的标题
     * @param description 图文消息/视频消息/音乐消息的描述
     * @param accessToken
     * @return
     */
    public static String sendVideoCustomMsg(String openId, String mediaId, String thumbMediaId, String title, String description, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("msgtype", MSGTYPE_VIDEO);
        Map<String, Object> data = new HashMap<>();
        data.put("media_id", mediaId);
        data.put("thumb_media_id", thumbMediaId);
        data.put("title", title);
        data.put("description", description);
        params.put("video", data);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送音乐客服消息
     * @param openId
     * @param title 图文消息/视频消息/音乐消息/小程序卡片的标题
     * @param description 图文消息/视频消息/音乐消息的描述
     * @param musicUrl 音乐链接
     * @param hqMusicUrl 高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
     * @param accessToken 调用接口凭证
     * @return
     */
    public static String sendMusicCustomMsg(String openId, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("msgtype", MSGTYPE_MUSIC);
        Map<String, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("description", description);
        data.put("musicurl", musicUrl);
        data.put("hqmusicurl", hqMusicUrl);
        data.put("thumb_media_id", thumbMediaId);
        params.put("music", data);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送图文消息（点击跳转到外链）
     * @param openId
     * @param data 图文消息列表
     * @param accessToken
     * @return
     */
    public static String sendNewsCustomMsg(String openId, Map<String, Object> data, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("msgtype", MSGTYPE_NEWS);
        params.put("news", data);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送图文消息（点击跳转到图文消息页面）
     * @param openId
     * @param mediaId 发送的图片/语音/视频/图文消息（点击跳转到图文消息页）的媒体ID
     * @param accessToken
     * @return
     */
    public static String sendMpnewsCustomMsg(String openId, String mediaId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("msgtype", MSGTYPE_MPNEWS);
        params.put("mpnews", new HashMap<>().put("media_id", mediaId));
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送卡券
     * @param openId
     * @param cardId 卡券ID
     * @param accessToken
     * @return
     */
    public static String sendWxcardCustomMsg(String openId, String cardId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("msgtype", MSGTYPE_WXCARD);
        params.put("wxcard", new HashMap<>().put("card_id", cardId));
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
