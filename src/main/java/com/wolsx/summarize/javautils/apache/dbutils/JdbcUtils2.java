package com.wolsx.summarize.javautils.apache.dbutils;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hy on 16-5-14.
 */
public class JdbcUtils2 {
    private static DataSource ds;

    static{

        try {
            Properties prop = new Properties();
            InputStream in = JdbcUtils2.class.getClassLoader().getResourceAsStream("dbcpconfig2.properties");
            prop.load(in);
//            BasicDataSourceFactory factory = new BasicDataSourceFactory();
            ds = BasicDataSourceFactory.createDataSource(prop);
        } catch (IOException e) {
            throw new ExceptionInInitializerError();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static DataSource getDataSource()
    {
        return ds;
    }
}
