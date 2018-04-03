package tech.ideashare.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * 
 */
public class IS_DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of
     * December in the year 2002
     */
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th
     * day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    private static final String SHORT_TIME_FORMAT = "HH:mm";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * YYYYMMDDhhmmss
     */
    public static String No_CROSS_DATETIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * yyyy年MM月dd日
     */
    public static final String CHINESE_EXPANDED_DATE_FORMAT = "yyyy年MM月dd日";

    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;

    /**
     * 无期限时间戳
     */
    public static final Timestamp UNLIMIT_TIMESTAMP = createTimestamp(9999, 12, 31);

    /**
     * <p>
     * Description:得到当前时间 不包括时间,分,秒
     * </p>
     *
     * @return Date
     */
    public static Date getNowDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return convertCalToTs(cal);
    }

    /**
     * 获取当前的日期，不包括时间
     *
     * @return
     */
    public static java.sql.Date getTodayDate() {
        Calendar c = Calendar.getInstance();
        java.sql.Date now = new java.sql.Date(c.getTimeInMillis());
        return now;
    }

    /**
     * 获取当前的日期，不包括时间
     *
     * @return
     */
    public static Date getBeforeLateDate(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * 取得N天前的日期，不包括时间
     *
     * @param days 提前天数
     * @return
     */
    public static Date getDateBeforeDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE) - days, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * 取得N天后的日期，不包括时间
     *
     * @param days 延后天数
     * @return
     */
    public static Date getDateAfterDays(int days) {
        return getDateBeforeDays(-days);
    }

    /**
     * <p>
     * Description:得到当前时间 包括时间,分,秒
     * </p>
     *
     * @return Timestamp
     */
    public static Timestamp getNowTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * <p>
     * Description:把Calendar类型转换为Timestamp
     * </p>
     *
     * @param cald
     * @return Timestamp
     */
    public static Timestamp convertCalToTs(Calendar cald) {
        return new Timestamp(cald.getTime().getTime());
    }

    /**
     * <p>
     * Description:把Timestamp类型转换为Calendar
     * </p>
     *
     * @param ts
     * @return Calendar
     */
    public static Calendar convertTsToCal(Timestamp ts) {
        Calendar cald = Calendar.getInstance();
        cald.setTime(new Date(ts.getTime()));
        return cald;
    }

    /**
     * <p>
     * Description:把Timestamp类型转换为Date
     * </p>
     *
     * @param ts
     * @return Date
     */
    public static Date convertTsToDt(Timestamp ts) {
        return new Date(ts.getTime());
    }

    /**
     * <p>
     * Description:把Date类型转换为Timestamp
     * </p>
     *
     * @param date
     * @return Timestamp
     */
    public static Timestamp convertDtToTs(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * <p>
     * Description:把Timestamp类型转换为String类型,返回数据截至到日期
     * </p>
     *
     * @param ts
     * @return String
     */
    public static String convertTsToStr(Timestamp ts) {
        if (ts != null){
            return ts.toString().substring(0, 10);
        }
        return "";
    }

    /**
     * <p>
     * Description:把Timestamp类型转换为String类型,返回数据截至到秒,格式为2003-02-05 14:23:05
     * </p>
     *
     * @param ts
     * @return String
     */
    public static String convertTsToStrWithSecs(Timestamp ts) {
        if (ts != null){
            return ts.toString().substring(0, 19);
        }
        return "";
    }

    /**
     * <p>
     * Description:把Timestamp类型转换为String类型,返回数据带有星期几显示,格式为2003-11-04 星期二
     * </p>
     *
     * @param ts
     * @return String
     */
    public static String convertTsToStrWithDayOfWeek(Timestamp ts) {
        if (ts != null){
            return ts.toString().substring(0, 10) + " " + getStrDay(ts);
        }
        return "";
    }

    /**
     * <p>
     * Description:根据传入的参数生成一个日期类型
     * </p>
     *
     * @param year :年份如1999 month:月份如3月（为实际需要创建的月份） date:日期如25
     * @param hour :小时如15 minute:分钟如25 second:秒如34
     * @return String
     */
    public static Date createDate(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, 0, 0, 0);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * <p>
     * Description:根据传入的参数生成一个日期类型
     * </p>
     *
     * @param year :年份如1999 month:月份如3月（为实际需要创建的月份） date:日期如25
     * @param hour :小时如15 minute:分钟如25 second:秒如34
     * @return String
     */
    public static Timestamp createTimestamp(int year, int month, int date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, hour, minute, second);
        cal.set(Calendar.MILLISECOND, 0);
        return convertCalToTs(cal);
    }

    /**
     * <p>
     * Description:根据传入的参数生成一个日期类型
     * </p>
     *
     * @param year :年份如1999 month:月份如3月（为实际需要创建的月份） date:日期如25
     * @return String
     */
    public static Timestamp createTimestamp(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return convertCalToTs(cal);
    }

    /**
     * @param str      传入的字符串格式如2003-02-01,2003/02/01
     * @param splitStr 传入字符串当中的分隔符 '-' '/'
     * @return Timestamp 日期类型
     */
    public static Timestamp createTimestamp(String str, String splitStr) {
        if ((str == null) || (str.trim().length() < 1)) {
            return null;
        }
        if ("".equals(splitStr)) {
            splitStr = "-";
        }
        if (str.lastIndexOf(" ") != -1)
            str = str.substring(0, 10);
        try {
            StringTokenizer st = new StringTokenizer(str, splitStr);
            int year = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int date = Integer.parseInt(st.nextToken());
            return createTimestamp(year, month, date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param ts 日期类型
     * @return 传入日期的对应年数
     */
    public static int getYear(Timestamp ts) {
        return convertTsToCal(ts).get(Calendar.YEAR);
    }

    /**
     * @param ts 日期类型
     * @return 传入日期的对应月份
     */
    public static int getMonth(Timestamp ts) {
        return convertTsToCal(ts).get(Calendar.MONTH) + 1;
    }

    /**
     * @param ts 日期类型
     * @return 传入日期的对应日期
     */
    public static int getDate(Timestamp ts) {
        return convertTsToCal(ts).get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param date 日期类型
     * @return 传入日期的对应年数
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * @param date 日期类型
     * @return 传入日期的对应月份
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * @param date 日期类型
     * @return 传入日期的对应日期
     */
    public static int getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param ts 日期类型
     * @return 传入日期类型的对应小时数
     */
    public static int getHour(Timestamp ts) {
        return convertTsToCal(ts).get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @param ts 日期类型
     * @return 传入日期类型的对应分钟数
     */
    public static int getMinute(Timestamp ts) {
        return convertTsToCal(ts).get(Calendar.MINUTE);
    }

    /**
     * @param ts 日期类型
     * @return 传入日期类型的对应秒数
     */
    public static int getSecond(Timestamp ts) {
        return convertTsToCal(ts).get(Calendar.SECOND);
    }

    /**
     * @param ts 日期类型
     * @return 传入日期类型的对应秒中的毫秒数
     */
    public static int getMillisecond(Timestamp ts) {
        return convertTsToCal(ts).get(Calendar.MILLISECOND);
    }

    /**
     * @param ts 日期类型
     * @return 传入日期类型的对应毫秒数
     */
    public static long getMilliseconds(Timestamp ts) {
        return ts.getTime();
    }

    /**
     * @param ts 日期类型
     * @return 传入日期类型的对应星期数，为数字1为星期天2为星期一依次类推
     */
    public static int getDay(Timestamp ts) {
        return convertTsToCal(ts).get(Calendar.DAY_OF_WEEK);
    }

    /**
     * @param ts 日期类型
     * @return 传入日期类型的对应星期几，返回为中文，如星期一星期二等
     */
    public static String getStrDay(Timestamp ts) {
        if (ts == null){
            return null;
        }
        int day = getDay(ts);
        String weekDay = "";
        switch (day) {
            case 1: {
                weekDay = "星期天";
            }
            break;
            case 2: {
                weekDay = "星期一";
            }
            break;
            case 3: {
                weekDay = "星期二";
            }
            break;
            case 4: {
                weekDay = "星期三";
            }
            break;
            case 5: {
                weekDay = "星期四";
            }
            break;
            case 6: {
                weekDay = "星期五";
            }
            break;
            case 7: {
                weekDay = "星期六";
            }
            break;
            default:
                weekDay = "";
                break;
        }
        return weekDay;
    }

    /**
     * @param addpart 为添加的部分可以为yy年数mm月份数dd天数hh小时数mi分钟数ss秒数
     * @param date    需要改动的日期类型
     * @param addnum  添加数目 可以为负数
     * @return 改动后的日期类型
     */
    public static Date dateAdd(String addpart, Date date, int addnum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if ("yy".equals(addpart)) {
            cal.add(Calendar.YEAR, addnum);
        } else if ("mm".equals(addpart)) {
            cal.add(Calendar.MONTH, addnum);
        } else if ("dd".equals(addpart)) {
            cal.add(Calendar.DATE, addnum);
        } else if ("hh".equals(addpart)) {
            cal.add(Calendar.HOUR, addnum);
        } else if ("mi".equals(addpart)) {
            cal.add(Calendar.MINUTE, addnum);
        } else if ("ss".equals(addpart)) {
            cal.add(Calendar.SECOND, addnum);
        } else {
            return null;
        }
        return cal.getTime();
    }

    /**
     * @param addpart 为添加的部分可以为yy年数mm月份数dd天数hh小时数mi分钟数ss秒数
     * @param ts      需要改动的日期类型
     * @param addnum  添加数目 可以为负数
     * @return 改动后的日期类型
     */
    public static Timestamp dateAdd(String addpart, Timestamp ts, int addnum) {
        Calendar cal = Calendar.getInstance();
        cal = convertTsToCal(ts);
        if ("yy".equals(addpart)) {
            cal.add(Calendar.YEAR, addnum);
        } else if ("mm".equals(addpart)) {
            cal.add(Calendar.MONTH, addnum);
        } else if ("dd".equals(addpart)) {
            cal.add(Calendar.DATE, addnum);
        } else if ("hh".equals(addpart)) {
            cal.add(Calendar.HOUR, addnum);
        } else if ("mi".equals(addpart)) {
            cal.add(Calendar.MINUTE, addnum);
        } else if ("ss".equals(addpart)) {
            cal.add(Calendar.SECOND, addnum);
        } else {
            return null;
        }
        return convertCalToTs(cal);
    }

    /**
     * @param diffpart 比较部分YEAR为比较年份 MONTH为比较月份 DATE为比较天数 WEEK比较星期数
     *                 如果传入的diffpart参数不为以上范围默认返回相差天数
     * @param ts1      需要比较的日期
     * @param ts2      需要比较的日期
     * @return 相差的大小
     */
    public static int dateDiff(String diffpart, Timestamp ts1, Timestamp ts2) {
        if ((ts1 == null) || (ts2 == null)) {
            return -1;
        }

        Date date1 = null;
        Date date2 = null;

        date1 = new Date(ts1.getTime());
        date2 = new Date(ts2.getTime());

        Calendar cal1 = null;
        Calendar cal2 = null;

        cal1 = Calendar.getInstance();
        cal2 = Calendar.getInstance();

        // different date might have different offset
        cal1.setTime(date1);
        long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);

        cal2.setTime(date2);
        long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);

        // Use integer calculation, truncate the decimals
        int hr1 = (int) (ldate1 / 3600000); // 60*60*1000
        int hr2 = (int) (ldate2 / 3600000);

        int days1 = (int) hr1 / 24;
        int days2 = (int) hr2 / 24;

        int dateDiff = days2 - days1;
        int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1.get(Calendar.DAY_OF_WEEK)) < 0 ? 1 : 0;
        int weekDiff = dateDiff / 7 + weekOffset;
        int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
        int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

        if ("YEAR".equals(diffpart)) {
            return yearDiff;
        } else if ("MONTH".equals(diffpart)) {
            return monthDiff;
        } else if ("DATE".equals(diffpart)) {
            return dateDiff;
        } else if ("WEEK".equals(diffpart)) {
            return weekDiff;
        } else {
            return dateDiff;
        }
    }

    /**
     * @param ts
     * @return true表示ts为闰年 false表示ts不是闰年
     */
    public static boolean isLeapyear(Timestamp ts) {
        Calendar cal = Calendar.getInstance();
        boolean booleanleapYear = ((GregorianCalendar) cal).isLeapYear(getYear(ts));
        return booleanleapYear;
    }

    /**
     * @param year 年
     * @return true表示year为闰年 false表示ts不是闰年
     */
    public static boolean isLeapyear(int year) {
        Calendar cal = Calendar.getInstance();
        boolean booleanleapYear = ((GregorianCalendar) cal).isLeapYear(year);
        return booleanleapYear;
    }

    /**
     * 比较传入的年月日是否与ts对应为同一天
     *
     * @param year  年份 传入－1表示不需要比较
     * @param month 月份 传入－1表示不需要比较
     * @param date  日期 传入－1表示不需要比较
     * @param ts    完成的Timestamp日期类型
     * @return
     */
    public static boolean isMatchDate(int year, int month, int date, Timestamp ts) {
        int year1 = getYear(ts);
        int month1 = getMonth(ts);
        int date1 = getDay(ts);

        if ((year != -1) && (year != year1)) {
            return false;
        }
        if ((month != -1) && (month != month1)) {
            return false;
        }
        if ((date != -1) && (date != date1)) {
            return false;
        }

        return true;
    }

    /**
     * 根据年和月得到该月的第一天
     *
     * @param year  Integer
     * @param month Integer 为实际的月份
     * @return Timestamp
     */
    public static Timestamp getFirstDayOfMonth(Integer year, Integer month) {
        if (year == null || month == null) {
            return null;
        }
        int y = year.intValue();
        int m = month.intValue();
        return createTimestamp(y, m, 1);
    }

    /**
     * 去除时间后面的小时分秒，只取具体时间日期
     *
     * @param ts
     * @return
     */
    public static Timestamp formatToDate(Timestamp ts) {
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(ts.getTime());
        cd.set(Calendar.HOUR_OF_DAY, 0);
        cd.set(Calendar.MINUTE, 0);
        cd.set(Calendar.SECOND, 0);
        cd.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cd.getTime().getTime());
    }

    /**
     * 去除时间后面的小时分秒，只取具体时间日期
     *
     * @param dt
     * @return
     */
    public static java.util.Date formatToDate(java.util.Date dt) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(dt);
        cd.set(Calendar.HOUR_OF_DAY, 0);
        cd.set(Calendar.MINUTE, 0);
        cd.set(Calendar.SECOND, 0);
        cd.set(Calendar.MILLISECOND, 0);
        return cd.getTime();
    }

    /**
     * 去除时间后面的小时分秒，只取具体时间日期
     *
     * @param dt
     * @return
     */
    public static java.sql.Date formatToDate(java.sql.Date dt) {
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(dt.getTime());
        cd.set(Calendar.HOUR_OF_DAY, 0);
        cd.set(Calendar.MINUTE, 0);
        cd.set(Calendar.SECOND, 0);
        cd.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cd.getTime().getTime());
    }

    /**
     * 取得指定月的上个月。
     *
     * @return
     */
    public static String getLastMonth(String yearM) {
        String yymmdd = yearM + "01";
        Date date = MyDateUtils.valueOf(yymmdd, ISO_DATE_FORMAT);
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, -1);
        // 设置时间为0时
        cal.set(java.util.GregorianCalendar.HOUR_OF_DAY, 0);
        cal.set(java.util.GregorianCalendar.MINUTE, 0);
        cal.set(java.util.GregorianCalendar.SECOND, 0);
        cal.set(java.util.GregorianCalendar.HOUR_OF_DAY, 0);
        cal.set(java.util.GregorianCalendar.MINUTE, 0);
        cal.set(java.util.GregorianCalendar.SECOND, 0);

        String lastMonth = dateToString(cal.getTime(), ISO_DATE_FORMAT);

        return lastMonth.substring(0, lastMonth.length() - 2);
    }

    /**
     * 取得指定月的前第十二个月。
     *
     * @return
     */
    public static String getBeforTwelveMonth(String yearM) {
        String yymmdd = yearM + "01";
        Date date = MyDateUtils.valueOf(yymmdd, ISO_DATE_FORMAT);
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, -12);
        // 设置时间为0时
        cal.set(java.util.GregorianCalendar.HOUR_OF_DAY, 0);
        cal.set(java.util.GregorianCalendar.MINUTE, 0);
        cal.set(java.util.GregorianCalendar.SECOND, 0);
        cal.set(java.util.GregorianCalendar.HOUR_OF_DAY, 0);
        cal.set(java.util.GregorianCalendar.MINUTE, 0);
        cal.set(java.util.GregorianCalendar.SECOND, 0);

        String lastMonth = dateToString(cal.getTime(), ISO_DATE_FORMAT);

        return lastMonth.substring(0, lastMonth.length() - 2);
    }

    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 根据时间变量返回时间字符串
     *
     * @param pattern 时间字符串样式
     * @param date    时间变量
     * @return 返回时间字符串
     */
    public static String dateToString(Date date, String pattern) {

        if (date == null) {

            return null;
        }

        try {

            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);

            return sfDate.format(date);
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     */
    public static Date valueOf(String dateString) {

        return valueOf(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     * @param format     日期格式
     * @return
     */
    public static Date valueOf(String dateString, String format) {

        return valueOf(dateString, format, LENIENT_DATE);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateText 字符串
     * @param format   日期格式
     * @param lenient  日期越界标志
     * @return
     */
    public static Date valueOf(String dateText, String format, boolean lenient) {

        if (dateText == null) {

            return null;
        }

        DateFormat df = null;

        try {

            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }

            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(false);

            return df.parse(dateText);
        } catch (ParseException e) {

            return null;
        }
    }

    /**
     * 根据当前月向前取月份。月份跨度由span指定
     *
     * @return 年月。For example:200907
     */
    public static String getBeforeMonth(Date currentDate, int span) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(currentDate);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, -span);

        // 设置时间为0时
        cal.set(java.util.GregorianCalendar.HOUR_OF_DAY, 0);
        cal.set(java.util.GregorianCalendar.MINUTE, 0);
        cal.set(java.util.GregorianCalendar.SECOND, 0);
        cal.set(java.util.GregorianCalendar.HOUR_OF_DAY, 0);
        cal.set(java.util.GregorianCalendar.MINUTE, 0);
        cal.set(java.util.GregorianCalendar.SECOND, 0);
        String lastMonth = dateToString(cal.getTime(), "yyyyMM");

        return lastMonth;
    }

    /**
     * 根据当前月向前取月份。月份跨度由span指定
     *
     * @return 年月。For example:200907
     */
    public static String getAfterMonth(Date currentDate, int span) {

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(currentDate);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, span);

        // 设置时间为0时
        cal.set(java.util.GregorianCalendar.HOUR_OF_DAY, 0);
        cal.set(java.util.GregorianCalendar.MINUTE, 0);
        cal.set(java.util.GregorianCalendar.SECOND, 0);
        cal.set(java.util.GregorianCalendar.HOUR_OF_DAY, 0);
        cal.set(java.util.GregorianCalendar.MINUTE, 0);
        cal.set(java.util.GregorianCalendar.SECOND, 0);
        String lastMonth = dateToString(cal.getTime(), "yyyyMM");

        return lastMonth;
    }

    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
    }

    /**
     * 取得当前日期是一年中的多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Timestamp date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(new Date(date.getTime()));
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得一个时间的当前周的星期几的日期
     *
     * @param date 传入的时间
     * @param days 星期几
     * @return 返回传入时间的当前周星期几的日期
     */
    public static Timestamp getDayByWeek(Timestamp date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(convertTsToDt(date));
        cal.set(Calendar.DAY_OF_WEEK, days);
        return convertDtToTs(cal.getTime());
    }

    /**
     * 获取传入日期所在月份第一天
     *
     * @param ts 传入日期
     * @return 传入日期所在月份第一天
     */
    public static Date getMonthFirstDate(Timestamp ts) {
        int year = getYear(ts);
        int month = getMonth(ts);
        return createTimestamp(year, month, 1);
    }

    /**
     * 输入日期,得到所在月第一天日期 ,返回日期小时分钟秒都为0.
     *
     * @param time
     * @return
     */
    public static Date getMonthFirstDate(Date time) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(time);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        cd.set(Calendar.HOUR_OF_DAY, 0);
        cd.set(Calendar.MINUTE, 0);
        cd.set(Calendar.SECOND, 0);
        cd.set(Calendar.MILLISECOND, 0);
        return cd.getTime();
    }

    /**
     * 获取系统当前日期所在月份第一天
     *
     * @return 系统当前日期所在月份第一天
     */
    public static Date getCurrMonthFirstDate() {
        Timestamp ts = MyDateUtils.getNowTime();
        return getMonthFirstDate(ts);
    }

    /**
     * 得到某月的最大天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(Integer year, Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
        return dateOfMonth;
    }

    /**
     * 由年月日得到该日期所在月份的第几周
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getWeeksInMonth(Integer year, Integer month, Integer day) {
        Calendar ca = Calendar.getInstance();
        ca.set(year, month - 1, day);
        return ca.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 获取该日期所在月的第几阶段
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getStageInMonth(int year, int month, int day) {
        Calendar currCal = Calendar.getInstance();
        currCal.set(Calendar.YEAR, year);
        currCal.set(Calendar.MONTH, month - 1);
        currCal.set(Calendar.DAY_OF_MONTH, day);
        int week = currCal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int currStage = week > 4 ? 4 : week;
        return currStage;
    }

    /**
     * 获取该日期所在月的第几阶段
     *
     * @param date
     * @return
     */
    public static int getStageInMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int currStage = week > 4 ? 4 : week;
        return currStage;
    }

    /**
     * 当前时间所在月份的第几阶段
     *
     * @return
     */
    public static int getCurrStageInMonth() {
        Calendar currCal = Calendar.getInstance();
        int week = currCal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int currStage = week > 4 ? 4 : week;
        return currStage;
    }

    /**
     * 根据年、周获取周的开始日期
     */
    public static Date getStartDateOfWeek(int year, int week, int firstDayOfWeek) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return org.apache.commons.lang.time.DateUtils.truncate(cal.getTime(), Calendar.DATE);

    }

    /**
     * 根据年、周获取周的结束日期
     */
    public static Date getEndDateOfWeek(int year, int week, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date endDate = org.apache.commons.lang.time.DateUtils.truncate(cal.getTime(), Calendar.DATE);
        endDate = org.apache.commons.lang.time.DateUtils.addDays(endDate, 1);
        return org.apache.commons.lang.time.DateUtils.addMilliseconds(endDate, -1);
    }

    /**
     * 根据日期获取当天结束时间
     *
     * @param date
     * @return
     */
    public static Date getLastTimeOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();

    }

    /**
     * 根据日期获取当天开始时间
     *
     * @param date
     * @return
     */
    public static Date getBeginTimeOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getBeginTimeOfToday() {
        return getBeginTimeOfDay(new Date());
    }

    /*
    public static void main(String[] args) throws Exception {

        MyDateUtils.dateToString(new Date(), MyDateUtils.DATETIME_PATTERN);

        Date d = new Date(1453456620000L);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime()));
        Calendar current = (Calendar) cal.clone();
        System.out.println(current);
        System.out.println(getMonthDays(2012, 7));
        System.out.println(getWeeksInMonth(2012, 8, 24));
        System.out.println((new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31")).getTime());
        System.out.println(getStageInMonth(2012, 11, 1) + " " + getCurrStageInMonth() + " "
                + getStageInMonth(new Date()));

        int week = 1;
        int year = 2013;
        Date startDate = getStartDateOfWeek(year, week, Calendar.MONDAY);
        Date endDate = getEndDateOfWeek(year, week, Calendar.MONDAY);
        System.out.println(startDate + " - " + endDate);
        System.out.println(MyDateUtils.dateAdd("mm", MyDateUtils.createTimestamp(2013, 3, 1), -3).toString());
        System.out.println(dateToString(getMonthFirstDate(new Date()), MyDateUtils.DATETIME_PATTERN));
        System.out.println(dateToString(new Date(), "yyyy.M"));
        System.out.println(dateToString(dateAdd("mm", new Date(), -2), "yyyy-MM-dd HH:mm:ss.sss"));
    }
    */

    /**
     * 计算两个日期之间间隔的天数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Integer getDayInterval(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return null;
        }

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        beginCalendar.set(Calendar.HOUR_OF_DAY, 0);
        beginCalendar.set(Calendar.MINUTE, 0);
        beginCalendar.set(Calendar.SECOND, 0);
        beginCalendar.set(Calendar.MILLISECOND, 0);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((endCalendar.getTime().getTime() - beginCalendar.getTime().getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 计算两个日期之间间隔的小时数, 不足一个小时按一个小时计算
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Integer getHourInterval(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return null;
        }

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        beginCalendar.set(Calendar.MILLISECOND, 0);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.set(Calendar.SECOND, 0);

        int secondsInterval = (int) ((endCalendar.getTime().getTime() - beginCalendar.getTime().getTime()) / 1000);
        return (secondsInterval % 3600 == 0) ? (secondsInterval / 3600) : (secondsInterval / 3600 + 1);
    }

    /**
     * 计算两个日期之间间隔的分钟数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Integer getMinuteInterval(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return null;
        }

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        beginCalendar.set(Calendar.SECOND, 0);
        beginCalendar.set(Calendar.MILLISECOND, 0);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((endCalendar.getTime().getTime() - beginCalendar.getTime().getTime()) / 1000 / 60);
    }

    public static String getDateIntervalName(Date date) {
        if (null == date) {
            return "";
        }

        Date curr = new Date();
        Integer minute = getMinuteInterval(date, curr);
        //防呆
        if (minute < 0) {
            minute = 0;
        }

        int day1 = getDate(date);
        int day2 = getDate(curr);
        if (day2 - day1 == 1) {
            return "昨天 " + dateToString(date, SHORT_TIME_FORMAT);
        }
        if (minute < 5) {
            return "刚刚";
        }
        if (minute >= 5 && minute < 60) {
            return minute + "分钟前";
        }
        if (minute >= 60 && minute < 24 * 60) {
            return minute / 60 + "小时前";
        }
        if (minute >= 24 * 60 && minute < 2 * 24 * 60) {
            return "昨天 10:20";
        }
        return minute / (24 * 60) + "天前";
    }
}
