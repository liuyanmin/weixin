package com.lym.utils.wx;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理工具类
 * Created by LIUYANMIN on 2018/4/21.
 */
public class UserManageUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManageUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获取用户基本信息
     * @param openId 普通用户的标识，对当前公众号唯一
     * @param accessToken 调用接口凭证
     * @return
     */
    public static String getUserInfo(String openId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        return HttpUtil.httpsRequest(url, "GET", null);
    }


    /**
     * 批量获取用户基本信息（最多支持一次拉取100条）
     * @param data map格式类型: {"openid": "otvxTs4dckWG7imySrJd6jSi0CWE","lang": "zh_CN"}
     * @param accessToken
     * @return
     */
    public static String getBatchUserInfo(List<Map<String, String>> data, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("user_list", data);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 分批获取用户列表
     * 一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求。
     * 注意: 拉取到最后一次的时候next_openid也是有值的，但count=0
     * @param nextOpenId 第一个拉取的OPENID，不填默认从头开始拉取
     * @param accessToken 调用接口凭证
     * @return 成功返回: {"total":2,"count":2,"data":{"openid":["oPiZ20-ZfrT5nncexmB3vj_ErRMY","oPiZ2009qZo8imODDtUm9i6LEfm0"]},"next_openid":"oPiZ2009qZo8imODDtUm9i6LEfm0"}
     */
    public static String getSubscribeOpenId(String nextOpenId, String accessToken) {
        String url;
        if (StringUtils.isNotBlank(nextOpenId)) {
            url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&next_openid=" + nextOpenId;
        } else {
            url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
        }
        return HttpUtil.httpsRequest(url, "GET", null);
    }


    /**
     * 获取所有的用户列表
     * @param accessToken 调用接口凭证
     * @return 返回关注用户openId 列表
     */
    public static List<String> getAllSubscribeOpenId(String accessToken) {
        List<String> returnList = new ArrayList<>();

        // 关注总用户数
        int totalCount;

        // 每次获取的用户数
        int count;

        String nextOpenId = "";
        String url;

        do {
            if (StringUtils.isNotBlank(nextOpenId)) {
                url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&next_openid=" + nextOpenId;
            } else {
                url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
            }
            String result = HttpUtil.httpsRequest(url, "GET", null);
            JSONObject json = JSONObject.parseObject(result);

            totalCount = json.getInteger("total");
            count = json.getIntValue("count");

            if (count > 0) {
                JSONObject data = json.getJSONObject("data");
                List<String> openIds = data.getObject("openid", new TypeReference<List<String>>() {
                });
                if (openIds != null && openIds.size() > 0) {
                    returnList.addAll(openIds);
                }
                nextOpenId = json.get("next_openid").toString();
            }
        } while (returnList.size() < totalCount);
        return returnList;
    }


    /**
     * 获取公众号的黑名单列表
     * 该接口每次调用最多可拉取 10000 个OpenID，当列表数较多时，可以通过多次拉取的方式来满足需求。
     * @param beginOpenId 当 begin_openid 为空时，默认从开头拉取
     * @param accessToken
     * @return 成功返回: {"total":2,"count":2,"data":{"openid":["oPiZ20-ZfrT5nncexmB3vj_ErRMY","oPiZ2009qZo8imODDtUm9i6LEfm0"]},"next_openid":"oPiZ2009qZo8imODDtUm9i6LEfm0"}
     */
    public static String getBlackList(String beginOpenId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("begin_openid", beginOpenId);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 批量拉黑用户
     * @param openIdList 需要拉入黑名单的用户的openid，一次拉黑最多允许20个
     * @param accessToken
     * @return 成功返回: {"errcode": 0,"errmsg": "ok"}
     */
    public static String batchBlackList(List<String> openIdList, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("openid_list", openIdList);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 批量取消拉黑用户
     * @param openIdList
     * @param accessToken
     * @return
     */
    public static String batchCancelBlackList(List<String> openIdList, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("openid_list", openIdList);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 设置用户备注名
     * @param openId 用户标识
     * @param remark 新的备注名，长度必须小于30字符
     * @param accessToken 调用接口凭证
     * @return {"errcode":0,"errmsg":"ok"}
     */
    public static String setUserRemark(String openId, String remark, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("openid", openId);
        params.put("remark", remark);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*************************************用户标签管理************************************/

    /**
     * 创建标签
     * 一个公众号，最多可以创建100个标签。
     * @param name 	标签名（30个字符以内）
     * @param accessToken 调用接口凭据
     * @return 成功返回: {"tag":{"id":100,"name":"北京"}}
     */
    public static String createTag(String name, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("name", name);
        params.put("tag", tmp);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取公众号已创建的标签
     * @param accessToken
     * @return 成功返回: {"tags":[{"id":2,"name":"星标组","count":0},{"id":100,"name":"北京","count":0}]}
     */
    public static String getTagList(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=" + accessToken;
        return HttpUtil.httpsRequest(url, "GET", null);
    }


    /**
     * 编辑标签
     * @param tagId 标签ID
     * @param tagName 标签名称
     * @param accessToken
     * @return 成功返回: {"errcode":0,"errmsg":"ok"}
     */
    public static String updateTag(int tagId, String tagName, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("id", tagId);
        tmp.put("name", tagName);
        params.put("tag", tmp);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 删除标签
     * 请注意，当某个标签下的粉丝超过10w时，后台不可直接删除标签。此时，开发者可以对该标签下的openid列表，先进行取消标签的操作，直到粉丝数不超过10w后，才可直接删除该标签。
     * @param tagId 标签ID
     * @param accessToken
     * @return 成功返回: {"errcode":0,"errmsg":"ok"}
     */
    public static String deleteTag(int tagId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("id", tagId);
        params.put("tag", tmp);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取标签下粉丝列表
     * @param tagId 标签ID
     * @param nextOpenId 拉取列表最后一个用户的openid
     * @param accessToken
     * @return 成功返回: {"count":2,"data":{"openid":["oPiZ20-ZfrT5nncexmB3vj_ErRMY","oPiZ2009qZo8imODDtUm9i6LEfm0"]},"next_openid":"oPiZ2009qZo8imODDtUm9i6LEfm0"}
     */
    public static String getTagOpenIdList(int tagId, String nextOpenId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("tagid", tagId);
        params.put("next_openid", nextOpenId == null ? "" : nextOpenId);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 批量为用户打标签
     * @param tagId 标签ID
     * @param openIdList 用户openId
     * @param accessToken
     * @return 成功返回: {"errcode":0,"errmsg":"ok"}
     */
    public static String batchTag(int tagId, List<String> openIdList, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("tagid", tagId);
        params.put("openid_list", openIdList);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 批量为用户取消标签
     * @param tagId 标签ID
     * @param openIdList 用户openId
     * @param accessToken
     * @return 成功返回: {"errcode":0,"errmsg":"ok"}
     */
    public static String batchCancelTag(int tagId, List<String> openIdList, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("tagid", tagId);
        params.put("openid_list", openIdList);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取用户身上的标签列表
     * @param openId 用户openId
     * @param accessToken
     * @return 成功返回: {"tagid_list":[100]}
     */
    public static String getUserTagList(String openId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=" + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("openid", openId);
        try {
            String p = objectMapper.writeValueAsString(params);
            return HttpUtil.httpsRequest(url, "POST", p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
