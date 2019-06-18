package com.miku.advs.core.util;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.miku.advs.core.common.constant.DBModule;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.factory.DatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;


public class DatabaseUtils {

	private static Logger log = LoggerFactory.getLogger(DatabaseUtils.class.getName());
	/**
	 * 形成sql语句中占位符
	 */
	public static String createQuestionMark(int num) {
		if (num <= 0)
			return "";

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append("?").append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}


	public static void createTableHashKey32(String srcTableName,String key,int moduleId){
		try{
			for(int i=0;i<32;i++){
				String destTableName  = srcTableName + ((key==null)?"":TableNameUtils.getKeySuffix(key)) + "_"+i;
				createTable(srcTableName,destTableName,moduleId);
			}
		}catch(Exception e){
           e.printStackTrace();
		}
	}

	public static void createTableDay(String srcTableName,String key,int databaseId,int days){
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DAY_OF_MONTH, days);
			String destTableName  = srcTableName + ((key==null)?"":TableNameUtils.getKeySuffix(key)) + TableNameUtils.getDateSuffix(c.getTime());

			createTable(srcTableName,destTableName,databaseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTableWeek(String srcTableName,String key,int moduleId,int weeks){
		try {
			Calendar c21 = Calendar.getInstance();
			c21.setTime(new Date());
			c21.add(Calendar.WEEK_OF_MONTH, weeks);

			String destTableName = srcTableName + ((key==null)?"":TableNameUtils.getKeySuffix(key)) + TableNameUtils.getMondayOfThisWeekSuffix(c21.getTime());

			createTable(srcTableName,destTableName,moduleId);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void createTableMonth(String srcTableName,String key,int moduleId,int months){
		try{
			Calendar c31 = Calendar.getInstance();
			c31.setTime(new Date());
			c31.add(Calendar.MONTH, months);

			String destTableName = srcTableName + ((key==null)?"":TableNameUtils.getKeySuffix(key)) + TableNameUtils.getYearMonthSuffix(c31.getTime());

			createTable(srcTableName,destTableName,moduleId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void dropTableDayBefore(String srcTableName,String key,int moduleId,int days){
		try{
			Date date=DateUtils.getDateBefore(new Date(), days);
			String destTableName  = srcTableName + ((key==null)?"":TableNameUtils.getKeySuffix(key)) + TableNameUtils.getDateSuffix(date);

			dropTable(destTableName,moduleId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void dropTableWeek(String srcTableName,String key,int moduleId,int weeks){
		try{
			Date date=DateUtils.getDateBeforeWeek(new Date(), -weeks);
			String destTableName  = srcTableName + ((key==null)?"":TableNameUtils.getKeySuffix(key)) + TableNameUtils.getMondayOfThisWeekSuffix(date);

			dropTable(destTableName,moduleId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void dropTableMonth(String srcTableName,String key,int moduleId,int months){
		try{
			Date date=DateUtils.getDateBeforeMonth(new Date(), -months);
			String destTableName  = srcTableName + ((key==null)?"":TableNameUtils.getKeySuffix(key)) + TableNameUtils.getYearMonthSuffix(date);

			dropTable(destTableName,moduleId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void createTable(String srcTableName, String destTableName,int databaseId){
		if(!isTableExits(destTableName,databaseId)){
			try {
				StringBuffer sql = new StringBuffer();

				sql.append("create table IF NOT EXISTS " + destTableName + " like "+ srcTableName);

				if(databaseId == DBModule.RTDBINFO_ADVS.ordinal()){
					DatabaseFactory.getInstance().createDefalutTable(sql.toString());
				} else if(databaseId == DBModule.RTDBINFO_SYS.ordinal()){
					DatabaseFactory.getInstance().createSysTable(sql.toString());
				}

				log.info("创建表："+destTableName);

				CacheUtil.put(Cache.CONSTANT,destTableName+"-"+databaseId,"1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void dropTable(String destTableName,int moduleId){
		if(isTableExits(destTableName,moduleId)){
			try {
				 if(!ToolUtil.isEmpty(destTableName)) return;

				StringBuffer sql = new StringBuffer();
				sql.append("DROP TABLE IF EXISTS " + destTableName);
				//TODO 删除表

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isTableExits(String destTableName,int databaseId){
		try {
			String tableExists= CacheUtil.get(Cache.CONSTANT,destTableName+"-"+databaseId);
			if(tableExists!=null && tableExists.equals("1")){
				return true;
			}

			boolean isExist = DatabaseFactory.getInstance().isTableExist(destTableName);

			if(isExist){
				CacheUtil.put(Cache.CONSTANT,destTableName+"-"+databaseId,"1");
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return false;
	}

}
