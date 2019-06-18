package com.miku.advs.core.util;

import com.alibaba.druid.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static final long TIME_MILLIS_ONE_MONTH = 30*24*60*60*1000l;

	public static final long TIME_MILLIS_ONE_DAY = 24*60*60*1000l;

	public static final long TIME_MILLIS_ONE_HOUR = 60*60*1000l;

	public static final long TIME_MILLIS_ONE_MINUTE = 60*1000l;

	public static final String FORMAT_YM = "yyyy-MM";

	public static final String FORMAT_YMD = "yyyy-MM-dd";

    public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_DATE = "yyyyMMdd";

	public static final String FORMAT_MD = "MM-dd";

	public final static String TIME_STAMP_PATTERN = "yyyyMMddHHmmssSSS";

	/**
	 * 返回以毫秒为单位的当前时间
	 * @return long
	 */
	public static long getNowDate(){
		return System.currentTimeMillis();
	}

	/**
	 * 返回以秒为单位的当前时间
	 * @return long
	 */
	public static long getNowDateSeconds(){
		return System.currentTimeMillis()/1000;
	}

	/**
	 * 返回以毫秒为单位的今天开始时间
	 * @return long
	 */
	public static long getTodayStartTime(){
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 返回以毫秒为单位的今天结束时间
	 * @return long
	 */
	public static long getTodayEndTime(){
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.MILLISECOND, 999);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 返回以毫秒为单位的指定时间的当天开始时间
	 * @return long
	 */
	public static long getStartTime(Date date){
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 返回以毫秒为单位的指定时间的当天结束时间
	 * @return long
	 */
	public static long getEndTime(Date date){
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.MILLISECOND, 999);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 返回以毫秒为单位的昨天开始时间
	 * @return long
	 */
	public static long getYesterdayStartTime(){
		return getTodayStartTime() - TIME_MILLIS_ONE_DAY;
	}

	/**
	 * 返回以毫秒为单位的昨天结束时间
	 * @return long
	 */
	public static long getYesterdayEndTime(){
		return getTodayEndTime() - TIME_MILLIS_ONE_DAY;
	}

	/**
	 * 返回以毫秒为单位的这个月第一天开始时间
	 * @return long
	 */
	public static long getMonthStartDateTime(){
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 返回以毫秒为单位的这个月第一天结束时间
	 * @return long
	 */
	public static long getMonthEndDateTime(){
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.roll(Calendar.DAY_OF_MONTH, -1);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTimeInMillis();
	}


	public static long getMonthStartTime(){
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTimeInMillis();
	}

	public static long getMonthEndTime(){
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.roll(Calendar.DAY_OF_MONTH, -1);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.MILLISECOND, 999);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 获取指定时间所在月份的开始时间
	 * @param date
	 * @return
	 */
	public static long getMonthStartTime(Date date){
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 获取指定时间所在月份的结束时间
	 * @param date
	 * @return
	 */
	public static long getMonthEndTime(Date date){
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.roll(Calendar.DAY_OF_MONTH, -1);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.MILLISECOND, 999);
		return currentDate.getTimeInMillis();
	}

	public static long getEndTimeByDate(Date date){
		Calendar currentDate = Calendar.getInstance() ;
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.MILLISECOND, 999);

		return currentDate.getTimeInMillis();
	}

    /**
     * 获取指定时间几天前的时间
     * @param Date
     * @return Date
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

	/**
     * 获取指定时间几天后的时间
     * @param Date
     * @return Date
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.DATE, day);
        return now.getTime();
    }

	public static Date getDateBeforeWeek(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.WEEK_OF_MONTH,day);
        return now.getTime();
    }

	public static Date getDateBeforeMonth(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.MONTH,day);
        return now.getTime();
    }

	/**
	 * 得到当前时间的周几
	 */
	public static Week getTodayWeek(){
		Calendar calendar = Calendar.getInstance();
		return Week.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
	}


	public static long getMondayOfThisWeek(Date day) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 1);

		return c.getTimeInMillis();
	}

	/**
	 * 通过格式化的字符串的到时间
	 */
	public static Date getDateFromStr(String str){
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str) ;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过格式化的字符串的到时间
	 */
	public static Date getDayFromStr(String str){
	    try {
	        return new SimpleDateFormat("yyyy-MM-dd").parse(str) ;
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}/**
	 * 通过格式化的字符串的到时间
	 */
	public static Date getDayFromStr(String str,String format){
	    try {
	        return new SimpleDateFormat(format).parse(str) ;
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * 格式化时间
	 * @param date
	 */
	public static String getDateTime(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) ;
	}


	public static String getDate(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date) ;
	}

	/**
	 * 验证格式化字符串是不是 时间格式(HH:mm:ss)
	 * @param String
	 */
	public static boolean isValidTime(String time){
		if(StringUtils.isEmpty(time)){
			return false;
		}
		String [] parts = time.split(":");
		if(parts == null || parts.length !=3){
			return false;
		}
		return true;
	}

	/**
	 * 从(HH:mm:ss)获取小时数
	 * @param String
	 */
	public static int getHour(String time){
		if(isValidTime(time)){
			String [] parts = time.split(":");
			if(parts != null && parts.length ==3){
				try{
					int hour = Integer.parseInt(parts[0]);
					if(hour >=0 && hour <= 24){
						return hour;
					}
				}catch(Exception e){
				}
			}
		}
		return 0;
	}

	/**
	 * 从(HH:mm:ss)获取分钟数
	 * @param String
	 */
	public static int getMinute(String time){
		if(isValidTime(time)){
			String [] parts = time.split(":");
			if(parts != null && parts.length ==3){
				try{
					int minute = Integer.parseInt(parts[1]);
					if(minute >=0 && minute <= 59){
						return minute;
					}
				}catch(Exception e){
				}
			}
		}
		return 0;
	}

	/**
	 * 从(HH:mm:ss)获取秒
	 * @param String
	 */
	public static int getSecond(String time){
		if(isValidTime(time)){
			String [] parts = time.split(":");
			if(parts != null && parts.length ==3){
				try{
					int second = Integer.parseInt(parts[2]);
					if(second >=0 && second <= 59){
						return second;
					}
				}catch(Exception e){
				}
			}
		}
		return 0;
	}

	/**
	 * 从（HH:mm:ss）获取该时间的毫秒数
	 * @param time
	 * @return
	 */
	public static long getTimeSecond(String time){
		if(isValidTime(time)){
			String [] parts = time.split(":");
			if(parts != null && parts.length ==3){
				try{
					int hour = Integer.parseInt(parts[0]);
					int minute = Integer.parseInt(parts[1]);
					int second = Integer.parseInt(parts[2]);
					long timeSecond = hour*TIME_MILLIS_ONE_HOUR+minute*TIME_MILLIS_ONE_MINUTE+second*1000;
					return timeSecond;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	/**
	 * 获取当前时间小时数
	 */
	public static int getCurrentHour(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取当前时间分钟数
	 */
	public static int getCurrentMinute(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 获取当前时间秒
	 */
	public static int getCurrentSecond(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.SECOND);
	}


	public static Date getTime(int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();

		if (date.before(new Date())) {
			date = addDay(date, 1);
		}
		return date;
	}

	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	public static Date MillisToTime(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(time));
		return getDateFromStr(date);
	}

	public static String getTimeFormat(long time){
		return new SimpleDateFormat(FORMAT_YMDHMS).format(new Date(time));
	}

	public static String getDayFormat(long time){
		return new SimpleDateFormat(FORMAT_YMD).format(new Date(time));
	}

	public static String getNowDateFormat(String format){
		return new SimpleDateFormat(format).format(new Date());
	}

	public static String getDateFormat(String format,Date date){
		return new SimpleDateFormat(format).format(date);
	}

	public static String getDateFormat(String format,long time){
		return new SimpleDateFormat(format).format(new Date(time));
	}

	public static void main(String[] args) {
//		System.out.print("s"+DateUtils.getNowDateSeconds()+new Random().nextInt(9999));
//		System.out.println(getDateFromStr("2015-03-01 00:00:00").getTime());
//		System.out.println(new Date(getMonthEndTime()));
		//System.out.println(getTodayStartTime());
//		System.out.println(getYesterdayStartTime());
//		System.out.println(getDateToStr(new Date(1381334228718l)));
//		System.out.println(getTodayStartTime()-DateUtils.TIME_MILLIS_ONE_HOUR*14);//1389456000000
		System.out.println(getCurrentHour());
		//System.out.println(getDateFromStr("2015-12-01 00:00:00").getTime());
		//System.out.println(getDateFromStr("2015-12-30 00:00:00").getTime());
		//System.out.println(getDateFromStr("2015-11-10 00:00:00").getTime());
		//System.out.println(getDateFromStr("2015-07-30 23:59:59").getTime());
		//System.out.println(MillisToTime(1440518400000L));
		//System.out.println(DateUtils.getTodayStartTime());
		//System.out.println(getDateFromStr("1456934400000L"));
		//long end = System.currentTimeMillis() - DateUtils.TIME_MILLIS_ONE_DAY*44;
		//end = DateUtils.getStartTime(new Date(end));
		//System.out.print(end);


		//System.out.println(getDate(new Date()).substring(5,10));
		//System.out.println(getMinute("2016-01-30 21:33:29"));
/*		System.out.println(getSecond("2016-01-30 21:33:29"));

		System.out.println(DateUtils.getDateBeforeMonth(new Date(), -1));
       long days = (DateUtils.getDayFromStr("2018-07-04").getTime()-DateUtils.getDayFromStr("2018-06-23").getTime())/TIME_MILLIS_ONE_DAY;

        System.out.println(days);*/

/*
		System.out.println( getTimeSecond("08:40:34") );
		System.out.println( getDateFromStr("2019-04-11 08:40:34").getTime()-getDateFromStr("2019-04-11 00:00:00").getTime());
*/

	}

}
