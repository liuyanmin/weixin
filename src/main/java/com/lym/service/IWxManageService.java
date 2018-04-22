package com.lym.service;

import com.lym.pojo.WeixinCommand;

import java.util.List;

/**
 * Created by LIUYANMIN on 2018/4/19.
 */
public interface IWxManageService {

    List<WeixinCommand> getCommandList();

    WeixinCommand getCommandByCmdName(String cmdName);

    int saveCommand(WeixinCommand command);
}
