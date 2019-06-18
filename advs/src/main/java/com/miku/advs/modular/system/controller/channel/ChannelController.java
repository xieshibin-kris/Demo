package com.miku.advs.modular.system.controller.channel;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.exception.BizExceptionEnum;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.channel.Channel;
import com.miku.advs.modular.system.service.channel.ChannelService;
import com.miku.advs.modular.system.warpper.ChannelWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by hp on 2019/4/22.
 */
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController {

    private final String PREFIX = "/modular/channel/channel/";

    @Autowired
    private ChannelService channelService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"channel.html";
    }

    @RequestMapping("/add_view")
    public String addView (Model model){

        return PREFIX+"channel_add.html";
    }

    @RequestMapping("/edit_view")
    public String editView (){
        return PREFIX+"channel_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) Integer id,@RequestParam(required = false) String name){

        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String,Object>> channels = channelService.selectAll(page,id,name);

        Page<Map<String,Object>> wrapped = new ChannelWarpper(channels).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Object updateStatus(Integer channelId,Integer status){
        if(ToolUtil.isOneEmpty(channelId,status)){
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        channelService.updateStatus(channelId,status);
        return  SUCCESS_TIP;
    }


    @RequestMapping("/add")
    @ResponseBody
    public Object add(Channel channel){
        channel.setCreateTime(new Date());
        channelService.save(channel);
        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Channel channel){

        channelService.update(channel);
        return SUCCESS_TIP;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete (Integer id){
        channelService.removeById(id);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getChannelInfo")
    @ResponseBody
    public Object getChannelSubInfo(Integer id){
        Channel channel = channelService.getById(id);
        return ResponseData.success(channel);
    }


}
