package com.miku.advs.core.util;

/*
 *  @项目名：  advs 
 *  @包名：    com.miku.advs.core.util
 *  @文件名:   HttpEncryptUtil
 *  @创建者:   jianxiong
 *  @创建时间:  2019/4/8 15:30
 *  @描述：    http 加密工具
 */

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

public class HttpEncryptUtil {

    private static Logger log = LoggerFactory.getLogger(HttpEncryptUtil.class);
    //APP加密请求内容
    public static JSONObject appEncrypt(String content) throws Exception{
        //将Base64编码后的Server公钥转换成PublicKey对象
        PublicKey serverPublicKey = RSAUtil.string2PublicKey(KeyUtil.SERVER_PUBLIC_KEY);
        //每次都随机生成AES秘钥
        String aesKeyStr = AESUtil.genKeyAES();
        SecretKey aesKey = AESUtil.loadKeyAES(aesKeyStr);
        //用Server公钥加密AES秘钥
        byte[] encryptAesKey = RSAUtil.publicEncrypt(aesKeyStr.getBytes(), serverPublicKey);
        //用AES秘钥加密APP公钥
       // byte[] encryptAppPublicKey = AESUtil.encryptAES(appPublicKeyStr.getBytes(), aesKey);
        //用AES秘钥加密请求内容
        byte[] encryptRequest = AESUtil.encryptAES(content.getBytes(), aesKey);

        JSONObject result = new JSONObject();
        result.put("a", RSAUtil.byte2Base64(encryptAesKey).replaceAll("\r\n", ""));
        //result.put("apk", RSAUtil.byte2Base64(encryptAppPublicKey).replaceAll("\r\n", ""));
        result.put("b", RSAUtil.byte2Base64(encryptRequest).replaceAll("\r\n", ""));
        return result;
    }

    //APP解密服务器的响应内容
    public static String appDecrypt(String content) throws Exception{
        JSONObject result = JSONObject.parseObject(content);
        String encryptAesKeyStr = (String) result.get("a");
        String encryptContent = (String) result.get("b");

        //将Base64编码后的APP私钥转换成PrivateKey对象
        PrivateKey appPrivateKey = RSAUtil.string2PrivateKey(KeyUtil.APP_PRIVATE_KEY);
        //用APP私钥解密AES秘钥
        byte[] aesKeyBytes = RSAUtil.privateDecrypt(RSAUtil.base642Byte(encryptAesKeyStr), appPrivateKey);
        //用AES秘钥解密请求内容
        SecretKey aesKey = AESUtil.loadKeyAES(new String(aesKeyBytes));
        byte[] response = AESUtil.decryptAES(RSAUtil.base642Byte(encryptContent), aesKey);

        return new String(response);
    }

    //服务器加密响应给APP的内容
    public static JSONObject serverEncrypt(String content) throws Exception{
        //将Base64编码后的APP公钥转换成PublicKey对象
        PublicKey appPublicKey = RSAUtil.string2PublicKey(KeyUtil.APP_PUBLIC_KEY);
        //每次都随机生成AES秘钥
        String aesKeyStr = AESUtil.genKeyAES();
        //将Base64编码后的AES秘钥转换成SecretKey对象
        SecretKey aesKey = AESUtil.loadKeyAES(aesKeyStr);
        //用APP公钥加密AES秘钥
        byte[] encryptAesKey = RSAUtil.publicEncrypt(aesKeyStr.getBytes(), appPublicKey);
        //用AES秘钥加密响应内容
        byte[] encryptContent = AESUtil.encryptAES(content.getBytes(), aesKey);

        JSONObject result = new JSONObject();
        result.put("a", RSAUtil.byte2Base64(encryptAesKey).replaceAll("\r\n", ""));
        result.put("b", RSAUtil.byte2Base64(encryptContent).replaceAll("\r\n", ""));
        return result;
    }

    //服务器解密APP的请求内容
    public static String serverDecrypt(String content) throws Exception{
        JSONObject result = JSONObject.parseObject(content);
        String encryptAesKeyStr = (String) result.get("a");
       // String encryptAppPublicKeyStr = (String) result.get("apk");
        String encryptContent = (String) result.get("b");

        //将Base64编码后的Server私钥转换成PrivateKey对象
        PrivateKey serverPrivateKey = RSAUtil.string2PrivateKey(KeyUtil.SERVER_PRIVATE_KEY);
        //用Server私钥解密AES秘钥
        byte[] aesKeyBytes = RSAUtil.privateDecrypt(RSAUtil.base642Byte(encryptAesKeyStr), serverPrivateKey);
        //用AES秘钥解密APP公钥
        SecretKey aesKey = AESUtil.loadKeyAES(new String(aesKeyBytes));
      //  byte[] appPublicKeyBytes = AESUtil.decryptAES(RSAUtil.base642Byte(encryptAppPublicKeyStr), aesKey);
        //用AES秘钥解密请求内容
        byte[] request = AESUtil.decryptAES(RSAUtil.base642Byte(encryptContent), aesKey);

        return new String(request);
    }

    //参数加密 Base64+XOR方式
    public static JSONObject paramEncrypt(JSONObject object){

        String xorkey = XORUtils.getRandomString(10);

        String encryptData = XORUtils.encrypt(object.toJSONString(), xorkey);

        String data = XORUtils.byte2Base64(encryptData.getBytes()).replace("\r\n","");

        JSONObject param = new JSONObject();
        param.put("a",xorkey);
        param.put("b",data);
        return param;
    }

    //参数解密 Base64+XOR方式
    public static JSONObject paramDecrypt(JSONObject jsonParam){
        log.info(jsonParam.toJSONString());

        String b = jsonParam.getString("b");
        try {
            b = new String(XORUtils.base642Byte(b));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = XORUtils.encrypt(b,jsonParam.getString("a"));
        if(!StringUtils.isEmpty(content)){
            log.info("param:"+content);
            return JSONObject.parseObject(content);
        } else {
            return new JSONObject();
        }
    }


}
