package com.lym.utils;

import com.lym.utils.wx.UserManageUtil;
import com.lym.utils.wx.WxUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理测试类
 * Created by LIUYANMIN on 2018/4/23.
 */
public class UserManageUtilTest {
    private static String accessToken;

    @Before
    public void getAccessToken() {
        accessToken = WxUtil.getAccessToken("test");
    }

    /**
     * 获取用户基本信息接口测试
     */
    @Test
    public void testGetUserInfo() {
        String result = UserManageUtil.getUserInfo("oPiZ20-ZfrT5nncexmB3vj_ErRMY", accessToken);
        System.out.println(result);
    }

    /**
     * 批量获取用户信息接口测试
     */
    @Test
    public void testGetBatchUserInfo() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> tmp = new HashMap<>();
        tmp.put("openid", "oPiZ20-ZfrT5nncexmB3vj_ErRMY");
        tmp.put("lang", "zh_CN");
        list.add(tmp);
        Map<String, String> tmp2 = new HashMap<>();
        tmp2.put("openid", "oPiZ2009qZo8imODDtUm9i6LEfm0");
        tmp2.put("lang", "zh_CN");
        list.add(tmp2);
        String result = UserManageUtil.getBatchUserInfo(list, accessToken);
        System.out.println(result);
    }

    /**
     * 获取用户列表接口测试
     */
    @Test
    public void testGetSubscribeOpenId() {
        String result = UserManageUtil.getSubscribeOpenId("", accessToken);
        System.out.println(result);
    }

    /**
     * 获取公众号黑名单接口测试
     */
    @Test
    public void testGetBlackList() {
        String result = UserManageUtil.getBlackList(null, accessToken);
        System.out.println(result);
    }

    /**
     * 批量拉黑用户接口测试
     */
    @Test
    public void testBatchBlackList() {
        List<String> openIds = new ArrayList<>();
        openIds.add("oPiZ20-ZfrT5nncexmB3vj_ErRMY");
        openIds.add("oPiZ2009qZo8imODDtUm9i6LEfm0");
        String result = UserManageUtil.batchBlackList(openIds, accessToken);
        System.out.println(result);
    }

    /**
     * 批量取消黑名单接口测试
     */
    @Test
    public void testBatchCancelBlackList() {
        List<String> openIds = new ArrayList<>();
        openIds.add("oPiZ20-ZfrT5nncexmB3vj_ErRMY");
        openIds.add("oPiZ2009qZo8imODDtUm9i6LEfm0");
        String result = UserManageUtil.batchCancelBlackList(openIds, accessToken);
        System.out.println(result);
    }

    /**
     * 设置用户昵称接口测试
     */
    @Test
    public void testSetUserRemark() {
        String result = UserManageUtil.setUserRemark("oPiZ2009qZo8imODDtUm9i6LEfm0", "馨馨", accessToken);
        System.out.println(result);
    }

    /**
     * 创建标签接口测试
     */
    @Test
    public void testCreateTag() {
        String result = UserManageUtil.createTag("牡丹江", accessToken);
        System.out.println(result);
    }

    /**
     * 获取公众号已创建的标签接口测试
     */
    @Test
    public void testGetTagList() {
        String result = UserManageUtil.getTagList(accessToken);
        System.out.println(result);
    }

    /**
     * 编辑标签接口测试
     */
    @Test
    public void testUpdateTag() {
        String result = UserManageUtil.updateTag(100, "Beijing", accessToken);
        System.out.println(result);
    }

    /**
     * 删除标签接口测试
     */
    @Test
    public void testDeleteTag() {
        String result = UserManageUtil.deleteTag(104, accessToken);
        System.out.println(result);
    }

    /**
     * 批量为用户打标签接口测试
     */
    @Test
    public void testBatchTag() {
        List<String> openIds = new ArrayList<>();
        openIds.add("oPiZ20-ZfrT5nncexmB3vj_ErRMY");
        openIds.add("oPiZ2009qZo8imODDtUm9i6LEfm0");
        String result = UserManageUtil.batchTag(100, openIds, accessToken);
        System.out.println(result);
    }

    /**
     * 获取用户身上的标签列表接口测试
     */
    @Test
    public void testGetUserTagList() {
        String result = UserManageUtil.getUserTagList("oPiZ2009qZo8imODDtUm9i6LEfm0", accessToken);
        System.out.println(result);
    }

    /**
     * 获取标签下粉丝列表接口测试
     */
    @Test
    public void testGetTagOpenIdList() {
        String result = UserManageUtil.getTagOpenIdList(100, null, accessToken);
        System.out.println(result);
    }

    /**
     * 批量为用户取消标签接口测试
     */
    @Test
    public void testBatchCancelTag() {
        List<String> openIds = new ArrayList<>();
        openIds.add("oPiZ20-ZfrT5nncexmB3vj_ErRMY");
        openIds.add("oPiZ2009qZo8imODDtUm9i6LEfm0");
        String result = UserManageUtil.batchCancelTag(100, openIds, accessToken);
        System.out.println(result);
    }

}
