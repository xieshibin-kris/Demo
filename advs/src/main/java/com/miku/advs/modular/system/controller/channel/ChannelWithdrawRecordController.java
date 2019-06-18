package com.miku.advs.modular.system.controller.channel;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.page.LayuiPageFactory;
import com.miku.advs.modular.system.entity.channel.ChannelWithdrawRecord;
import com.miku.advs.modular.system.service.channel.ChannelWithdrawRecordService;
import com.miku.advs.modular.system.warpper.ChannelWithdrawRecordWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by hp on 2019/4/25.
 */
@Controller
@RequestMapping("/channel_withdraw_record")
public class ChannelWithdrawRecordController extends BaseController {
    private final String PREFIX ="/modular/channel/channel_withdraw_record/";

    @Autowired
    private ChannelWithdrawRecordService channelWithdrawRecordService;

    @RequestMapping("")
    public String index(Model model){
        return PREFIX+"channel_withdraw_record.html";
    }

    @RequestMapping("/add_view")
    public String addView (Model model){
        return PREFIX+"channel_withdraw_record_add.html";
    }

    @RequestMapping("/edit_view")
    public String editView (){
        return PREFIX+"channel_withdraw_record_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list (@RequestParam(required = false) String dateParam, @RequestParam(required = false) String account){
        Page<Map<String,Object>> page = LayuiPageFactory.defaultPage();

        Page<Map<String,Object>> records = channelWithdrawRecordService.selectAllRecords(page,dateParam,account);

        Page<Map<String,Object>> wrap = new ChannelWithdrawRecordWrapper(records).wrap();

        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(ChannelWithdrawRecord channelWithdrawRecord){
        channelWithdrawRecordService.insert(channelWithdrawRecord);
        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ChannelWithdrawRecord channelWithdrawRecord){
        channelWithdrawRecordService.update(channelWithdrawRecord);
        return SUCCESS_TIP;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        channelWithdrawRecordService.delete(id);
        return SUCCESS_TIP;
    }

    @RequestMapping("/getChannelWithRecordInfo")
    @ResponseBody
    public Object getChannelWithdrawRecordInfo(Long id){

        if(ToolUtil.isEmpty(id)){
            throw new RequestEmptyException();
        }

        ChannelWithdrawRecord channelWithdrawRecord = channelWithdrawRecordService.getById(id);

        return ResponseData.success(channelWithdrawRecord);

    }
}
