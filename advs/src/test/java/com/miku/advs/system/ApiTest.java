package com.miku.advs.system;

import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.util.HttpEncryptUtil;
import com.miku.advs.core.util.XORUtils;
import com.miku.advs.modular.system.entity.system.Domain;
import com.miku.advs.modular.system.mapper.api.TaskMapper;
import com.miku.advs.modular.system.service.api.SystemApiService;
import com.miku.advs.modular.system.service.system.DomainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by hp on 2019/4/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {

    @Resource
    private SystemApiService systemApiService;

    @Autowired
    private DomainService domainService;

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testGetConfig() throws Exception {

        JSONObject param = buildCfgParam();
        param = HttpEncryptUtil.paramDecrypt(param);

        //JSONObject paramResponse =  systemApiService.getConfig(param);

        //System.out.println("响应的加密参数："+paramResponse.toJSONString());
        //System.out.println("解密后参数："+HttpEncryptUtil.paramDecrypt(paramResponse).toJSONString());
    }

    @Test
    public void testUpLog() throws Exception {
        JSONObject param = buildUplogParam();

        param = HttpEncryptUtil.paramDecrypt(param);

        int row = systemApiService.upLog(param);

        System.out.println("响应结果："+ JSONObject.toJSONString(SuccessResponseData.success(row,"结果","")));
    }

    @Test
    public void testReport(){

        JSONObject param = buildReportParam();

        param = HttpEncryptUtil.paramDecrypt(param);


        int row = systemApiService.reportData(param);

        System.out.println("响应结果："+ JSONObject.toJSONString(SuccessResponseData.success(row,"结果","")));

    }

    @Test
    public void testDomain(){
        Page<Domain> page = domainService.selectDomainList(1);
        for (Domain domain:page.getRecords()){
            System.out.println(domain.getName()+":"+domain.getUrl());
        }
    }

    @Test
    public void testDotasource(){
        Map<String,Object> map = taskMapper.getTableName("rtdb_active_user");
        System.out.println("rtdb_active_user:--"+map.get("table_name"));
    }

    //创建配置接口参数
    private JSONObject buildCfgParam() throws Exception{

        JSONObject dataObj = new JSONObject();
        //dataObj.put("uuid","f430695dc5e240488adfff5dcwqettfghgj6");
        dataObj.put("wm","ss:dd:55:66");
        dataObj.put("ei","478521369");
        dataObj.put("rt","1280x400");
        dataObj.put("tps","102400");
        dataObj.put("tss","2560");
        dataObj.put("si","we");
        dataObj.put("ov","19");
        dataObj.put("mf","zhizhaoshang");
        dataObj.put("m","q6");
        dataObj.put("pn","com.test.hello");
        dataObj.put("av","1");
        dataObj.put("sv","10");
        dataObj.put("cid","14");

        JSONArray array = new JSONArray();

        JSONObject aid = new JSONObject();
        aid.put("id","sd3213413");
        array.add(aid);

        JSONObject aid2 = new JSONObject();
        aid2.put("id","41");
        array.add(aid2);

        dataObj.put("aids",array);

        return HttpEncryptUtil.paramEncrypt(dataObj);
    }

    //创建日志参数
    private JSONObject buildUplogParam() throws Exception{
        String xorkey = XORUtils.getRandomString(10);
        JSONObject object = new JSONObject();
        object.put("uuid","ggg55421");
        object.put("err", "runtime  error sdfasd sdfsfwewc sdsdgdgsdfgrwefasdfasdfsdfsdfsdgdfsadlj" +
                "lkjdklsjfklsdjfkjwoeillkskdkfsldlewoolllmd55d77sdf5sd5fw5e75s5a[]]qpqqo");
        object.put("ty","1001");
        object.put("av","10");
        object.put("sv","2");
        object.put("cid","1005");

        String encryptData = XORUtils.encrypt(object.toJSONString(), xorkey);

        String data = XORUtils.byte2Base64(encryptData.getBytes()).replace("\r\n","");

        JSONObject param = new JSONObject();
        param.put("a",xorkey);
        param.put("b",data);
        return param;
    }

    private JSONObject buildReportParam(){
        String xorkey = XORUtils.getRandomString(10);
        JSONObject object = new JSONObject();
        object.put("uuid","ggg5542136ewrqtdfg");
        object.put("av",1001);
        object.put("sv",10);
        object.put("cid",15);

        JSONArray array = new JSONArray();
        JSONObject object1 = new JSONObject();
        object1.put("aid","asdfa");
        object1.put("r",10);
        object1.put("s",3);
        object1.put("c",10);
        object1.put("d","20190423");
        array.add(object1);

        JSONObject object2 = new JSONObject();
        object2.put("aid","31");
        object2.put("r",24);
        object2.put("s",12);
        object2.put("c",32);
        object2.put("d","20190422");
        array.add(object2);

        object.put("d",array);

        String encryptData = XORUtils.encrypt(object.toJSONString(), xorkey);

        String data = XORUtils.byte2Base64(encryptData.getBytes()).replace("\r\n","");

        JSONObject param = new JSONObject();
        param.put("a",xorkey);
        param.put("b",data);
        return param;
    }

}
