package com.miku.advs.modular.system.controller.advertise;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.common.exception.BizExceptionEnum;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.advertise.Advertise;
import com.miku.advs.modular.system.entity.advertise.AdvertiseIncome;
import com.miku.advs.modular.system.entity.channel.Channel;
import com.miku.advs.modular.system.service.advertise.AdvtiseIncomeService;
import com.miku.advs.modular.system.service.channel.ChannelService;
import com.miku.advs.modular.system.warpper.AdvIncomeWrapper;
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
 * Created by hp on 2019/4/30.
 */
@Controller
@RequestMapping("/adv_income")
public class AdvertiseIncomeController extends BaseController {
    private final String PREFIX ="/modular/system/adv_income/";

    @Autowired
    private AdvtiseIncomeService advtiseIncomeService;

    @Autowired
    private ChannelService channelService;

    @RequestMapping("")
    public String index(Model model){
        List<Channel> channelList = channelService.selectAll();

        model.addAttribute("channelList",channelList);
        return PREFIX+"adv_income.html";
    }

    @RequestMapping("/add_view")
    public String addView (Model model){
        return PREFIX+"adv_income_add.html";
    }

    @RequestMapping("/edit_view")
    public String editView (){
        return PREFIX+"adv_income_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String dateParam,@RequestParam(required = false)  Integer channelId,@RequestParam(required = false)  String advertiseId){

        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String,Object>> list = advtiseIncomeService.selectAll(page,dateParam,channelId,advertiseId);

        Page<Map<String,Object>> wrap = new AdvIncomeWrapper(list).wrap();

        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(AdvertiseIncome advertiseIncome){

        Advertise advertise = ConstantFactory.me().getAdvertiseById(advertiseIncome.getAdvertiseid());

        if(advertise == null){
            throw new ServiceException(BizExceptionEnum.ADVERTISE_NULL);
        }
        advertiseIncome.setChannelid(advertise.getChannelid());
        advertiseIncome.setCreatetime(System.currentTimeMillis());

        advtiseIncomeService.insert(advertiseIncome);

        return SUCCESS_TIP;

    }

    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(AdvertiseIncome advertiseIncome){

        advtiseIncomeService.update(advertiseIncome);

        return SUCCESS_TIP;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }
        advtiseIncomeService.removeById(id);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getAdvIncomeInfo")
    @ResponseBody
    public Object getAdvIncomeInfo(Long id){
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }
        AdvertiseIncome advertiseIncome = advtiseIncomeService.getById(id);
        Map<String,Object> map = new HashMap<>();
        if (advertiseIncome !=  null){
            map = BeanUtil.beanToMap(advertiseIncome);
        }
        return ResponseData.success(map);
    }

}
