package com.lym.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public interface IWxService {

    String getAccessToken(HttpServletRequest request);
}
