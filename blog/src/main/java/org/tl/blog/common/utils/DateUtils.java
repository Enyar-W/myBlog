package org.tl.blog.common.utils;

import lombok.extern.log4j.Log4j2;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期操作类
 * 已继承了org.apache.commons.lang3.time.DateUtils，
 * 如需使用一些工具方法时请仔细查看其api，其已有的方法可直接调用避免重复造轮子
 * http://commons.apache.org/proper/commons-lang/javadocs/api-3.1/org/apache/commons/lang3/time/DateUtils.html
 */
@Log4j2
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    /**
     * the milli second of a day
     */
    public static final long DAYMILLI = 24 * 60 * 60 * 1000;
    /**
     * the milli seconds of an hour
     */
    public static final long HOURMILLI = 60 * 60 * 1000;
    /**
     * the milli seconds of a minute
     */
    public static final long MINUTEMILLI = 60 * 1000;
    /**
     * the milli seconds of a second
     */
    public static final long SECONDMILLI = 1000;
    /**
     * added time
     */
    public static final String TIMETO = " 23:59:59";
    /**
     * flag before
     */
    public static final transient int BEFORE = 1;
    /**
     * flag after
     */
    public static final transient int AFTER = 2;
    /**
     * flag equal
     */
    public static final transient int EQUAL = 3;
    /**
     * date format dd/MMM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG = "dd/MMM/yyyy:HH:mm:ss +0900";
    /**
     * date format dd/MM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG2 = "dd/MM/yyyy:HH:mm:ss +0900";
    /**
     * date format YYYY-MM-DD HH24:MI:SS
     */
    public static final String DB_TIME_PATTERN = "YYYY-MM-DD HH24:MI:SS";
    /**
     * date format YYYYMMDDHH24MISS
     */
    public static final String DB_TIME_PATTERN_1 = "YYYYMMDDHH24MISS";
    /**
     * date format dd/MM/yy HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";
    /**
     * date format dd/MM/yy HH24:mm
     */
    public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";
    /**
     * date format yyyy年MM月dd日 HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT_2 = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * date format yyyyMMddHHmmss
     */
    public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";
    /**
     * date format yyyyMMddHHmmssSSS
     */
    public static final String TIME_PATTERN_MILLISECOND = "yyyyMMddHHmmssSSS";
    /**
     * date format yyyyMMdd
     */
    public static final String DATE_FMT_0 = "yyyyMMdd";
    /**
     * date format yyyy/MM/dd
     */
    public static final String DATE_FMT_1 = "yyyy/MM/dd";
    /**
     * date format yyyy/MM/dd hh:mm:ss
     */
    public static final String DATE_FMT_2 = "yyyy/MM/dd hh:mm:ss";
    /**
     * date format yyyy-MM-dd
     */
    public static final String DATE_FMT_3 = "yyyy-MM-dd";
    /**
     * date format yyyy年MM月dd日
     */
    public static final String DATE_FMT_4 = "yyyy年MM月dd日";
    /**
     * date format yyyy-MM-dd HH
     */
    public static final String DATE_FMT_5 = "yyyy-MM-dd HH";
    /**
     * date format yyyy-MM
     */
    public static final String DATE_FMT_6 = "yyyy-MM";
    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_7 = "MM月dd日 HH:mm";
    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_8 = "HH:mm:ss";
    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_9 = "yyyy.MM.dd";
    public static final String DATE_FMT_10 = "HH:mm";
    public static final String DATE_FMT_11 = "yyyy.MM.dd HH:mm:ss";
    /**
     * date format yyyy年MM月dd日
     */
    public static final String DATE_FMT_12 = "MM月dd日";
    public static final String DATE_FMT_13 = "yyyy年MM月dd日HH时mm分";
    public static final String DATE_FMT_14 = "yyyyMM";
    public static final String DATE_FMT_15 = "MM-dd HH:mm:ss";
    public static final String DATE_FMT_16 = "yyyyMMddHHmm";
    public static final String DATE_FMT_17 = "HHmmss";
    public static final String DATE_FMT_18 = "yyyy";
    /**
     * date format yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * localDateTime 转 自定义格式string
     *
     * @param localDateTime
     * @param format        例：yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String formatLocalDateTimeToString(LocalDateTime localDateTime, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(formatter);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * string 转 LocalDateTime
     *
     * @param dateStr 例："2017-08-11 01:00:00"
     * @param format  例："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String dateStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据时间获取当月有多少天数
     *
     * @param date
     * @return
     */
    public static int getActualMaximum(Date date) {

        return dateToLocalDateTime(date).getMonth().length(dateToLocalDate(date).isLeapYear());
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return 1:星期一；2:星期二；3:星期三；4:星期四；5:星期五；6:星期六；7:星期日；
     */
    public static int getWeekOfDate(Date date) {
        return dateToLocalDateTime(date).getDayOfWeek().getValue();
    }


    /**
     * 计算两个日期LocalDate相差的天数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsDateDiffDay(LocalDate before, LocalDate after) {

        return Math.abs(Period.between(before, after).getDays());
    }

    /**
     * 计算两个时间LocalDateTime相差的天数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsTimeDiffDay(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getDays());
    }

    /**
     * 计算两个时间LocalDateTime相差的月数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsTimeDiffMonth(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getMonths());
    }

    /**
     * 计算两个时间LocalDateTime相差的年数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsTimeDiffYear(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getYears());
    }


    /**
     * 根据传入日期返回星期几
     *
     * @param date 日期
     * @return 1-7 1：星期天,2:星期一,3:星期二,4:星期三,5:星期四,6:星期五,7:星期六
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 获取指定日期的当月的月份数
     *
     * @param date
     * @return
     */
    public static int getLastMonth(Date date) {
        return dateToLocalDateTime(date).getMonth().getValue();

    }


    /**
     * 特定日期的当月第一天
     *
     * @param date
     * @return
     */
    public static LocalDate newThisMonth(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    }

    /**
     * 特定日期的当月最后一天
     *
     * @param date
     * @return
     */
    public static LocalDate lastThisMonth(Date date) {
        int lastDay = getActualMaximum(date);
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), lastDay);
    }


    /**
     * 特定日期的当年第一天
     *
     * @param date
     * @return
     */
    public static LocalDate newThisYear(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), 1, 1);

    }


    public static void main(String[] args) {
    }


    public static Timestamp getCurrentDateTime() {
        return new Timestamp(Instant.now().toEpochMilli());

    }

    /**
     * 获取当前时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));

    }


    /**
     * 修改日期时间的时间部分
     *
     * @param date
     * @param customTime 必须为"hh:mm:ss"这种格式
     */
    public static LocalDateTime reserveDateCustomTime(Date date, String customTime) {
        String dateStr = dateToLocalDate(date).toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 修改日期时间的时间部分
     *
     * @param date
     * @param customTime 必须为"hh:mm:ss"这种格式
     */
    public static LocalDateTime reserveDateCustomTime(Timestamp date, String customTime) {
        String dateStr = timestampToLocalDate(date).toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 把日期后的时间归0 变成(yyyy-MM-dd 00:00:00:000)
     *
     * @param date
     * @return LocalDateTime
     */
    public static final LocalDateTime zerolizedTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);

    }

    /**
     * 把时间变成(yyyy-MM-dd 00:00:00:000)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime zerolizedTime(Timestamp date) {
        LocalDateTime localDateTime = timestampToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);
    }

    /**
     * 把日期的时间变成(yyyy-MM-dd 23:59:59:999)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime getEndTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999 * 1000000);
    }

    /**
     * 把时间变成(yyyy-MM-dd 23:59:59:999)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime getEndTime(Timestamp date) {
        LocalDateTime localDateTime = timestampToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999 * 1000000);
    }

    /**
     * 计算特定时间到 当天 23.59.59.999 的秒数
     *
     * @return
     */
    public static int calculateToEndTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime end = getEndTime(date);
        return (int) (end.toEpochSecond(ZoneOffset.UTC) - localDateTime.toEpochSecond(ZoneOffset.UTC));
    }


    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param localDateTime 例：ChronoUnit.DAYS
     * @param chronoUnit
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(LocalDateTime localDateTime, ChronoUnit chronoUnit, int num) {
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param chronoUnit 例：ChronoUnit.DAYS
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(Date date, ChronoUnit chronoUnit, int num) {
        long nanoOfSecond = (date.getTime() % 1000) * 1000000;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param chronoUnit 例：ChronoUnit.DAYS
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(Timestamp date, ChronoUnit chronoUnit, int num) {
        long nanoOfSecond = (date.getTime() % 1000) * 1000000;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        long nanoOfSecond = (date.getTime() % 1000) * 1000000;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));

        return localDateTime;
    }

    /**
     * Timestamp 转 LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime timestampToLocalDateTime(Timestamp date) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, date.getNanos(), ZoneOffset.of("+8"));

        return localDateTime;
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {

        return dateToLocalDateTime(date).toLocalDate();
    }

    /**
     * timestamp 转 LocalDateTime
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate timestampToLocalDate(Timestamp date) {

        return timestampToLocalDateTime(date).toLocalDate();
    }

    /**
     * 比较两个LocalDateTime是否同一天
     *
     * @param begin
     * @param end
     * @return
     */
    public static boolean isTheSameDay(LocalDateTime begin, LocalDateTime end) {
        return begin.toLocalDate().equals(end.toLocalDate());
    }


    /**
     * 比较两个时间LocalDateTime大小
     *
     * @param time1
     * @param time2
     * @return 1:第一个比第二个大；0：第一个与第二个相同；-1：第一个比第二个小
     */
    public static int compareTwoTime(LocalDateTime time1, LocalDateTime time2) {

        if (time1.isAfter(time2)) {
            return 1;
        } else if (time1.isBefore(time2)) {
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 比较time1,time2两个时间相差的秒数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffSecond(Timestamp time1, Timestamp time2) {
        long diff = timestampToLocalDateTime(time1).toEpochSecond(ZoneOffset.UTC) - timestampToLocalDateTime(time2).toEpochSecond(ZoneOffset.UTC);
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * 比较time1,time2两个时间相差的分钟数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffMin(Timestamp time1, Timestamp time2) {
        long diff = getTwoTimeDiffSecond(time1, time2) / 60;
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * 比较time1,time2两个时间相差的小时数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffHour(Timestamp time1, Timestamp time2) {
        long diff = getTwoTimeDiffSecond(time1, time2) / 3600;
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * 判断当前时间是否在时间范围内
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isTimeInRange(Date startTime, Date endTime) throws Exception {
        LocalDateTime now = getCurrentLocalDateTime();
        LocalDateTime start = dateToLocalDateTime(startTime);
        LocalDateTime end = dateToLocalDateTime(endTime);
        return (start.isBefore(now) && end.isAfter(now)) || start.isEqual(now) || end.isEqual(now);
    }

    /**
     * 获取指定时间差的日期
     * @param date 基准日期
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second){
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 获取指定时间差的日期
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute){
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 获取指定时间差的日期
     * @param  date 基准日期
     * @param  hour 相差小时
     * @return Date, 返回的日期
     */
    public static Date addHour(Date date, int hour) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 获取指定时间差的日期
     * @param  date 基准日期
     * @param  day 相差天数
     * @return Date, 返回的日期
     */
    public static Date addDay(Date date, int day) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 获取指定时间差的日期
     * @param  date 基准日期
     * @param  month 相差月数
     * @return Date, 返回的日期
     */
    public static Date addMonth(Date date, int month) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 获取指定时间差的日期
     * @param  date 基准日期
     * @param  year 相差年数
     * @return Date, 返回的日期
     */
    public static Date addYear(Date date, int year) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 当日凌晨时间，方便基于日期的比较
     * @return
     */
    public static Date getToday() {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 日期格式化
     * @param  v
     * @param  fm 格式化标示符 例如:yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     * @param  def
     * @return Date, 返回的日期
     */
    public static Date toDate(String v, String fm, Date def) {
        if (v == null || v.length() == 0)
            return def;
        try {
            return new SimpleDateFormat(fm).parse(v.trim());
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 生成格式化日期 yyyy-MM-dd
     * @param  v
     * @param  def
     * @return Date, 返回的日期
     */
    public static Date dateValue(String v, Date def) {
        return toDate(v, "yyyy-MM-dd", def);
    }

    /**
     * 生成格式化日期 yyyy-MM-dd HH:mm:ss
     * @param  v
     * @param  def
     * @return Date, 返回的日期
     */
    public static Date datetimeValue(String v, Date def) {
        return toDate(v, "yyyy-MM-dd HH:mm:ss", def);
    }

    /**
     * 输出格式化日期 yyyy-MM-dd
     * @param  fm 格式化标示符
     * @param  def 要格式化输出的日期
     * @return String, 返回格式化的日期字符串
     */
    public static String dateString(String fm,Date def){
        return new SimpleDateFormat(fm).format(def);
    }

    public static String dateString(String fm, String def){
        SimpleDateFormat dateFormater = new SimpleDateFormat(fm);
        try {
            Date date = dateFormater.parse(def);
            return dateString(fm, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回批定格式的时间字符串
     * @param date Date
     * @param pattern String
     * @return String
     */
    public static String formatDate(java.util.Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }



    /**
     * 获取某天的开始时刻00:00:00.000
     * @param date 需要获取天内的时间
     * @return Date java.util.Date
     */
    public static Date getStartOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 获取某天的最后时刻23:59:59.999
     * @param date 需要获取天内的时间
     * @return Date java.util.Date
     */
    public static Date getEndOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MILLISECOND, 999);
        return cld.getTime();
    }

    /**
     * 获取指定时间所在周的第一天的00:00:00.000
     * @param date 需要获取周的某天
     * @return Date java.util.Date
     */
    public static Date getStartOfWeek(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取指定时间所在周的最后一天的23:59:59.999
     * @param date 需要获取周的某天
     * @return Date java.util.Date
     */
    public static Date getEndOfWeek(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return getEndOfDay(cld.getTime());
    }

    /**
     * 获取指定时间所在月的第一天的00:00:00.000
     *
     * @param date 需要获取月的某天
     * @return Date java.util.Date
     */
    public static Date getStartOfMonth(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取指定时间所在月的最后一天的23:59:59.999
     * @param date 需要获取月的某天
     * @return Date java.util.Date
     */
    public static Date getEndOfMonth(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        int maxDay = cld.getActualMaximum(Calendar.DAY_OF_MONTH);
        cld.set(Calendar.DAY_OF_MONTH, maxDay);
        return getEndOfDay(cld.getTime());
    }

    /**
     * 获取指定时间所在年的第一天的00:00:00.000
     * @param date 需要获取年的某天
     * @return Date java.util.Date
     */
    public static Date getStartOfYear(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.DAY_OF_YEAR, 1);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取指定时间所在年的最后一天的23:59:59.999
     * @param date 需要获取年的某天
     * @return Date java.util.Date
     */
    public static Date getEndOfYear(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.MONTH, Calendar.DECEMBER);
        cld.set(Calendar.DAY_OF_MONTH, 31);
        return getEndOfDay(cld.getTime());
    }

    /**
     * 获取当前时间的上n个周的第1天的00：00：00.000，方便基于日期的比较
     * @param interval 间隔多少月，0表示当周，1表示上个周……
     * @return 指定周的第一天的00：00：00.000的java.util.Date
     */
    public static Date getPreWeek(int intervalWeek) {
        long dayMilliSecond = 24 * 60 * 60 * 1000;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        for (int i = 0; i < intervalWeek; i++) {
            long s1 = cal.getTimeInMillis();
            cal.setTimeInMillis(s1 - dayMilliSecond);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        return cal.getTime();
    }

    /**
     * 获取当前时间的上n个月的第1天的00：00：00.000，方便基于日期的比较
     * @param intervalMonth 间隔多少月，0表示当月，1表示上个月……
     * @return 指定月份的第一天的00：00：00.000的java.util.Date
     */
    public static Date getPreMonth(int intervalMonth) {
        long dayMilliSecond = 24 * 60 * 60 * 1000;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < intervalMonth; i++) {
            long s1 = cal.getTimeInMillis();
            cal.setTimeInMillis(s1 - dayMilliSecond);
            cal.set(Calendar.DAY_OF_MONTH, 1);
        }
        return cal.getTime();
    }

    /**
     * 将"yyyy-MM-dd"格式的日期字符串转换为date对象
     * @param str
     * @return
     * @update date 2015年7月21日
     */
    public static Date parseDate(String str) {
        try {
            return parseDate(str, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将"yyyy-MM-dd HH:mm:ss"格式的日期字符串转换为date对象
     * @param str
     * @return
     * @update date 2015年7月21日
     */
    public static Date parseDateDetail(String str) {
        try {
            return parseDate(str, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
