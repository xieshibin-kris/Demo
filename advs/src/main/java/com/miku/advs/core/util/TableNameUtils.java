package com.miku.advs.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TableNameUtils {
	
	public static final String FORMAT_YM = "_yyyy_MM";
	
	public static final String FORMAT_YMD = "_yyyy_MM_dd";

	/**
	 * 获取输入日期的所属周第一天的日期
	 * @return _xxxx_xx_xx
	 */
	public static String getMondayOfThisWeekSuffix(Date day) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return getDateSuffix(c.getTime());
	}
	
	/**
	 * 获取输入日期的所属月
	 * @param day
	 * @return _xxxx_xx
	 */
	public static String getYearMonthSuffix(Date date) {
		return new SimpleDateFormat(FORMAT_YM).format(date);
	}
	
	/**
	 * 获取当前日期生成的后缀
	 * @param date
	 * @return _xxxx_xx_xx
	 */
	public static String getDateSuffix(Date date) {
		return new SimpleDateFormat(FORMAT_YMD).format(date);
	}
	
	/**
	 * 获取当前日期的表名
	 * @param prefix 表前缀
	 * @param time
	 * @return _xxxx_xx
	 */
	public static String getSuffix(String format,long time){
		return new SimpleDateFormat(format).format(time);
	}

	/**
	 * 获取输入字串的hash处理后缀
	 * @return _x
	 */
	public static String getHashKeySuffix(String key){
		try{
			int value=Math.abs(key.hashCode());
			return "_"+String.valueOf(value%16);
		}catch(Exception e){
			
		}
		return "";
	}
	
	/**
	 * 获取输入字串的hash处理后缀
	 * @param key
	 * @param count 表的个数
	 * @return _x
	 */
	public static String getHashKeySuffix(String key,int count){
		try{
			int value=Math.abs(key.hashCode());
			return "_"+String.valueOf(value%count);
		}catch(Exception e){
			
		}
		return "";
	}
	
	public static String getHashKeySuffix32(String key){
		try{
			int value=Math.abs(key.hashCode());
			return "_"+String.valueOf(value%32);
		}catch(Exception e){
			
		}
		return "";
	}
	
	public static String getKeySuffix(String key) {
		return "_"+key;
	}

	/**
	 * 获取输入字串的hash处理后缀
	 * @param key
	 * @param count 表的个数
	 * @return _x
	 */
	public static String getHash32TableName(String prefix,String key){
		try{
			int value=Math.abs(key.hashCode());
			return prefix + "_" + String.valueOf(value%32);
		}catch(Exception e){
			
		}
		return prefix;
	}
		
	/**
	 * 获取输入字串的hash处理后缀
	 * @param key
	 * @param count 表的个数
	 * @return _x
	 */
	public static String getHashTableName(String prefix,String key,int count){
		try{
			int value=Math.abs(key.hashCode());
			return prefix + "_" + String.valueOf(value%count);
		}catch(Exception e){
			e.printStackTrace();
		}
		return prefix;
	}
	
	/**
	 * 获取当前月的表名
	 * @param prefix 表前缀
	 * @param time
	 * @return
	 */
	public static String getMonthTableName(String prefix,long time){
	    if (time <= 0) time = new Date().getTime();
		return prefix + new SimpleDateFormat(FORMAT_YM).format(new Date(time));
	}
	
	/**
	 * 获取当前周的表名
	 * @param prefix 表前缀
	 * @param time
	 * @return
	 */
	public static String getWeekTableName(String prefix,long time){
        if (time <= 0) time = new Date().getTime();
		return prefix + getMondayOfThisWeekSuffix(new Date(time));
	}

	/**
	 * 获取当前日期的表名
	 * @param prefix 表前缀
	 * @param time
	 * @return _xxxx_xx_xx
	 */
	public static String getDayTableName(String prefix,long time){
        if (time <= 0) time = new Date().getTime();
		return prefix + new SimpleDateFormat(FORMAT_YMD).format(new Date(time));
	}
	
	/**
	 * 获取当前日期的表名
	 * @param prefix 表前缀
	 * @param time
	 * @return _xxxx_xx_xx
	 */
	public static String getDayTableName(String prefix,Date date){
		return prefix + new SimpleDateFormat(FORMAT_YMD).format(date);
	}
	
	/**
	 * 获取当前日期表名
	 * @param prefix
	 * @param from
	 * @param end
	 * @return
	 */
	public static String getDayTableName(String prefix,long from,long end) {
		StringBuffer sql = new StringBuffer(prefix);
		if (from > 0L) {
			sql.append(getDateSuffix(new Date(from)));
		}else if (end > 0L) {
			sql.append(getDateSuffix(new Date(end)));
		}else {
			sql.append(getDateSuffix(new Date()));
		}
		return sql.toString();
	}
	
	
	/**
	 * 获取当前周表名
	 * @param prefix
	 * @param from
	 * @param end
	 * @return
	 */
	public static String getWeekTableName(String prefix,long from,long end) {
		StringBuffer sql = new StringBuffer(prefix);
		if (from > 0L) {
			sql.append(getMondayOfThisWeekSuffix(new Date(from)));
		}else if (end > 0L) {
			sql.append(getMondayOfThisWeekSuffix(new Date(end)));
		}else {
			sql.append(getMondayOfThisWeekSuffix(new Date()));
		}
		return sql.toString();
	}
	
	/**
	 * 获取当前月表名
	 * @param prefix
	 * @param from
	 * @param end
	 * @return
	 */
	public static String getMonthTableName(String prefix,long from,long end) {
		StringBuffer sql = new StringBuffer(prefix);
		if (from > 0L) {
			sql.append(getYearMonthSuffix(new Date(from)));
		}else if (end > 0L) {
			sql.append(getYearMonthSuffix(new Date(end)));
		}else {
			sql.append(getYearMonthSuffix(new Date()));
		}
		return sql.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(TableNameUtils.getMondayOfThisWeekSuffix(new Date()));
	}
	
}
