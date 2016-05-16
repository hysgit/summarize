package com.wolsx.summarize.javautils.apache.dbutils;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hy on 16-5-14.
 */
public class Demo2 {

    //测试dbutils的各个结果集处理器

    /**
     * ArrayHandler: 把结果集中的第一行数据转成对象数组。
     * @throws SQLException
     */
    @Test
    public void arrayHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from users where id=?";
        Object[] result = qr.query(sql, 1, new ArrayHandler());
        for (Object obj : result) {
            System.out.println(obj);
        }
    }

    /**
     * ArrayListHandler: 这个处理器，是把查询结果中的每一条记录转成一个数组
     * 然后把这些数组放到一个List中。
     * @throws SQLException
     */
    @Test
    public void arrayListHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from users";
        List<Object[]> list = qr.query(sql, new ArrayListHandler());
        for (Object[] objs : list) {
            System.out.println(Arrays.toString(objs));
        }
    }

    /**
     * ColumnListHandler:这个结果集处理器，把查询结果中指定名称的列，取出来，放到list中
     * @throws SQLException
     */
    @Test
    public void columnListHandler() throws SQLException
    {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from users";
        List<Object> name = qr.query(sql, new ColumnListHandler<>("name"));
        System.out.println(name);
    }

    /**
     * KeyedHandler: 把结果集中的每一行，都封装到一个Map里，key为列名，然后把这每一个Map，放到一个Map中，
     * 这个Map的key是新建KeyedHandler对象时指定的key。
     * @throws SQLException
     */
    @Test
    public void keyedHandler() throws SQLException
    {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from users";
        Map<Integer,Map<String, Object>> map = (Map<Integer, Map<String, Object>>) qr.query(sql, new KeyedHandler("id"));
        for(Map.Entry<Integer,Map<String, Object>> me:map.entrySet())
        {
            Integer id = me.getKey();
            for (Map.Entry<String, Object> entry : me.getValue().entrySet()) {
                String name = entry.getKey();
                Object value = entry.getValue();
                System.out.println(name + "=" + value);
            }
        }
    }

    /**
     * MapHandler: 将查询结果的第一行封装到Map里，key是列名，value就是对应的值
     * @throws SQLException
     */
    @Test
    public void mapHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from users";
        Map<String, Object> map = qr.query(sql, new MapHandler());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * MapListHandler: 将查询结果的第一行封装到Map里，然后把每个map放到list中
     */
    @Test
    public void mapListHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from users";
        List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
        System.out.println(list);
    }
}
