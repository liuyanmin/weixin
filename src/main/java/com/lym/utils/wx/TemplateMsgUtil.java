package com.lym.utils.wx;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 模板消息工具类
 *
 * Created by LIUYANMIN on 2018/4/10.
 */
public class TemplateMsgUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateMsgUtil.class);

    /**
     * 发送微信模板消息默认线程池大小
     */
    public static final int DEFAULT_THREAD_POOL_SIZE = 10;

    /**
     * JSON转换工具类
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 设置所属行业
     * 每个公众号需要设置两个行业，每月可以更改一次
     * @param industryId1 	公众号模板消息所属行业编号
     * @param industryId2 	公众号模板消息所属行业编号
     * @param accessToken 接口调用凭证
     * @return
     */
    public static String setIndustry(String industryId1, String industryId2, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("industry_id1", industryId1);
        params.put("industry_id2", industryId2);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取设置的行业信息
     * @param accessToken
     * @return 成功返回: {"primary_industry":{"first_class":"教育","second_class":"培训"},"secondary_industry":{"first_class":"教育","second_class":"院校"}}
     */
    public static String getIndustry(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + accessToken;
        return HttpUtil.httpsRequest(url, "GET", null);
    }


    /**
     * 获得模板ID
     * @param templateIdShort 模板库中模板的编号，有“TM**”和“OPENTM**”等形式
     * @param accessToken 	接口调用凭证
     * @return 成功返回: {"errcode":0,"errmsg":"ok","template_id":"_dtFJlnzcyI6hbq0_gaIGl6ak5WDh6hbTo2YYAFXxMg"}
     */
    public static String getTemplateId(String templateIdShort, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("template_id_short", templateIdShort);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取模板列表
     * @param accessToken
     * @return 成功返回: {"template_list":[{"template_id":"_dtFJlnzcyI6hbq0_gaIGl6ak5WDh6hbTo2YYAFXxMg","title":"工资发放通知","primary_industry":"教育","deputy_industry":"培训","content":"{{first.DATA}}\n上月应发工资：{{keyword1.DATA}}\n扣缴社会保险：{{keyword2.DATA}}\n个人所得税：{{keyword3.DATA}}\n实发工资：{{keyword4.DATA}}\n公司承担社会保险：{{keyword5.DATA}}\n{{remark.DATA}}","example":"向您工资卡发放\r\n上月应发工资：12000.00￥\r\n扣缴社会保险：1000.00￥\r\n个人所得税：200.00￥\r\n实发工资：10800.00￥\r\n公司承担社会保险：3200.00￥\r\n详细工资明细请登录公司人事系统查询。"}]}
     */
    public static String getTemplateInfoList(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + accessToken;
        return HttpUtil.httpsRequest(url, "GET", null);
    }


    /**
     * 删除模板
     * @param templateId 公众帐号下模板消息ID
     * @param accessToken 接口调用凭证
     * @return 成功返回: {"errcode" : 0,"errmsg" : "ok"}
     */
    public static String deleteTemplate(String templateId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("template_id", templateId);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送一次性订阅消息
     * @param openId 填接收消息的用户openid
     * @param templateId 订阅消息模板ID
     * @param clickUrl 点击消息跳转的链接，需要有ICP备案
     * @param scene 订阅场景值
     * @param title 消息标题，15字以内
     * @param data 	消息正文
     * @param accessToken
     * @return 成功返回: {"errcode" : 0,"errmsg" : "ok"}
     */
    public static String sendOneTimeTemplate(String openId, String templateId, String clickUrl, String scene, String title, Map<String, Object> data, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("template_id", templateId);
        params.put("url", clickUrl);
        params.put("scene", scene);
        params.put("title", title);
        params.put("data", data);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 单个用户发送模板消息
     * @param openId 用户openid
     * @param templateId 模板消息ID
     * @param clickUrl 模板消息点击URL
     * @param data 消息内容
     * @return 返回发送结果，格式: {"errcode":0,"errmsg":"ok","msgid":200228332}
     */
    public static String sendOne(String openId, String templateId, String clickUrl, Map<String, Object> data, String accessToken) {
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", openId);
        params.put("template_id", templateId);
        params.put("url", clickUrl);
        params.put("topcolor", "#FFFFFF");
        params.put("data", data);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(tmpurl, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 异步批量发送模板消息，可以指定线程池大小
     * @param openIds 发送的用户ID
     * @param templateId 模板消息ID
     * @param clickUrl 模板消息点击URL
     * @param data 消息内容
     */
    public static void asyncBatchSend(List<String> openIds, String templateId, String clickUrl, Map<String, Object> data, String accessToken) {
        asyncBatchSend(openIds, templateId, clickUrl, data, DEFAULT_THREAD_POOL_SIZE, accessToken);
    }


    /**
     * 异步批量发送模板消息，可以指定线程池大小
     * @param openIds 发送的用户ID
     * @param templateId 模板消息ID
     * @param clickUrl 模板消息点击URL
     * @param data 消息内容
     * @param threadPoolSize 线程池大小
     */
    public static void asyncBatchSend(List<String> openIds, String templateId, String clickUrl, Map<String, Object> data, int threadPoolSize, String accessToken) {
        ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);
        for (String openId : openIds) {
            threadPool.execute(new AyncSendTemplateMsg(openId, templateId, clickUrl, data, accessToken));
        }
        threadPool.shutdown();
    }


    /**
     * 同步批量发送模板消息，使用默认线程池大小为10
     * @param openIds 发送的用户ID
     * @param templateId 模板消息ID
     * @param clickUrl 模板消息点击URL
     * @param data 消息内容
     * @return
     */
    public static List<Future<String>> syncBatchSend(List<String> openIds, String templateId, String clickUrl, Map<String, Object> data, String accessToken) {
        return syncBatchSend(openIds, templateId, clickUrl, data, DEFAULT_THREAD_POOL_SIZE, accessToken);
    }


    /**
     * 同步批量发送模板消息，可以指定线程池大小
     * @param openIds 发送的用户ID
     * @param templateId 模板消息ID
     * @param clickUrl 模板消息点击URL
     * @param data 消息内容
     * @param threadPoolSize 线程池大小
     * @return 成功返回: {"errcode":0,"errmsg":"ok","openId":"123"}
     */
    public static List<Future<String>> syncBatchSend(List<String> openIds, String templateId, String clickUrl, Map<String, Object> data, int threadPoolSize, String accessToken) {
        ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);
        List<SyncSendTemplateMsg> task = new ArrayList<>();
        for (String openId : openIds) {
            task.add(new SyncSendTemplateMsg(openId, templateId, clickUrl, data, accessToken));
        }
        try {
            List<Future<String>> resultJson = threadPool.invokeAll(task);
            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();// 关闭局部线程池
        }
        return null;
    }


    /**
     * 描述: 发送模板信息给用户
     * @param toUser 用户的openid
     * @param templateId 模板消息ID
     * @param clickUrl 用户点击详情时跳转的url
     * @param topColor 模板字体的颜色
     * @param data 模板消息数据
     * @return
     */
    public static String sendWechatMsgToUser(String toUser, String templateId, String clickUrl, String topColor, Map<String, Object> data, String accessToken) {
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("touser", toUser);
        params.put("template_id", templateId);
        params.put("url", clickUrl);
        params.put("topcolor", topColor);
        params.put("data", data);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(tmpurl, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送模板消息线程类(同步发送使用)
     */
    static class SyncSendTemplateMsg implements Callable<String> {
        private String openId;
        private String templateId;
        private String clickUrl;
        private Map<String, Object> data;
        private String accessToken;

        public SyncSendTemplateMsg(String openId, String templateId, String clickUrl, Map<String, Object> data, String accessToken) {
            this.openId = openId;
            this.templateId = templateId;
            this.clickUrl = clickUrl;
            this.data = data;
            this.accessToken = accessToken;
        }

        @Override
        public String call() throws Exception {
            String result = sendWechatMsgToUser(openId, templateId, clickUrl, "#FFFFFF", data, accessToken);
            Map<String, Object> returnMap = new HashMap<>();
            try {
                returnMap = objectMapper.readValue(result, new TypeReference<ArrayList<HashMap<String, Object>>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            String msg = returnMap.get("errmsg").toString();
            if (!"ok".equalsIgnoreCase(msg)) {
                LOGGER.error("消息发送失败，openid: " + openId + ", " + result);
            }
            returnMap.put("openId", openId);
            return objectMapper.writeValueAsString(returnMap);
        }
    }


    /**
     * 发送模板消息线程类(异步发送使用)
     */
    static class AyncSendTemplateMsg implements Runnable {
        private String openId;
        private String templateId;
        private String clickUrl;
        private Map<String, Object> data;
        private String accessToken;

        public AyncSendTemplateMsg(String openId, String templateId, String clickUrl, Map<String, Object> data, String accessToken) {
            this.openId = openId;
            this.templateId = templateId;
            this.clickUrl = clickUrl;
            this.data = data;
            this.accessToken = accessToken;
        }

        @Override
        public void run() {
            String result = sendWechatMsgToUser(openId, templateId, clickUrl, "#FFFFFF", data, accessToken);
            Map<String, Object> returnMap = new HashMap<>();
            try {
                returnMap = objectMapper.readValue(result, new TypeReference<ArrayList<HashMap<String, Object>>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            String msg = returnMap.get("errmsg").toString();
            if (!"ok".equalsIgnoreCase(msg)) {
                LOGGER.error("消息发送失败，openid: " + openId + ", " + result);
            }
        }
    }

}
