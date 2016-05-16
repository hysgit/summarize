package com.wolsx.summarize.javautils.apache.dbutils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by hy on 16-5-14.
 */
public class Demo1 {
    /**
     create database jdbc character set utf8 collate utf8_general_ci;

     use jdbc;

     create table users(
     id int primary key,
     name varchar(40),
     password varchar(40),
     email varchar(60),
     birthday date
     );

     insert into users(id,name,password,email,birthday) values(1,'zs','123456','zs@sina.com','1980-12-04');
     insert into users(id,name,password,email,birthday) values(2,'lisi','123456','lisi@sina.com','1981-12-04');
     insert into users(id,name,password,email,birthday) values(3,'wangwu','123456','wangwu@sina.com','1979-12-04');
     */

    /**
     * 插入
     */
    @Test
    public void insert()
    {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "insert into users(id,name,password,email,birthday) values (?,?,?,?,?)";
        Object params[] = {2, "aaa2", "123", "aa@sina.com", new Date()};
        try {
            runner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 更新
     */
    @Test
    public void update()
    {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "update users set email=? where id = ?";
        Object params[] = {"update@sina.com",1};
        try {
            runner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    @Test
    public void delete()
    {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "delete from users where id = ?";
        try {
            runner.update(sql, 1);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void find()
    {
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from users where id=?";
        try {
            User user = runner.query(sql, 1, new BeanHandler<User>(User.class));
            System.out.println(user);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    @Test
    public void getAll() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from users";
        List<User> list = qr.query(sql, new BeanListHandler<User>(User.class));
        System.out.println(list);
    }

    @Test
    public void batch() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
        Object params[][] = new Object[3][5];
        for(int i=0;i < params.length;i++) {
            params[i] = new Object[]{i+1,"aa"+1,"123", i+"@sina.com", new Date()};
        }
        qr.batch(sql, params);

    }
}
