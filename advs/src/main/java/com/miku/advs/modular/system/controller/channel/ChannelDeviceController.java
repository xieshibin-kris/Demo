package com.miku.advs.modular.system.controller.channel;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import com.miku.advs.modular.system.service.channel.ChannelDeviceService;
import com.miku.advs.modular.system.service.channel.ChannelSubService;
import com.miku.advs.modular.system.warpper.ChannelDeviceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/24.
 */
@RequestMapping("/channel_device")
@Controller
public class ChannelDeviceController extends BaseController {

    private final String PREFIX = "/modular/channel/channel_device/";

    @Autowired
    private ChannelDeviceService channelDeviceService;

    @Autowired
    private ChannelSubService channelSubService;

    @RequestMapping("")
    public String index(Model model){
        List<ChannelSub> channelList = channelSubService.selectActiveChannels();
        model.addAttribute("channelList",channelList);

        return PREFIX+"channel_device.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public  Object list(@RequestParam(required = false) String uuid,@RequestParam(required = false)Integer channelId){
        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String,Object>> pages = channelDeviceService.selectAll(page,uuid,channelId);

        Page<Map<String,Object>> wrap = new ChannelDeviceWrapper(pages).wrap();
        return LayuiPageFactory.createPageInfo(pages);

    }
}
