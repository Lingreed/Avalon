package com.bryan.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2017/10/23
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyyMMdd", "yyyyMMdd HHmmss"};

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return currDate;
    }

    /**
     * 获取指定前后几天时间
     *
     * @param day
     * @return
     */
    public static Date getNow(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, day); // 设置为前一天
        Date currDate = cal.getTime();
        return currDate;
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        if (date == null)
            return "";
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当天小时字符串 格式（hh）
     */
    public static String getHour() {
        return formatDate(new Date(), "HH");
    }

    /**
     * 得到当天分钟字符串 格式（mm）
     */
    public static String getMinute() {
        return formatDate(new Date(), "mm");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 前/后?分钟
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date rollMinute(Date d, int minute) {
        return new Date(d.getTime() + minute * 60 * 1000);
    }

    /**
     * 前/后?天
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 前/后?月
     *
     * @param d
     * @param mon
     * @return
     */
    public static Date rollMon(Date d, int mon) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, mon);
        return cal.getTime();
    }

    /**
     * 前/后?年
     *
     * @param d
     * @param year
     * @return
     */
    public static Date rollYear(Date d, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 日期转换为字符串 MM月dd日 hh:mm
     *
     * @param date
     * @return
     */
    public static String dateStr(Date date) {
        return formatDate(date, "MM月dd日 hh:mm");
    }

    /**
     * 日期转换为字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateStr2(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * yyyy年MM月dd日HH时mm分ss秒
     *
     * @param date
     * @return
     */
    public static String dateStr5(Date date) {
        return formatDate(date, "yyyy年MM月dd日 HH时mm分ss秒");
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String dateStr3(Date date) {
        return formatDate(date, "yyyyMMddHHmmss");
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateStr4(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");

    }

    /**
     * yyyy年MM月dd日
     *
     * @param date
     * @return
     */
    public static String dateStr6(Date date) {
        return formatDate(date, "yyyy年MM月dd日");
    }

    /**
     * yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String dateStr7(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * MM-dd
     *
     * @param date
     * @return
     */
    public static String dateStr8(Date date) {
        return formatDate(date, "MM-dd");
    }

    /**
     * yyyyMM
     *
     * @param date
     * @return
     */
    public static String dateStr9(Date date) {
        return formatDate(date, "yyyyMM");
    }

    /**
     * String转date
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Date dateStr10(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本月第一天
     *
     * @param date
     * @return
     */
    public static Date getBeginForMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        date = c.getTime();
        return date;
    }

    /**
     * 获取本月最后一天
     *
     * @param date
     * @return
     */
    public static Date getEndForMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        date = c.getTime();
        return date;
    }

    /**
     * 获取开始时间
     *
     * @param dateStr
     * @return
     */
    public static String getStartDate(String dateStr) {
        Date date = DateUtils.parseDate(dateStr);
        if (date == null) {
            return "";
        }
        return DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getEndDate(String dateStr) {
        dateStr = dateStr + " 235959";
        Date date = DateUtils.parseDate(dateStr);
        if (date == null) {
            return "";
        }
        return DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 通过周期获取日期
     *
     * @param beginDate
     * @param num
     * @param type      1-天，2-周，3-月，4-季，5-年
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getDay(Date beginDate, int num, int type) {
        Date temp_date = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        if (type == 1) {
            c.setTime(beginDate);
            c.add(c.DATE, num);
            temp_date = c.getTime();
        }
        if (type == 2) {
            c.setTime(beginDate);
            c.add(c.WEEK_OF_MONTH, num);
            temp_date = c.getTime();
        }
        if (type == 3) {
            c.setTime(beginDate);
            c.add(c.MONTH, num);
            temp_date = c.getTime();
        }
        // 按照一季度3个月来算
        if (type == 4) {
            c.setTime(beginDate);
            c.add(c.MONTH, num * 3);
            temp_date = c.getTime();
        }
        if (type == 5) {
            c.setTime(beginDate);
            c.add(c.YEAR, num);
            temp_date = c.getTime();
        }

        return format.format(temp_date);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date dayBegin(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date dayEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定年月的最大天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getLastDay(int year, int month) {
        int day = 1;
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        int last = cal.getActualMaximum(Calendar.DATE);
        return last;
    }

    /**
     * 获得该时间的年，月，日
     *
     * @param date
     * @param type 0:年，1：月，其他：日
     * @return 年份，月份，当月的天数
     */
    public static Integer getByDate(Date date, int type) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (type == 0) {
            return c.get(Calendar.YEAR);
        } else if (type == 1) {
            return c.get(Calendar.MONTH) + 1;
        }
        return c.get(Calendar.DAY_OF_MONTH);
    }
}
