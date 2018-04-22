package com.lym.dao;

import com.lym.pojo.WeixinCommand;

import java.util.List;

/**
 * Created by LIUYANMIN on 2018/4/19.
 */
public interface IWxManageDao {

    /**
     * 获取关键词回复列表
     */
    List<WeixinCommand> getCommandList();

    /**
     * 通过指令名称获取指令信息
     * @param cmdName 指令名称
     * @return
     */
    WeixinCommand getCommandByCmdName(String cmdName);

    /**
     * 保存指令信息
     * @param command
     * @return
     */
    int saveCommand(WeixinCommand command);

    /**
     * 更新指令信息
     * @param command
     * @return
     */
    int updateCommand(WeixinCommand command);
}
