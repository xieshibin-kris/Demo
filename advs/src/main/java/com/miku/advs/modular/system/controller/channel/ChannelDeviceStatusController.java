package com.miku.advs.modular.system.controller.channel;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.channel.DeviceStatus;
import com.miku.advs.modular.system.service.channel.ChannelDeviceStatusService;
import com.miku.advs.modular.system.warpper.ChannelDeviceStatusWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2019/4/26.
 */
@RequestMapping("/channel_device_status")
@Controller
public class ChannelDeviceStatusController extends BaseController {
    private final String PREFIX ="/modular/channel/channel_device_status/";

    @Autowired
    private ChannelDeviceStatusService channelDeviceStatusService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"channel_device_status.html";
    }

    @RequestMapping("/add_view")
    public String addView (){
        return PREFIX+"channel_device_status_add.html";
    }

    @RequestMapping("/edit_view")
    public String editView (){
        return PREFIX+"channel_device_status_edit.html";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String uuid){
        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String,Object>> devices = channelDeviceStatusService.selectAllDeviceStatus(page,uuid);

        Page<Map<String,Object>> wrap = new ChannelDeviceStatusWrapper(devices).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(DeviceStatus deviceStatus){
        channelDeviceStatusService.insert(deviceStatus);
        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(DeviceStatus deviceStatus){
        channelDeviceStatusService.update(deviceStatus);
        return SUCCESS_TIP;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        channelDeviceStatusService.removeById(id);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getChannelDeviceStatusInfo")
    @ResponseBody
    public Object getChannelDeviceStatusInfo(Long id){

        if(ToolUtil.isEmpty(id)){
            throw new RequestEmptyException();
        }

        DeviceStatus deviceStatus = channelDeviceStatusService.getById(id);

        Map<String,Object> map = new HashMap<>();

        if(deviceStatus != null){
            map = BeanUtil.beanToMap(deviceStatus);
        }

        return ResponseData.success(map);

    }
}
