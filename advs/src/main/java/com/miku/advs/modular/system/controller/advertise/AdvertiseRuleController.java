package com.miku.advs.modular.system.controller.advertise;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.annotion.BussinessLog;
import com.miku.advs.core.common.constant.factory.ConstantFactory;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.advertise.AdvRule;
import com.miku.advs.modular.system.entity.system.Country;
import com.miku.advs.modular.system.service.advertise.AdvertiseRuleService;
import com.miku.advs.modular.system.service.system.CountryService;
import com.miku.advs.modular.system.warpper.AdvertiseRuleWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.miku.advs.core.common.constant.Const.TIME_LIST;

/**
 * Created by hp on 2019/4/10.
 */
@Controller
@RequestMapping("/adv_rule")
public class AdvertiseRuleController extends BaseController {

    private static String PREFIX = "/modular/system/adv_rule/";

    @Autowired
    private AdvertiseRuleService advertiseRuleService;

    @Autowired
    private CountryService countryService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"adv_rule.html";
    }

    @RequestMapping("/add_view")
    public String addView(Model model){
        Map<Integer,String> map = ConstantFactory.me().getSystemVersionList();
        List<Country> countryList = countryService.selectAll();

        model.addAttribute("versionList",map);
        model.addAttribute("timeList",TIME_LIST);
        model.addAttribute("countryList",countryList);
        return PREFIX+"adv_rule_add.html";
    }

    @RequestMapping("/edit_view")
    public String editView(String id,Model model){
        AdvRule rule = advertiseRuleService.getById(id);
        if(rule == null) rule = new AdvRule();
        Map<Integer,String> map = ConstantFactory.me().getSystemVersionList();
        List<Country> countryList = countryService.selectAll();

        model.addAttribute("adv",rule);
        model.addAttribute("versionList",map);
        model.addAttribute("timeList",TIME_LIST);
        model.addAttribute("countryList",countryList);
        return PREFIX+"adv_rule_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) Integer id ){
        Page <Map<String,Object>> page = LayuiPageFactory.defaultPage();
        Page<Map<String, Object>> advs = advertiseRuleService.selectAdvRule(page,id);

        //数据类型需为map结构
        Page<Map<String, Object>> wrapped = new AdvertiseRuleWarpper(advs ).wrap();

        return LayuiPageFactory.createPageInfo(wrapped);
    }

    @RequestMapping("/add")
    @BussinessLog(value = "新增广告规则" )
    @ResponseBody
    public Object add(AdvRule rule){
        rule.setCreatetime(new Date());
        advertiseRuleService.save(rule);

        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    @BussinessLog(value = "修改广告规则" )
    @ResponseBody
    public Object edit(AdvRule rule){
        rule.setUpdatetime(new Date());
        advertiseRuleService.updateDate(rule);
        return SUCCESS_TIP;
    }

    @RequestMapping("/delete")
    @BussinessLog(value = "删除广告规则" )
    @ResponseBody
    public Object delete(Integer id){
        advertiseRuleService.removeById(id);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getAdvRuleInfo")
    @ResponseBody
    public Object getAdvRuleInfo(String id){

        AdvRule rule = advertiseRuleService.getById(id);

        Map<String,Object> map = new HashMap<>();
        if(rule != null){
            map = BeanUtil.beanToMap(rule);
            map.remove("timerange");
        }

        return ResponseData.success(map);

    }
}
