package cn.xukai.java.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xukai on 2017/1/3.
 */
public class DateUtil {
    /*
     * 毫秒转化 时-分-秒-毫秒
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuffer sb = new StringBuffer();
        if(hour >= 0) {
            if(hour<=9)
                sb.append("0"+hour+":");
            else
                sb.append(hour+":");
        }
        if(minute >= 0) {
            if(minute<=9)
                sb.append("0"+minute+":");
            else
                sb.append(minute+":");
        }
        if(second >= 0) {
            if (second<=9)
                sb.append("0"+second);
            else
                sb.append(second);
        }
        return sb.toString();
    }
    // 时间戳 ------------------------------------------------------------
    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param date_str  字符串日期
     * @param format    如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
