package com.wolsx.summarize.javabasic.classuse;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hy on 7/6/16.
 */
public class CalendarTest2 {

    @Test
    public void test1()
    {
        Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.DAY_OF_WEEK);
        int firstDayOfWeek = c.getFirstDayOfWeek();
        System.out.println(i);
        System.out.println(firstDayOfWeek);
        c.setFirstDayOfWeek(2);

    }

    @Test
    public void test2()
    {
        //获取当前天所在的周的一周日子
        /**
         * 这里首先有一个一周起始是周一还是周日的问题。
         * Calendar有方法，可以获取到给定日期是星期几
         * 如果是周日开始，那么，就从给定的日期，往前，开始找周日，用add()方法，加-1来往前推
         * 找到周日后，记为第一天，然后再往后，推6天。就获取到了给定日期所在的一周日期数据 。
         * 一天是星期几和一周以哪天为开始无关
         */
        Map<Integer,String> dateMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);//寻找到当前日期所在周的第一天，把周一视为一周开始
        }
        for (int i = 0; i < 7; i++) {
            dateMap.put(i+1,new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        System.out.println(dateMap);
    }
}
