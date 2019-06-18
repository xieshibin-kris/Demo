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
import com.miku.advs.core.common.constant.dictmap.AdvertiseDict;
import com.miku.advs.core.common.exception.BizExceptionEnum;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.core.util.StringUtils;
import com.miku.advs.modular.system.entity.advertise.AdvRule;
import com.miku.advs.modular.system.entity.advertise.Advertise;
import com.miku.advs.modular.system.entity.advertise.AdvertiserHost;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import com.miku.advs.modular.system.entity.system.Dict;
import com.miku.advs.modular.system.service.advertise.AdvertiseRuleService;
import com.miku.advs.modular.system.service.advertise.AdvertiseService;
import com.miku.advs.modular.system.service.advertise.AdvertiserHostService;
import com.miku.advs.modular.system.service.channel.ChannelSubService;
import com.miku.advs.modular.system.service.system.DictService;
import com.miku.advs.modular.system.warpper.AdvertiseWarpper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2019/4/2.
 */
@RequestMapping("/adv")
@Controller
public class AdvertiseController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static String PREFIX = "/modular/system/adv/";

    @Autowired
    private AdvertiseService advertiseService;

    @Autowired
    private AdvertiserHostService advertiserHostService;

    @Autowired
    private ChannelSubService channelSubService;

    @Autowired
    private AdvertiseRuleService advertiseRuleService;

    @Autowired
    private DictService dictService;

    @RequestMapping("")
    @Permission
    public String index(){
        return PREFIX+"adv.html";
    }

    @RequestMapping("/add_view")
    @Permission
    public String addView (Model model){
        List<AdvertiserHost> list = advertiserHostService.list();
        List<ChannelSub> channelList = channelSubService.selectActiveChannels();
        List<AdvRule> ruleList = advertiseRuleService.selectAllRule();
        List<Dict> adTypeList = dictService.selectByParentCode("adType");
        List<Dict> adPositionList = dictService.selectByParentCode("adPosition");

        model.addAttribute("adPositionList",adPositionList);
        model.addAttribute("adTypeList",adTypeList);
        model.addAttribute("channelList",channelList);
        model.addAttribute("list",list);
        model.addAttribute("ruleList",ruleList);

        return PREFIX+"adv_add.html";
    }

    @RequestMapping("/edit_view")
    @Permission
    public String editView (String id,Model model){
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        List<AdvertiserHost> list = advertiserHostService.list();
        List<ChannelSub> channelList = channelSubService.selectActiveChannels();
        List<AdvRule> ruleList = advertiseRuleService.selectAllRule();
        List<Dict> adTypeList = dictService.selectByParentCode("adType");
        List<Dict> adPositionList = dictService.selectByParentCode("adPosition");

        model.addAttribute("adPositionList",adPositionList);
        model.addAttribute("adTypeList",adTypeList);
        model.addAttribute("channelList",channelList);
        model.addAttribute("list",list);
        model.addAttribute("ruleList",ruleList);
        return PREFIX+"adv_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String id, @RequestParam(required = false)  String name){
        Page<Map<String, Object>> advs = advertiseService.selectAdvertises(id,name);

        //数据类型需为map结构
        Page<Map<String, Object>> wrapped = new AdvertiseWarpper(advs ).wrap();

        return LayuiPageFactory.createPageInfo(wrapped);
    }

    @RequestMapping("/add")
    @BussinessLog(value = "添加广告" ,key = "name",dict = AdvertiseDict.class)
    @ResponseBody
    public Object add(@Valid Advertise advertise, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info(bindingResult.getFieldError().getDefaultMessage());
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        if(ToolUtil.isEmpty(advertise.getId())){
            advertise.setId(StringUtils.getRandCodeNo(10));
        }
        advertise.setCreatetime(System.currentTimeMillis());
        advertiseService.save(advertise);
        return SUCCESS_TIP;

    }

    @RequestMapping("/edit")
    @BussinessLog(value = "编辑广告信息" )
    @ResponseBody
    public Object edit (Advertise advertise){
        advertiseService.update(advertise);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getAdvInfo")
    @ResponseBody
    public Object getAdvInfo(@RequestParam String id){
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }

        Advertise advertise = advertiseService.getAdvertiseById(id);

        Map<String, Object> map = new HashMap<>();

        if(advertise!=null) map = BeanUtil.beanToMap(advertise);

        return ResponseData.success(map);
    }

    @BussinessLog(value = "删除广告" )
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id){
        advertiseService.delete(id);
        return SUCCESS_TIP;
    }

}
