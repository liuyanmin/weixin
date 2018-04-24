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
     * 获得模板ID接口测试
     */
    @Test
    public void testGetTemplateId() {
        String result = TemplateMsgUtil.getTemplateId("OPENTM200851401", accessToken);
        System.out.println(result);
    }

}
