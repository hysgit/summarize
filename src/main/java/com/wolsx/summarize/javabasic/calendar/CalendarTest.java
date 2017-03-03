package com.wolsx.summarize.javabasic.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hy on 7/6/16.
 */
public class CalendarTest {
    public static void main(String[] args) {
        //常用方法
        param();
        //常用属性
        method();
    }

    //常用方法
    public static void method() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        //setTime():使用给定的Date设置此 Calendar 的时间
        c.setTime(date);
        //获取Calendar对象
        Calendar cm = Calendar.getInstance();
        //getTime():获取当前时间，类似于new Date();
        Date d = cm.getTime();
        System.err.println("Calendar获得时间：" + d);
        System.err.println("new Date创建的时间：" + date);
        //getTimeInMillis():返回此 Calendar 的时间值，以毫秒为单位。
        long dl = c.getTimeInMillis();
        long ddate = cm.getTimeInMillis();
        System.err.println("毫秒数：" + dl);
        System.err.println("毫秒数：" + ddate);

        //setTimeInMillis():用给定的 long 值设置此Calendar的当前时间值。
        long sv = 123456;
        Calendar sc = Calendar.getInstance();
        sc.setTimeInMillis(sv);
        SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String st = ss.format(sc.getTime());
        System.err.println(st);

        //get():返回给定日历字段的值。
        int year = c.get(Calendar.YEAR);
        System.err.println(year);

        //set():将给定的日历字段设置为给定值
        c.set(Calendar.YEAR, 2);
        int y = c.get(Calendar.YEAR);
        System.err.println(y);//输出2

        //Calendar比较：before(),after(),equals(),compareTo().
        try {
            String startTime = "2012-12-12 12:45:39";
            String endTime = "2012-12-12 12:45:40";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.setTime(startDate);
            end.setTime(endDate);
            if (start.before(end)) {
                System.err.println("开始时间小于结束时间");
            } else if (start.after(end)) {
                System.err.println("开始时间大于结束时间");
            } else if (start.equals(end)) {
                System.err.println("开始时间等于结束时间");
            }
            /*
             * start < end 返回-1
             * start = end 返回0
             * start > end 返回1
             */
            int count = start.compareTo(end);
            System.err.println(count);

            //add():为给定的日历字段添加或减去指定的时间量
            start.add(Calendar.YEAR, -3);
            System.err.println("原来的时间：" + startTime);
            System.err.println("add后的时间：" + sdf.format(start.getTime()));

            //toString():转换为字符串
            System.err.println(start.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //常用属性
    public static void param() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //Calendar.YEAR:日期中的年
        int year = c.get(Calendar.YEAR);
        //Calendar.MONTH:日期中的月，需要加1
        int mounth = c.get(Calendar.MONTH) + 1;
        //Calendar.DATE:日期中的日
        int day = c.get(Calendar.DATE);
        //Calendar.HOUR:日期中的小时(12小时制)
        int hour = c.get(Calendar.HOUR);
        //Calendar.HOUR_OF_DAY：24小时制
        int HOUR_OF_DAY = c.get(Calendar.HOUR_OF_DAY);
        //Calendar.MINUTE:日期中的分钟
        int minute = c.get(Calendar.MINUTE);
        //Calendar.SECOND:日期中的秒
        int second = c.get(Calendar.SECOND);
        System.err.println(year + "-" + mounth + "-" + day + " " + hour + ":" + minute + ":" + second);

        //Calendar.WEEK_OF_YEAR:当前年中星期数
        int WEEK_OF_YEAR = c.get(Calendar.WEEK_OF_YEAR);
        //Calendar.WEEK_OF_MONTH:当前月中星期数
        int WEEK_OF_MONTH = c.get(Calendar.WEEK_OF_MONTH);
        //Calendar.DAY_OF_YEAR:当前年中的第几天
        int DAY_OF_YEAR = c.get(Calendar.DAY_OF_YEAR);
        //Calendar.DAY_OF_MONTH:当前月中的第几天
        int DAY_OF_MONTH = c.get(Calendar.DAY_OF_MONTH);
        //Calendar.DAY_OF_WEEK:当前星期的第几天(星期天表示第一天，星期六表示第七天)
        int DAY_OF_WEEK = c.get(Calendar.DAY_OF_WEEK);
        //Calendar.DAY_OF_WEEK_IN_MONTH:当前月中的第几个星期
        int DAY_OF_WEEK_IN_MONTH = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date ampm = format.parse("2012-12-15 21:59:59");
            Calendar cc = Calendar.getInstance();
            cc.setTime(ampm);
            //AM_PM:HOUR 是在中午之前还是在中午之后,在中午12点之前返回0，在中午12点(包括12点)之后返回1
            int AM_PM = cc.get(Calendar.AM_PM);
        } catch (Exception e) {
        }

    }
}
