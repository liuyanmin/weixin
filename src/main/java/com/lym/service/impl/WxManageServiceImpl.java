package com.lym.service.impl;

import com.lym.dao.IWxManageDao;
import com.lym.pojo.WeixinCommand;
import com.lym.service.IWxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LIUYANMIN on 2018/4/19.
 */
@Service
public class WxManageServiceImpl implements IWxManageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxManageServiceImpl.class);

    @Autowired
    private IWxManageDao wxManageDao;

    @Override
    public List<WeixinCommand> getCommandList() {
        List<WeixinCommand> commandList = wxManageDao.getCommandList();
        return commandList;
    }

    @Override
    public WeixinCommand getCommandByCmdName(String cmdName) {
        WeixinCommand command = wxManageDao.getCommandByCmdName(cmdName);
        return command;
    }

    @Override
    public int saveCommand(WeixinCommand command) {
        String cmdName = command.getCmdname();
        WeixinCommand tmp = wxManageDao.getCommandByCmdName(cmdName);
        int rows = 0;
        if (tmp == null) {
            rows = wxManageDao.saveCommand(command);
        } else {
            rows = wxManageDao.updateCommand(command);
        }
        return rows;
    }

}
