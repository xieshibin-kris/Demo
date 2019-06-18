package com.miku.advs.modular.system.controller.system;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miku.advs.modular.system.entity.system.set.Set;
import com.miku.advs.modular.system.entity.system.set.Setting;
import com.miku.advs.modular.system.service.system.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Wrapper;
import java.util.Map;

/**
 * Created by hp on 2019/5/7.
 */
@RequestMapping("/set")
@Controller
public class SetController extends BaseController {

    private String PREFIX = "/modular/system/set/";

    @Autowired
    private SetService setService;

    @RequestMapping("")
    public String index(Model model) {

        Map<String,Object> setMaps = setService.getSetMapsByCache();
        model.addAttribute("sets",setMaps);
        return PREFIX + "set_edit.html";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Setting setting){

        Map<String,Object> setMap = BeanUtil.beanToMap(setting);

        if(setMap!=null){
            for ( Map.Entry<String,Object> entry : setMap.entrySet()){
                Set set = new Set();
                try {
                    set.setFiled(entry.getKey());
                    set.setValue((String) entry.getValue());
                } catch (Exception e){
                    e.printStackTrace();
                }
                int row = setService.updateSet(set);
                if(row <= 0) {
                    setService.save(set);
                }

            }
        }
        setService.updateSetMapsCache();

        return SUCCESS_TIP;

    }

}
