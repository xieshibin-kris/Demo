package com.miku.advs.modular.system.controller.system;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.annotion.BussinessLog;
import com.miku.advs.core.common.constant.dictmap.DomainDict;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.system.Domain;
import com.miku.advs.modular.system.service.system.DomainService;
import com.miku.advs.modular.system.warpper.DomainWarpper;
import com.miku.advs.modular.system.warpper.UserWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2019/4/19.
 */
@Controller
@RequestMapping("/domain")
public class DomainController extends BaseController {

    private static String PREFIX = "/modular/system/domain/";

    @Autowired
    private DomainService domainService;


    @RequestMapping("")
    public String index(){
        return PREFIX+"domain.html";
    }

    @RequestMapping("/add_view")
    public String addView(){
        return PREFIX+"domain_add.html";
    }

    @RequestMapping("/edit_view")
    public String editView(){
        return PREFIX+"domain_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(){

        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String,Object>> domains = domainService.selectDomains(page,null);
        Page wrapped = new DomainWarpper(domains).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);

    }


    @RequestMapping("/add")
    @BussinessLog(value = "添加接口域名",key = "name,url",dict = DomainDict.class)
    @ResponseBody
    public Object add(Domain domain){
        domain.setCreateTime(System.currentTimeMillis());
        domainService.save(domain);
        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    @BussinessLog(value = "添加接口域名",key = "url",dict = DomainDict.class)
    @ResponseBody
    public Object edit(Domain domain){
        domainService.update(domain);
        return SUCCESS_TIP;
    }

    @BussinessLog(value = "删除域名"  )
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Integer id){
        domainService.removeById(id);
        return SUCCESS_TIP;
    }


    @RequestMapping("/getDomainInfo")
    @ResponseBody
    public Object getDomainInfo(Integer id){
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }
        Domain domain = domainService.getById(id);
        Map<String, Object> map = new HashMap<>();

        if(domain!=null) map = BeanUtil.beanToMap(domain);

        return SuccessResponseData.success(map);
    }



}
