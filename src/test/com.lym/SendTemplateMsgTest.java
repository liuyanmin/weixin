package com.lym;

import com.alibaba.fastjson.JSONObject;
import com.lym.service.impl.WxServiceImpl;
import com.lym.utils.wx.TemplateMsgUtil;
import com.lym.utils.wx.WxUtil;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIUYANMIN on 2018/4/12.
 */
public class SendTemplateMsgTest {

    /**
     * 测试获取access_token
     * 由于无法获取到HttpServletRequest 对象，所以测试该接口的时候需要手动修改一下接口
     */
    @Test
    public void getAccessTokenTest() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        WxServiceImpl wxService = (WxServiceImpl) ctx.getBean("wxServiceImpl");
        String accessToken = wxService.getAccessToken(null);
        System.out.println(accessToken);
    }

    @Test
    public void sendOneTest() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        String templateId = "O9wipYxl3zFmFjACzn388FSuiA2rdKP80HGkCPer4L0";
        // 消息点击跳转url
        String clickUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdaba4ecf6272dd98&redirect_uri=http%3a%2f%2fgrow.iciba.com%2fgrow%2ffoundWord%2fcode%3fmodule%3dto_theme%26configId%3d1&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        String first = "Hello，新的学习任务来啦，快去瞧一瞧~~\n";
        String keyword1 = "英语";
        String keyword2 = "主题";
        String keyword3 = "2018-04-10";
        String keyword4 = "1天";
        String keyword5 = "暂无\n\n动起来，跟着我一起发现单词之美~~\n\n";
        String remark = "点击查看详情>>";
        JSONObject data = packJsonMsg(first, keyword1, keyword2, keyword3, keyword4, keyword5, remark);
        List<String> openIds = new ArrayList<>();
        openIds.add("oPiZ20-ZfrT5nncexmB3vj_ErRMY");
        String accessToken = WxUtil.getAccessToken("test");
        String result = TemplateMsgUtil.sendOne("oPiZ20-ZfrT5nncexmB3vj_ErRMY", templateId, clickUrl, data, accessToken);
        System.out.println(result);
    }

    public JSONObject packJsonMsg(String first, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String remark) {
        JSONObject root = new JSONObject();
        try {
            JSONObject firstJson = new JSONObject();
            firstJson.put("value", first);
            firstJson.put("color", "#000000");
            root.put("first", firstJson);

            JSONObject keyword1Json = new JSONObject();
            keyword1Json.put("value", keyword1);
            keyword1Json.put("color", "#000000");
            root.put("keyword1", keyword1Json);

            JSONObject keyword2Json = new JSONObject();
            keyword2Json.put("value", keyword2);
            keyword2Json.put("color", "#e16817");
            root.put("keyword2", keyword2Json);

            JSONObject keyword3Json = new JSONObject();
            keyword3Json.put("value", keyword3);
            keyword3Json.put("color", "#000000");
            root.put("keyword3", keyword3Json);

            JSONObject keyword4Json = new JSONObject();
            keyword4Json.put("value", keyword4);
            keyword4Json.put("color", "#000000");
            root.put("keyword4", keyword4Json);

            JSONObject keyword5Json = new JSONObject();
            keyword5Json.put("value", keyword5);
            keyword5Json.put("color", "#000000");
            root.put("keyword5", keyword5Json);

            JSONObject remarkJson = new JSONObject();
            remarkJson.put("value", remark);
            remarkJson.put("color", "#e16817");
            root.put("remark", remarkJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }

}
