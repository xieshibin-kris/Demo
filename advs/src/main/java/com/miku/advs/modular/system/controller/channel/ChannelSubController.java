package com.miku.advs.modular.system.controller.channel;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.modular.system.entity.channel.Channel;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import com.miku.advs.modular.system.entity.channel.Package;
import com.miku.advs.modular.system.service.channel.ChannelService;
import com.miku.advs.modular.system.service.channel.ChannelSubService;
import com.miku.advs.modular.system.service.channel.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/5/13.
 */
@RequestMapping("/channel_sub")
@Controller
public class ChannelSubController extends BaseController {

    private final String PREFIX = "/modular/channel/channel_sub/";

    @Autowired
    private ChannelSubService channelSubService;

    @Autowired
    private PackageService packageService;

    @Autowired
    private ChannelService channelService;

    @RequestMapping("")
    public String index(Model model){
        return PREFIX+"channel_sub.html";
    }

    @RequestMapping("/add_view")
    public String addView (Model model){

        List<Channel> channelList = channelService.selectAll();
        List<Package> packages = packageService.selectAll();

        model.addAttribute("packages",packages);
        model.addAttribute("channelList",channelList);

        return PREFIX+"channel_sub_add.html";
    }

    @RequestMapping("/edit_view")
    public String editView (){
        return PREFIX+"channel_sub_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) Integer id,@RequestParam(required = false) String name){
        Page<Map<String,Object>> page = LayuiPageFactory.defaultPage();

        Page<Map<String,Object>> list =channelSubService.selectAll(page,id,name);

        for (Map<String,Object> map :list.getRecords()){
            map.put("createTime", DateUtils.getTimeFormat((Long) map.get("createTime")));
            map.put("parentName", ConstantFactory.me().getChannelName((Integer) map.get("parentId")));
            map.put("packageName",ConstantFactory.me().getPackageName((Integer)map.get("packageId")));
        }

        return LayuiPageFactory.createPageInfo(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(ChannelSub channelSub){
        channelSub.setCreatetime(System.currentTimeMillis());
        channelSubService.save(channelSub);
        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ChannelSub channelSub){
        channelSubService.update(channelSub);
        return SUCCESS_TIP;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete (Integer id){
        channelSubService.removeById(id);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getChannelSubInfo")
    @ResponseBody
    public Object getChannelSubInfo(Integer id){
        ChannelSub channelSub = channelSubService.getById(id);
        return ResponseData.success(channelSub);
    }

}
