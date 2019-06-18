package com.miku.advs.modular.system.controller.channel;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.channel.Channel;
import com.miku.advs.modular.system.entity.channel.ChannelIncomeStat;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import com.miku.advs.modular.system.entity.channel.Package;
import com.miku.advs.modular.system.service.channel.ChannelIncomeStatService;
import com.miku.advs.modular.system.service.channel.ChannelService;
import com.miku.advs.modular.system.service.channel.ChannelSubService;
import com.miku.advs.modular.system.service.channel.PackageService;
import com.miku.advs.modular.system.warpper.ChannelIncomeStatWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/25.
 */
@RequestMapping("/channel_income_stat")
@Controller
public class ChannelIncomeStatController extends BaseController {

    private final String PREFIX = "/modular/channel/channel_income_stat/";

    @Autowired
    private ChannelIncomeStatService channelIncomeStatService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelSubService channelSubService;

    @Autowired
    private PackageService packageService;

    @RequestMapping("")
    public String index(Model model) {
        List<Channel> channelList = channelService.selectAll();
        List<ChannelSub> channelSubList = channelSubService.selectActiveChannels();
        List<Package> packageList = packageService.selectAll();

        model.addAttribute("channelList",channelList);
        model.addAttribute("channelSubList",channelSubList);
        model.addAttribute("packageList",packageList);

        model.addAttribute("channelList", channelList);
        return PREFIX + "channel_income_stat.html";
    }

    @RequestMapping("/add_view")
    public String addView(Model model) {
        List<ChannelSub> channelSubList = channelSubService.selectActiveChannels();

        model.addAttribute("channelList",channelSubList);
        return PREFIX + "channel_income_stat_add.html";
    }

    @RequestMapping("/edit_view")
    public String editView() {
        return PREFIX + "channel_income_stat_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String dateParam, @RequestParam(required = false) Integer channelId,
                       @RequestParam(required = false) Integer parentId, @RequestParam(required = false) Integer packageId) {
        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String, Object>> stats = channelIncomeStatService.selectAllStats(page, dateParam, channelId,parentId,packageId);

        Page<Map<String, Object>> wrap = new ChannelIncomeStatWrapper(stats).wrap();
        return LayuiPageFactory.createPageInfo(wrap);

    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(ChannelIncomeStat channelIncomeStat) {
        channelIncomeStatService.insert(channelIncomeStat);

        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ChannelIncomeStat channelIncomeStat) {
        channelIncomeStatService.update(channelIncomeStat);
        return SUCCESS_TIP;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String date, Integer channelId) {
        channelIncomeStatService.delete(date, channelId);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getChannelIncomeStatInfo")
    @ResponseBody
    public Object getChannelIncomeStatInfo(String date, Integer channelId) {
        if (ToolUtil.isOneEmpty(date, channelId)) {
            throw new RequestEmptyException();
        }

        ChannelIncomeStat channelIncomeStat = channelIncomeStatService.getById(date, channelId);

        Map<String, Object> map = new HashMap<>();

        if (channelIncomeStat != null) {
            map = BeanUtil.beanToMap(channelIncomeStat);
            ChannelSub channelSub = ConstantFactory.me().getChannelSubById(channelIncomeStat.getChannelid());
            map.put("channelName", channelSub != null ? channelSub.getName() : "");
        }
        return ResponseData.success(map);
    }


}
