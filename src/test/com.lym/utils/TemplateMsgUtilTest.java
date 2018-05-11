package com.lym.utils;

import com.lym.utils.wx.TemplateMsgUtil;
import com.lym.utils.wx.WxUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * 模板消息工具类测试
 * Created by LIUYANMIN on 2018/4/24.
 */
public class TemplateMsgUtilTest {
    private static String accessToken;

    @Before
    public void getAccessToken() {
        accessToken = WxUtil.getAccessToken("test");
    }

    /**
     * 设置行业信息接口测试
     */
    @Test
    public void testSetIndustry() {
        String result = TemplateMsgUtil.setIndustry("16", "17", accessToken);
        System.out.println(result);
    }


    /**
     * 获取设置的行业信息接口测试
     */
    @Test
    public void testGetIndustry() {
        String result = TemplateMsgUtil.getIndustry(accessToken);
        System.out.println(result);
    }


    /**
     * 获得模板ID接口测试
     */
    @Test
    public void testGetTemplateId() {
        String result = TemplateMsgUtil.getTemplateId("OPENTM200851401", accessToken);
        System.out.println(result);
    }


    /**
     * 获取模板列表接口测试
     */
    @Test
    public void testGetTemplateInfoList() {
        String result = TemplateMsgUtil.getTemplateInfoList(accessToken);
        System.out.println(result);
    }


    /**
     * 删除模板接口测试
     */
    @Test
    public void testDeleteTemplate() {
        String result = TemplateMsgUtil.deleteTemplate("banjt_OxMOw0GGGRjmTe6JvpX4eFE0Rq82XNL4DMXOY", accessToken);
        System.out.println(result);
    }


    /**
     * 一次性订阅消息接口测试
     */
    @Test
    public void testSendOneTimeTemplate() {

    }

}
