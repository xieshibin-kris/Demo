package com.miku.advs.core.util;

/*
 *  @项目名：  advs 
 *  @包名：    com.miku.advs.core.util
 *  @文件名:   XORUtils
 *  @创建者:   jianxiong
 *  @创建时间:  2019/4/8 18:03
 *  @描述：
 */

import java.io.IOException;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class XORUtils {
    /**
     * 异或算法加密/解密
     *
     * @param data 数据（密文/明文）
     * @param key 密钥
     * @return 返回解密/加密后的数据
     */
//    public static byte[] encrypt(byte[] data, byte[] key) {
//        if (data == null || data.length == 0 || key == null || key.length == 0) {
//            return data;
//        }
//        byte[] result = new byte[data.length]; // 使用密钥字节数组循环加密或解密
//        for (int i = 0; i < data.length; i++) {
//            result[i] = (byte)(data[i] ^ key[i % key.length]);
//        }
//        return result;
//    }

    /**
     * 异或算法加密/解密
     * @param str
     * @param key
     * @return
     */
    public static String encrypt(String str,String key) {
        if(str == null || "".equals(str) || key == null || "".equals(key)){
            return str;
        }

        try{
            char[] data = str.toCharArray();
            char[] keyData = key.toCharArray();
            int keyIndex = 0;
            for (int x = 0; x < data.length; x++) {
                data[x] = (char) (data[x] ^ keyData[keyIndex]);
                if (++keyIndex == keyData.length) {
                    keyIndex = 0;
                }
            }
            return new String(data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return str;
    }

    //字节数组转Base64编码
    public static String byte2Base64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    //Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    //产生指定长度的随机字符串
    public static String getRandomString(int length){
        //产生随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //循环length次
        for(int i=0; i<length; i++){
            //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                //如果number产生的是数字0；
                case 0:
                    //产生A-Z的ASCII码
                    result=Math.round(Math.random()*25+65);
                    //将ASCII码转换成字符
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    //产生a-z的ASCII码
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    //产生0-9的数字
                    sb.append(String.valueOf
                            (new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }
}
