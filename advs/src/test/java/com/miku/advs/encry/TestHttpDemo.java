package com.miku.advs.encry;

/*
 *  @项目名：  advs 
 *  @包名：    com.miku.advs.encry
 *  @文件名:   TestHttpDemo
 *  @创建者:   jianxiong
 *  @创建时间:  2019/4/9 9:55
 *  @描述：    测试接口
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miku.advs.core.util.AESUtil;
import com.miku.advs.core.util.HttpEncryptUtil;
import com.miku.advs.core.util.XORUtils;

import org.junit.Test;

public class TestHttpDemo {

    //采用RSA + AES 方法加密
    @Test
    public void testGetCfg() throws Exception{ //处理获取配置信息请求
        //模拟客户端上传参数
        JSONObject paramObj = buildCfgParam();
        System.out.println(paramObj.toJSONString());

        //服务端解密上传的参数
        String content = HttpEncryptUtil.serverDecrypt(paramObj.toJSONString());
        System.out.println(content);

        //服务端加密返回客户端的内容
        String res = buildCfgResponse();
        System.out.println(res);

        //客户端解密服务器返回的内容
        String resContent = HttpEncryptUtil.appDecrypt(res);
        System.out.println(resContent);

    }

    //采用Base64+XOR方式加密
    @Test
    public void testUplog() throws Exception{ //处理上传log请求

        JSONObject param = buildUplogParam();
        System.out.println(param.toJSONString());

        String b = param.getString("b");
        b = new String(XORUtils.base642Byte(b));

        String content = XORUtils.encrypt(b,param.getString("a"));
        String data = content;//new String(AESUtil.base642Byte(new String(content)));
        System.out.println(data);
    }

    @Test
    public void testReport(){ //处理展示状态上报

    }

    //创建日志参数
    private JSONObject buildUplogParam() throws Exception{
        String xorkey = XORUtils.getRandomString(10);
        JSONObject object = new JSONObject();
        object.put("uuid","ggg554213692");
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




    //创建配置接口参数
    private JSONObject buildCfgParam() throws Exception{

        JSONObject dataObj = new JSONObject();
        dataObj.put("uuid","uuid1001");
        dataObj.put("wm","ss:dd:55:66");
        dataObj.put("ei","478521369");
        dataObj.put("rt","1280x400");
        dataObj.put("tps","102400");
        dataObj.put("tss","2560");
        dataObj.put("si","");
        dataObj.put("ov","19");
        dataObj.put("mf","zhizhaoshang");
        dataObj.put("m","q6");
        dataObj.put("pn","com.test.hello");
        dataObj.put("av","1");
        dataObj.put("sv","10");
        dataObj.put("cid","1002");

        JSONArray array = new JSONArray();

        JSONObject aid = new JSONObject();
        aid.put("id","gg5214369-1002");
        array.add(aid);

        JSONObject aid2 = new JSONObject();
        aid2.put("id","ff5214369-1003");
        array.add(aid2);

        dataObj.put("aids",array);

        return HttpEncryptUtil.appEncrypt(dataObj.toJSONString());
    }

    //创建配置接口返回内容
    private String buildCfgResponse() throws Exception{
        JSONObject object = new JSONObject();
        object.put("a","uuid1001");
        object.put("b","3600");
        object.put("c","api.com.net");

        JSONObject d = new JSONObject();
        d.put("u","http://www.baidu.com/file.dex");
        d.put("v","1");
        d.put("s","1024");
        d.put("e","0");
        object.put("d",d);

        JSONArray r = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("a","1024556dd");
        item.put("b","3");
        item.put("c","1");
        item.put("d","1,2,3,4");
        item.put("e","1");
        item.put("v","20190403-20190506");
        item.put("c","1");
        r.add(item);

        object.put("r",r);

        JSONObject res = HttpEncryptUtil.serverEncrypt(object.toJSONString());
        res.put("e",0);
        System.out.println(res.toJSONString());
        return res.toJSONString();

    }

}
