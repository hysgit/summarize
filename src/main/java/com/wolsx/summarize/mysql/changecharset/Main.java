package com.wolsx.summarize.mysql.changecharset;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hy on 7/28/16.
 */
public class Main {
    public static void main(String[] args) {
        //获取所有表
        Connection conn = null;
        try {
            conn = DbUtilsTool.openConn("MySQL", "haolangtomon2.mysql.rds.aliyuncs.com", "3306", "zitech", "zi_user", "zicloud2015");
            String sql = "show tables";
            QueryRunner qr = new QueryRunner();
            List<String> tblNameList = (List<String>) qr.query(conn, sql, new ColumnListHandler(1));
            sql = "ALTER DATABASE zitech CHARACTER SET `utf8mb4` COLLATE `utf8mb4_general_ci`";
            qr.update(conn, sql);
            for (String str : tblNameList) {
                sql = "ALTER TABLE " + str + " CONVERT TO CHARACTER SET `utf8mb4` COLLATE `utf8mb4_general_ci`";
                qr.update(conn, sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
