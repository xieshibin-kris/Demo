package com.miku.advs.core.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.UUID;


public class StringUtils {
	/**
	 * 判断字串是否为空
	 * @param
	 * @author yangchao
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str) || "null".equals(str.toLowerCase())){
			return true;
		}
		return false;
	}

	/**
	 * 生成UUID通用唯一识别码
	 * @return
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 保留一位小数
	 */
	public static String toFloat1(float f){
		DecimalFormat df =new   DecimalFormat("#.0");

		return df.format(f);
	}

	/**
	 * 将ipv4地址转换为long类型
	 */
	public static long ipToLong(String ip){
		if(isEmpty(ip)){
			return 0;
		}

		String [] array = ip.split("\\.");
		if(array.length != 4){
			return 0;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(array[0]);
		for(int i=1;i<array.length;i++){
			if(array[i].length()==1){
				sb.append("00").append(array[i]);
			}else if(array[i].length()==2){
				sb.append("0").append(array[i]);
			}else{
				sb.append(array[i]);
			}
		}
		try{
			return Long.parseLong(sb.toString());
		}catch(Exception e){
		}
		return 0;
	}

	/**
	 * 按位数对字串左边进行补零
	 * @param  str
	 * @param strLength 位数
	 */
	public static String addLeftZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}

	/**
	 * 对字串进行MD5加密
	 * @param inStr
	 */
	public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = (md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

	/**
	 * 生成随机码
	 * @param len
	 * @return
	 */
	public static String getRandCode(int len) {
	    String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int rand = (int) (Math.random() * a.length());
            sbf.append(a.charAt(rand));
        }
	    return sbf.toString();
	}

    public static String getRandCodeNo(int len) {
		String a = "abcdefghij0123456789kl0123456789mnopqr0123456789stuvwxyz0123456789";
		StringBuffer sbf = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int rand = (int) (Math.random() * a.length());
			sbf.append(a.charAt(rand));
		}
		return sbf.toString();
	}

	public static String getRandNum() {
	    int randNum = 1 + (int)(Math.random() * ((999999 - 1) + 1));
	    if(String.valueOf(randNum).length()!=6){
	        return getRandNum();
	    }
	    return String.valueOf(randNum);
	}

	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			System.out.println("随机数为:" + getRandCodeNo(10));
		}
	}
}
