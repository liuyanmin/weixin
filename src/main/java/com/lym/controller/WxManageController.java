package com.lym.controller;

import com.lym.pojo.WeixinCommand;
import com.lym.service.IWxManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by LIUYANMIN on 2018/4/19.
 */
@Controller
@RequestMapping("/manage")
public class WxManageController {

    @Autowired
    private IWxManageService wxManageService;

    @RequestMapping("/command/list")
    public Object getCommandList(HttpServletRequest request, Map<String, Object> model) {
        List<WeixinCommand> weixinCommandList = wxManageService.getCommandList();
        model.put("commandList", weixinCommandList);
        return "command/list";
    }

    @RequestMapping("/command/add")
    public Object toAddCommand(HttpServletRequest request) {
        return "command/add";
    }

    @RequestMapping("/command/edit")
    public Object toEditCommand(HttpServletRequest request, Map<String, Object> model) {
        String cmdName = request.getParameter("cmdName");
        WeixinCommand command = wxManageService.getCommandByCmdName(cmdName);
        model.put("command", command);
        return "command/edit";
    }

    @RequestMapping("/command/save")
    public Object saveCommand(HttpServletRequest request, WeixinCommand command) {
        wxManageService.saveCommand(command);
        return "redirect:/manage/command/list";
    }

}
