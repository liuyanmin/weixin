package com.lym.dao.impl;

import com.lym.dao.IWxManageDao;
import com.lym.pojo.WeixinCommand;
import com.lym.pojo.WeixinCommandExample;
import com.lym.pojo.mapper.WeixinCommandMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by LIUYANMIN on 2018/4/19.
 */
public class WxManageDao extends SqlSessionDaoSupport implements IWxManageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxManageDao.class);

    private static final String WEIXIN_COMMAND_PRE = "com.lym.pojo.mapper.WeixinCommandMapper.";

    @Override
    public List<WeixinCommand> getCommandList() {
        WeixinCommandExample example = new WeixinCommandExample();
        List<WeixinCommand> commandList = this.getSqlSession().selectList(WEIXIN_COMMAND_PRE + "selectByExample", example);
        return commandList;
    }

    @Override
    public WeixinCommand getCommandByCmdName(String cmdName) {
        WeixinCommandExample example = new WeixinCommandExample();
        WeixinCommandExample.Criteria criteria = example.createCriteria();
        criteria.andCmdnameEqualTo(cmdName);
        List<WeixinCommand> commandList = this.getSqlSession().selectList(WEIXIN_COMMAND_PRE + "selectByExample", example);
        return (commandList != null && commandList.size() > 0) ? commandList.get(0) : null;
    }

    @Override
    public int saveCommand(WeixinCommand command) {
        WeixinCommandMapper mapper = this.getSqlSession().getMapper(WeixinCommandMapper.class);
        return mapper.insert(command);
    }

    @Override
    public int updateCommand(WeixinCommand command) {
        WeixinCommandMapper mapper = this.getSqlSession().getMapper(WeixinCommandMapper.class);
        return mapper.updateByPrimaryKeySelective(command);
    }

}
