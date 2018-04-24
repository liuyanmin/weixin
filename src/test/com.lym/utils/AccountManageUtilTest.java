package com.lym.utils;

import com.lym.utils.wx.AccountManageUtil;
import com.lym.utils.wx.WxUtil;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;

/**
 * 账户管理测试类
 * Created by LIUYANMIN on 2018/4/24.
 */
public class AccountManageUtilTest {
    private static String accessToken;

    @Before
    public void getAccessToken() {
        accessToken = WxUtil.getAccessToken("test");
    }

    /**
     * 创建永久二维码接口测试
     */
    @Test
    public void testCreatePermanentQrcode() {
        String result = AccountManageUtil.createPermanentQrcode(1, accessToken);
        System.out.println(result);
    }

    /**
     * 创建临时二维码接口测试
     */
    @Test
    public void testCreateInterimQrcode() {
        String result = AccountManageUtil.createInterimQrcode(7200, 2, accessToken);
        System.out.println(result);
    }

    /**
     * ticket 换取二维码图片接口测试
     */
    @Test
    public void testGetQrcodeImage() {
        byte[] bytes = AccountManageUtil.getQrcodeImage("gQFJ8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAydU05UzA0LUNkU2oxWGxCdU5xMU8AAgS1id5aAwQgHAAA");
        byte2image(bytes, "F://test.jpg");
    }

    /**
     * 字节数组转图片
     * @param data 字节数组
     * @param path 图片url
     */
    public void byte2image(byte[] data, String path){
        if(data.length < 3 || path.equals("")) {
            return;
        }
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    /**
     * 长链接转短连接接口测试
     */
    @Test
    public void testLong2shortLink() {
        String result = AccountManageUtil.long2shortLink("http://www.baidu.com", accessToken);
        System.out.println(result);
    }

}
