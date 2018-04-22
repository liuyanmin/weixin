package com.lym.utils.wx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据统计工具类
 * Created by LIUYANMIN on 2018/4/21.
 */
public class DataStatisticUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataStatisticUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 获取用户增减数据
     * @param beginDate 起始时间，格式: yyyy-MM-dd
     * @param endDate 结束时间，格式: yyyy-MM-dd
     * @param accessToken
     * @return
     */
    public static String getUserSummary(String beginDate, String endDate, String accessToken) {
        String url = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("begin_date", beginDate);
        params.put("end_date", endDate);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取累计用户数据
     * @param beginDate 起始时间，格式: yyyy-MM-dd
     * @param endDate 结束时间，格式: yyyy-MM-dd
     * @param accessToken
     * @return
     */
    public static String getUserCumulate(String beginDate, String endDate, String accessToken) {
        String url = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("begin_date", beginDate);
        params.put("end_date", endDate);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
