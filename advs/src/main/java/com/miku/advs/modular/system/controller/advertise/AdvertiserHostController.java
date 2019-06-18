package com.miku.advs.modular.system.controller.advertise;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.annotion.BussinessLog;
import com.miku.advs.core.common.annotion.Permission;
import com.miku.advs.core.common.constant.dictmap.AdvertiseHostDict;
import com.miku.advs.core.common.exception.BizExceptionEnum;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.core.log.LogObjectHolder;
import com.miku.advs.modular.system.entity.advertise.AdvertiserHost;
import com.miku.advs.modular.system.service.advertise.AdvertiserHostService;
import com.miku.advs.modular.system.warpper.AdvertiserHostWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2019/4/2.
 */
@Controller
@RequestMapping("/adv_host")
public class AdvertiserHostController extends BaseController {

    @Autowired
    private AdvertiserHostService advertiserService;

    private static String PREFIX = "/modular/system/adv_host/";

    @RequestMapping("")
    public String index() {
        return PREFIX + "adv_host.html";
    }

    @RequestMapping(value = "/list" )
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) Integer id,@RequestParam(required = false)  String name){

        Page<Map<String, Object>> advs = advertiserService.selectAdvertisers(id,name);

        Page<Map<String, Object>> wrapped = new AdvertiserHostWarpper(advs).wrap();

        return LayuiPageFactory.createPageInfo(wrapped);
    }

    @RequestMapping("/host_add")
    public String addView(){
        return PREFIX+"adv_host_add.html";
    }

    @RequestMapping("/host_edit")
    public String editView(Integer id){
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        AdvertiserHost advertiser = advertiserService.getById(id);
        LogObjectHolder.me().set(advertiser);
        return PREFIX+"adv_host_edit.html";
    }

    @BussinessLog(value = "添加广告主",key = "name",dict = AdvertiseHostDict.class)
    @RequestMapping("/add")
    @ResponseBody
    public Object add(@Valid AdvertiserHost advertiser, BindingResult result){
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        advertiser.setCreatetime(new Date());
        advertiserService.add(advertiser);

        return SUCCESS_TIP;
    }

    @BussinessLog(value = "修改广告主",key = "name",dict = AdvertiseHostDict.class )
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit (@Valid AdvertiserHost advertiser, BindingResult result){
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        advertiserService.edit(advertiser);

        return SUCCESS_TIP;
    }

    @BussinessLog(value = "删除广告主")
    @RequestMapping("/delete")
    @Permission
    @ResponseBody
    public Object delete (@RequestParam Integer id){

        if(ToolUtil.isEmpty(id)){
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        advertiserService.delete(id);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getHostInfo")
    @ResponseBody
    public Object getHostInfo(@RequestParam Integer id){
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }

        //this.userService.assertAuth(userId);
        AdvertiserHost advertiser = advertiserService.getById(id);

        Map<String, Object> map = new HashMap<>();

        if(advertiser!=null) map = BeanUtil.beanToMap(advertiser);

        return ResponseData.success(map);
    }

}
