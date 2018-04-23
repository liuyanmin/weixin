package com.lym.utils;

import com.lym.utils.wx.MenuUtil;
import com.lym.utils.wx.WxUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理工具类测试
 * Created by LIUYANMIN on 2018/4/23.
 */
public class MenuUtilTest {
    private static String accessToken;

    @Before
    public void getAccessToken() {
        accessToken = WxUtil.getAccessToken("test");
    }

    /**
     * 创建菜单接口测试
     */
    @Test
    public void testCreateMenu() {
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>> menuList = new ArrayList<>();

        /******************第一个一级按钮****************/
        Map<String, Object> firstTab = new HashMap<>();
        List<Map<String, Object>> firstList = new ArrayList<>();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("type", "view");
        item1.put("name", "我要参加");
        item1.put("url", "https://w.url.cn/s/ADz2GJf");
        firstList.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        item2.put("type", "view");
        item2.put("name", "公益明星排行榜");
        item2.put("url", "https://w.url.cn/s/ACEvYmn");
        firstList.add(item2);
        Map<String, Object> item3 = new HashMap<>();
        item3.put("type", "view");
        item3.put("name", "活动细则");
        item3.put("url", "https://w.url.cn/s/AzZuVhf");
        firstList.add(item3);

        firstTab.put("name", "Book分之一");
        firstTab.put("sub_button", firstList);


        /******************第二个一级按钮****************/
        Map<String, Object> secondTab = new HashMap<>();
        List<Map<String, Object>> secondList = new ArrayList<>();

        item1 = new HashMap<>();
        item1.put("type", "view");
        item1.put("name", "90天训练营");
        item1.put("url", "https://hoplink.ksosoft.com/artp");
        secondList.add(item1);
        item2 = new HashMap<>();
        item2.put("type", "view");
        item2.put("name", "免费阅读");
        item2.put("url", "https://hoplink.ksosoft.com/s3mb");
        secondList.add(item2);
        item3 = new HashMap<>();
        item3.put("type", "view");
        item3.put("name", "词汇量测试");
        item3.put("url", "https://hoplink.ksosoft.com/3q45");
        secondList.add(item3);

        secondTab.put("name", "免费阅读");
        secondTab.put("sub_button", secondList);


        /******************第三个一级按钮****************/
        Map<String, Object> thirdTab = new HashMap<>();
        thirdTab.put("type", "view");
        thirdTab.put("name", "我的阅读");
        thirdTab.put("url", "https://hoplink.ksosoft.com/g43x");

        menuList.add(firstTab);
        menuList.add(secondTab);
        menuList.add(thirdTab);
        params.put("button", menuList);

        System.out.println(params);

        String result = MenuUtil.createMenu(params, accessToken);
        System.out.println(result);
    }

    /**
     * 自定义菜单查询接口测试
     */
    @Test
    public void testSelectMenu() {
        String result = MenuUtil.selectMenu(accessToken);
        System.out.println(result);
    }


    /**
     * 自定义菜单删除接口测试
     */
    @Test
    public void testDeleteMenu() {
        String result = MenuUtil.deleteMenu(accessToken);
        System.out.println(result);
    }

}
