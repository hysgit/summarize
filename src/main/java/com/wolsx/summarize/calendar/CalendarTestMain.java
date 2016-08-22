package com.wolsx.summarize.calendar;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hy on 8/19/16.
 */
public class CalendarTestMain {

    /**
     * 测试Calendar增加或减少日期遇到月底或者月尾的时候
     */
    @Test
    public void test1() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String day ="2016-02-28";
        calendar.setTime(simpleDateFormat.parse(day));
        calendar.add(Calendar.DATE, 1);//2016-02-29
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1); //2016-03-01
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DATE, -1); //2016-03-01
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        day = "2016-01-01";
        calendar.setTime(simpleDateFormat.parse(day));
        calendar.add(Calendar.DATE, -1);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }
}
