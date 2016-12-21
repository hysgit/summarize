package com.wolsx.summarize.cadlendartest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hy on 12/21/16.
 */
public class CalendarTest {
    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        Date  date = calendar.getTime();
        long time = date.getTime();
        System.out.println(time);
        System.out.println(date);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        Date date2 = calendar.getTime();
        long time1 = date2.getTime();
        System.out.println(time1);
        System.out.println(date2);
        System.out.println((time1-time)/1000/60/60);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
        System.out.println(sdf.format(calendar.getTime()));
        Calendar instance = Calendar.getInstance();

    }
}
