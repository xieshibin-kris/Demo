package com.miku.advs.modular.system.controller.channel;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.channel.Channel;
import com.miku.advs.modular.system.service.advertise.ShowStatisticService;
import com.miku.advs.modular.system.service.channel.ChannelService;
import com.miku.advs.modular.system.warpper.ChannelAdvDetailWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/23.
 */
@Controller
@RequestMapping("/channel_adv_detail")
public class ChannelAdvDetailController extends BaseController {

    private final String PREFIX = "/modular/channel/channel_adv_detail/";

    @Autowired
    private ShowStatisticService showStatisticService;

    @Autowired
    private ChannelService channelService;

    @RequestMapping("")
    public String index (Model model){
        List<Channel> channelList = channelService.selectAll();
        model.addAttribute("channelList",channelList);

        return PREFIX+"channel_adv_detail.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String dateParam,@RequestParam(required = false) Integer channelId,
                       @RequestParam(required = false) String advertiseId){
        Page  page = LayuiPageFactory.defaultPage();

        Page<Map<String, Object>> channelAdvs = showStatisticService.selectAll(page,dateParam,channelId,advertiseId);
        Page<Map<String,Object>>  warpperd = new ChannelAdvDetailWarpper(channelAdvs).wrap();

        return LayuiPageFactory.createPageInfo(warpperd);
    }

}
