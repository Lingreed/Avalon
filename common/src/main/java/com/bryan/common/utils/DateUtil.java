package com.bryan.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;
import org.ocpsoft.prettytime.units.Second;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.util.Calendar.DATE;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {

    public static final String LONG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String CHINA_MINITE_DATE_PATTERN = "yyyy年MM月dd日  HH:mm";

    public static final String MAIL_CHINA_MINITE_DATE_PATTERN = "yyyy年MM月dd日 HH:mm (EEE)";

    public static final String SHORT_DATE_PATTERN = "yyyy-MM-dd";

    public static final String CHINA_MONTH_DAY_DATE_PATTERN = "MM月dd日";

    public static final String CHINA_MONTH_DATE_PATTERN = "MM月dd日  HH:mm";

    public static final String HOUR_MINITE_PATTERN = "HH:mm";

    public static final String SECOND_PATTERN = "HH:mm:ss";

    public static final String MINITE_DATE_PATTERN = "yyyy-MM-dd HH:mm";

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM", "yyyyMMdd", "yyyyMMdd HHmmss" };

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static Date getNow() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
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
	 * @Title: parseDate  
	 * @Description: 格式化当前日期
	 * @param d1 日期
	 * @return yyyy-MM-dd HH:mm:ss 格式日期
	 */
	public static Date parseDate(Date d1) {
		String str = DateFormatUtils.format(d1, "yyyy-MM-dd");
		return parseDate(str);
	}
	
	/**
	 * @Title: getToday  
	 * @Description: 返回当前yyyy-MM-dd格式日期
	 * @return
	 */
	public static Date getToday(){
		return parseDate(new Date());
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		if (date == null)
			return "";
		String formatDate;
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
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear(Date date) {
		return formatDate(date ,"yyyy");
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
	 * 前/后?周
	 * 
	 * @param d
	 * @param week
	 * @return
	 */
	public static Date rollWeek(Date d, int week) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.WEEK_OF_YEAR, week);
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
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
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
	 * yyyyMM
	 * 
	 * @param date
	 * @return
	 */
	public static String dateStr11(Date date) {
		return formatDate(date, "yyyy-MM");
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
		Date date = parseDate(dateStr);
		if (date == null) {
			return "";
		}
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getEndDate(String dateStr) {
		dateStr = dateStr + " 235959";
		Date date = parseDate(dateStr);
		if (date == null) {
			return "";
		}
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 通过周期获取日期
	 * 
	 * @param beginDate
	 * @param num
	 * @param type
	 *            1-天，2-周，3-月，4-季，5-年
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getDay(Date beginDate, int num, int type) {
		Date temp_date = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		if (type == 1) {
			c.setTime(beginDate);
			c.add(DATE, num);
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

	// 指定日期和当前日期相差天数
	public static int daysBetween(String smdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(sdf.format(getNow())));
		long time2 = cal.getTimeInMillis();
		long between_days = (time1 - time2) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
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
	 * @Title: getLastDay  
	 * @Description: 获取指定年月的最大天数
	 * @param year
	 * @param month
	 * @return int 返回类型  
	 */
	public static int getLastDay(int year, int month) {
		int day = 1;
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getActualMaximum(DATE);
	}

	// 今日剩余秒数
	public static long differSecond(Date smdate) {
		Date endTime = dayEnd(smdate);
		return (endTime.getTime() - smdate.getTime()) / 1000;
	}

	// 5分钟时间戳
	public static long timeStamp() {
		int min = 0;
		if (Integer.parseInt(getMinute()) % 5 == 0) {
			min = Integer.parseInt(getMinute());
		} else {
			for (int i = 1; i < 5; i++) {
				if (Integer.parseInt(getMinute()) + i % 5 == 0) {
					min = Integer.parseInt(getMinute()) + i;
					break;
				}
			}
		}
		String time = getYear() + getMonth() + getDay() + getHour() + min;
		return Long.parseLong(time);
	}

	public static String formatDate(Date date) {
		String dateStr = "";
		if (date != null) {
			PrettyTime prettyTime = new PrettyTime(Locale.CHINA);
			prettyTime.removeUnit(JustNow.class);
			prettyTime.removeUnit(Second.class);
			prettyTime.removeUnit(Millisecond.class);
			dateStr = prettyTime.format(date);
		}
		return dateStr.replace(" ", "");
	}

    public static Date getFormatedDate(DateFormat df, String strDate) {
        try {
            return df.parse(strDate);
        } catch (Exception ex) {
            throw new RuntimeException("日期格式不对，无法解析。", ex);
        }
    }

    private static DateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static Date getFormatedDate(String strDate, String formate) {
        return getFormatedDate(getDateFormat(formate), strDate);
    }

	/**
	 * @Title: greaterThanNOw  
	 * @Description: 大于当前时间
	 * @param v1
	 * @return boolean
	 */
	public static boolean greaterThanNOw(Date v1){
		return v1.compareTo(new Date()) >= 0;
	}
	
	/**
	 * @Title: lessThanNow  
	 * @Description:  小于当前时间
	 * @param v1
	 * @return boolean
	 */
	public static boolean lessThanNow(Date v1){
		return v1.compareTo(new Date())<= 0;
	}
	
	/**
	 * @Title: greaterAndEqual  
	 * @Description: v1>= v2
	 * @param v1
	 * @param v2
	 * @return boolean
	 */
	public static boolean greaterAndEqual(Date v1, Date v2){
		return v1.compareTo(v2) >= 0;
	}
	
	/**
	 * @Title: greaterAndEqual  
	 * @Description: v1> v2
	 * @param v1
	 * @param v2
	 * @return boolean
	 */
	public static boolean greaterThan(Date v1, Date v2){
		return v1.compareTo(v2) > 0;
	}
	
	/**
	 * @Title: greaterThan  
	 * @Description: v1 \<= v2
	 * @param v1
	 * @param v2
	 * @return boolean
	 */
	public static boolean lessAndEqual(Date v1, Date v2){
		return v1.compareTo(v2) <= 0;
	}



	/**
	 * 取得当前日期是多少周
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime (date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 取得当前日期是多少月
	 *
	 * @param date
	 * @return
	 */
	public static int getMonthOfYear(Date date) {
		String month = formatDate(date,"MM");
		return Integer.parseInt(month);
	}


	/**
	 * 得到某一年周的总数
	 *
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}
	
	/**
	 * 得到某年某周的第一天
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static String getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set (Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		String date = formatDate(getFirstDayOfWeek(cal.getTime ()), "yyyy-MM-dd");

		return date;
	}
	
	/**
	 * 得到某年某周的最后一天
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static String getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE , week * 7);
		String date = formatDate(getLastDayOfWeek(cal.getTime()), "yyyy-MM-dd");
		return date;
	}
	/**
	 * 取得指定日期所在周的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime ();
	}
	/**
	 * 取得指定日期所在周的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的第一天
	 *
	 * @return
	 */
	public static Date getFirstDayOfWeek() {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime ();
	}
	/**
	 * 取得当前日期所在周的最后一天
	 *
	 * @return
	 */
	public static Date getLastDayOfWeek() {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 将周转化为日期 (得到某年某周的第一天)到最后周的一天
	 *
	 * @param year
	 * @param weeks
	 *
	 * @return
	 */
	public static String getWeekDate(Integer year , Integer weeks) {

		return DateUtil.getFirstDayOfWeek(year,weeks)+"~"+DateUtil.getLastDayOfWeek(year,weeks);
	}

	public static void main(String[] args) {

		System.out.println(getWeekDate(2016,52)+"|"+getWeekDate(2017,1));
        
		System.out.println(getWeekOfYear(getNow()));
		System.out.println(dateStr2(rollWeek(parseDate("2017-11-12"), 0)));
	}
}
