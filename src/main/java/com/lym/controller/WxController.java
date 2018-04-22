package com.lym.controller;

import com.lym.service.IWxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
@Controller
@RequestMapping("/wxController")
public class WxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxController.class);
    @Autowired
    private IWxService wxService;

    /**
     * 获取access_token
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/accessToken")
    public Object getAccessToken(HttpServletRequest request) {
        String result = wxService.getAccessToken(request);
        return result;
    }

}
