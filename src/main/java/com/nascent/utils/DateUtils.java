package com.nascent.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * com.nascent.utils
 *
 * @Author guiping.Qiu
 * @Date 2018/3/22
 */
public class DateUtils {
    //日期时间类型格式
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //日期类型格式
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    //时间类型的格式
    private static final String TIME_FORMAT = "HH:mm:ss";
    //年的格式
    private static final String YEAR_FORMAT = "yyyy";
    //月的格式
    private static final String MONTH_FORMAT = "MM";
    //天的格式
    private static final String DAY_FORMAT = "dd";
    //小时的格式
    private static final String HOUR_FORMAT = "HH";
    //分钟的格式
    private static final String MINUTE_FORMAT = "mm";
    //秒的格式
    private static final String SECOND_FORMAT = "ss";
    //星期的格式
    private static final String WEEK_FORMAT = "E";


    //注意SimpleDateFormat不是线程安全的
    private static ThreadLocal<SimpleDateFormat> ThreadDateTime = new ThreadLocal<SimpleDateFormat>();


    /**
     * 当前日期
     *
     * @return 系统当前时间
     */
    public static final Date now() {
        return new Date();
    }

    /**
     * 返回格式化的当前时间
     *
     * @return
     */
    public static String formatNow(){
        return formatDateTime(now());
    }


    /**
     * 系统时间的毫秒数
     *
     * @return 系统时间的毫秒数
     */
    public static long getMilliseconds() {
        return System.currentTimeMillis();
    }

    /**
     * 系统时间的纳秒数
     *
     * @return 系统时间的纳秒数
     */
    public static long getNanoTime() {
        return System.nanoTime();
    }


    /**
     * 指定毫秒数表示的日期
     *
     * @param millis 毫秒数
     * @return 指定毫秒数表示的日期
     */
    public static final Date getDate(long millis) {
        return new Date(millis);
    }


    /**
     * 获取当前年份字符串 格式（yyyy）
     **/
    public static String getYear() {
        return format(new Date(), YEAR_FORMAT);
    }

    /**
     * 获取指定日期年份字符串 格式（yyyy）
     **/
    public static String getYear(Date date) {
        return format(date, YEAR_FORMAT);
    }

    /**
     * 获取当前月份字符串 格式（MM）
     **/
    public static String getMonth() {
        return format(new Date(), MONTH_FORMAT);
    }

    /**
     * 获取指定月份字符串 格式（MM）
     **/
    public static String getMonth(Date date) {
        return format(date, MONTH_FORMAT);
    }


    /**
     * 获取当天字符串 格式（dd）
     **/
    public static String getDay() {
        return format(new Date(), DAY_FORMAT);
    }

    /**
     * 获取指定字符串 格式（dd）
     **/
    public static String getDay(Date date) {
        return format(date, DAY_FORMAT);
    }


    /**
     * 获取当前星期字符串 格式（E）星期几
     **/
    public static String getWeek() {
        return format(new Date(), WEEK_FORMAT);
    }

    /**
     * 获取指定星期字符串 格式（E）星期几
     **/
    public static String getWeek(Date date) {
        return format(date, WEEK_FORMAT);
    }

    /**
     * 取默认最小时间
     *
     * @returnDate
     */
    public static Date getMinDate() {
        return parseDateTime("1970-01-01 00:00:00");
    }

    /**
     * 根据当前日期获取是周几
     *
     * @return 字符串1, 2, 3...., 7
     */
    public static String getWeek4Day() {
        return getWeek4Day(new Date());
    }

    /**
     * 根据指定日期获取是周几
     *
     * @return 字符串1, 2, 3...., 7
     */
    public static String getWeek4Day(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        // 一周中的第几天(国外周日是第一天,所以需要减1)
        int n = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (n < 0) {
            n = 0;
        }
        return weekDays[n];
    }


    /**
     * 获取当前小时字符串 格式（HH）小时
     **/
    public static String getHour() {
        return format(new Date(), HOUR_FORMAT);
    }

    /**
     * 获取当前分钟字符串 格式（mm）分钟
     **/
    public static String getMinute() {
        return format(new Date(), MINUTE_FORMAT);
    }

    /**
     * 获取当前分钟字符串 格式（ss）分钟
     **/
    public static String getSecond() {
        return format(new Date(), SECOND_FORMAT);
    }


    /**
     * 获取当前日期时间
     *
     * @return 返回当前时间的字符串值
     */
    public static String getDateTime() {
        return instance(DATETIME_FORMAT).format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return 返回当前时间的字符串值
     */
    public static String getTime() {
        return instance(TIME_FORMAT).format(new Date());
    }

    /**
     * 获取日期
     *
     * @return 返回当前日期的字符串值
     */
    public static String getDate() {
        return instance(DATE_FORMAT).format(new Date());
    }

    /**
     * 实例化日期格式类
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat instance(String pattern) {
        SimpleDateFormat df = ThreadDateTime.get();
        if (df == null) {
            df = new SimpleDateFormat();
            ThreadDateTime.set(df);
        }
        df.applyPattern(pattern);
        return df;
    }

    /**
     * 将指定的日期时间格式化成字符串返回
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return format(date, DATETIME_FORMAT);
    }

    /**
     * 将指定的日期格式化成字符串返回
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT);
    }

    /**
     * 将指定的时间格式化成字符串返回
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return format(date, TIME_FORMAT);
    }

    /**
     * 日期根据格式转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {

        if (date == null || StringUtils.isEmpty(pattern)) {
            return null;
        }
        return instance(pattern).format(date);
    }

    /**
     * 日期型字符串解析成日期
     *
     * @param str     日期型字符串
     * @param pattern 解析格式
     * @return 转换后的日期
     */
    public static Date parse(String str, String pattern) {
        Date date = null;
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(pattern)) {
            return date;
        }

        try {
            date = instance(pattern).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 时间型字符串转日期
     *
     * @param str 时间型字符串
     * @return 转换后的日期
     */
    public static Date parseTime(String str) {
        return parse(str, TIME_FORMAT);
    }

    /**
     * 日期时间型字符串转日期
     *
     * @param str 日期时间型字符串
     * @return 转换后的日期
     */
    public static Date parseDateTime(String str) {
        return parse(str, DATETIME_FORMAT);
    }

    /**
     * 日期型字符串转日期
     *
     * @param str 日期型字符串
     * @return 转换后的日期
     */
    public static Date parseDate(String str) {
        return parse(str, DATE_FORMAT);
    }


    /**
     * 日期计算
     *
     * @param date   要计算的日期
     * @param field  calendar field.
     * @param amount calendar amount.
     * @return
     */
    public static final Date add(Date date, int field, int amount) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 在当前时间上加或减 -年
     *
     * @param amount
     * @return
     */
    public static Date addYear(int amount) {
        return add(now(), Calendar.YEAR, amount);
    }

    /**
     * 在指定的时间上加或减 -年
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * 在当前时间上加或减 -月
     *
     * @param amount
     * @return
     */
    public static Date addMonth(int amount) {
        return add(now(), Calendar.MONTH, amount);
    }

    /**
     * 在指定的时间上加或减 -月
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }


    /**
     * 在当前时间上加或减 -天
     *
     * @param amount
     * @return
     */
    public static Date addDay(int amount) {
        return add(now(), Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * 在指定的时间上加或减 -天
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DAY_OF_YEAR, amount);
    }


    /**
     * 在当前时间上加或减 -小时
     *
     * @param amount
     * @return
     */
    public static Date addHour(int amount) {
        return add(now(), Calendar.HOUR, amount);
    }

    /**
     * 在指定的时间上加或减 -小时
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHour(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }


    /**
     * 在当前时间上加或减 -分钟
     *
     * @param amount
     * @return
     */
    public static Date addMinute(int amount) {
        return add(now(), Calendar.MINUTE, amount);
    }

    /**
     * 在指定的时间上加或减 -分钟
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinute(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * 在当前时间上加或减 -秒
     *
     * @param amount
     * @return
     */
    public static Date addSecond(int amount) {
        return add(now(), Calendar.SECOND, amount);
    }

    /**
     * 在指定的时间上加或减 -秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSecond(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }


    /**
     * 根据指定格式判断是否日期型字符串
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static boolean isDate(String dateStr, String pattern) {

        boolean isDate = false;
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(pattern)) {
            return isDate;
        }

        try {
            instance(pattern).parse(dateStr);
            isDate = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isDate;
    }

    /**
     * 判断是否日期型字符串
     *
     * @param dateStr
     * @return
     */
    public static boolean isDate(String dateStr) {
        return isDate(dateStr, DATETIME_FORMAT);
    }

    /**
     * 判断是否周末
     *
     * @return
     */
    public static boolean isWeekend() {
        return isWeekend(new Date());
    }

    /**
     * 判断是否周末
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        boolean weekend = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            weekend = true;
        }
        return weekend;
    }


    /**
     * 判断是否在某个日期时间段内
     * @param startTime
     * @param endTime
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean betweenDateTime(String startTime,String endTime,Date date)
            throws ParseException {
        return betweenDateTime(parseDateTime(startTime),parseDateTime(endTime),date);
    }

    /**
     * 判断在某个日期时间段内
     * @param startTime
     * @param endTime
     * @param date
     * @return
     */
    public static boolean betweenDateTime(Date startTime,Date endTime,Date date){
        return date.after(startTime) && date.before(endTime);
    }



    /**
     * 时间date1和date2的时间差-单位毫秒
     *
     * @param date1
     * @param date2
     * @return 秒
     */
    public static long subtract(Date date1, Date date2) {
        long cha = 0;
        if(date2.getTime() > date1.getTime()){
            cha = (date2.getTime() - date1.getTime());
        }else{
            cha = (date1.getTime() - date2.getTime());
        }
        return cha;
    }

    /**
     * 时间date1和date2的时间差-单位毫秒
     *
     * @param date1
     * @param date2
     * @return 秒
     */
    public static long subtract(String date1, String date2) {
        return subtract(parseDateTime(date1), parseDateTime(date2));
    }

    /**
     * 时间date1和date2的时间差-单位秒
     *
     * @param date1
     * @param date2
     * @return 秒
     */
    public static long subtractSecond(Date date1, Date date2) {
        long cha = subtract(date1, date2) / 1000;
        return cha;
    }

    /**
     * 字符串日期时间date1和date2的时间差-单位秒
     *
     * @param date1
     * @param date2
     * @return 秒
     */
    public static long subtractSecond(String date1, String date2) {
        return subtractSecond(parseDateTime(date1), parseDateTime(date2));
    }


    /**
     * 时间date1和date2的时间差-单位分钟
     *
     * @param date1
     * @param date2
     * @return 分钟
     */
    public static int subtractMinute(String date1, String date2) {
        return subtractMinute(parseDateTime(date1), parseDateTime(date2));
    }

    /**
     * 时间date1和date2的时间差-单位分钟
     *
     * @param date1
     * @param date2
     * @return 分钟
     */
    public static int subtractMinute(Date date1, Date date2) {
        long cha = subtract(date1, date2);
        return (int) cha / (1000 * 60);
    }

    /**
     * 时间date1和date2的时间差-单位小时
     *
     * @param date1
     * @param date2
     * @return 小时
     */
    public static int subtractHour(Date date1, Date date2) {
        long cha = subtract(date1, date2);
        return (int) cha / (60 * 60 * 1000);
    }

    /**
     * 时间date1和date2的时间差-单位小时
     *
     * @param date1
     * @param date2
     * @return 小时
     */
    public static int subtractHour(String date1, String date2) {
        return subtractHour(parseDateTime(date1), parseDateTime(date2));
    }


    /**
     * 时间date1和date2的时间差-单位天
     *
     * @param date1
     * @param date2
     * @return 天
     */
    public static int subtractDay(String date1, String date2) {
        return subtractDay(parseDateTime(date1), parseDateTime(date2));
    }

    /**
     * 时间date1和date2的时间差-单位天
     *
     * @param date1
     * @param date2
     * @return 天
     */
    public static int subtractDay(Date date1, Date date2) {
        long cha = subtract(date1, date2);
        return (int) cha / (1000 * 60 * 60 * 24);
    }

    /**
     * 时间date1和date2的时间差-单位月
     *
     * @param date1
     * @param date2
     * @return 月
     */
    public static int subtractMonth(String date1, String date2) {
        return subtractMonth(parseDateTime(date1),parseDateTime(date2));
    }

    /**
     * 时间date1和date2的时间差-单位月
     *
     * @param date1
     * @param date2
     * @return 月
     */
    public static int subtractMonth(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH);
        if (year1 == year2) {
            result = month2 - month1;
        } else {
            result = 12 * (year2 - year1) + month2 - month1;
        }
        return result;
    }

    /**
     * 时间date1和date2的时间差-单位年
     *
     * @param date1
     * @param date2
     * @return 年
     */
    public static int subtractYear(String date1, String date2) {
        return subtractYear(parseDateTime(date1),parseDateTime(date2));
    }

    /**
     * 时间date1和date2的时间差-单位年
     *
     * @param date1
     * @param date2
     * @return 年
     */
    public static int subtractYear(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        result = year2 - year1;
        return result;
    }


    

    /**
     *  根据指定Calendar的field类型将时间段分成日期list
     * @param startDate
     * @param endDate
     * @param number
     * @param field
     * @return
     */
    public static List<Date> dateSplit2DateList(Date startDate, Date endDate, int number,int field) {
        List<Date> list = new ArrayList<>();
        do  {
            list.add(startDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(field,number);
            if(calendar.getTime().after(endDate)){
                list.add(endDate);
            }
            startDate = calendar.getTime();
        }while(startDate.getTime() <= endDate.getTime());
        return list;
    }

    /**
     *  根据指定Calendar的field类型将时间段分成字符串list
     * @param startDate
     * @param endDate
     * @param number
     * @param field
     * @return
     */
    public static List<String> dateSplit2StrList(Date startDate, Date endDate, int number,int field,String pattern) {
        List<String> list = new ArrayList<>();
        do  {
            list.add(format(startDate,pattern));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(field,number);
            if(calendar.getTime().after(endDate)){
                list.add(format(startDate,pattern));
            }
            startDate = calendar.getTime();
        }while(startDate.getTime() <= endDate.getTime());
        return list;
    }


    /**
     *  根据指定Calendar的field类型将时间段分成日期list
     * @param startDate
     * @param endDate
     * @param number
     * @param field
     * @return
     */
    public static List<Date> splitBy2DateList(String startDate, String endDate, int number,int field, String pattern) {
        return  dateSplit2DateList(parse(startDate,pattern),parse(endDate,pattern),field,number);
    }



    /**
     * 根据指定Calendar的field类型将时间段分成字符串list
     * @param startDate
     * @param endDate
     * @param field
     * @param number
     * @return
     */
    public static List<String> splitBy2StrList(String startDate, String endDate,int field, int number,String pattern){
        return splitBy2StrList(startDate,startDate,number,field,pattern);
    }



}
