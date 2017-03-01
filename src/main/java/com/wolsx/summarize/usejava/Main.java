package com.wolsx.summarize.usejava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hy on 2/27/17.
 */
public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader br11 = new BufferedReader(new FileReader("/home/hy/tmp/log/bbkjnurs/16.txt"));


        StringBuilder sb = new StringBuilder();
        String line = null;
        while(null != (line = br11.readLine()))
        {
            sb.append(line);
        }

        Pattern pattern = Pattern.compile("(?<=\"timestamp\":)(\\d*)");
        Matcher matcher = pattern.matcher(sb.toString());
        List<Long> list = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group();
            list.add(new Long(group));
        }
        long max = 0;
        long min = Long.MAX_VALUE;
        Long first = null;
        List<Long> maxlist = new ArrayList<>();
        for(Long lo: list)
        {
            if(first != null)
            {
                long dif = Math.abs(lo-first);
                if(dif > max){
                    max = dif;
                }
                if(dif < min)
                {
                    min = dif;
                }
                if(dif > 15*1000)
                {
                    maxlist.add(dif);
                    System.out.println(lo);
                }

            }
            first = lo;
        }
        System.out.println("总数据数:"+ list.size());
        System.out.println("max: " + max/1000 );
        System.out.println("min: " + min/1000 );
        System.out.println("maxlist.size: " +maxlist.size());
        System.out.println("maxlist : " +maxlist);
        System.out.println("----------------------------------------------------------------------------");


    }
}
