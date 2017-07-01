package com.zxit.tools;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UtilDate {
    public static Date getDate() {
        Calendar canlendar = Calendar.getInstance();
        return canlendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getDate());
    }

    public static Date getDate(long millis) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTimeInMillis(millis);
        return canlendar.getTime();
    }

    public static long getMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String getDateFormate(Date date, String formate) {
        try {
            SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
            return simpleDateFormate.format(date);
        } catch (Exception localException) {
        }
        return "";
    }

    public static String get4yMdHmsS(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static String get4yMdHms(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String get4yMdHm(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm");
    }

    public static String get4yMd(Date date) {
        return getDateFormate(date, "yyyy-MM-dd");
    }

    public static Date parse4yMdHmsS(String sDate) {
        return parseDate(sDate, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static Date parse4yMdHms(String sDate) {
        return parseDate(sDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parse4yMdHm(String sDate) {
        return parseDate(sDate, "yyyy-MM-dd HH:mm");
    }

    public static Date parse4yMd(String sDate) {
        return parseDate(sDate, "yyyy-MM-dd");
    }

    public static Date parseDate(String sDate, String formate) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        try {
            return simpleDateFormate.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static double getDifTwoTime(Date minuendTime, Date subtrahendTime,
                                       String tdatestr) {
        if ((minuendTime == null) || (subtrahendTime != null)) {
            return getDifTwoTime(minuendTime.getTime(),
                    subtrahendTime.getTime(), tdatestr);
        }
        return 0.0D;
    }

    public static double getDifTwoTime(long minuendTime, long subtrahendTime,
                                       String tdatestr) {
        if ((tdatestr == null) || (tdatestr.equals(""))) {
            tdatestr = "MS";
        }
        double temp = 1.0D;

        if ("MS".equalsIgnoreCase(tdatestr)) {
            temp = 1.0D;
        }

        if ("S".equalsIgnoreCase(tdatestr)) {
            temp = 1000.0D;
        }

        if ("M".equalsIgnoreCase(tdatestr)) {
            temp = 60000.0D;
        }

        if ("H".equalsIgnoreCase(tdatestr)) {
            temp = 3600000.0D;
        }

        if ("D".equalsIgnoreCase(tdatestr)) {
            temp = 86400000.0D;
        }
        return minuendTime - subtrahendTime / temp;
    }

    public static int getPartOfTime(Date date, String part) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTime(date);

        if (part.equalsIgnoreCase("Y")) {
            return canlendar.get(1);
        }

        if (part.equalsIgnoreCase("M")) {
            return canlendar.get(2) + 1;
        }

        if (part.equalsIgnoreCase("D")) {
            return canlendar.get(5);
        }

        if (part.equalsIgnoreCase("H")) {
            return canlendar.get(11);
        }

        if (part.equalsIgnoreCase("M")) {
            return canlendar.get(12);
        }

        if (part.equalsIgnoreCase("S")) {
            return canlendar.get(13);
        }

        if (part.equalsIgnoreCase("MS")) {
            return canlendar.get(14);
        }
        return -1;
    }

    public static String parsString2CnYmd(String big) {
        if (big == null) {
            return "";
        }
        String reStr = "";
        String temp = big.toString();
        if (temp.length() != 14) {
            return temp;
        }

        String yyyy = temp.substring(2, 4);
        String mm = temp.substring(4, 6);
        String dd = temp.substring(6, 8);
        String hh = temp.substring(8, 10);
        String mi = temp.substring(10, 12);
        reStr = yyyy + "年" + mm + "月" + dd + "日" + hh + "点" + mi + "分";

        return reStr;
    }

    public static int[] getMonthCalendar(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        int firstDay = cal.getMinimum(5);

        int lastDay = cal.getActualMaximum(5);

        int[] day = new int[lastDay];
        for (int i = 0; i < lastDay; i++) {
            day[i] = (i + firstDay);
        }
        return day;
    }

    public static List<BigDecimal> getDatePeriod(Date date, int beforeDays) {
        List<BigDecimal> datePeriodList = new ArrayList<BigDecimal>();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int inputDayOfYear = cal.get(6);
        for (int i = beforeDays - 1; i >= 0; i--) {
            cal.set(6, inputDayOfYear - i);
            datePeriodList.add(new BigDecimal(dateFormat.format(cal.getTime())));
        }
        return datePeriodList;
    }

    public String getMinuSJ(String d1, String d2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        long l = sdf.parse(d1).getTime() - sdf.parse(d2).getTime();
        long day = l / 86400000L;
        long hour = l / 3600000L - 24L * day;
        long min = l / 60000L - day * 24L * 60L - hour * 60L;
        long s = l / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min
                * 60L;
        String Lstr = "";
        if (day > 0L) {
            Lstr = Lstr + day + "天";
        }
        if (hour > 0L) {
            Lstr = Lstr + hour + "小时";
        }
        if (min > 0L) {
            Lstr = Lstr + min + "分";
        }
        if (s > 0L) {
            Lstr = Lstr + s + "秒";
        }
        return Lstr;
    }

    public static String getDate3() {
        Calendar riqi = new GregorianCalendar();
        int mm = (riqi.get(2) + 2) % 12;
        if (mm == 0)
            mm = 12;
        int dd = riqi.get(5);
        if (mm == 2) {
            if (dd > 28)
                dd = 28;
        } else
            switch (mm) {
                case 4:
                case 6:
                case 9:
                case 11:
                    if (dd > 30)
                        dd = 30;
                    break;
                case 5:
                case 7:
                case 8:
                case 10:
            }
        if (mm == 1) {
            return riqi.get(1) + 1 + "-" + mm + "-" + dd;
        }
        return riqi.get(1) + "-" + mm + "-" + dd;
    }

    public static String getDate2() {
        String nowDate = GetDate();
        return nowDate.substring(0, 4) + "-" + nowDate.substring(4, 6) + "-"
                + nowDate.substring(6);
    }

    public static String getDate2Chinese() {
        Calendar date = new GregorianCalendar();
        return date.get(1) + "年" + (date.get(2) + 1) + "月" + date.get(5) + "日";
    }

    public static String getToday() {
        Calendar cal = Calendar.getInstance();
        cal.add(5, 0);
        String today = new SimpleDateFormat("yyyy-MM-dd ")
                .format(cal.getTime());

        return today;
    }

    public static String getYestoday() {
        Calendar cal = Calendar.getInstance();
        cal.add(5, -1);
        String yestoday = new SimpleDateFormat("yyyy-MM-dd ").format(cal
                .getTime());
        return yestoday;
    }

    public static String getDateTime() {
        Calendar riqi = new GregorianCalendar();

        return riqi.get(1) + "-" + (riqi.get(2) + 1) + "-" + riqi.get(5) + " "
                + riqi.get(11) + ":" + riqi.get(12) + ":" + riqi.get(13);
    }

    public static String GetDateString(Date date) {
        if (date == null)
            return "";
        return date.toString().substring(0, 10);
    }

    public static String GetDate() {
        Date c_d = new Date();
        String mm = "";
        String dd = "";
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(c_d);
        int y = c.get(1);
        int m = c.get(2) + 1;
        int d = c.get(5);
        if (m <= 9)
            mm = "0" + String.valueOf(m);
        else
            mm = String.valueOf(m);
        if (d <= 9)
            dd = "0" + String.valueOf(d);
        else
            dd = String.valueOf(d);
        return String.valueOf(y) + mm + dd;
    }

    public static String GetTime() {
        Date c_d = new Date();
        String hh = "";
        String mm = "";
        String ss = "";
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(c_d);
        int h = c.get(11);
        int mi = c.get(12);
        int s = c.get(13);
        if (h < 9)
            hh = "0" + String.valueOf(h);
        else
            hh = String.valueOf(h);
        if (mi < 9)
            mm = "0" + String.valueOf(mi);
        else
            mm = String.valueOf(mi);
        if (s < 9)
            ss = "0" + String.valueOf(s);
        else
            ss = String.valueOf(s);
        return hh + mm + ss;
    }

    public static String getDateStr(String t) {
        SimpleDateFormat datefmt = new SimpleDateFormat();
        String str = "";
        try {
            datefmt.applyPattern("yyyy-MM-dd");
            Date vdate = datefmt.parse(t);

            DateFormat fmt = DateFormat.getDateInstance(0, Locale.CHINA);
            str = fmt.format(vdate);
        } catch (Exception e) {
            str = "日期转换出错!";
        }
        return str;
    }

    public static boolean isToday(Date myDate) {
        Date c_d = myDate;
        String mm = "";
        String dd = "";
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(c_d);
        int y = c.get(1);
        int m = c.get(2) + 1;
        int d = c.get(5);
        if (m <= 9)
            mm = "0" + String.valueOf(m);
        else
            mm = String.valueOf(m);
        if (d <= 9)
            dd = "0" + String.valueOf(d);
        else
            dd = String.valueOf(d);
        String myDateStr = String.valueOf(y) + mm + dd;

        return myDateStr.equals(GetDate());
    }

    public static String formatDate(Date date, String aformat) {
        if (date == null) {
            return "------";
        }
        String s1 = "";

        SimpleDateFormat simpledateformat = new SimpleDateFormat(aformat);
        s1 = simpledateformat.format(date);
        return s1;
    }
}