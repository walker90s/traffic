package com.tt.traffic.common.util;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 崔振
 * @todo 时间工具�?
 */
public class TimeUtil {

    public static Date getCurrentTime() {
        Date date = null;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(sdf.format(new Date()));
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * @return String para的标准时间格式的�?,例如：返�?'2012-08-09 16:00:00'
     */
    public static String getLocalString() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(currentDate);
        return date;
    }

    public static String getLocalString(Date currentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(currentDate);

        return date;
    }

    //	的到默认的时间格式为("yyyy-MM-dd HH:mm:ss")的当前时�?
    public static String getCurrentDateTime() {
        return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
    }

    /*
    根据输入的格�?(String _dtFormat)得到当前时间格式
    */
    public static String getCurrentDateTime(String _dtFormat) {
        String currentdatetime = "";
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat dtFormat = new SimpleDateFormat(_dtFormat);
            currentdatetime = dtFormat.format(date);
        } catch (Exception e) {
            System.out.println("时间格式不正�?");
            e.printStackTrace();
        }
        return currentdatetime;
    }

    public static Timestamp getTimestamp(String str) {
        Timestamp ret = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = dateFormat.parse(str);
            long datelong = date.getTime();
            ret = new Timestamp(datelong);

        } catch (Exception e) {
        }
        return ret;
    }

    public static Timestamp getTimestamp(String str, String _dtFormat) {
        Timestamp ret = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(_dtFormat);

            Date date = dateFormat.parse(str);
            long datelong = date.getTime();
            ret = new Timestamp(datelong);

        } catch (Exception e) {
        }
        return ret;
    }

    public static Timestamp getTimestamp() {
        return getTimestamp(0);
    }

    public static String getTimestampString(Timestamp sta) {
        String ret = "";
        try {
            String str = sta.toString();
            ret = str.substring(0, str.lastIndexOf('.'));
        } catch (Exception e) {
            ret = "";
        }
        return ret;
    }

    /**
     * length 推荐直接�? StaticVariable中的 YearToMonth �? YearToDay 做参�?.
     *
     * @param sta
     * @param length
     * @return
     */
    public static String getTimestampFormat(Timestamp sta, int length) {
        String ret = "";
        try {
            String str = sta.toString();
            ret = str.substring(0, length);
        } catch (Exception e) {
            ret = "";
        }
        return ret;
    }

    /**
     * Date 转换 String，根�? format格式，如: HH:mm:ss
     * @param date
     * @param dtFormat
     * @return
     */
    public static String getDateFormat(Date date, String dtFormat) {
        String ret = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            ret = dateFormat.format(date);
        } catch (Exception e) {
            ret = "";
        }
        return ret;
    }

    public static Timestamp getTimestamp(int disday) {
        Calendar c;
        c = Calendar.getInstance();
        long realtime = c.getTimeInMillis();
        realtime += 86400000L * disday;
        return new Timestamp(realtime);
    }

    /**
     * @param disday int 和当前距离的天数
     * @return String para的标准时间格式的�?,例如：返�?'2012-8-10 00:00:00'
     */

    @SuppressWarnings("unused")
    public static String getTimeString(int disday) {
        String ls_display = "";
        Calendar c;
        c = Calendar.getInstance();
        long realtime = c.getTimeInMillis();
        realtime += 86400000L * disday;
        c.setTimeInMillis(realtime);
        String _yystr = "", _mmstr = "", _ddstr = "";
        int _yy = c.get(Calendar.YEAR);
        _yystr = _yy + "";
        int _mm = c.get(Calendar.MONTH) + 1;
        _mmstr = _mm + "";
        if (_mm < 10) {
            _mmstr = "0" + _mm;
        }
        int _dd = c.get(Calendar.DATE);
        _ddstr = _dd + "";
        if (_dd < 10) {
            _ddstr = "0" + _dd;
        }
        ls_display = "'" + _yy + "-" + _mm + "-" + _dd + " 00:00:00'";
        return ls_display;
    }

    public static Date getCurrentDateTime(String strDate, String _dtFormat) {
        Date tDate = null;

        SimpleDateFormat smpDateFormat = new SimpleDateFormat(_dtFormat);
        ParsePosition pos = new ParsePosition(0);
        tDate = smpDateFormat.parse(strDate, pos); //标准格式的date类型时间

        return tDate;
    }



    /**
     * 返回时间�? 形式为：XX天XX小时XX分XX�?
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getDiffTime(String startTime, String endTime) {
        try {
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date begin = dfs.parse(startTime);
            Date end = dfs.parse(endTime);
            long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成�?
            long day1 = between / (24 * 3600);
            long hour1 = between % (24 * 3600) / 3600;
            long minute1 = between % 3600 / 60;
            long second1 = between % 60 / 60;
            return "" + day1 + "�?" + hour1 + "小时" + minute1 + "�?" + second1 + "�?";
        } catch (ParseException e) {
        }
        return "";
    }

    /**
     * 计算date1 - date2 的时间差
     *
     * @param date1
     * @param date2
     * @return 根据情况返回不同�?  day+"�?"+hour+"小时"+min+"�?"+s+"�?"
     */
    public static String dateDiff(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            long onTime = date1.getTime();
            long ponTime = date2.getTime();
            long dateDiff = onTime - ponTime;
            if (dateDiff > 0) {
                long day = dateDiff / (24 * 60 * 60 * 1000);
                long hour = (dateDiff / (60 * 60 * 1000) - day * 24);
                long min = ((dateDiff / (60 * 1000)) - day * 24 * 60 - hour * 60);
                long s = (dateDiff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                String datediff = "";
                if (day == 0 && hour > 0) {
                    datediff = hour + "小时" + min + "�?" + s + "�?";
                } else if (day == 0 && hour == 0 && min > 0) {
                    datediff = min + "�?" + s + "�?";
                } else if (day > 0) {
                    datediff = day + "�?" + hour + "小时" + min + "�?" + s + "�?";
                }
                return datediff;
            }
        }
        return "";
    }

    /**
     * 获取指的两个日期内的日期范围�?12/1,12/2,12/3...�?
     *
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getDateRange(Date begin, Date end, SimpleDateFormat dateFormat) {
        List<String> dateRange = new ArrayList<String>();
        //默认为月/日格�?
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("M/d");
        }
        //加入第一个日�?
        dateRange.add(dateFormat.format(begin));
        //加入其余日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        while (!cal.getTime().equals(end)) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dateRange.add(dateFormat.format(cal.getTime()));
        }
        return dateRange;
    }

    /**
     * 获取系统时间当月�?后一�?
     * @return
     */
    public static int getLastDayOfSystemMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取以当前日期为准的前后几天�?-1 昨天 -2 前天 1明天 2后天，以此类�?
     * @param index
     * @return
     */
    public static Date getNDate(int index){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, index);
        Date eday = null;
        try {
            eday = sdf.parse(sdf.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return eday;
    }

    /**
     * 获取date的前1天�??
     *
     * @param date
     * @return
     */
    public static Date getPreviousDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 获取date�?在月第一�?
     *
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * java的Date类型转换�? XMLGregorianCalendar类型
     * @return
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarByDate(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
        }
        return gc;
    }
}