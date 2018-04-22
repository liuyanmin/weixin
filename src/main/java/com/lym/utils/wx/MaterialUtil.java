package com.lym.utils.wx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 素材管理工具类
 * Created by LIUYANMIN on 2018/4/20.
 */
public class MaterialUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaterialUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 素材类型-图片
     */
    private static final String MATERIAL_TYPE_IMAGE = "image";
    /**
     * 素材类型-视频
     */
    private static final String MATERIAL_TYPE_VIDEO = "video";
    /**
     * 素材类型-语音
     */
    private static final String MATERIAL_TYPE_VOICE = "voice";
    /**
     * 素材类型-图文
     */
    private static final String MATERIAL_TYPE_NEWS = "news";


    /**
     * 获取素材总数（永久素材的总数，也会计算公众平台官网素材管理中的素材）
     * @param accessToken
     * @return 成功返回: {"voice_count":COUNT,"video_count":COUNT,"image_count":COUNT,"news_count":COUNT}
     */
    public static String getMaterialCount(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=" + accessToken;
        return HttpUtil.httpsRequest(url, "GET", null);
    }


    /**
     * 获取永久素材的列表
     * @param type 素材类型
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count 返回素材的数量，取值在1到20之间
     * @param accessToken
     * @return
     */
    public static String getMaterialList(String type, int offset, int count, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("offset", offset);
        params.put("count", count);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取永久素材
     * @param mediaId 要获取的素材的media_id
     * @param accessToken 调用接口凭证
     * @return
     */
    public static String getMaterialOne(String mediaId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("media_id", mediaId);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 删除永久素材
     * @param mediaId 要获取的素材的media_id
     * @param accessToken 调用接口凭证
     * @return
     */
    public static String deleteMaterialOne(String mediaId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("media_id", mediaId);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
