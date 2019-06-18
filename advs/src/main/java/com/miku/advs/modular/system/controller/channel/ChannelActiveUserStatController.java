package com.miku.advs.modular.system.controller.channel;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.channel.Channel;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import com.miku.advs.modular.system.entity.channel.Package;
import com.miku.advs.modular.system.service.channel.*;
import com.miku.advs.modular.system.warpper.ChannelActiveUserStatWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/28.
 */
@Controller
@RequestMapping("/channel_activeuser_stat")
public class ChannelActiveUserStatController extends BaseController {


    private final String PREFIX = "/modular/channel/channel_activeuser_stat/";

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelSubService channelSubService;

    @Autowired
    private PackageService packageService;

    @Autowired
    private ChannelActiveUserStatService channelActiveUserStatService;

    @RequestMapping("")
    public String index (Model model){
        List<Channel> channelList = channelService.selectAll();
        List<ChannelSub> channelSubList = channelSubService.selectActiveChannels();
        List<Package> packageList = packageService.selectAll();

        model.addAttribute("channelList",channelList);
        model.addAttribute("channelSubList",channelSubList);
        model.addAttribute("packageList",packageList);

        return PREFIX+"channel_activeuser_stat.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String dateParam, @RequestParam(required = false) Integer channelId, @RequestParam(required = false) Integer parentId,
                       @RequestParam(required = false) Integer packageId) {
        Page page = LayuiPageFactory.defaultPage();
        //Page<Map<String,Object>> list = uuidInfoService.selectnewUserCount(page,dateParam,channelId);
        Page<Map<String, Object>> list = channelActiveUserStatService.selectAll(page, dateParam, channelId, parentId,packageId);

        Page<Map<String, Object>> wrapList = new ChannelActiveUserStatWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrapList);
    }
}
