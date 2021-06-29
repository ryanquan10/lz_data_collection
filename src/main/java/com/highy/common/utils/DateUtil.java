package com.highy.common.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.TimeZone;

import org.springframework.context.i18n.LocaleContextHolder;

public class DateUtil {

    public static final String CM_LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String CM_SHORT_DATE_FORMAT = "yyyy-MM-dd";
    
    private static final String YEAR_MONTH_DAY = "yyyyMMdd";
    
    private static final String ITSM_FORMAT = "yyyyMMdd24HHmm";

    public static final String CM_CH_SHORT_DATE_FORMAT = "yyyy年MM月dd日";

    public static final String CM_SHORT_MONTH_FORMAT = "yyyy-MM";

    public static final String CM_SHORT_YEAR_FORMAT = "yyyy";
    
    public static final String CM_SHORT_NOW_MONTH_FORMAT = "MM";

    public static final String YEAR_MONTH = "yyyyMM";
    
    public static final String MONTH_DAY="MMdd";

    public final static String[] MONTH = { "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December" };

    public final static String[] DAY = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday" };

    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);

    /**
     * 取得今天的日期
     * 
     * @return
     */
    public static String getToday() {
        Date myDate = new Date();
        String today = DateUtil.DateToString(myDate, CM_SHORT_DATE_FORMAT);
        return today;
    }
    public static String getTodayString() {
        Date myDate = new Date();
        String today = DateUtil.DateToString(myDate, YEAR_MONTH_DAY);
        return today;
    }
    
    public static String getITSMFormatToday() {
        Date myDate = new Date();
        String today = DateUtil.DateToString(myDate, ITSM_FORMAT);
        return today;
    }
    

    /**
     * 取得yyyy年MM月dd日
     * 
     * @return
     */
    public static String getChineseDate() {
        Date myDate = new Date();
        String nowYear = DateUtil.DateToString(myDate, CM_CH_SHORT_DATE_FORMAT);
        return nowYear;
    }
    
    /**
     * 取得今年年份
     * 
     * @return
     */
    public static String getNowYear() {
        Date myDate = new Date();
        String nowYear = DateUtil.DateToString(myDate, CM_SHORT_YEAR_FORMAT);
        return nowYear;
    }

    /**
     * 获得当前时间
     * 
     * @return String
     */
    public static java.sql.Timestamp getNowTime() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
     * 取得当月的月份
     * 
     * @return
     */
    public static String getMonth() {
        Date myDate = new Date();
        String month = DateUtil.DateToString(myDate, CM_SHORT_MONTH_FORMAT);
        return month;
    }
    
    /**
     * 取得当月的月份
     * 
     * @return
     */
    public static String getNowMonth() {
        Date myDate = new Date();
        String month = DateUtil.DateToString(myDate, CM_SHORT_NOW_MONTH_FORMAT);
        return month;
    }
    
    /**
     * 取得月份
     * 
     * @return
     */
    public static String getMonth(String sourceDate,String sourceDateFormat) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(CM_SHORT_NOW_MONTH_FORMAT);
        return  format.format(sourceDate);
    }

    /**
     * 取得当月的第一天
     * 
     * @return
     */
    public static String getFirstDayOfCurrMonth() {
        Date myDate = new Date();
        String month = DateUtil.DateToString(myDate, CM_SHORT_MONTH_FORMAT);
        return month + "-01";
    }

    /**
     * 取得当月的最后一天
     * 
     * @return
     */
    public static Calendar getEndDayOfCurrMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    /**
     * 取得根据参数取得当月的最后一天
     * 
     * @return
     */
    public static Calendar getEndDayOfCurrMonth(Date myDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    /**
     * 取得当月的年月
     * 
     * @param ymFormat
     *            : 年月格式如"yyyyMM"或"yyyy-MM"
     * @return
     */
    public static String getMonth(String ymFormat) {
        Date myDate = new Date();
        String month = DateUtil.DateToString(myDate, ymFormat);
        return month;
    }

    /**
     * 取得下月的月份,形式如yyyy-MM
     * 
     * @return
     */
    public static String getNextMonth() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, 1);

        String nextmonth = DateUtil.DateToString(cal.getTime(), CM_SHORT_MONTH_FORMAT);
        return nextmonth;
    }
    
    /**
     * 取得下月的月份,形式如yyyy-MM
     * 
     * @return
     */
    public static String getNextYearAndMonth(String yearMonth) {
        
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM");
        Date myDate = new Date();
        try {
            myDate = format.parse(yearMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, 1);

        String nextmonth = DateUtil.DateToString(cal.getTime(), CM_SHORT_MONTH_FORMAT);
        return nextmonth;
    }
    

    /**
     * 取得下月的月份,形式如y
     * 
     * @param ymFormat
     *            格式如:yyyyMM
     * @return
     */
    public static String getNextMonth(String ymFormat) {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, 1);

        String nextmonth = DateUtil.DateToString(cal.getTime(), ymFormat);
        return nextmonth;
    }
    
    /**
     * 取得下月的月份,形式如y
     * 
     * @param ymFormat
     *            格式如:yyyyMM
     * @return
     */
    public static String getMonth(String ymFormat,int month) {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, month);

        String nextmonth = DateUtil.DateToString(cal.getTime(), ymFormat);
        return nextmonth;
    }

    public static java.sql.Date getMonthDate(Date myDate, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, month);
        java.util.Date newDate = cal.getTime();
        return new java.sql.Date(newDate.getTime());
    }
    
 

    /**
     * 取得上月的月份
     * 
     * @return
     */
    public static String getUpMonth() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, -1);

        String nextmonth = DateUtil.DateToString(cal.getTime(), CM_SHORT_MONTH_FORMAT);
        return nextmonth;
    }
    
    /**
     * 取得没有横杠上月的月份
     * 
     * @return
     */
    public static String getYearMonth() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, -1);

        String nextmonth = DateUtil.DateToString(cal.getTime(), YEAR_MONTH);
        return nextmonth;
    }
    
    /**
     * 取得上月的月份
     * 
     * @return
     */
    public static String getUpMonthByDateStr(String date) {
        Date myDate = DateUtil.StringToDateByFormat(date, "yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, -1);

        String nextmonth = DateUtil.DateToString(cal.getTime(), CM_SHORT_MONTH_FORMAT);
        return nextmonth;
    }
    
    public static String getUpMonthYear() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, -1);

        String nextmonth = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
        return nextmonth;
    }
    
    
    public static String getFormatMonth(Integer month){
        if(null==month) {
            return null;
        }
        if(month.intValue()<10){
            return "0"+month;
        }else{
            return ""+month;
        }
    }

    /**
     * 取得从某一时间开始的一段年份
     * 
     * @param currdate
     *            Date
     * @param len
     *            int
     * @return List
     */
    public static List<String> getYear(Date currdate, int len) {
        List<String> lists = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        int ln = Math.abs(len);
        if (len >= 0) {
            for (int i = 0; i < len; i++) {
                cal.setTime(currdate);
                cal.add(Calendar.YEAR, i);

                String year = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
                lists.add(year);
            }
        }
        else {
            for (int i = 1; i <= ln; i++) {
                cal.setTime(currdate);
                cal.add(Calendar.YEAR, -i);
                String year = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
                lists.add(year);
            }

        }
        return lists;
    }

    /**
     * 取得明日的日期
     * 
     * @param today
     * @return
     */
    public static String getTomorrow() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, 1);
        String tomorrow = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
        return tomorrow;
    }

    /**
     * 取得后天的日期
     * 
     * @return
     */
    public static String getDayAfterTomorrow() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, 2);
        String tomorrow = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
        return tomorrow;
    }

    /**
     * 取得昨日的日期
     * 
     * @param today
     * @return
     */
    public static String getYesterday() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, -1);
        String yesterday = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
        return yesterday;
    }

    /**
     * 取昨日日期
     * 
     * @param myDate
     *            Date
     * @return Date
     */
    public static Date getYesterday(Date myDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 取明日日期
     * 
     * @param myDate
     *            Date
     * @return Date
     */
    public static Date getTomorrow(Date myDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
    
    public static Calendar getCalendarDate(String strDate) throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.setTime(convertStringToDate(strDate));
        return cal;
    }

    /**
     * 取得日期的完整打印格式
     * 
     * @param date
     * @return
     */
    public static String getFullDateString(String date) {
        Date myDate = DateUtil.StringToDate(date);
        return dateFormat.format(myDate);
    }

    /**
     * 日期变为字符串
     * 
     * @param date
     * @param iso
     * @return
     */
    public static String DateToString(Date date, String iso) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(iso);
        return format.format(date);
    }

    public static String DateToString(Date date) {
        String myDate = "";
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd");
        try {
            myDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDate;
    }
    /**
     * 字符串变为日期
     * 
     * @param date
     * @param iso
     * @return
     */
    public static Date StringToDate(String date) {
        Date myDate = new Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(CM_LONG_DATE_FORMAT);
        try {
            myDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    /**
     * 根据起始日期 及 间隔时间 得到结束日期
     * 
     * @param startDate
     *            起始日期
     * @param offset
     *            间隔时间
     * @return 结束日期
     */
    public static String getEndDate(String startDate, int offset) {
        Calendar cal = Calendar.getInstance();
        Date day = DateUtil.StringToDate(startDate);
        cal.setTime(day);
        cal.add(Calendar.DATE, offset);

        return DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
    }

    /**
     * 根据起始日期 及 间隔时间 得到结束日期 得到的格式是yyyy-MM-dd
     * 
     * @param startDate
     *            起始日期
     * @param offset
     *            间隔时间
     * @return 结束日期
     */

    public static String getEndDateForSQLDate(String startDate, int offset) {
        Calendar cal = Calendar.getInstance();
        Date day = DateUtil.StringToDateByFormat(startDate, CM_SHORT_DATE_FORMAT);
        cal.setTime(day);
        cal.add(Calendar.DATE, offset);

        return DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
    }

    /**
     * 获得指定月份的天数
     * 
     * @param c
     *            GregorianCalendar GregorianCalendar(int year, 设置日历中的 YEAR 时间域的值 int month, 设置日历中的
     *            MONTH 时间域的值 int date) 设置日历中的 DATE 时间域的值，该值没意义
     * @return int 天数
     */
    public static int daysInMonth(GregorianCalendar c) {
        int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
        return daysInMonths[c.get(GregorianCalendar.MONTH)];
    }

    /**
     * 获得指定月份的天数
     * 
     * @return int 天数
     */
    public static int daysInMonth() {
        int year = Integer.parseInt(getNowYear());
        int month = Integer.parseInt(getMonth().substring(5, 7));

        GregorianCalendar c = new GregorianCalendar(year, month, 0);
        int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
        return daysInMonths[c.get(GregorianCalendar.MONTH)];
    }

    /**
     * 判断今天是否第一天
     * 
     * @return true : 是每月的第一天； false :不是
     */
    public static boolean isFirstDay() {
        boolean check = false;
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        String today = DateUtil.DateToString(cal.getTime(), "dd");

        if (today.equals("01")) {
            check = true;
        }
        return check;
    }

    /**
     * 根据日期得到年
     * 
     * @param currDate
     *            Date
     * @return String
     */
    public static String getYear(java.sql.Date currDate) {
        String strDate = currDate.toString();
        String[] obj = strDate.split("-");
        return obj[0];
    }

    /**
     * 取得当年的年份
     * 
     * @return
     */
    public static String getYear() {
        Date myDate = new Date();
        String year = DateUtil.DateToString(myDate, CM_SHORT_YEAR_FORMAT);
        return year;
    }
    
    public static String getMonthDay(){
        return DateToString(new Date(), MONTH_DAY);
    }
    
    /**
     * 
     * @param dateStr
     * @param DATE_FORMAT
     * @return
     * @throws ParseException
     */
    public static String getYear(String dateStr,String DATE_FORMAT) throws ParseException{
        Date date = convertStringToDate(DATE_FORMAT,dateStr);
        return DateUtil.DateToString(date, CM_SHORT_YEAR_FORMAT);
    }

    /**
     * 取得上年的年份
     * 
     * @return
     */
    public static String getUpYear() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.YEAR, -1);

        String nextyear = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
        return nextyear;
    }

    /**
     * 取得指定日期的年份
     * 
     * @return
     */
    public static String getYearByDate(Date _date) {
        String year = DateUtil.DateToString(_date, CM_SHORT_YEAR_FORMAT);
        return year;
    }

    /**
     * 取得指定日期的年份月份
     * 
     * @return
     */
    public static String getMonthByDate(Date _date) {
        String month = DateUtil.DateToString(_date, CM_SHORT_MONTH_FORMAT);
        return month;
    }

    /**
     * 取得指定日期的下一月的年份月份
     * 
     * @return
     */
    public static String getNextMonthByDate(Date _date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(_date);
        cal.add(Calendar.MONTH, 1);
        String month = DateUtil.DateToString(cal.getTime(), CM_SHORT_MONTH_FORMAT);
        return month;
    }

    /**
     * 取得下年的年份
     * 
     * @return
     */
    public static String getNextYear() {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.YEAR, 1);

        String nextyear = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
        return nextyear;
    }

    // /**
    // * 取得当前时间的下个年月
    // * @return
    // */
    // public static String getNextYearMonth() {
    // String nextYearMonth = null;
    // String nextMonth = getNextMonth(); //得到下个月
    //
    // StringBuffer buff = null;
    // if (nextMonth.equals("01") || nextMonth.equals("1")) {
    // buff = new StringBuffer(getNextYear()); //如果下个月是一月份,则年份取下一年
    // }
    // else {
    // buff = new StringBuffer(getNowYear()); //年份取当年
    // }
    // buff.append(nextMonth);
    // nextYearMonth = buff.toString();
    // buff = null;
    //
    // return nextYearMonth;
    // }

    /**
     * 根据日期得到月
     * 
     * @param currDate
     *            Date 日期
     * @return String
     */
    public static String getMonth(java.sql.Date currDate) {
        String strDate = currDate.toString();
        String[] obj = strDate.split("-");
        return obj[1];
    }

    /**
     * 根据日期得到日
     * 
     * @param currDate
     *            Date 日期
     * @return String
     */
    public static String getDay(java.sql.Date currDate) {
        String strDate = currDate.toString();
        String[] obj = strDate.split("-");
        return obj[2];
    }

    /**
     * 根据日期得到当月的天数
     * 
     * @param currYear
     *            String 年
     * @param currMonth
     *            String 月
     * @return Integer
     */
    public static Integer getDaysOfMonth(String currYear, String currMonth) {
        Integer result = null;
        try {
            int year = Integer.parseInt(currYear);
            int month = Integer.parseInt(currMonth);
            switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                result = new Integer(31);
                break;
            case 2:
                if ((year % 4 == 0) || ((year % 100 == 0) && (year % 400 == 0))) {
                    result = new Integer(29);
                }
                else {
                    result = new Integer(28);
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                result = new Integer(30);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据年月日得到日期
     * 
     * @param year
     *            String 年 YYYY
     * @param month
     *            String 月MM
     * @param day
     *            String 日dd
     * @return Date
     */
    public static java.sql.Date getDate(String year, String month, String day) {
        java.sql.Date result = null;
        try {
            String str = year + "-" + month + "-" + day;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = dateFormat.parse(str);
            result = new java.sql.Date(date1.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static java.sql.Date getDate(String strDate) {
        java.sql.Date result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = dateFormat.parse(strDate);
            result = new java.sql.Date(date1.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 得到两个日期间最大的日期天数
     * 
     * @param startDate
     *            Date
     * @param endDate
     *            Date
     * @return int
     */
    public static int getMaxDays(java.sql.Date startDate, java.sql.Date endDate) {
        int count = 0;
        int maxCount = 0;
        int year = Integer.parseInt(getYear(startDate));
        int startMonth = Integer.parseInt(getMonth(startDate));
        int endMonth = Integer.parseInt(getMonth(endDate));
        String strYear = String.valueOf(year);
        String strMonth = null;
        for (int i = startMonth; i <= endMonth; i++) {
            strMonth = String.valueOf(i);
            count = getDaysOfMonth(strYear, strMonth).intValue();
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    /**
     * 从传入月份的日期算起
     * 
     * @param month
     *            String 传入当前日期 例如："2005-08-01"
     * @param offset
     *            int 传入0既返回 "2005-08-01"，1 就是 "2005-08-02"；类推
     * @return Date sql类型的日期
     */
    public static java.sql.Date getSQLDate(String month, int offset) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        String day = "";
        try {
            myDate = format.parse(month);
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            cal.add(Calendar.DATE, offset);
            day = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return java.sql.Date.valueOf(day);
    }

    /**
     * 日期间隔天数
     * 
     * @param startDate
     *            String 开始日期 例如："2005-08-01"
     * @param endDate
     *            String 结束日期 例如："2005-08-01"
     * @return int 间隔天数
     */
    public static int getDiff(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = formatter.parse(startDate, new ParsePosition(0));
        Date endTime = formatter.parse(endDate, new ParsePosition(0));
        long l = Math.abs(endTime.getTime() - startTime.getTime());
        return (int) (l / 86400000); // 注意Cast问题
    }

    /**
     * 日期间隔天数
     * 
     * @param startDate
     *            String 开始日期 例如："2005-08-01"
     * @param endDate
     *            String 结束日期 例如："2005-08-01"
     * @return int 间隔天数
     */
    public static boolean isDateAfter(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = formatter.parse(startDate, new ParsePosition(0));
        Date endTime = formatter.parse(endDate, new ParsePosition(0));
        boolean flag = startTime.after(endTime);
        return flag;
    }

    /**
     * 得到YYYY-MM类型
     * 
     * @param year
     *            int 年
     * @param month
     *            int 月
     * @throws Exception
     * @return String
     */
    public static String getShortDate(int year, int month) {
        String strYear = String.valueOf(year);
        String strMonth = String.valueOf(month);
        if (strMonth.length() == 1) {
            strMonth = "0" + strMonth;
        }
        return strYear + "-" + strMonth;
    }

    /**
     * 把指定格式的字符串变为日期型
     * 
     * @param date
     * @param iso
     * @return
     */
    public static Date StringToDateByFormat(String date, String iso) {
        Date myDate = new Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(iso);
        try {
            myDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    /**
     * java.sql.date 格式转成标准 yyyy-MM-dd 字符串格式
     * 
     * @param date
     *            Date
     * @return String
     */
    public static String SQLDateToStr(java.sql.Date date) {
        String result = String.valueOf(date);

        if (result != null && result.length() > 10) {
            result = result.substring(0, 8);
        }

        return result;
    }

    /**
     * 转换Timestamp为字符串
     * 
     * @param date
     *            Timestamp
     * @return String
     */
    public static String TimeToStr(java.sql.Timestamp date) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(CM_LONG_DATE_FORMAT);

        java.sql.Timestamp dateTime = new java.sql.Timestamp(date.getTime());
        String dateString = format.format(dateTime);

        return dateString;
    }
    
    /**
     * 转换Timestamp为字符串
     * 
     * @param date
     *            Timestamp
     * @param format
     * @return String
     */
    public static String TimeToStr(java.sql.Timestamp date, String formatStr) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(formatStr);
        java.sql.Timestamp dateTime = new java.sql.Timestamp(date.getTime());
        String dateString = format.format(dateTime);
        return dateString;
    }

    /**
     * 转换Timestamp为字符串
     * 
     * @param date
     *            Timestamp
     * @return String
     */
    public static String DateToStr(java.util.Date date) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(CM_LONG_DATE_FORMAT);

        java.sql.Timestamp dateTime = new java.sql.Timestamp(date.getTime());
        String dateString = format.format(dateTime);

        return dateString;
    }
    
    public static final String convertDateToString(Date aDate) {
        return getDateTime(CM_SHORT_DATE_FORMAT, aDate);
    }
    
    public static final String convertDateToString(Date aDate,String aMask) {
        return getDateTime(aMask, aDate);
    }
    
    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     *
     * @param aMask
     *            the date pattern the string is in
     * @param aDate
     *            a date object
     * @return a formatted string representation of the date
     *
     * @see java.text.SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /*----------------------------------------------------------------------*/

    /**
     * 返回两个年月之间间隔的月数
     * 
     * @param dealMonth
     *            "YYYY-MM-dd" 结束时间
     * @param alterMonth
     *            "YYYY-MM-dd" 开始时间
     * @return
     * @pre alterMonth != null
     * @pre dealMonth != null
     */
    public static int calBetweenTwoMonth(String startYear, String endYear) {
        String dealMonth = DateUtil.StrReplace(endYear, "-", "");
        // System.out.println(dealMonth);
        dealMonth = dealMonth.substring(0, 6);
        // System.out.println(dealMonth);
        String alterMonth = DateUtil.StrReplace(startYear, "-", "");
        alterMonth = alterMonth.substring(0, 6);

        int length = 0;
        if (dealMonth.length() != 6 || alterMonth.length() != 6) {
            length = -1;
        }
        else {
            int dealInt = Integer.parseInt(dealMonth);
            int alterInt = Integer.parseInt(alterMonth);
            if (dealInt < alterInt) {
                length = -2;
            }
            else {
                int dealYearInt = Integer.parseInt(dealMonth.substring(0, 4));
                int dealMonthInt = Integer.parseInt(dealMonth.substring(4, 6));
                int alterYearInt = Integer.parseInt(alterMonth.substring(0, 4));
                int alterMonthInt = Integer.parseInt(alterMonth.substring(4, 6));
                length = (dealYearInt - alterYearInt) * 12 + (dealMonthInt - alterMonthInt);
            }
        }
        return length;
    }

    /**
     * 该函数将在指定的字符串中特定替换成新的字符串
     * 
     * @param rStr
     *            String 指定字符串
     * @param rFix
     *            String 要替换的字符串
     * @param rRep
     *            String 要替换成的字符串
     * @return String 已替换的字符串
     */
    public static String StrReplace(String line, String oldString, String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    public static boolean getTimeDiff(String hour) throws Exception {
        boolean lb = false;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(CM_LONG_DATE_FORMAT);
        java.sql.Date currDate = new java.sql.Date(System.currentTimeMillis());
        java.sql.Timestamp diffTime = new java.sql.Timestamp(format.parse(
                currDate.toString() + " " + hour).getTime());
        lb = diffTime.before(currDate);
        return lb;
    }

    /**
     * 得到日期中是星期几
     * 
     * @param year
     *            String
     * @param month
     *            String
     * @param day
     *            String
     * @return String
     */
    public static int getWeekOfMonth(String year, String month, String day) {
        java.sql.Date myDate = DateUtil.getDate(year, month, day);
        int index = 0;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            index = cal.get(Calendar.DAY_OF_WEEK);
            /*
             * switch (index) { case 1: result = "星期日"; break; case 2: result = "星期一"; break; case
             * 3: result = "星期二"; break; case 4: result = "星期三"; break; case 5: result = "星期四";
             * break; case 6: result = "星期五"; break; case 7: result = "星期六"; break; default: result
             * = ""; break; }
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return index;

    }

    /**
     * 四舍5入，保留小数2位
     * 
     * @param d
     *            double
     * @return String
     */
    public static String changePer(double d) {
        String str = "";
        DecimalFormat df = new DecimalFormat("######0.00"); // 设置输出数值的格式为XX.XX
        str = df.format(d);
        return str;
    }

    /**
     * 获得最早的时间(同年、同月)
     * 
     * @param date1
     *            String
     * @param date2
     *            String
     * @return String
     */
    public static String getStartDate(String date1, String date2) {
        if (date1 == null || date2 == null) {
            return "";
        }

        String a = date1.substring(8, 10);
        String b = date2.substring(8, 10);
        int c = Integer.parseInt(a); // 数据库
        int d = Integer.parseInt(b); // 外部传进

        if (c >= d) {
            return date2;
        } else {
            return date1;
        }
    }

    /**
     * 如果开始时间 > 结束时间 就返回false
     * 
     * @param startDay
     *            String
     * @param endDay
     *            String
     * @return boolean
     */
    public static boolean checkDate(String startDay, String endDay) {
        boolean check = true;

        String a = startDay.substring(8, 10);
        String b = endDay.substring(8, 10);
        int c = Integer.parseInt(a);
        int d = Integer.parseInt(b);
        // System.out.print(c + "==" + d);

        if (c >= d) {
            check = false;
        }

        return check;
    }

    public static String getYear(int num) {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.YEAR, num);

        String year = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
        return year;
    }

    public static String getMonth(int num) {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.MONTH, num);
        String month = DateUtil.DateToString(cal.getTime(), CM_SHORT_MONTH_FORMAT);
        return month;
    }

    public static String getDay(int num) {
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, num);
        String tomorrow = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
        return tomorrow;
    }

    public static String[] getLastWeek() {
        String[] week = new String[2];
        Date myDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        // 分周日和非周日两种情况，在美国周日是一周第一天
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.add(Calendar.DATE, -13);
        }
        else {
            cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK) + 5));
        }

        week[0] = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
        cal.add(Calendar.DATE, 6);
        week[1] = DateUtil.DateToString(cal.getTime(), CM_SHORT_DATE_FORMAT);
        // System.out.println( week[0]+ "|||" + week[1]);

        return week;
    }

    public static Date getYesterday2() {
        java.util.Date d = StringToDate(getYesterday());
        return d;

    }

    /** 获取两个时间间隔 */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);

            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);

            // 这里精确到了秒，我们可以在做差的时候将时间精确到天
        } catch (Exception e) {
            return "";
        }

        return day + "";
    }

    /**
     * 根据开始日期和结束日期得到中级时间
     * 
     * @param beginTime
     * @param endTime
     * @return
     */
    public static String[] getBetweenDates(String beginTime, String endTime) throws Exception {
        String begin_Time = beginTime + " 00:00:00";
        String end_Time = endTime + " 24:00:00";
        int datelength = new Integer(DateUtil.getTwoDay(end_Time, begin_Time)).intValue();
        String[] dts = new String[datelength];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dts[0] = endTime;
        for (int i = 1; i < datelength; i++) {
            java.util.Date date = dateFormat.parse(end_Time);
            Date yesterday = DateUtil.getYesterday(date);
            String yester_day = dateFormat.format(yesterday);
            end_Time = yester_day + " 24:00:00";
            dts[i] = yester_day;
        }
        return dts;
    }

    /**
     * JXL中通过DateCell.getDate()获取单元格中的时间为（实际填写日期+8小时），原因是JXL是按照GMT时区来解析XML。 本方法用于获取单元格中实际填写的日期！
     * 例如单元格中日期为“2009-9-10”，getDate得到的日期便是“Thu Sep 10 08:00:00 CST 2009”； 单元格中日期为“2009-9-10
     * 16:00:00”，getDate得到的日期便是“Fri Sep 11 00:00:00 CST 2009”
     * 
     * @author XHY
     * @param jxlDate
     *            通过DateCell.getDate()获取的时间
     * @return
     * @throws ParseException
     */
    public static java.util.Date convertDate4JXL(java.util.Date jxlDate) throws ParseException {
        if (jxlDate == null) {
            return null;
        }
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        dateFormat.setTimeZone(gmt);
        String str = dateFormat.format(jxlDate);
        TimeZone local = TimeZone.getDefault();
        dateFormat.setTimeZone(local);
        return dateFormat.parse(str);
    }

    /**
     * 通过参数"yyyy-MM"得到当前年月的最后一天
     * 
     * @param datetemp
     * @return
     */
    public static String getTheLastDateOfMouth(String datetemp, String showformat) {
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdfInput = new SimpleDateFormat(showformat);
        SimpleDateFormat chineseDateFormat1 = new SimpleDateFormat("yyyy-MM");
        try {
            today.setTime(chineseDateFormat1.parse(datetemp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        today.set(Calendar.DATE, today.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdfInput.format(today.getTime());
    }

    /**
     * 通过参数"yyyy-MM"得到当前年月的第一天
     * 
     * @param datetemp
     * @return
     */
    public static String getTheFirstDateOfMouth(String datetemp, String showformat) {
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdfInput = new SimpleDateFormat(showformat);
        SimpleDateFormat chineseDateFormat1 = new SimpleDateFormat("yyyy-MM");
        try {
            today.setTime(chineseDateFormat1.parse(datetemp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        today.set(Calendar.DATE, 1);
        return sdfInput.format(today.getTime());
    }

    /**
     * 返回开始日期格式,为 yyyy-MM-dd 00:00:00
     * 
     * @param date
     *            如果输入null,则取当天日期
     * @return yyyy-MM-dd 00:00:00格式的日期
     */

    public static Date getStartDate(Date date) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.HOUR_OF_DAY, 0);
        rightNow.set(Calendar.MINUTE, 0);
        rightNow.set(Calendar.SECOND, 0);

        return rightNow.getTime();
    }

    /**
     * 返回开始日期格式,为 yyyy-MM-dd 23:59:59
     * 
     * @param date
     *            如果输入null,则取当天日期
     * @return yyyy-MM-dd 23:59:59格式的日期
     */

    public static Date getEndDate(Date date) {
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.HOUR_OF_DAY, 23);
        rightNow.set(Calendar.MINUTE, 59);
        rightNow.set(Calendar.SECOND, 59);

        return rightNow.getTime();
    }

    public static Date addDays(int days) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
    
    public static String getCurTimeString() {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String todayAsString = df.format(today);
        return todayAsString;
    }
    
    /**
     * 检验输入是否为正确的日期格式(不含秒的任何情况),严格要求日期正确性,格式:yyyy-MM-dd
     * @param sourceDate
     * @return
     */
    public static boolean checkDate(String sourceDate){
        if(sourceDate==null){
            return false;
        }
        try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               dateFormat.setLenient(false);
               dateFormat.parse(sourceDate);
               return true;
        } catch (Exception e) {
        }
         return false;
    }
    
    /**
     * 检验输入是否为正确的日期格式(不含秒的任何情况),严格要求日期正确性,格式:yyyy-MM-dd
     * @param sourceDate
     * @return
     */
    public static boolean checkDateLegal(String sourceDate,String sourceDateFormat){
        if(sourceDate==null){
            return false;
        }
        try {
               SimpleDateFormat dateFormat = new SimpleDateFormat(sourceDateFormat);
               dateFormat.setLenient(false);
               dateFormat.parse(sourceDate);
               return true;
        } catch (Exception e) {
        }
         return false;
    }
    
    public static Date convertStringToDate(String strDate)
    throws ParseException {
      Date aDate = null;

      try {

          aDate = convertStringToDate(getDatePattern(), strDate);
      } catch (ParseException pe) {
          pe.printStackTrace();
          throw new ParseException(pe.getMessage(),
                                   pe.getErrorOffset());
      }

      return aDate;
  }
    
    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(String aMask, String strDate)
      throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
            defaultDatePattern = "yyyy-MM-dd";
        } catch (MissingResourceException mse) {
            defaultDatePattern = "yyyy-MM-dd";
//            defaultDatePattern = "MM/dd/yyyy";
        }

        return defaultDatePattern;
    }
    /*----------------------------------------------------------------------*/

    public static void main(String[] args) throws ParseException {

        System.out
                .println(DateUtil.getTheLastDateOfMouth("2010-10", DateUtil.CM_SHORT_DATE_FORMAT));
        System.out.println(DateUtil.DateToString(new Date(), "HHmmss"));
        System.out.println(checkDateLegal(null, "yyyy.MM-yyyy.MM"));
        System.out.println(getMonth("yyyy-MM-dd"));
        System.out.println(getUpMonthYear());
        System.out.println(getUpMonthByDateStr("2013-1"));
    }
}