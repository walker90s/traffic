package com.tt.traffic.common.util;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class DateUtil {
	private static Logger logger = Logger.getLogger(DateUtil.class);
	public static String format = "yyyy-MM-dd HH:mm:ss";
	/*
	 * 2011-01-01 01:01:00
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat(format);

	public static class DatePair {
		Date startDate;
		Date endDate;

		public DatePair(Date startDate, Date endDate) {
			this.startDate = startDate;
			this.endDate = endDate;
		}

		public String toString() {
			return "Start date: " + sdf.format(startDate) + "   End date:" + sdf.format(endDate);
		}
	}

	public static String getCurrentDateString() {
		return sdf.format(new Date());
	}

	public static enum IntervalType {
		Week {
			@Override
			public int getIntervalValue(Calendar cal) {
				return cal.get(Calendar.WEEK_OF_YEAR);
			}
		},
		Month {
			@Override
			public int getIntervalValue(Calendar cal) {
				return cal.get(Calendar.MONTH);
			}
		},
		Quarter {
			@Override
			public int getIntervalValue(Calendar cal) {
				return (cal.get(Calendar.MONTH) / 3) + 1;
			}
		};

		public abstract int getIntervalValue(Calendar cal);

		public static IntervalType parse(String str) {
			if ("Week".equalsIgnoreCase(str)) {
				return Week;
			} else if ("Month".equalsIgnoreCase(str)) {
				return Month;
			} else if ("Quarter".equalsIgnoreCase(str)) {
				return Quarter;
			}

			throw new InvalidParameterException("Wrong parameter: " + str);
		}

	}

	public static List<DatePair> getIntervalDates(Date startDate, Date endDate,
			IntervalType intervalType) {
		List<DatePair> dates = new ArrayList<DatePair>();

		Calendar cal = Calendar.getInstance();

		cal.setTime(startDate);

		Date thisWeekFirstDay = startDate;
		Date thisWeekLastDay = startDate;

		int iniIntervalValue = intervalType.getIntervalValue(cal);

		while (!thisWeekLastDay.after(endDate)) {
			cal.add(Calendar.DAY_OF_MONTH, 1);

			if (thisWeekLastDay.equals(endDate)) {
				dates.add(new DatePair(thisWeekFirstDay, thisWeekLastDay));
				thisWeekLastDay = cal.getTime();
			} else {
				if (iniIntervalValue == intervalType.getIntervalValue(cal)) {
					thisWeekLastDay = cal.getTime();
				} else {
					dates.add(new DatePair(thisWeekFirstDay, thisWeekLastDay));
					thisWeekFirstDay = cal.getTime();
					thisWeekLastDay = cal.getTime();
					iniIntervalValue = intervalType.getIntervalValue(cal);
				}
			}
		}

		return dates;
	}

	/**
	 * 获取num天后的时间字符串
	 * 
	 * @param num
	 *            为整数
	 * @return
	 */
	public static String getNumDaysLaterStrYMDHMSDate(Integer num) {
		String ret = "";
		if (num == null || num == 0)
			return ret;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, num);
		ret = sdf.format(cal.getTime());
		return ret;
	}

	/**
	 * 获取num天前的时间字符串
	 * 
	 * @param num
	 *            为整数
	 * @return
	 */
	public static String getNumDaysBeforeStrYMDHMSDate(Integer num) {
		String ret = "";
		if (num == null || num == 0)
			return ret;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - num);
		ret = sdf.format(cal.getTime());
		return ret;
	}

	/**
	 * 获取num天的时间字符串
	 * 
	 * @param date
	 *            格式为YYYY-MM-DD 的时间字符串
	 * @param num
	 *            为整数
	 * @return
	 */
	public static String getStrYMDDate(String date, int num) {
		String ret = null;
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(ymd.parse(date));
			cal.add(Calendar.DAY_OF_YEAR, num);
			ret = ymd.format(cal.getTime());
		} catch (ParseException e) {
			ret = null;
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * 返回增加numHour小时后的日期时间字符串
	 * 
	 * @param date
	 *            格式为YYYY-MM-DD 的时间字符串
	 * @param hour
	 *            小时字符串
	 * @param numHour
	 *            需要增加的小时数
	 * @return
	 */
	public static String getStrYMDHDate(String date, String hour, int numHour) {
		String ret = null;
		if (null != date && null != hour) {
			StringBuffer sb = new StringBuffer();
			sb.append(date).append(" ");
			Integer intHour = Integer.parseInt(hour);
			if (intHour >= 0 && intHour < 10) {
				if(hour.length()==1){
					sb.append("0").append(hour);
				}else{
					sb.append(hour);
				}
			} else if (intHour >= 10 && intHour < 24) {
				sb.append(hour);
			} else {
				return ret;
			}
			sb.append(":00:00");
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(sdf.parse(sb.toString()));
				cal.add(Calendar.HOUR_OF_DAY, numHour);
				ret = sdf.format(cal.getTime());
			} catch (ParseException e) {
				ret = null;
				e.printStackTrace();
			}
		}

		// System.out.println("ret:"+ret);
		return ret;
	}

	/**
	 * 获取世界时的时间字符串,格式为yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return
	 */
	public static String getStrStandardDate(Date date) {
		// Date nowTime = new Date(); // 要转换的时间
		String retStrDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());

		System.out.println("北京时间：" + cal.getTime());

		cal.add(Calendar.HOUR, -8);
		System.out.println("格林威治时间：" + cal.getTime());
		SimpleDateFormat ss = new SimpleDateFormat("yyyyMMddHHmmss");
		retStrDate = ss.format(cal.getTime());
		return retStrDate;
	}

	/*参数以YYYY-MM-DD hh:mm:ssc*/
	public static String BjTime2UtcTime(String bjTime)
	{
		Date bjDate= DateUtil.getStringToDate(bjTime, "yyyy-MM-dd HH:mm:ss");
		String retStrDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(bjDate.getTime());
//		System.out.println("北京时间：" + cal.getTime());
		cal.add(Calendar.HOUR, -8);
//		System.out.println("格林威治时间：" + cal.getTime());
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		retStrDate = ss.format(cal.getTime());
		return retStrDate;
	}
	
	public static String UtcTime2BjTime(String UTCTime)
	{
		Date bjDate= DateUtil.getStringToDate(UTCTime, "yyyy-MM-dd HH:mm:ss");
		String retStrDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(bjDate.getTime());
//		System.out.println("北京时间：" + cal.getTime());
		cal.add(Calendar.HOUR, 8);
//		System.out.println("格林威治时间：" + cal.getTime());
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		retStrDate = ss.format(cal.getTime());
		return retStrDate;
	}
	
	
	/**
	 * 返回例如(2012年05月19日)格式的日期字符串
	 * 
	 * @return
	 */
	public static String getCurrentDateFormatString() {
		String d = sdf.format(new Date());
		StringBuffer sb = new StringBuffer();
		sb.append(d.substring(0, 4)).append("年").append(d.substring(5, 7)).append("月").append(
				d.substring(8, 10)).append("日");
		return sb.toString();
	}

	public static void main(String[] args) throws ParseException {
		// String sd = "2010-12-22 00:00:00";
		// String ed = "2011-07-03 00:00:00";
		//
		// getStrYMDHDate("2012-02-27","11",24);

		// List<DatePair> dates = getIntervalDates(sdf.parse(sd), sdf.parse(ed),
		// IntervalType.Week);
		//
		// printDatePairList(dates);
		//
		// System.out.println();
		//
		// List<DatePair> dates2 = getIntervalDates(sdf.parse(sd),
		// sdf.parse(ed), IntervalType.Month);
		//
		// printDatePairList(dates2);
		//
		// System.out.println();
		//
		// List<DatePair> dates3 = getIntervalDates(sdf.parse(sd),
		// sdf.parse(ed), IntervalType.Quarter);
		//
		// printDatePairList(dates3);
		int reti=dateDiff("2013-04-14 08:00:00", "2013-04-15 11:00:00");
		logger.info("reti:"+reti);
	}

	private static void printDatePairList(List<DatePair> list) {
		for (DatePair dp : list) {
			System.out.println(dp.toString());
		}
	}

	/**
	 * 比较时间字符串的大小 s1大于s2返回字符串1，s1等于s2返回字符串0，s1小于s2返回字符串-1，其他返回null;
	 * 时间字符串格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param s1
	 *            时间字符串
	 * @param s2
	 *            时间字符串
	 * @return
	 */
	public static Integer compareToDateStr(String s1, String s2) {
		Integer ret = null;
		if (s1 == null || s2 == null) {
			return ret;
		}
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		java.util.Calendar c1 = java.util.Calendar.getInstance();

		java.util.Calendar c2 = java.util.Calendar.getInstance();

		try {

			c1.setTime(df.parse(s1));

			c2.setTime(df.parse(s2));

		} catch (java.text.ParseException e) {

			System.err.println("格式不正确");
			e.printStackTrace();
		}

		Integer result = c1.compareTo(c2);

		if (result == 0) {
			ret = 0;
			// System.out.println("c1相等c2");
		} else if (result < 0) {
			ret = -1;
			// System.out.println("c1小于c2");
		} else if (result > 0) {
			ret = 1;
			// System.out.println("c1大于c2");
		}

		return ret;
	}

	/**
	 * 将日期字符串转换成日期
	 * 
	 * @param date
	 *            时间格式的字符串
	 * @param format
	 *            时间格式(如：yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static Date getStringToDate(String date, String format) {

		Date retdate = null;
		try {
			if (null != date && null != format && !"".equals(date) && !"".equals(format)) {
				SimpleDateFormat formatters = new SimpleDateFormat(format);
				retdate = formatters.parse(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return retdate;
	}

	/**
	 * 取得dateNum天后的日期时间
	 * 
	 * @param date
	 *            日期
	 * @param dateNum
	 *            天数
	 * @return
	 */
	public static Date getDateByDateNum(Date date, int dateNum) {
		Calendar cal = Calendar.getInstance();
		// java.util.Date date = new java.util.Date();
		cal.setTime(date);
		// date = cal.getTime();
		cal.add(Calendar.DATE, dateNum);
		java.util.Date dates = cal.getTime();
		return dates;
	}

	/**
	 * 将日期时间转换成指定格式dateformat的字符串
	 * 
	 * @param date
	 *            日期
	 * @param dateformat
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateToString(Date date, String dateformat) {
		if (null != date && null != dateformat) {
			SimpleDateFormat df = new SimpleDateFormat(dateformat);
			// Calendar cal = Calendar.getInstance();
			// java.util.Date date = new java.util.Date();
			// cal.setTime(date);
			// date = cal.getTime();//date是当前系统时间变量；
			// System.out.println(df.format(date));
			// cal.add(Calendar.DATE, -1);
			// java.util.Date dates = cal.getTime();//dates是前1天的时间变量；
			// System.out.println(df.format(dates));
			return df.format(date);
		} else {
			return null;
		}

	}
	/**
	 * 获取num后的时间
	 * 
	 * @param date
	 *            日期时间
	 * @param dateformat
	 *            时间格式 : yyyy-MM-dd HH:mm:ss
	 * @param num
	 *            时间
	 * @param type
	 *            H为小时，M为分钟
	 * @return
	 */
	public static String getDateNumString(String date, String dateformat, Integer num, String type) {
		String retDateStr=null;
		if (null != date && !"".equals(date) && null != dateformat && !"".equals(dateformat) && null != type && null != num) {
			try {
				Date retdate = null;
				SimpleDateFormat formatters = new SimpleDateFormat(dateformat);
				retdate = formatters.parse(date);
	
				SimpleDateFormat df = new SimpleDateFormat(dateformat);
				Calendar cal = Calendar.getInstance();
				// java.util.Date date = new java.util.Date();
				cal.setTime(retdate);
				//retdate = cal.getTime();// date是当前系统时间变量；
				// System.out.println(df.format(retdate));
				if (type.equals("H")) {
					cal.add(Calendar.HOUR, num);
				} else if (type.equals("M")) {
					cal.add(Calendar.MINUTE, num);
				} else {
					return null;
				}
	
				java.util.Date dates = cal.getTime();// dates是前1天的时间变量；
				// System.out.println(df.format(dates));
				retDateStr = df.format(dates);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return retDateStr;

	}
	/**
	 * 获取num后的时间
	 * 
	 * @param date
	 *            日期时间
	 * @param dateformat
	 *            时间格式
	 * @param num
	 *            时间
	 * @param type
	 *            H为小时，M为分钟
	 * @return
	 */
	public static String getDateNumToString(Date date, String dateformat, Integer num, String type) {
		if (null != date && null != dateformat && null != type && null != num) {
			SimpleDateFormat df = new SimpleDateFormat(dateformat);
			Calendar cal = Calendar.getInstance();
			// java.util.Date date = new java.util.Date();
			cal.setTime(date);
			date = cal.getTime();// date是当前系统时间变量；
			// System.out.println(df.format(date));
			if (type.equals("H")) {
				cal.add(Calendar.HOUR, num);
			} else if (type.equals("M")) {
				cal.add(Calendar.MINUTE, num);
			} else {
				return null;
			}

			java.util.Date dates = cal.getTime();// dates是前1天的时间变量；
			// System.out.println(df.format(dates));
			return df.format(dates);
		} else {
			return null;
		}

	}

	public static Date getFormatTime(String tTime, String formatFrom, String formatTo) {
		SimpleDateFormat sdfFrom = new SimpleDateFormat(formatFrom);
		SimpleDateFormat sdfTo = new SimpleDateFormat(formatTo);
		try {
			Date d = sdfFrom.parse(tTime);
			String td = sdfTo.format(d);
			return sdfTo.parse(td);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getFormatTime(String tTime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date d = DateUtil.sdf.parse(tTime);
			String td = sdf.format(d);
			return sdf.parse(td);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
    public static Integer dateDiff(String startTime, String endTime) {    
        //按照传入的格式生成一个simpledateformate对象
		//SimpleDateFormat sd = new SimpleDateFormat(foramt);
		long nd = 1000*24*60*60;
		//一天的毫秒数 
	    long nh = 1000*60*60;
		//一小时的毫秒数    
		long nm = 1000*60;
		//一分钟的毫秒数    
		long ns = 1000;
		//一秒钟的毫秒数    
		long diff;
		Long hour=new Long(-1);
		try {    
		    //获得两个时间的毫秒时间差异    
		    diff = sdf.parse(endTime).getTime() - sdf.parse(startTime).getTime();
		    long day = diff/nd;
		    //计算差多少天    
		    hour = diff/nh;
		    //计算差多少小时    
		    long min = diff%nd%nh/nm;
		    //计算差多少分钟    
		    long sec = diff%nd%nh%nm/ns;
		    //计算差多少秒    
		    //输出结果    
		    //System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
		   // logger.info("时间相差："+hour+"小时");
		    return hour.intValue();
		} catch (ParseException e) {    
		    e.printStackTrace();
		}
		return hour.intValue();    
    }
	/**
	 * 两个日期相差多少个小时
	 * @param bdate
	 * @param edate
	 * @return
	 */
	public static Long dealCompareDateHourVal(String bdate,String edate){
		Long ret=null;
		try
		{
		    Date d1 = sdf.parse(bdate);
		    Date d2 = sdf.parse(edate);
		    long diff = d2.getTime() - d1.getTime();
		    //long days = diff / (1000 * 60 * 60 * 24);

			ret = diff/(1000*60*60);
		}
		catch (Exception e)
		{
		}
		return ret;
	}
	/**
	 * 两个日期相差多少个minute分钟
	 * @param startTime
	 * @param endTime
	 * @return
	 */
    public static Integer dateMinuteDiff(String startTime, String endTime,Integer minute) {    

        //按照传入的格式生成一个simpledateformate对象
		//SimpleDateFormat sd = new SimpleDateFormat(foramt);
		long nd = 1000*24*60*60;
		//一天的毫秒数 
	    long nh = 1000*60*60;
		//一小时的毫秒数    
		int nm = 1000*60;
		//一分钟的毫秒数    
		long ns = 1000;
		//一秒钟的毫秒数    
		Long diff;
		Integer minInt=null;
		try {    
		    //获得两个时间的毫秒时间差异    
		    diff = sdf.parse(endTime).getTime() - sdf.parse(startTime).getTime(); 
			   Long min = diff/(minute*nm) + 1;
			   minInt = min.intValue();

		    //计算差多少分钟    
		} catch (ParseException e) {    
		    e.printStackTrace();
		}
		return  minInt;
    }
	/**
	 * 两个日期相差多少个minute分钟
	 * @param startTime
	 * @param endTime
	 * @return
	 */
    public static Integer dateCountMinuteDiff(String startTime, String endTime,Integer minute) {    
        //按照传入的格式生成一个simpledateformate对象
		//SimpleDateFormat sd = new SimpleDateFormat(foramt);
		long nd = 1000*24*60*60;
		//一天的毫秒数 
	    long nh = 1000*60*60;
		//一小时的毫秒数    
		int nm = 1000*60;
		//一分钟的毫秒数    
		long ns = 1000;
		//一秒钟的毫秒数    
		Long diff;
		Integer minInt=null;
		try {    
		    //获得两个时间的毫秒时间差异    
		    diff = sdf.parse(endTime).getTime() - sdf.parse(startTime).getTime(); 
			   Long min = diff/(minute*nm) + 1;
			   minInt = min.intValue();

		    //计算差多少分钟    
		} catch (ParseException e) {    
		    e.printStackTrace();
		}
		return  minInt;
    }
	/**
	 * 两个时间相差多少分钟
	 * @param startTime
	 * @param endTime
	 * @return
	 */
    public static Integer dateDiffMinute(String startTime, String endTime) {    
        //按照传入的格式生成一个simpledateformate对象
		//SimpleDateFormat sd = new SimpleDateFormat(foramt);
		long nd = 1000*24*60*60;
		//一天的毫秒数 
	    long nh = 1000*60*60;
		//一小时的毫秒数    
		long nm = 1000*60;
		//一分钟的毫秒数    
		long ns = 1000;
		//一秒钟的毫秒数    
		long diff;
		Long min=new Long(-1);
		try {    
		    //获得两个时间的毫秒时间差异    
		    diff = sdf.parse(endTime).getTime() - sdf.parse(startTime).getTime();
		    //long day = diff/nd;
		    //计算差多少天    
		    //long hour = diff/nh;
		    //计算差多少小时    
		    min = diff/nm;
		    //计算差多少分钟    
		    //long sec = diff%nd%nh%nm/ns;
		    //计算差多少秒    
		    //输出结果    
		    //System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
		   // logger.info("时间相差："+hour+"小时");
		    return min.intValue();
		} catch (ParseException e) {    
		    e.printStackTrace();
		}
		return min.intValue();    
    }
	/**
	 * 返回两个时间段之间的小时时间列表。
	 * 如果起始时间是到小时的时间，不被包含到列表中。
	 * 如果起始时间是到分钟的时间会包含到列表中。
	 * 结束时间会包含在列表中
	 * @param startTime 起始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static List<String> dealListDate(String startTime,String endTime) {
		List<String> listDate = new ArrayList<String>();
		String format ="yyyy-MM-dd HH:mm:ss";
		Date dateBegin=DateUtil.getStringToDate(startTime, format);
		Date dateEnd=DateUtil.getStringToDate(endTime, format);
		if(!startTime.equals(endTime) && dateEnd.after(dateBegin) ){
			Integer addMinute = 60 - Integer.parseInt(startTime.substring(14, 16));
			if(addMinute<60){
				listDate.add(startTime);
			}
			String strTempDate = DateUtil.getDateNumToString(dateBegin,format,addMinute,"M");
			Date tempDateEnd=DateUtil.getStringToDate(strTempDate, format);
			for(;dateEnd.after(tempDateEnd);){
				listDate.add(strTempDate);
				strTempDate = DateUtil.getDateNumToString(tempDateEnd,format,1,"H");
				tempDateEnd=DateUtil.getStringToDate(strTempDate, format);
			}
			listDate.add(endTime);
		}else if(startTime.equals(endTime)){
			listDate.add(startTime);
		}
		return listDate;
	}
}
