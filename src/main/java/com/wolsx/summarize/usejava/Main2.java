package com.wolsx.summarize.usejava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hy on 2/27/17.
 */
public class Main2
{
    public static void main(String[] args) throws IOException {
        String fileName = "/home/hy/tmp/log/bbkjnurs/2.txt";
        BufferedReader br11 = new BufferedReader(new FileReader(fileName));

        String sn = fileName.replace("/home/hy/tmp/log/bbkjnurs/", "").replace(".txt", "");
        System.out.println("sn:"+sn);
        StringBuilder sb = new StringBuilder();
        String linenow = null;

        Pattern pattern = Pattern.compile("(?<=\"timestamp\":)(\\d*)");
        Map<Long, String> map = new HashMap<>();
        List<Long> keys = new ArrayList<>();
        while(null != (linenow = br11.readLine()))
        {
            Matcher matcher = pattern.matcher(linenow);
            if (matcher.find()) {
                String group = matcher.group();
                Long key = new Long(group);
                map.put(key,linenow);
                keys.add(key);
            }
        }

        long max = 0;
        long min = Long.MAX_VALUE;
        Long first = null;
        List<Long> maxlist = new ArrayList<>();
        Set<Long> longs = map.keySet();
        for(Long key: keys)
        {
            if(first != null)
            {
                long dif = Math.abs(key-first);
                if(dif > max){
                    max = dif;
                }
                if(dif < min)
                {
                    min = dif;
                }
                if(dif > 60*1000)
                {
                    maxlist.add(dif);
                    System.out.println("间隔: "+dif/1000+"秒");
                    String pre = map.get(first);
                    String nowstr = map.get(key);
                    Pattern p2 = Pattern.compile("(?<=nursestation\\s)(\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");
                    Matcher matcher = p2.matcher(pre);
                    if(matcher.find())
                    {
                        System.out.println(matcher.group());
                    }
                    matcher = p2.matcher(nowstr);
                    if(matcher.find())
                    {
                        System.out.println(matcher.group());
                    }
                    System.out.println("");

                }

            }
            first = key;
        }
        System.out.println("总数据数:"+ map.size());
        System.out.println("max: " + max/1000 );
        System.out.println("min: " + min/1000 );
        System.out.println("maxlist.size: " +maxlist.size());
        System.out.println("maxlist : " +maxlist);
        System.out.println("----------------------------------------------------------------------------");


    }
}
